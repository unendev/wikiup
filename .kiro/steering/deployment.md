# Deployment Guide

## Prerequisites

### Backend Requirements
- Java 8 or higher
- Maven 3.6+
- MySQL 8.0+
- ChromaDB instance
- Sufficient RAM for DJL models (2GB+ recommended)

### Frontend Requirements
- Node.js 18+ and npm
- Web server (nginx, Apache, or similar)

## Environment Configuration

### Backend (.env)
```properties
# Database
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/ragdb
SPRING_DATASOURCE_USERNAME=your_username
SPRING_DATASOURCE_PASSWORD=your_password

# JWT
JWT_SECRET=your-secret-key-min-256-bits
JWT_EXPIRATION=86400000

# ChromaDB
CHROMA_DB_URL=http://localhost:8000

# Server
SERVER_PORT=8080
```

### Frontend (.env.production)
```properties
VITE_API_BASE_URL=https://api.yourdomain.com
```

## Build Process

### Backend
```bash
cd backend
mvnw clean package -DskipTests
# Output: target/rag-service-0.0.1-SNAPSHOT.jar
```

### Frontend
```bash
cd frontend
npm install
npm run build
# Output: dist/ directory
```

## Deployment Steps

### Backend Deployment

#### Option 1: Standalone JAR
```bash
java -jar target/rag-service-0.0.1-SNAPSHOT.jar
```

#### Option 2: Systemd Service (Linux)
Create `/etc/systemd/system/rag-service.service`:
```ini
[Unit]
Description=RAG Service
After=network.target

[Service]
Type=simple
User=raguser
WorkingDirectory=/opt/rag-service
ExecStart=/usr/bin/java -jar /opt/rag-service/rag-service.jar
Restart=on-failure

[Install]
WantedBy=multi-user.target
```

Enable and start:
```bash
sudo systemctl enable rag-service
sudo systemctl start rag-service
```

### Frontend Deployment

#### Nginx Configuration
```nginx
server {
    listen 80;
    server_name yourdomain.com;
    
    root /var/www/rag-frontend/dist;
    index index.html;
    
    location / {
        try_files $uri $uri/ /index.html;
    }
    
    location /api {
        proxy_pass http://localhost:8080;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection 'upgrade';
        proxy_set_header Host $host;
        proxy_cache_bypass $http_upgrade;
    }
    
    location /ws {
        proxy_pass http://localhost:8080;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection "Upgrade";
        proxy_set_header Host $host;
    }
}
```

## Database Setup

### Initialize Database
```bash
mysql -u root -p < backend/init.sql
```

### Run Migrations
Ensure JPA is configured to update schema:
```properties
spring.jpa.hibernate.ddl-auto=update
```

## Monitoring

### Health Checks
- Backend: `http://localhost:8080/actuator/health`
- Frontend: Check if index.html loads

### Logs
- Backend: Check application logs
- Frontend: Check browser console and network tab
- Nginx: `/var/log/nginx/access.log` and `error.log`

## Rollback Procedure

1. Stop the service
2. Restore previous JAR/build
3. Restore database backup if needed
4. Restart the service
5. Verify functionality

## Security Checklist

- [ ] Change default passwords
- [ ] Use strong JWT secret
- [ ] Enable HTTPS/TLS
- [ ] Configure CORS properly
- [ ] Set up firewall rules
- [ ] Regular security updates
- [ ] Database backups configured
- [ ] Monitor for suspicious activity
