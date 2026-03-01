# Inventory Production Planning System

A fullstack application for managing products, raw materials, and performing intelligent production simulation based on stock availability and value prioritization.

## Objective

To optimize inventory and production planning by maximizing production value through intelligent allocation of shared raw materials.

This system allows:
- Product management
- Raw material management
- Product composition definition (Bill of Materials)
- Individual product production simulation
- Global production simulation
- Automatic prioritization of production by highest product value
- Calculation of total production value

The core business rule follows a greedy allocation strategy, prioritizing higher-value products when raw materials are shared.

## Architecture

### Backend
- Java 17+
- Quarkus
- JAX-RS (REST API)
- Hibernate ORM (Panache)
- PostgreSQL
- Maven

### Frontend
- React
- Vite
- Axios
- React Router
- TailwindCSS

## Core Business Logic

The global production simulation:
1. Sorts products by price (descending)
2. Calculates maximum producible quantity based on available stock
3. Virtually consumes stock
4. Continues to the next product
5. Returns:
    - List of producible products
    - Quantities
    - Total aggregated production value

> **Important:** A raw material may be shared across multiple products. The system prioritizes higher-value products as required.
>
> **Note:** The simulation does not modify real stock.

## How to Run

### Backend

**Requirements**
- Java 17+
- Maven
- PostgreSQL running locally

**Create Database**
```sql
CREATE DATABASE inventory;
```

**Configure application.properties**
```properties
quarkus.datasource.db-kind=postgresql
quarkus.datasource.username=postgres
quarkus.datasource.password=postgres
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/inventory
quarkus.hibernate-orm.database.generation=update
quarkus.http.cors=true
quarkus.http.cors.origins=http://localhost:5173
quarkus.http.cors.methods=GET,POST,PUT,DELETE
quarkus.http.cors.headers=*
```

**Run Backend**
```bash
./mvnw quarkus:dev
```
The Backend will be available at: `http://localhost:8080`

### Frontend

**Install Dependencies**
```bash
npm install
```

**Run Application**
```bash
npm run dev
```
The Frontend will be available at: `http://localhost:5173`

## API Endpoints

### Products
```http
GET    /products
POST   /products
GET    /products/{id}
```

### Raw Materials
```http
GET    /raw-materials
POST   /raw-materials
```

### Product Composition
```http
POST   /products/{id}/raw-materials
GET    /products/{id}/raw-materials
DELETE /products/{id}/raw-materials/{relationId}
```

### Individual Product Simulation
```http
GET /products/{id}/simulation
```

### Global Production Simulation
```http
GET /production/simulation
```

**Example Response:**
```json
{
  "products": [
     {
        "productId": "uuid",
        "productName": "Product A",
        "productPrice": 200,
        "producibleQuantity": 3,
        "totalValue": 600
     }
  ],
  "totalProductionValue": 900
}
```

## Technical Decisions

- BigDecimal used for financial precision
- Clear separation of concerns: Entity, DTO, Service, Resource
- Simulation is virtual (does not persist stock changes)
- Greedy strategy implemented according to business requirement

## Possible Improvements

- Linear programming optimization (true profit maximization)
- Unit and integration tests
- Authentication & role management

---

**Author:** Thiago Almeida | Fullstack Developer
