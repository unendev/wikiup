# Backend Development Standards

## Code Organization

### Package Structure
Follow standard Spring Boot layered architecture:
```
com.example.ragservice/
├── config/          # Configuration classes
├── controller/      # REST controllers
├── service/         # Business logic
├── repository/      # Data access layer
├── model/           # Entity classes
├── dto/             # Data Transfer Objects
├── security/        # Security configuration
├── exception/       # Custom exceptions
└── util/            # Utility classes
```

## Coding Standards

### General Rules
- Use Java 8 features appropriately (lambdas, streams, Optional)
- Follow Spring Boot best practices
- Use Lombok annotations to reduce boilerplate (@Data, @Builder, @Slf4j)
- Keep methods focused and under 30 lines when possible
- Use meaningful variable and method names

### REST API Design
- Use proper HTTP methods (GET, POST, PUT, DELETE)
- Return appropriate HTTP status codes
- Use `@RestController` for REST endpoints
- Use `@RequestMapping` with proper path prefixes
- Validate input with `@Valid` and validation annotations
- Return consistent response structures

### Security
- All endpoints require authentication unless explicitly public
- Use JWT tokens for stateless authentication
- Store sensitive data in environment variables or `.env` file
- Never commit credentials to version control
- Use BCrypt for password hashing

### Database
- Use JPA entities with proper annotations
- Define relationships clearly (@OneToMany, @ManyToOne, etc.)
- Use repository interfaces extending JpaRepository
- Write efficient queries, use @Query when needed
- Enable connection pooling with HikariCP
- Use transactions appropriately with @Transactional

### Error Handling
- Use @ControllerAdvice for global exception handling
- Create custom exceptions for business logic errors
- Return meaningful error messages to clients
- Log errors appropriately with SLF4J

### WebSocket
- Use STOMP protocol for messaging
- Configure proper message brokers
- Handle connection lifecycle properly
- Implement error handling for WebSocket connections

## Build & Run

### Development
```bash
cd backend
mvnw spring-boot:run
```

### Build
```bash
mvnw clean package
```

### Environment Variables
Required in `.env` or environment:
- Database connection (URL, username, password)
- JWT secret key
- ChromaDB connection details
- Any API keys for external services
