# Resume Builder

A Spring Boot app that uses Gemini AI to generate Word document resumes. Built this to learn microservices architecture and play around with AI APIs.

## What it does

Takes resume info (work experience, education, skills) and uses Google's Gemini API to format it nicely, then exports as a .docx file. The AI part helps make the content sound more professional and optimized for ATS systems.

## Tech used

- Spring Boot for the backend
- Gemini API for AI content generation
- Apache POI for creating Word documents
- Eureka for service discovery (experimenting with microservices)
- MySQL for storing resume data

## Project structure

```
├── api-gateway/          # Routes requests to services
├── eureka-server/        # Service registry
├── resume-service/       # Handles resume CRUD
├── ai-service/           # Calls Gemini API
└── document-service/     # Generates DOCX files
```

## Running it

You'll need Java 17+ and Maven installed.

1. Clone the repo
2. Add your Gemini API key in `application.properties`
3. Set up MySQL database (schema in `/sql` folder)
4. Start services in this order:
   - Eureka server (port 8761)
   - API Gateway (port 8080)
   - Then the other services

```bash
cd eureka-server && mvn spring-boot:run
cd api-gateway && mvn spring-boot:run
# etc...
```

## API endpoints

Create a resume:
```
POST /api/resume/create
```

Download as Word doc:
```
GET /api/resume/{id}/download
```

## Things that work

- Basic resume creation and storage
- Gemini integration for content improvement
- Word document generation with Apache POI
- Service discovery between microservices

## Known issues

- No frontend yet, just APIs
- Error handling could be better
- Need to add proper validation
- Document formatting is pretty basic right now

## Why microservices?

Honestly wanted to learn the pattern. For this project size, a monolith would've been simpler, but breaking it into services helped me understand:
- How services communicate
- Service discovery with Eureka
- API gateway patterns
- Independent deployment

## Future improvements

- Better document templates
- Proper error handling and logging
- Docker compose setup for easy deployment


---

Built while learning Spring Cloud. Feedback welcome.
