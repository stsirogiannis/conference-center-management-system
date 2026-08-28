# System Documentation

## Section A) Entity-Relationship Diagram (Logical Model)

The main system entities and their attributes:

- **ConfCentre (Conference Center):**
  - `Unique_Code` (Primary Key)
  - `Name`, `Address`, `City`, `Phone_Number`, `Email`, `Services`
- **ConfRoom (Conference Room):**
  - `Room_Code` (Primary Key)
  - `Name`, `Max_Capacity`, `Seating_Type`, `Equipment`, `WiFi_Availability`, `Hourly_Rent`, `Availability`
- **RoomRes (Room Reservation):**
  - `Reservation_ID` (Primary Key)
  - `Start_Date`, `End_Date`, `Start_Time`, `End_Time`, `Preferred_City`, `Preferred_ConferenceCentre`, `Participants`, `Preferred_Equipment`, `Invoice_Required`, `Reservation_Status`
- **PaymDet (Payment Details):**
  - `Payment_ID` (Primary Key)
  - `Amount`, `Method`, `Date`, `Status`
- **Client (Customer):**
  - `Client_ID` (Primary Key)
  - `Name`, `Email`, `Phone_Number`

---

## Section B) Entity Relationship Assumptions

1.  **ConfCentre – ConfRoom (1 to Many / 1:N):**
    A Conference Center (`ConfCentre`) can have multiple Conference Rooms (`ConfRoom`). Each conference room belongs to one and only one conference center.
2.  **ConfRoom – RoomRes (1 to Many / 1:N):**
    A conference room can have many reservations (`RoomRes`). Each reservation refers to a specific room, but a room can accept many reservations across different time periods.
3.  **Client - RoomRes (1 to Many / 1:N):**
    A client (`Client`) can make many reservations (`RoomRes`). Each reservation is made by a single client.
4.  **RoomRes – PaymDet (1 to 1 / 1:1):**
    Each reservation (`RoomRes`) can be associated with one payment (`PaymDet`). For each reservation, there can be only one payment (e.g., settling the reservation amount).

---

## Section C) Relational Model

The database tables are created with the following constraints (all fields are `VARCHAR2(10)` or `VARCHAR(10)`):

- **ConfCentre:** `Unique_Code` (PK).
- **ConfRoom:** `Room_Code` (PK), `ConfCentre_Unique_Code` (FK to ConfCentre).
- **Client:** `Client_ID` (PK), `PaymDet_Payment_ID` (FK to Payment).
- **PaymDet:** `Payment_ID` (PK), `Client_Client_ID` (FK to Client).
- **RoomRes:** `Reservation_ID` (PK), `ConfRoom_Room_Code` (FK to ConfRoom), `PaymDet_Payment_ID` (FK to PaymDet), `Client_Client_ID` (FK to Client).

---

## Section D) Application User Manual

### Application Description

This application is a conference center management system, implemented in Java with a MySQL database connection. It allows users to enter, edit, and view information for conference centers, rooms, clients, reservations, and payments via a console interface. Interaction is handled through numeric menu options.

> **!! WARNING:** The application is designed so that every user input must not exceed ten (10) characters. To exit the application, enter "0".

### Function Menu:

1. Insert Conference Center
2. Insert Room
3. Insert Client
4. Book Room
5. Insert Payment
6. List Conference Center Rooms
7. Booked Rooms
8. Pending Reservations with Pending Payment
9. Available Rooms
10. Update Reservation Status
11. Update Payment Status
12. Cancelled Reservations (Delete after date)
13. Update Conference Center
14. Update Room
15. Update Client
16. Update Reservation
17. Update Payment
18. Exit

_(See the source file Documentation.pdf for detailed execution examples of each menu option)._
