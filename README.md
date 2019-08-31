[![CircleCI](https://circleci.com/gh/chrisdoberman/mssc-beer-service.svg?style=svg)](https://circleci.com/gh/chrisdoberman/mssc-beer-service)
# MSSC Beer Service

Spring Boot Microservice Example

# Default Port Mappings - For Single Host
| Service Name | Port | 
| --------| -----|
| Brewery Beer Service | 8080 |
| [Brewery Beer Order Service](https://github.com/chrisdoberman/mssc-beer-order-service/tree/my-working-branch) | 8081 |
| [Brewery Beer Inventory Service](https://github.com/chrisdoberman/mssc-beer-inventory-service/tree/my-working-branch) | 8082 |

# ActiveMQ docker container command:
docker run -it --rm \
  -p 8161:8161 \
  -p 61616:61616 \
  vromero/activemq-artemis