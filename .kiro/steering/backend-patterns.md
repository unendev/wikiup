---
inclusion: fileMatch
fileMatchPattern: 'backend/**/*'
---

# Backend Development Patterns

## Spring Boot Standards

### Project Structure
```
src/main/java/com/example/ragservice/
├── config/          # Configuration classes
├── controller/      # REST controllers
├── dto/            # Data Transfer Objects
├── entity/         # JPA entities
├── exception/      # Custom exceptions
├── filter/         # Security filters
├── repository/     # JPA repositories
├── security/       # Security configuration
├── service/        # Business logic
├── util/           # Utility classes
└── websocket/      # WebSocket handlers
```

## REST API Standards

### Controller Pattern
```java
@RestController
@RequestMapping("/api/v1/resource")
@RequiredArgsConstructor
public class ResourceController {
    
    private final ResourceService service;
    
    @GetMapping
    public ResponseEntity<List<ResourceDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }
    
    @PostMapping
    public ResponseEntity<ResourceDTO> create(@Valid @RequestBody ResourceDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(service.create(dto));
    }
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse(ex.getMessage()));
    }
}
```

### Response Standards
- Use `ResponseEntity<T>` for all endpoints
- Return appropriate HTTP status codes
- Use DTOs for request/response bodies
- Never expose entities directly

## Security Patterns

### JWT Authentication
- Token stored in `Authorization: Bearer <token>` header
- Token validation in `JwtAuthenticationFilter`
- User details loaded from database
- Token expiration: configurable in application.properties

### Role-Based Access Control
```java
@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/admin")
public ResponseEntity<?> adminOnly() {
    // admin-only logic
}
```

### Password Encoding
Always use BCrypt:
```java
@Autowired
private PasswordEncoder passwordEncoder;

String encoded = passwordEncoder.encode(rawPassword);
```

## JPA/Database Standards

### Entity Pattern
```java
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles")
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();
    
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
```

### Repository Pattern
```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
```

### Service Pattern
```java
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    
    private final UserRepository repository;
    
    public User findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
    }
    
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repository.findAll();
    }
}
```

## WebSocket Standards

### Configuration
- Use STOMP protocol over WebSocket
- Configure message broker for pub/sub
- Implement authentication in handshake interceptor

### Handler Pattern
```java
@Component
@RequiredArgsConstructor
public class ChatWebSocketHandler extends TextWebSocketHandler {
    
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        // Handle message
    }
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // Connection established
    }
}
```

## RAG Service Patterns

### Embedding Generation
- Use DJL with sentence-transformers model
- Batch processing for efficiency
- Cache embeddings when possible

### Vector Search
- Query ChromaDB with semantic similarity
- Return top-k results with scores
- Filter by metadata when needed

### LLM Integration
- Stream responses using WebFlux
- Handle API rate limits
- Implement retry logic with exponential backoff

## Error Handling

### Global Exception Handler
```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        log.error("Unexpected error", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorResponse("Internal server error"));
    }
}
```

### Custom Exceptions
Create specific exceptions for business logic:
```java
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, Long id) {
        super(String.format("%s not found with id: %d", resource, id));
    }
}
```

## Configuration Standards

### Application Properties
- Use profiles: `application-dev.properties`, `application-prod.properties`
- Externalize sensitive data (use environment variables)
- Document all custom properties

### Bean Configuration
```java
@Configuration
public class AppConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    @ConfigurationProperties(prefix = "app.security")
    public SecurityProperties securityProperties() {
        return new SecurityProperties();
    }
}
```

## Lombok Usage

### Recommended Annotations
- `@Data`: for DTOs and simple entities
- `@RequiredArgsConstructor`: for dependency injection
- `@Slf4j`: for logging
- `@Builder`: for complex object creation
- Avoid `@AllArgsConstructor` with JPA entities

## Logging Standards

### Use SLF4J with Lombok
```java
@Slf4j
@Service
public class MyService {
    
    public void process() {
        log.info("Processing started");
        log.debug("Debug details: {}", details);
        log.error("Error occurred", exception);
    }
}
```

### Log Levels
- ERROR: exceptions and critical issues
- WARN: potential problems
- INFO: important business events
- DEBUG: detailed diagnostic information
- TRACE: very detailed diagnostic information
