#  Inventory Production Planning System

A fullstack application for managing products, raw materials, and performing intelligent production simulation based on stock availability and value prioritization.

---

##  Objective

This system allows:
* Product management
* Raw material management
* Product composition definition (Bill of Materials)
* Individual product production simulation
* Global production simulation
* Automatic prioritization of production by highest product value
* Calculation of total production value

The core business rule follows a greedy allocation strategy, prioritizing higher-value products when raw materials are shared.

---

##  Architecture

### Backend
* Java 17+
* Quarkus
* JAX-RS (REST API)
* Hibernate ORM (Panache)
* PostgreSQL
* Maven

### Frontend
* React
* Vite
* Axios
* React Router
* TailwindCSS

---

##  Core Business Logic

The global production simulation:
1. Sorts products by price (descending).
2. Calculates maximum producible quantity based on available stock.
3. Virtually consumes stock.
4. Continues to the next product.
5. Returns:
   * List of producible products
   * Quantities
   * Total aggregated production value

> **Important:** A raw material may be shared across multiple products. The system prioritizes higher-value products as required.
> 
> **Note:** The simulation does not modify real stock.

---

## How to Run

### 1 Backend

**Requirements**
* Java 17+
* Maven
* PostgreSQL running locally

**Create Database**
```sql
CREATE DATABASE inventory;

Configure application.properties

Properties
quarkus.datasource.db-kind=postgresql
quarkus.datasource.username=postgres
quarkus.datasource.password=postgres
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/inventory

quarkus.hibernate-orm.database.generation=update

quarkus.http.cors=true
quarkus.http.cors.origins=http://localhost:5173
quarkus.http.cors.methods=GET,POST,PUT,DELETE
quarkus.http.cors.headers=*
Run Backend

Bash
./mvnw quarkus:dev
The Backend will be available at: http://localhost:8080

2️ Frontend

Install Dependencies

Bash
npm install
Run Application

Bash
npm run dev
The Frontend will be available at: http://localhost:5173

 Main API Endpoints
Products

HTTP
GET    /products
POST   /products
GET    /products/{id}
Raw Materials

HTTP
GET    /raw-materials
POST   /raw-materials
Product Composition

HTTP
POST   /products/{id}/raw-materials
GET    /products/{id}/raw-materials
DELETE /products/{id}/raw-materials/{relationId}
Individual Product Simulation

HTTP
GET /products/{id}/simulation
Global Production Simulation (Main Requirement)

HTTP
GET /production/simulation
Example response:

JSON
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
 Data Model
Product: id, name, price

RawMaterial: id, name, stockQuantity

ProductRawMaterial (Many-to-Many Relationship): id, product_id, raw_material_id, required_quantity

 Technical Decisions
BigDecimal used for financial precision.

Clear separation of concerns: Entity, DTO, Service, Resource.

Simulation is virtual (does not persist stock changes).

Greedy strategy implemented according to business requirement.

 Possible Improvements
Linear programming optimization (true profit maximization).

Unit and integration tests.

Authentication & role management.

Author
Thiago Almeida Computer Science Student | Fullstack Developer

