# Configure AWS provider
provider "aws" {
  region = "us-east-1"  # Replace with your desired region
  
}

terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.48.0"
    }
  }

  backend "s3" {
    bucket         	   = "flexion-terraform-state-bucket"
    key              	 = "state/terraform.tfstate"
    region         	   = "us-east-1"
    encrypt        	   = true
    dynamodb_table     = "flexion-tf-lockid"
  }
}

# Define a security group for the EC2 instances
resource "aws_security_group" "web" {
  name        = "flex_web_sg"
  description = "Allow HTTP inbound traffic"

  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

data "aws_ami" "ubuntu" {
  most_recent = true

  filter {
    name   = "name"
    values = ["ubuntu/images/hvm-ssd/ubuntu-focal-20.04-amd64-server-*"]
  }

  filter {
    name   = "virtualization-type"
    values = ["hvm"]
  }

  owners = ["099720109477"] # Canonical
}

# Define an AWS EC2 instance
resource "aws_instance" "web" {
  ami           = data.aws_ami.ubuntu.id  # Replace with your desired AMI
  instance_type = "t2.micro"      # Replace with your desired instance type
  count         = 1               # Number of instances

  tags = {
    Name = "web-instance-${count.index}"
  }

  # Associate the EC2 instance with the security group
  security_groups = [aws_security_group.web.name]

  # Provision Docker and run your container
  user_data = <<-EOF
              #!/bin/bash
              yum update -y
              yum install docker -y
              service docker start
              docker run -d -p 8080:8080 kevindocker123/unit-conversion
              EOF
}

# Define an AWS Elastic Load Balancer
resource "aws_elb" "web" {
  name               = "web-elb"
  availability_zones = ["us-east-1a", "us-east-1b"]  # Replace with your desired availability zones

  listener {
    instance_port     = 8080
    instance_protocol = "http"
    lb_port           = 80
    lb_protocol       = "http"
  }

  # Attach EC2 instances to the load balancer
  instances = aws_instance.web.*.id
}
