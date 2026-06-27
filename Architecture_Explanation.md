# Trading Signal Tracker - Architecture Explanation# Trading Signal Tracker - Architecture Explanation

## Introduction

Trading Signal Tracker is a backend application built using Spring Boot. It allows users to create cryptocurrency trading signals and track their performance using live market data from the Binance Public API.

The project follows a layered architecture so that each part of the application has a clear responsibility. This makes the project easier to understand, maintain, and extend.

---

## Application Flow

```
Client
   │
   ▼
Controller
   │
   ▼
Service
   │
   ├────► Binance API
   │
   ▼
Repository
   │
   ▼
PostgreSQL Database
```

---

## Layers

### Controller

The controller handles incoming HTTP requests.

Its responsibilities are:

- Receiving client requests
- Passing data to the service layer
- Returning responses to the client

---

### Service

The service layer contains the main business logic.

It is responsible for:

- Validating trading rules
- Fetching the latest market price
- Calculating ROI
- Updating signal status
- Saving updated data

---

### Repository

The repository communicates with PostgreSQL using Spring Data JPA.

It performs operations such as:

- Saving signals
- Retrieving signals
- Updating records
- Deleting records

---

### Entity

The `TradingSignal` entity represents the database table.

It stores information such as:

- Symbol
- Direction
- Entry Price
- Stop Loss
- Target Price
- Entry Time
- Expiry Time
- Status
- ROI

---

### DTO

DTOs are used for request and response handling.

Using DTOs keeps the API independent from the database entity and helps maintain a clean project structure.

---

### Utility Classes

Utility classes are used for reusable logic like:

- Signal validation
- ROI calculation
- Status evaluation

This avoids duplicate code and keeps the service layer clean.

---

## Binance API Integration

The application uses the Binance Public API to fetch the latest cryptocurrency price.

Example:

```
GET https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT
```

The latest market price is used to calculate ROI and update the trading signal status.

---

## Signal Status Logic

For a BUY signal:

- If the current price reaches the target price, the status becomes **TARGET_HIT**.
- If the current price falls below the stop loss, the status becomes **STOPLOSS_HIT**.

For a SELL signal:

- If the current price reaches the target price, the status becomes **TARGET_HIT**.
- If the current price rises above the stop loss, the status becomes **STOPLOSS_HIT**.

If the expiry time has passed before either condition is met, the status becomes **EXPIRED**.

---

## Why This Architecture?

I chose a layered architecture because it separates different responsibilities within the project.

- Controllers only handle API requests.
- Services contain business logic.
- Repositories manage database operations.
- Utility classes keep common logic reusable.

This structure makes the project easier to understand and modify in the future.

---

## Conclusion

This project demonstrates the use of Spring Boot to build a RESTful backend application with database integration, external API communication, business logic implementation, and clean code organization. It also helped me understand how different layers of a backend application work together to process requests and return responses.

## Introduction

Trading Signal Tracker is a backend application built using Spring Boot. It allows users to create cryptocurrency trading signals and track their performance using live market data from the Binance Public API.

The project follows a layered architecture so that each part of the application has a clear responsibility. This makes the project easier to understand, maintain, and extend.

---

## Application Flow

```
Client
   │
   ▼
Controller
   │
   ▼
Service
   │
   ├────► Binance API
   │
   ▼
Repository
   │
   ▼
PostgreSQL Database
```

---

## Layers

### Controller

The controller handles incoming HTTP requests.

Its responsibilities are:

- Receiving client requests
- Passing data to the service layer
- Returning responses to the client

---

### Service

The service layer contains the main business logic.

It is responsible for:

- Validating trading rules
- Fetching the latest market price
- Calculating ROI
- Updating signal status
- Saving updated data

---

### Repository

The repository communicates with PostgreSQL using Spring Data JPA.

It performs operations such as:

- Saving signals
- Retrieving signals
- Updating records
- Deleting records

---

### Entity

The `TradingSignal` entity represents the database table.

It stores information such as:

- Symbol
- Direction
- Entry Price
- Stop Loss
- Target Price
- Entry Time
- Expiry Time
- Status
- ROI

---

### DTO

DTOs are used for request and response handling.

Using DTOs keeps the API independent from the database entity and helps maintain a clean project structure.

---

### Utility Classes

Utility classes are used for reusable logic like:

- Signal validation
- ROI calculation
- Status evaluation

This avoids duplicate code and keeps the service layer clean.

---

## Binance API Integration

The application uses the Binance Public API to fetch the latest cryptocurrency price.

Example:

```
GET https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT
```

The latest market price is used to calculate ROI and update the trading signal status.

---

## Signal Status Logic

For a BUY signal:

- If the current price reaches the target price, the status becomes **TARGET_HIT**.
- If the current price falls below the stop loss, the status becomes **STOPLOSS_HIT**.

For a SELL signal:

- If the current price reaches the target price, the status becomes **TARGET_HIT**.
- If the current price rises above the stop loss, the status becomes **STOPLOSS_HIT**.

If the expiry time has passed before either condition is met, the status becomes **EXPIRED**.

---

## Why This Architecture?

I chose a layered architecture because it separates different responsibilities within the project.

- Controllers only handle API requests.
- Services contain business logic.
- Repositories manage database operations.
- Utility classes keep common logic reusable.

This structure makes the project easier to understand and modify in the future.

---

## Conclusion

This project demonstrates the use of Spring Boot to build a RESTful backend application with database integration, external API communication, business logic implementation, and clean code organization. It also helped me understand how different layers of a backend application work together to process requests and return responses.