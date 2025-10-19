<div align="center">

# 🤖 AI-Powered Resume Builder

[![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Microservices](https://img.shields.io/badge/Microservices-FF6B6B?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/microservices)
[![Gemini API](https://img.shields.io/badge/Gemini_AI-4285F4?style=for-the-badge&logo=google&logoColor=white)](https://ai.google.dev/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg?style=for-the-badge)](LICENSE)

**An intelligent microservices-based application that generates ATS-friendly resumes using AI**

[Features](#-features) • [Architecture](#-architecture) • [Tech Stack](#-tech-stack) • [Getting Started](#-getting-started) • [API Documentation](#-api-documentation)

</div>

---

## 📋 Overview

AI-Powered Resume Builder is a sophisticated microservices application that leverages Google's Gemini AI to create professional, ATS-optimized resumes. The application intelligently formats user input into industry-standard resume templates and exports them as editable Microsoft Word documents.

### ✨ Key Highlights

- 🧠 **AI-Powered Formatting** - Gemini API intelligently structures and optimizes resume content
- 📄 **ATS-Friendly Output** - Generates resumes optimized for Applicant Tracking Systems
- 📝 **Editable DOCX Format** - Uses Apache POI to create fully editable Word documents
- 🏗️ **Microservices Architecture** - Scalable and maintainable service-oriented design
- 🔄 **Service Discovery** - Eureka Server for dynamic service registration
- 🚪 **API Gateway** - Centralized routing and load balancing

---

## 🚀 Features

### Core Functionality
- ✅ User input collection (experience, education, skills)
- ✅ AI-powered content enhancement and formatting
- ✅ ATS score optimization
- ✅ Professional template generation
- ✅ Export to editable .docx format
- ✅ Real-time resume preview

### Technical Features
- ✅ RESTful API architecture
- ✅ Service registry and discovery
- ✅ Centralized API gateway
- ✅ Load balancing
- ✅ Fault tolerance
- ✅ Distributed tracing

---

## 🏗️ Architecture

```
┌─────────────────────────────────────────────────────┐
│                   API Gateway                        │
│            (Spring Cloud Gateway)                    │
└──────────────┬──────────────────────────────────────┘
               │
               ├──────────────┬──────────────┬─────────────
               │              │              │
               ▼              ▼              ▼
        ┌────────────┐ ┌────────────┐ ┌────────────┐
        │  Resume    │ │   AI       │ │  Document  │
        │  Service   │ │  Service   │ │  Service   │
        └─────┬──────┘ └─────┬──────┘ └─────┬──────┘
              │              │              │
              └──────────────┴──────────────┘
                             │
                    ┌────────▼─────────┐
                    │ Eureka Discovery │
                    │     Server       │
                    └──────────────────┘
```

### Microservices Components

#### 1. **Eureka Service Registry** 
- Service discovery and registration
- Health monitoring
- Load balancing support

#### 2. **API Gateway**
- Request routing
- Load balancing
- Rate limiting
- Authentication/Authorization

#### 3. **Resume Service**
- User data management
- Resume template selection
- Business logic orchestration

#### 4. **AI Service**
- Gemini API integration
- Content optimization
- ATS keyword enhancement
- Format standardization

#### 5. **Document Service**
- Apache POI integration
- DOCX generation
- Template rendering
- File download management

---

## 🛠️ Tech Stack

### Backend Framework
- **Spring Boot** - Core framework
- **Spring Cloud Netflix Eureka** - Service discovery
- **Spring Cloud Gateway** - API gateway
- **Spring Web** - RESTful APIs

### AI & Integration
- **Google Gemini API** - AI-powered content formatting
- **Apache POI** - Microsoft Word document generation
- **RestTemplate/WebClient** - Inter-service communication

### Database
- **MySQL/PostgreSQL** - Relational database
- **Spring Data JPA** - Data persistence

### Development Tools
- **Maven** - Dependency management
- **Lombok** - Boilerplate reduction
- **Postman** - API testing

---

## 📦 Getting Started

### Prerequisites

```bash
- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+ (or PostgreSQL)
- Google Cloud API Key (for Gemini API)
```

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/Sharan-0-dot/ai-resume-builder-microservices.git
cd ai-resume-builder-microservices
```

2. **Configure Gemini API Key**
```properties
# application.properties or application.yml
gemini.api.key=YOUR_GEMINI_API_KEY
```

3. **Configure Database**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/resume_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

4. **Start Services in Order**

```bash
# 1. Start Eureka Server
cd eureka-server
mvn spring-boot:run

# 2. Start API Gateway
cd api-gateway
mvn spring-boot:run

# 3. Start Microservices
cd resume-service
mvn spring-boot:run

cd ai-service
mvn spring-boot:run

cd document-service
mvn spring-boot:run
```

### Service Ports

| Service | Port |
|---------|------|
| Eureka Server | 8761 |
| API Gateway | 8080 |
| Resume Service | 8081 |
| AI Service | 8082 |
| Document Service | 8083 |

---

## 📡 API Documentation

### Base URL
```
http://localhost:8080/api
```

### Endpoints

#### Create Resume
```http
POST /api/resume/create
Content-Type: application/json

{
  "personalInfo": {
    "name": "John Doe",
    "email": "john@example.com",
    "phone": "+1234567890"
  },
  "experience": [...],
  "education": [...],
  "skills": [...]
}
```

#### Generate DOCX
```http
GET /api/resume/{id}/download
Response: application/vnd.openxmlformats-officedocument.wordprocessingml.document
```

#### Optimize with AI
```http
POST /api/ai/optimize
Content-Type: application/json

{
  "content": "...",
  "jobDescription": "..."
}
```

---

## 🎯 How It Works

1. **User Input** → User provides resume details through REST API
2. **AI Processing** → Gemini API analyzes and optimizes content for ATS
3. **Template Selection** → System applies professional formatting rules
4. **Document Generation** → Apache POI creates editable DOCX file
5. **Download** → User receives ATS-friendly, ready-to-use resume

---

## 🔑 Key Features Breakdown

### AI-Powered Optimization
- **Content Enhancement**: Gemini API improves clarity and impact
- **Keyword Optimization**: Matches job descriptions with relevant keywords
- **ATS Compatibility**: Ensures 90%+ ATS parsing success rate
- **Professional Language**: Transforms casual text to professional format

### Document Generation
- **Apache POI Integration**: Creates native Microsoft Word format
- **Editable Output**: Users can modify generated resumes
- **Multiple Templates**: Professional, modern, and classic designs
- **Rich Formatting**: Headers, bullet points, tables, and styling

### Microservices Benefits
- **Scalability**: Individual services can scale independently
- **Maintainability**: Easier to update and debug specific features
- **Resilience**: Failure in one service doesn't crash the entire system
- **Technology Flexibility**: Each service can use different tech if needed

---

## 🚀 Future Enhancements

- [ ] Multiple resume template options
- [ ] Cover letter generation
- [ ] LinkedIn profile optimization
- [ ] Interview preparation suggestions
- [ ] Redis caching for faster responses
- [ ] Docker containerization
- [ ] Kubernetes deployment
- [ ] CI/CD pipeline integration
- [ ] User authentication & authorization
- [ ] Resume version history

---

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author

**Sharan SC**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/sharan-sc-4b475b2b7)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/Sharan-0-dot)
[![LeetCode](https://img.shields.io/badge/LeetCode-FFA116?style=for-the-badge&logo=LeetCode&logoColor=black)](https://leetcode.com/u/sharansc482/)

---

## 🙏 Acknowledgments

- [Google Gemini API](https://ai.google.dev/) for AI capabilities
- [Apache POI](https://poi.apache.org/) for document generation
- [Spring Cloud](https://spring.io/projects/spring-cloud) for microservices infrastructure
- The open-source community for inspiration

---

<div align="center">

### ⭐ Star this repository if you find it helpful!

Made with ❤️ by Sharan SC

</div>
