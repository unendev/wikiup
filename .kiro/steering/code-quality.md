---
inclusion: always
---

# Code Quality Standards

## General Principles

### SOLID Principles
- **Single Responsibility**: Each class/function should have one reason to change
- **Open/Closed**: Open for extension, closed for modification
- **Liskov Substitution**: Subtypes must be substitutable for base types
- **Interface Segregation**: Many specific interfaces over one general interface
- **Dependency Inversion**: Depend on abstractions, not concretions

### DRY (Don't Repeat Yourself)
- Extract common logic into reusable functions/components
- Use composition over duplication
- Create utility functions for repeated operations

### KISS (Keep It Simple, Stupid)
- Prefer simple solutions over complex ones
- Avoid premature optimization
- Write code that's easy to understand and maintain

## Code Style

### Formatting
- Use consistent indentation (2 spaces for frontend, 4 for backend)
- Maximum line length: 100 characters
- Use meaningful whitespace for readability
- Group related code together

### Naming Conventions
- Use descriptive, self-documenting names
- Avoid abbreviations unless widely understood
- Boolean variables: use `is`, `has`, `should` prefixes
- Functions: use verb phrases (e.g., `getUserById`, `validateToken`)
- Constants: use UPPER_SNAKE_CASE

### Comments
- Write self-documenting code first
- Comment "why", not "what"
- Use JSDoc/JavaDoc for public APIs
- Keep comments up-to-date with code changes
- Remove commented-out code

## Error Handling

### Frontend
```typescript
// Good: Specific error handling
try {
  const data = await fetchData()
  return data
} catch (error) {
  if (error instanceof NetworkError) {
    showNotification('Network error. Please check your connection.')
  } else if (error instanceof ValidationError) {
    showValidationErrors(error.fields)
  } else {
    showNotification('An unexpected error occurred.')
    console.error('Unexpected error:', error)
  }
  throw error
}

// Bad: Generic error handling
try {
  const data = await fetchData()
  return data
} catch (error) {
  console.log('Error')
}
```

### Backend
```java
// Good: Specific exception handling
@ExceptionHandler(UserNotFoundException.class)
public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {
    log.warn("User not found: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ErrorResponse(ex.getMessage()));
}

// Bad: Catching generic Exception
try {
    // logic
} catch (Exception e) {
    // Don't do this
}
```

## Testing Standards

### Unit Tests
- Test one thing at a time
- Use descriptive test names: `should_returnUser_when_validIdProvided`
- Follow AAA pattern: Arrange, Act, Assert
- Mock external dependencies
- Aim for high coverage of business logic

### Integration Tests
- Test API endpoints end-to-end
- Test database interactions
- Test authentication/authorization flows
- Use test databases, not production

## Security Best Practices

### Input Validation
- Validate all user input on both frontend and backend
- Use whitelist validation over blacklist
- Sanitize input to prevent XSS
- Use parameterized queries to prevent SQL injection

### Authentication/Authorization
- Never store passwords in plain text
- Use strong password hashing (BCrypt)
- Implement proper session management
- Validate JWT tokens on every request
- Check user permissions before operations

### Sensitive Data
- Never commit secrets to version control
- Use environment variables for configuration
- Don't log sensitive information
- Encrypt data at rest and in transit

## Performance Best Practices

### Frontend
- Lazy load routes and components
- Debounce/throttle frequent operations
- Use virtual scrolling for long lists
- Optimize images and assets
- Minimize bundle size

### Backend
- Use database indexes appropriately
- Implement caching where beneficial
- Use pagination for large datasets
- Optimize database queries (avoid N+1)
- Use connection pooling

## Accessibility

### Frontend
- Use semantic HTML elements
- Provide alt text for images
- Ensure keyboard navigation works
- Use ARIA labels where needed
- Maintain sufficient color contrast
- Support screen readers

## Git Commit Standards

### Commit Message Format
```
<type>(<scope>): <subject>

<body>

<footer>
```

### Types
- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation changes
- `style`: Code style changes (formatting)
- `refactor`: Code refactoring
- `test`: Adding/updating tests
- `chore`: Build process or auxiliary tool changes

### Examples
```
feat(auth): add JWT token refresh endpoint

Implement automatic token refresh when token expires.
Includes new /api/v1/auth/refresh endpoint.

Closes #123
```

```
fix(chat): resolve WebSocket connection timeout

Increase connection timeout from 5s to 30s to handle
slow network conditions.
```

## Code Review Checklist

### Functionality
- [ ] Code works as intended
- [ ] Edge cases are handled
- [ ] Error handling is appropriate
- [ ] No obvious bugs

### Code Quality
- [ ] Code is readable and maintainable
- [ ] Follows project conventions
- [ ] No code duplication
- [ ] Appropriate abstractions

### Security
- [ ] Input is validated
- [ ] No security vulnerabilities
- [ ] Sensitive data is protected
- [ ] Authentication/authorization is correct

### Performance
- [ ] No obvious performance issues
- [ ] Database queries are optimized
- [ ] Resources are properly managed

### Testing
- [ ] Tests are included (if required)
- [ ] Tests pass
- [ ] Coverage is adequate

### Documentation
- [ ] Code is self-documenting
- [ ] Complex logic is commented
- [ ] API changes are documented
- [ ] README is updated if needed
