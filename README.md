# Unfamousk4y's AI Music Platform

![Status](https://img.shields.io/badge/status-deployed-brightgreen)
![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green)
![Python](https://img.shields.io/badge/Python-3.10+-blue)
![AWS](https://img.shields.io/badge/AWS-EC2-yellow)

A full-stack AI-powered music platform that accepts audio file uploads and automatically analyzes mood, tempo, and energy using a Python microservice. Built with a Java Spring Boot REST API, PostgreSQL persistence, AWS cloud infrastructure, and a React frontend. Deployed end-to-end with an automated CI/CD pipeline.

**Demo Video:** [https://www.loom.com/share/eda68832a91c49dea3c01fc0947772f3]

React Frontend (Nginx, port 80) -> Java SpringBoot API (port 8080) -> PostgreSQL Database -> Python FastAPI AI Service (port 8001) -> Librosa Audio Analysis

## How to run locally
1. Clone the repo: `git clone https://github.com/unfamousk4y/AI_Music_Platform.git`
2. Start PostgreSQL and create database 'musicplatform'
3. Run Spring Boot: `cd musicplatform && ./mvnw spring-boot:run`
4. Run Python service: `cd ai-service && uvicorn main:app --port 8001`
5. Run React: `cd music-platform-ui && npm start`

## Tech Stack
| Layer | Technology |
|-------|-----------|
| Backend API | Java 17 + Spring Boot 3 |
| AI Service | Python + FastAPI + Librosa |
| Database | PostgreSQL (AWS RDS) |
| File Storage | AWS S3 |
| Frontend | React |
| Cloud | AWS EC2 (Ubuntu Linux) |
| CI/CD | GitHub Actions |

## Project Status
Currently in active development. Following an 8-phase build plan from local setup through full AWS deployment and CI/CD automation.

## Roadmap
- [x] Phase 1 — Environment setup & repo
- [x] Phase 2 — Java Spring Boot REST API + Auth
- [x] Phase 3 — Python AI microservice (audio analysis)
- [x] Phase 4 — Java ↔ Python integration
- [x] Phase 5 — React frontend
- [x] Phase 6 — AWS deployment (EC2, RDS, S3)
- [x] Phase 7 — CI/CD pipeline (GitHub Actions)
- [x] Phase 8 — Polishing and prep
