# Testing Standards

## Backend Testing

### Unit Tests
- Use JUnit 5 for unit tests
- Test business logic in service layer
- Mock dependencies with Mockito
- Aim for 80%+ code coverage
- Test edge cases and error conditions

### Integration Tests
- Use `@SpringBootTest` for integration tests
- Test controller endpoints with MockMvc
- Use test database (H2 or test MySQL instance)
- Test security configurations
- Verify WebSocket connections

### Test Structure
```java
@SpringBootTest
class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserService userService;
    
    @Test
    void shouldCreateUserSuccessfully() {
        // Given
        UserDto userDto = createTestUser();
        
        // When
        User result = userService.createUser(userDto);
        
        // Then
        assertNotNull(result);
        assertEquals(userDto.getUsername(), result.getUsername());
    }
}
```

### Test Naming
- Use descriptive names: `should[ExpectedBehavior]When[Condition]`
- Examples:
  - `shouldReturnUserWhenValidIdProvided`
  - `shouldThrowExceptionWhenUserNotFound`
  - `shouldAuthenticateUserWithValidCredentials`

## Frontend Testing

### Component Tests
- Test component rendering
- Test user interactions
- Test props and emits
- Mock external dependencies
- Test edge cases

### Test Organization
```typescript
describe('LoginView', () => {
  it('should render login form', () => {
    // Test implementation
  });
  
  it('should validate email format', () => {
    // Test implementation
  });
  
  it('should call login service on submit', () => {
    // Test implementation
  });
});
```

## Manual Testing Checklist

### Before Each Release
- [ ] User registration works
- [ ] User login works
- [ ] JWT token refresh works
- [ ] Chat interface loads
- [ ] Messages send and receive
- [ ] WebSocket reconnects on disconnect
- [ ] Admin panel accessible to admin users
- [ ] Unauthorized access blocked
- [ ] Error messages display correctly
- [ ] Responsive design works on mobile
- [ ] Cross-browser compatibility (Chrome, Firefox, Safari)

### Performance Testing
- [ ] API response times < 200ms for simple queries
- [ ] WebSocket latency acceptable
- [ ] Database queries optimized
- [ ] Frontend bundle size reasonable
- [ ] No memory leaks in long sessions

### Security Testing
- [ ] SQL injection prevention
- [ ] XSS prevention
- [ ] CSRF protection
- [ ] Authentication required for protected routes
- [ ] Authorization checks work correctly
- [ ] Sensitive data not exposed in responses
- [ ] HTTPS enforced in production
