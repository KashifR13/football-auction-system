# Atomic Task Set for Football Auction System 🚀

Below are all the atomic tasks for the project. You can edit this file, or use it directly for importing into GitHub Issues using the GitHub Action provided.

---

## 🔧 Project Initialization & Setup

- [ ] Initialize Git repository and push to GitHub
- [ ] Set up project structure with `/backend`, `/frontend`, `/docs`
- [ ] Configure `.gitignore` for Java, Gradle, Node, etc.
- [ ] Add README.md with project overview

---

## ⚙️ Backend - Spring Boot (Java 17)

### 🏗️ Project Bootstrapping

- [ ] Initialize Spring Boot project using Java 17 and Gradle
- [ ] Add dependencies: Web, Spring Data JPA, PostgreSQL, Lombok, DevTools
- [ ] Create base package structure: `com.kashif.auction`

### 🧩 Core Models

- [ ] Create `Player` entity
- [ ] Create `Team` entity
- [ ] Create `Manager` entity
- [ ] Create `Auction` entity
- [ ] Create `Bid` entity

### 🛠️ Repositories

- [ ] Create JPA repository for `Player`
- [ ] Create JPA repository for `Team`
- [ ] Create JPA repository for `Manager`
- [ ] Create JPA repository for `Auction`
- [ ] Create JPA repository for `Bid`

### 🌐 REST APIs

- [ ] Create Controller: `/players` (GET, POST, PUT, DELETE)
- [ ] Create Controller: `/teams` (GET, POST)
- [ ] Create Controller: `/managers` (GET, POST)
- [ ] Create Controller: `/auctions` (POST, GET active)
- [ ] Create Controller: `/bids` (POST, GET bids for player)

### 📋 Business Logic

- [ ] Implement auction logic with countdown (X going once, twice, sold!)
- [ ] Ensure manager budgets are respected during bidding
- [ ] Enforce unique player ownership
- [ ] Track and update sold players list
- [ ] Prevent re-bidding on sold players

### 🧪 Backend Testing

- [ ] Add unit tests for services
- [ ] Add integration tests for key endpoints
- [ ] Add test data loading via CommandLineRunner

---

## 🎨 Frontend - React

### ⚙️ Setup

- [ ] Set up React project with Vite or CRA
- [ ] Configure Tailwind CSS or CSS Modules

### 📄 Pages

- [ ] Home / Dashboard
- [ ] Player List with Filters
- [ ] Auction Interface (host and managers)
- [ ] Team View / Roster
- [ ] Player Registration Page

### 📦 Components

- [ ] PlayerCard component
- [ ] BidBox component
- [ ] Countdown timer for bids
- [ ] TeamSummaryCard
- [ ] AuctionHistoryPanel

---

## 🧑‍💻 Player Registration Workflow

- [ ] Create registration form (profile pic, positions, skills, etc.)
- [ ] Save data in backend
- [ ] Show list of registered players publicly
- [ ] Allow update/edit until auction lock

---

## 🧑‍⚖️ Auction Flow Logic (Host-Driven)

- [ ] UI for Host to "announce" player
- [ ] Managers click to place a bid (host updates current bid)
- [ ] Display real-time auction state: current bid + who is leading
- [ ] Host controls "going once, twice..." countdown
- [ ] Finalize sale and update manager's budget + team list

---

## 🗃️ Database & Hosting

- [ ] Use PostgreSQL locally (Docker or system install)
- [ ] Add schema and test data SQL under `/docs`
- [ ] Prepare SQL backup/restore scripts

---

## 🚀 DevOps

- [ ] Add GitHub Actions workflow for CI build
- [ ] Add workflow to auto-run backend tests
- [ ] Add workflow to import issues from markdown

---

## 📦 Deployment

- [ ] Prepare for deployment on free tier (Render, Railway, Vercel, Netlify)
- [ ] Set up environment variables for DB
- [ ] Document deployment instructions

---

## 📚 Documentation

- [ ] Update README with tech stack
- [ ] Add API documentation with Swagger
- [ ] Write contribution guide
- [ ] Add auction rules document in `/docs`

---

🎉 **That’s the full scope! Now go build something awesome.**
