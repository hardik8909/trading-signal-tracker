# Trading Signal Tracker

## Overview

Trading Signal Tracker is a Spring Boot backend application that allows users to create and manage cryptocurrency trading signals. The application connects to the Binance Public API to fetch the latest market price, calculates the ROI (Return on Investment), and updates the trading signal status automatically.

The project was developed as part of a backend internship assignment to demonstrate REST API development, database management, external API integration, and clean project architecture.

---

## Features

- Create a trading signal
- View all trading signals
- View a trading signal by ID
- Delete a trading signal
- Fetch live cryptocurrency prices using Binance API
- Calculate ROI automatically
- Update signal status based on live market prices
- Input validation
- Global exception handling
- Swagger API documentation

---

## Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Swagger (OpenAPI)
- Lombok

---

## Project Structure

```
src
├── client
├── config
├── controller
├── dto
├── entity
├── enums
├── exception
├── mapper
├── repository
├── service
├── util
└── resources
```

---

## API Endpoints

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | /api/signals | Create a trading signal |
| GET | /api/signals | Get all trading signals |
| GET | /api/signals/{id} | Get a signal by ID |
| DELETE | /api/signals/{id} | Delete a signal |

---

## Sample Request

```json
{
  "symbol": "BTCUSDT",
  "direction": "BUY",
  "entryPrice": 100000,
  "stopLoss": 98000,
  "targetPrice": 105000,
  "entryTime": "2026-06-26T20:30:00",
  "expiryTime": "2026-06-27T20:30:00"
}
```

---

## Business Rules

### BUY Signal

- Stop Loss must be less than Entry Price.
- Target Price must be greater than Entry Price.

### SELL Signal

- Stop Loss must be greater than Entry Price.
- Target Price must be less than Entry Price.

The application checks the latest market price whenever a signal is fetched and updates its status accordingly.

Possible statuses are:

- OPEN
- TARGET_HIT
- STOPLOSS_HIT
- EXPIRED

---

## Database Configuration

Update your PostgreSQL configuration in the `application.properties` file.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/trading_signal_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

---

## Running the Project

Clone the repository.

```
git clone <repository-url>
```

Move into the project directory.

```
cd trading-signal-tracker
```

Run the project.

```
mvn spring-boot:run
```

Open Swagger:

```
http://localhost:8080/swagger-ui/index.html
```

---

## Future Improvements

If this project is extended further, the following features can be added:

- User Authentication
- Docker Support
- Scheduled background updates
- Pagination and filtering
- Redis caching

---

## Author

Hardik Kumar