# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a modern e-commerce microservice platform built with Spring Cloud and DDD (Domain-Driven Design) architecture. The system implements a hybrid architecture using both Dubbo RPC for high-performance service communication and Spring Cloud for RESTful APIs.

## Development Commands

### Build and Test
```bash
# Build the entire project
mvn clean compile

# Run tests
mvn test

# Package all services
mvn clean package

# Skip tests during build (for faster builds)
mvn clean package -DskipTests

# Run a specific service
mvn spring-boot:run -pl mall-service/order-service
```

### Code Quality
```bash
# Run code style checks
mvn checkstyle:check

# Run static code analysis
mvn spotbugs:check

# Generate dependency report
mvn dependency:tree
```

### Docker Operations
```bash
# Start infrastructure services
docker-compose -f docs/tempconfig/docker-compose-nacos.yml up -d

# Stop all services
docker-compose -f docs/tempconfig/docker-compose-nacos.yml down
```

## Architecture

### Module Structure
- **mall-common**: Shared utilities, base classes, and common configurations
- **mall-api**: Service interface definitions for Dubbo RPC and Feign clients
- **mall-service**: Core microservices implementing business logic
- **mall-gateway**: Spring Cloud Gateway for API routing and security

### Core Services
- **user-service** (port 8081): User management, authentication
- **product-service** (port 8082): Product catalog, inventory management
- **order-service** (port 8083): Order processing with DDD implementation
- **payment-service** (port 8084): Payment processing integration
- **inventory-service** (port 8085): Inventory tracking and updates

### Technology Stack
- **Java 17** with Spring Boot 3.2.2
- **Spring Cloud 2023.0.0** for microservice coordination
- **Dubbo 3.2.9** for RPC communication using Triple protocol
- **Nacos** for service discovery and configuration management
- **MySQL 8.0** as primary database
- **Redis 7.x** for caching and sessions
- **RabbitMQ** for asynchronous messaging
- **Seata** for distributed transactions
- **MyBatis-Plus** for database operations
- **Sa-Token** for authentication and authorization

### DDD Implementation
The order-service demonstrates Domain-Driven Design patterns:
- **Domain Layer**: Entities, Value Objects, Domain Services, and Repositories
- **Application Layer**: Application Services coordinating use cases
- **Infrastructure Layer**: Database persistence and external service integration
- **Interface Layer**: REST controllers and Dubbo providers

Key DDD components in order-service:
- Aggregate roots (Order, Refund) with domain events
- Value objects (OrderNo, Money, UserId)
- Domain services (OrderDomainService, OrderFactory)
- Repository interfaces with infrastructure implementations

### Configuration Management
All services use Nacos for centralized configuration:
- Environment-specific configs (dev/test/prod)
- Dynamic configuration refresh capability
- Common configurations shared across services in `common.yml`

### Service Communication
- **Synchronous**: Dubbo RPC for internal service-to-service calls
- **Asynchronous**: RabbitMQ for event-driven communication
- **External APIs**: REST endpoints via Spring Cloud Gateway

### Database Design
Each service owns its data following microservice principles:
- `mall_user`: User and authentication data
- `mall_product`: Product catalog and inventory
- `mall_order`: Orders and order items
- `mall_payment`: Payment records and transactions

## Development Guidelines

### Running Services Locally
1. Start infrastructure: `docker-compose -f docs/tempconfig/docker-compose-nacos.yml up -d`
2. Wait for Nacos to be healthy (check http://localhost:8848/nacos)
3. Start services in dependency order or use IDE to run individual services
4. Gateway runs on port 8080, individual services on 808x ports

### Testing
- Unit tests use JUnit 5 and Mockito
- Integration tests may use TestContainers
- Contract testing between services is encouraged
- Achieve minimum 80% code coverage

### Code Style
- Follow Google Java Style Guide (enforced by Checkstyle)
- Use Lombok for reducing boilerplate code
- MapStruct for object mapping between layers
- Proper exception handling with custom exceptions

### Adding New Services
1. Create service module in `mall-service/`
2. Create corresponding API module in `mall-api/`
3. Configure Nacos registration and discovery
4. Add database schema in appropriate database
5. Configure Dubbo provider and consumer if needed
6. Add to main pom.xml modules section

### External Dependencies
The project uses Alibaba's Maven repository for faster dependency resolution in China. Key version management is centralized in the parent pom.xml.

## Monitoring and Observability

Infrastructure services provide comprehensive monitoring:
- **Prometheus**: Metrics collection (port 9090)
- **Grafana**: Monitoring dashboards (port 3000, admin/admin123)
- **Zipkin**: Distributed tracing (port 9411)
- **Sentinel**: Circuit breaking and flow control (port 8858)
- **Seata**: Distributed transaction management (port 8091)