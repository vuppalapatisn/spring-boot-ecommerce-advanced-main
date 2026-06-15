
# Microservices E-commerce with API Gateway, Eureka Discovery, and RabbitMQ

Services:
- eureka-server (8761)
- api-gateway   (8080)
- catalog-service (registers to Eureka)
- payment-service (registers to Eureka, listens to RabbitMQ)
- order-service   (registers to Eureka, calls payment-service via discovery, publishes order events to RabbitMQ)
- rabbitmq (5672/15672)

Use Docker Compose to build and run all.
