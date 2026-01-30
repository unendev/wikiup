# RAG Service

A full-stack Retrieval-Augmented Generation (RAG) service with real-time chat capabilities, user authentication, and vector similarity search.

## Features

- 🔐 **User Authentication** - JWT-based authentication with Spring Security
- 💬 **Real-time Chat** - WebSocket-powered chat interface
- 🤖 **RAG System** - Intelligent question answering using ChromaDB and DJL
- 👥 **User Management** - Admin panel for system administration
- 🔍 **Vector Search** - Semantic search with ChromaDB vector database
- 📱 **Responsive UI** - Modern interface built with Vue 3 and Tailwind CSS

## Tech Stack

### Backend
- Spring Boot 2.7.18
- Java 8
- MySQL + JPA/Hibernate
- JWT Authentication
- ChromaDB (Vector Database)
- Deep Java Library (DJL) with PyTorch
- WebSocket (STOMP)

### Frontend
- Vue 3 (Composition API)
- TypeScript
- Vite
- Tailwind CSS
- Pinia (State Management)
- Vue Router
- Axios

## Prerequisites

- **Java**: JDK 8 or higher
- **Maven**: 3.6+
- **Node.js**: 18+
- **MySQL**: 8.0+
- **ChromaDB**: Running instance

## Quick Start

### 1. Database Setup

```bash
# Create database and run initialization script
mysql -u root -p < backend/init.sql
```

### 2. Backend Setup

```bash
cd backend

# Configure environment variables
# Copy .env.example to .env and update values
# Required: database credentials, JWT secret, ChromaDB URL

# Run the application
mvnw spring-boot:run
```

Backend will start on `http://localhost:8080`

### 3. Frontend Setup

```bash
cd frontend

# Install dependencies
npm install

# Start development server
npm run dev
```

Frontend will start on `http://localhost:5173`

## Environment Configuration

### Backend (.env)
```properties
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/ragdb
SPRING_DATASOURCE_USERNAME=your_username
SPRING_DATASOURCE_PASSWORD=your_password
JWT_SECRET=your-secret-key-min-256-bits
JWT_EXPIRATION=86400000
CHROMA_DB_URL=http://localhost:8000
```

### Frontend (.env)
```properties
VITE_API_BASE_URL=http://localhost:8080
```

## Project Structure

```
├── backend/                 # Spring Boot application
│   ├── src/main/java/      # Java source code
│   │   └── com/example/ragservice/
│   │       ├── config/     # Configuration classes
│   │       ├── controller/ # REST controllers
│   │       ├── service/    # Business logic
│   │       ├── repository/ # Data access layer
│   │       ├── model/      # Entity classes
│   │       ├── dto/        # Data Transfer Objects
│   │       └── security/   # Security configuration
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
│
└── frontend/               # Vue 3 application
    ├── src/
    │   ├── components/     # Reusable components
    │   ├── views/          # Page components
    │   ├── router/         # Route configuration
    │   ├── stores/         # Pinia stores
    │   ├── services/       # API services
    │   ├── composables/    # Composition utilities
    │   └── types/          # TypeScript types
    └── package.json
```

## Development

### Backend Development
```bash
cd backend
mvnw spring-boot:run
```

### Frontend Development
```bash
cd frontend
npm run dev
```

### Build for Production

**Backend:**
```bash
cd backend
mvnw clean package -DskipTests
# Output: target/rag-service-0.0.1-SNAPSHOT.jar
```

**Frontend:**
```bash
cd frontend
npm run build
# Output: dist/
```

## API Endpoints

### Authentication
- `POST /api/auth/register` - User registration
- `POST /api/auth/login` - User login
- `POST /api/auth/refresh` - Refresh JWT token

### Chat
- `POST /api/chat/message` - Send chat message
- `GET /api/chat/history` - Get chat history
- `WS /ws` - WebSocket connection

### Admin
- `GET /api/admin/users` - List all users (admin only)
- `DELETE /api/admin/users/{id}` - Delete user (admin only)

## Testing

### Backend Tests
```bash
cd backend
mvnw test
```

### Frontend Tests
```bash
cd frontend
npm run test
```

## Deployment

See [deployment.md](.kiro/steering/deployment.md) for detailed deployment instructions including:
- Production build process
- Nginx configuration
- Systemd service setup
- Security checklist

## Development Guidelines

- **Backend Standards**: See [backend-standards.md](.kiro/steering/backend-standards.md)
- **Frontend Standards**: See [frontend-standards.md](.kiro/steering/frontend-standards.md)
- **Git Workflow**: See [git-workflow.md](.kiro/steering/git-workflow.md)
- **Testing Standards**: See [testing-standards.md](.kiro/steering/testing-standards.md)

## Contributing

1. Create a feature branch from `develop`
2. Follow the coding standards in `.kiro/steering/`
3. Write tests for new features
4. Submit a pull request with clear description
5. Ensure all tests pass and code builds successfully

## License

[Your License Here]

## Support

For issues and questions, please open an issue in the repository.
