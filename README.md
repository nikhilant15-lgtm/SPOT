# 🅿️ SPOT — Smart Parking Operating Terminal

A console-based parking lot management system built using core Java fundamentals — no frameworks, no external libraries.

---

## Features

- Multi-floor parking (Ground, B1, B2) with Bike, Car, and Truck slots
- Automatic nearest-slot allocation on vehicle entry
- Ticket generation with entry timestamp
- Dynamic billing — hourly rate, 15-min grace period, overstay penalty
- Customer registration with Weekly / Monthly season passes
- Live slot dashboard and occupancy report
- Revenue report broken down by vehicle type

---

## Project Structure

```
spot/
├── vehicle/      → Vehicle (abstract), Car, Bike, Truck
├── slot/         → ParkingSlot, Floor, SlotManager
├── entry/        → Ticket, EntryGate
├── exit/         → BillCalculator, ExitGate
├── penalty/      → PenaltyRule, FineEngine
├── customer/     → Customer, SeasonPass
├── reports/      → OccupancyReport, RevenueTracker
├── ui/           → ConsoleUI
└── Main.java
```

---

## How to Run

```bash
# 1. Compile
javac -d out $(find . -name "*.java")

# 2. Run
cd out && java Main
```

> Requires Java 8 or above.

---

## OOP Concepts Used

| Concept | Example |
|---|---|
| Abstract Class | `Vehicle` with abstract `getHourlyRate()` |
| Inheritance | `Car`, `Bike`, `Truck` extend `Vehicle` |
| Polymorphism | Each vehicle type returns its own rate |
| Encapsulation | All fields private with getters |
| Packages | 8 packages, one responsibility each |

---

## Pricing

| Vehicle | Rate |
|---------|------|
| Bike    | ₹10 / hr |
| Car     | ₹20 / hr |
| Truck   | ₹40 / hr |
| Season Pass (Weekly)  | ₹500 flat |
| Season Pass (Monthly) | ₹1500 flat |

---

## Built With

- Java (Core — no external dependencies)
- OOP, Packages, Loops, Conditionals, Static Constants
