---
inclusion: always
---

# API Conventions

## REST API Design

### Base URL
- Development: `http://localhost:8080/api/v1`
- Production: Configure via environment

### Endpoint Naming
- Use plural nouns: `/users`, `/messages`, `/documents`
- Use kebab-case for multi-word resources: `/chat-sessions`
- Nested resources: `/users/{id}/messages`

### HTTP Methods
- `GET`: Retrieve resource(s)
- `POST`: Create new resource
- `PUT`: Update entire resource
- `PATCH`: Partial update
- `DELETE`: Remove resource

### Status Codes
- `200 OK`: Successful GET, PUT, PATCH
- `201 Created`: Successful POST
- `204 No Content`: Successful DELETE
- `400 Bad Request`: Invalid input
- `401 Unauthorized`: Missing/invalid authentication
- `403 Forbidden`: Insufficient permissions
- `404 Not Found`: Resource doesn't exist
- `409 Conflict`: Resource conflict (e.g., duplicate)
- `422 Unprocessable Entity`: Validation errors
- `500 Internal Server Error`: Server error

## Request/Response Format

### Request Body (JSON)
```json
{
  "username": "user123",
  "email": "user@example.com",
  "password": "securePassword"
}
```

### Success Response
```json
{
  "id": 1,
  "username": "user123",
  "email": "user@example.com",
  "roles": ["USER"],
  "createdAt": "2024-01-15T10:30:00Z"
}
```

### Error Response
```json
{
  "error": "Validation failed",
  "message": "Username already exists",
  "timestamp": "2024-01-15T10:30:00Z",
  "path": "/api/v1/auth/register"
}
```

### Validation Error Response
```json
{
  "error": "Validation failed",
  "errors": [
    {
      "field": "username",
      "message": "Username must be between 3 and 20 characters"
    },
    {
      "field": "password",
      "message": "Password must contain at least one uppercase letter"
    }
  ]
}
```

## Authentication Endpoints

### POST /api/v1/auth/register
Register new user
```json
Request:
{
  "username": "newuser",
  "password": "SecurePass123",
  "email": "user@example.com"
}

Response (201):
{
  "token": "eyJhbGciOiJIUzI1NiIs...",
  "user": {
    "id": 1,
    "username": "newuser",
    "email": "user@example.com",
    "roles": ["USER"]
  }
}
```

### POST /api/v1/auth/login
Authenticate user
```json
Request:
{
  "username": "user123",
  "password": "SecurePass123"
}

Response (200):
{
  "token": "eyJhbGciOiJIUzI1NiIs...",
  "user": {
    "id": 1,
    "username": "user123",
    "roles": ["USER"]
  }
}
```

### POST /api/v1/auth/logout
Logout user (requires authentication)
```json
Response (200):
{
  "message": "Logged out successfully"
}
```

### GET /api/v1/auth/me
Get current user info (requires authentication)
```json
Response (200):
{
  "id": 1,
  "username": "user123",
  "email": "user@example.com",
  "roles": ["USER"],
  "createdAt": "2024-01-15T10:30:00Z"
}
```

### POST /api/v1/auth/refresh
Refresh JWT token
```json
Response (200):
{
  "token": "eyJhbGciOiJIUzI1NiIs..."
}
```

## Chat/RAG Endpoints

### POST /api/v1/chat/query
Send question to RAG system
```json
Request:
{
  "question": "How do I survive winter in Don't Starve?",
  "sessionId": "optional-session-id"
}

Response (200):
{
  "answer": "To survive winter...",
  "sources": [
    {
      "title": "Winter Survival Guide",
      "url": "/wiki/winter",
      "relevance": 0.95
    }
  ],
  "sessionId": "session-123"
}
```

### WebSocket /ws/chat
Real-time streaming chat
```
Connect: ws://localhost:8080/ws/chat?token=<jwt-token>

Send:
{
  "type": "question",
  "content": "How do I survive winter?",
  "sessionId": "session-123"
}

Receive (streaming):
{
  "type": "chunk",
  "content": "To survive winter",
  "sessionId": "session-123"
}

{
  "type": "complete",
  "sources": [...],
  "sessionId": "session-123"
}
```

## Admin Endpoints

### GET /api/v1/admin/users
List all users (requires ADMIN role)
```json
Response (200):
[
  {
    "id": 1,
    "username": "user123",
    "email": "user@example.com",
    "roles": ["USER"],
    "createdAt": "2024-01-15T10:30:00Z"
  }
]
```

### PUT /api/v1/admin/users/{id}/roles
Update user roles (requires ADMIN role)
```json
Request:
{
  "roles": ["USER", "ADMIN"]
}

Response (200):
{
  "id": 1,
  "username": "user123",
  "roles": ["USER", "ADMIN"]
}
```

## Pagination

### Query Parameters
- `page`: Page number (0-indexed)
- `size`: Items per page (default: 20, max: 100)
- `sort`: Sort field and direction (e.g., `createdAt,desc`)

### Response Format
```json
{
  "content": [...],
  "page": 0,
  "size": 20,
  "totalElements": 100,
  "totalPages": 5,
  "last": false
}
```

## Headers

### Required Headers
- `Content-Type: application/json` (for JSON requests)
- `Authorization: Bearer <token>` (for authenticated endpoints)

### Optional Headers
- `Accept-Language: zh-CN` (for i18n)
- `X-Request-ID: <uuid>` (for request tracing)

## Rate Limiting

### Headers
- `X-RateLimit-Limit`: Maximum requests per window
- `X-RateLimit-Remaining`: Remaining requests
- `X-RateLimit-Reset`: Time when limit resets (Unix timestamp)

### Response (429 Too Many Requests)
```json
{
  "error": "Rate limit exceeded",
  "message": "Too many requests. Please try again later.",
  "retryAfter": 60
}
```
