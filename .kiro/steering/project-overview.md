# Project Overview

This is a RAG (Retrieval-Augmented Generation) service with a full-stack architecture.

## Tech Stack

### Backend
- **Framework**: Spring Boot 2.7.18
- **Java Version**: 1.8
- **Database**: MySQL with JPA/Hibernate
- **Authentication**: JWT with Spring Security
- **Vector Database**: ChromaDB
- **ML Framework**: Deep Java Library (DJL) with PyTorch
- **Real-time Communication**: WebSocket
- **Connection Pool**: HikariCP

### Frontend
- **Framework**: Vue 3 with Composition API
- **Language**: TypeScript
- **Build Tool**: Vite
- **Styling**: Tailwind CSS
- **State Management**: Pinia
- **Routing**: Vue Router
- **HTTP Client**: Axios
- **Form Validation**: VeeValidate
- **Markdown Rendering**: Marked

## Project Structure

```
├── backend/              # Spring Boot application
│   ├── src/main/java/   # Java source code
│   ├── src/main/resources/
│   │   └── application.properties
│   ├── data/            # Local data storage
│   ├── init.sql         # Database initialization
│   └── pom.xml          # Maven dependencies
│
└── frontend/            # Vue 3 application
    ├── src/
    │   ├── components/  # Reusable Vue components
    │   ├── views/       # Page-level components
    │   ├── router/      # Vue Router configuration
    │   ├── stores/      # Pinia state management
    │   ├── services/    # API service layer
    │   ├── composables/ # Composition API utilities
    │   └── types/       # TypeScript type definitions
    └── package.json
```

## Key Features
- User authentication and authorization
- Real-time chat interface with WebSocket
- RAG-based question answering
- Admin panel for system management
- Vector similarity search with ChromaDB
