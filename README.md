# Conference Center Management System

**Note:** The user-facing interface (menus, prompts, and messages), as well as the code comments, are in Greek.

## Overview
This project is a Java-based console application for managing conference centers, rooms, clients, reservations, and payments. The system connects to a MySQL database named `psifiakaDB`.

## Features
- **Conference Center Management:** Add and update conference center details.
- **Room Management:** Add and update rooms linked to specific conference centers.
- **Client Management:** Register and update client information.
- **Reservation Management:** Create room bookings, manage reservation statuses (Confirmed/Pending/Cancelled), and clear past cancelled bookings.
- **Payment Management:** Process payments and update statuses.
- **Search and Listings:** Retrieve lists of available rooms by city, view booked rooms, and find pending reservations that have pending payments.

## Database Entities & Relationships
The relational model consists of the following tables:
- **ConfCentre**: Stores conference center details.
- **ConfRoom**: Stores room capabilities and pricing. Each room belongs to exactly one `ConfCentre` (1:N).
- **Client**: Stores client contact details.
- **RoomRes**: Stores reservation dates, times, and preferences. A client can make multiple reservations (1:N) and a room can have multiple reservations (1:N).
- **PaymDet**: Stores payment transactions. Each reservation is linked to one payment (1:1).

> **Important Data Constraint**: All database fields (`VARCHAR(10)`) enforce a strict maximum length of 10 characters. User input must respect this limit to prevent SQL errors.

## Prerequisites
- Java Development Kit (JDK)
- MySQL Server
- MySQL JDBC Driver (`mysql-connector-j.jar`)

## Setup Instructions
1. **Set Up the Database:** Import the provided `psifiakaDB.sql` dump into your MySQL server. This will create the `psifiakaDB` database, the necessary tables, and insert sample data.
2. **Verify Credentials:** If necessary, adjust the `userName` and `password` variables in the database connection string within `Main.java` (default is `root` and an empty password).
3. **Compile the Application:** Compile the `Main.java` file, ensuring the MySQL JDBC driver is included in the classpath. Run this command in your terminal:
   ```bash
   javac -cp ".:mysql-connector-j.jar" Main.java

## Usage
The application operates via a numeric console menu. Enter the number corresponding to the desired action and follow the on-screen prompts.
To safely exit the application, enter `0` at the main menu.

## References
For an in-depth explanation of the Entity-Relationship (Logical) Model, Relational Model, and the complete application user manual, please reference the included **Documentation.pdf** & **Documentation.md** files.
