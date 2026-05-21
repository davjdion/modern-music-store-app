# Modern Music Equipment Store

Modern Music Equipment Store is a full-stack web application that simulates an online store for music equipment. Users can log in, browse products, check stock availability, and manage a shopping cart through a React frontend connected to a Spring Boot backend.

## Overview

This project was built as a practical full-stack application using:

- `React` and `JavaScript` for the frontend
- `Spring Boot` and `Java` for the backend
- `Spring Security` for authentication
- `Spring Data JDBC` for database access
- `H2 Database` for local data storage
- `Maven` and `npm` for build and dependency management

The application demonstrates client-server communication, REST API integration, route protection, database operations, and local deployment.

## Features

- User login with authenticated access
- Product listing with stock and price information
- Shopping cart management
- Protected cart route for logged-in users only
- REST API communication between frontend and backend
- SQL-based database initialization with sample data
- Automated build and run scripts

## Tech Stack

### Frontend

- `React`
- `JavaScript`
- `React Router`
- `Bootstrap`
- `React Bootstrap`

### Backend

- `Java 17`
- `Spring Boot`
- `Spring Web`
- `Spring Security`
- `Spring Data JDBC`
- `Maven`

### Database

- `H2 Database`
- `SQL`

## Project Structure

```text
modern-music-store-app/
├── frontend/   # React application
├── backend/    # Spring Boot application
├── run.sh      # Build and run script
├── clean.sh    # Cleanup script
└── Readme.md
```

## How It Works

The frontend runs in the browser and communicates with the backend through REST endpoints under `/api`. The backend handles authentication, product data, stock data, cart operations, and database access.

Main flow:

1. The user logs in from the React frontend.
2. The frontend sends requests to the Spring Boot API.
3. The backend validates access for protected endpoints.
4. Product and cart data are loaded from the database.
5. The frontend updates the UI based on the API responses.

## Available Endpoints

Examples of the main backend routes:

- `POST /api/users/auth/login` - authenticate a user
- `GET /api/products/with-stock` - fetch products with stock information
- `GET /api/cart/user/{userId}` - fetch a user's cart
- `POST /api/cart` - add item to cart
- `DELETE /api/cart/{id}` - remove item from cart

## Running the Project

There are two common ways to run the application.

### Option 1: Run frontend and backend separately

Start the backend:

```bash
cd backend
mvn spring-boot:run
```

Start the frontend in a second terminal:

```bash
cd frontend
npm install
npm start
```

Open:

- Frontend: `http://localhost:3000`
- Backend: `http://localhost:8081`

### Option 2: Run the full project with the script

From the root folder:

```bash
sh run.sh
```

Then open:

- `http://localhost:8081`

This script:

- checks the required tools
- installs frontend dependencies
- builds the React app
- copies the frontend build into Spring Boot static resources
- builds the backend
- starts the server

## Cleaning the Project

To remove generated files and dependencies:

```bash
sh clean.sh
```

## Demo Data

The project includes sample data for:

- users
- products
- stock
- cart items

The database schema and seed data are initialized from SQL files located in:

- `backend/src/main/resources/data/schema.sql`
- `backend/src/main/resources/data/data.sql`

## Authentication

Authentication is implemented with `Spring Security` and `Basic Auth`.

- Public endpoint: login
- Protected endpoints: users and cart

The frontend stores the logged-in user and credentials in `localStorage` and uses them when sending authenticated requests to the backend.

## Purpose

This project was created to demonstrate practical full-stack development skills, including:

- frontend development with React
- backend API development with Spring Boot
- authentication and route protection
- CRUD operations and SQL integration
- full application build and local deployment

## Future Improvements

- password hashing instead of plain text demo passwords
- order checkout flow
- admin panel for product management
- improved validation and error handling
- deployment to a cloud platform
