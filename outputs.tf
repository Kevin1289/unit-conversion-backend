output "elb_dns_name" {
  description = "The DNS name of the Elastic Load Balancer"
  value       = aws_elb.web.dns_name
}
