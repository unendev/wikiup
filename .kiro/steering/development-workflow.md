---
inclusion: always
---

# Development Workflow

## Local Development Setup

### Backend
```bash
cd backend
# Configure MySQL connection in .env or application.properties
mvn clean install
mvn spring-boot:run
```

### Frontend
```bash
cd frontend
npm install
npm run dev
```

## Environment Configuration

### Backend (.env or application.properties)
- `MYSQL_HOST`: Database host
- `MYSQL_PORT`: Database port (default: 3306)
- `MYSQL_DATABASE`: Database name
- `MYSQL_USERNAME`: Database user
- `MYSQL_PASSWORD`: Database password
- `JWT_SECRET`: Secret key for JWT signing
- `JWT_EXPIRATION`: Token expiration time
- `DEEPSEEK_API_KEY`: LLM API key
- `CHROMA_HOST`: ChromaDB host

### Frontend (.env)
- `VITE_API_BASE_URL`: Backend API URL (default: http://localhost:8080)

## Branch Strategy

### Main Branches
- `main`: Production-ready code
- `develop`: Integration branch for features

### Feature Branches
- `feature/<feature-name>`: New features
- `fix/<bug-name>`: Bug fixes
- `refactor/<description>`: Code refactoring
- `docs/<description>`: Documentation updates
