# 🚇 MetroCard Travel Billing System — Geektrust Challenge

A Java console application that simulates a Metro Card travel billing system between **CENTRAL** and **AIRPORT** stations.  
This project processes travel commands, applies correct fare rules, performs auto-recharge when needed, and prints the final summary exactly as required by the Geektrust problem.

---

## 📌 Features

- Add balance to MetroCard  
- Check-in passengers with correct fare calculation  
- Apply **50% discount** on return journey  
- Auto-recharge MetroCard when balance is insufficient  
- Add **2% service fee** to station collection during recharge  
- Maintain station-level:
  - Total collection  
  - Total discount  
  - Passenger type summary  
- Output formatted exactly as Geektrust expects  

---

## 🏛 Passenger Types & Fares

| Passenger Type     | Fare |
|--------------------|------|
| ADULT              | 200  |
| SENIOR_CITIZEN     | 100  |
| KID                | 50   |

---

## 🔁 Return Journey Logic

If a passenger travels:

- CENTRAL → AIRPORT  
- AIRPORT → CENTRAL  

The second trip receives **50% discount**.

Example:  
Senior Citizen fare = 100  
Return fare = 50 (discount 50)

---

## ⚡ Auto Recharge Logic

If MetroCard balance < required fare:

1. Recharge = (fare - currentBalance)  
2. Service Fee = 2% of recharge  
3. Service fee is added to station's collection  
4. Card balance increases by (recharge + fare deduction)

---

## 🧾 Input Format

```
BALANCE <CARD_ID> <AMOUNT>
CHECK_IN <CARD_ID> <PASSENGER_TYPE> <STATION>
PRINT_SUMMARY
```

Example:

```
BALANCE MC1 600
CHECK_IN MC1 ADULT CENTRAL
PRINT_SUMMARY
```

---

## 📝 Sample Output (Geektrust Format)

```
TOTAL_COLLECTION CENTRAL <collection> <discount>
PASSENGER_TYPE_SUMMARY
<type1> <count>
<type2> <count>

TOTAL_COLLECTION AIRPORT <collection> <discount>
PASSENGER_TYPE_SUMMARY
<type1> <count>
<type2> <count>
```

❗ Passenger types with **0 count are NOT printed**, based on sample outputs.

---

## 📂 Project Structure

```
com.example.geektrust
 ├── Main.java
 ├── enums
 │     ├── PassengerType.java
 │     └── StationType.java
 ├── model
 │     ├── MetroCard.java
 │     └── StationSummary.java
 ├── service
 │     ├── MetroCardService.java
 │     ├── FareService.java
 │     └── StationService.java
 └── processor
       └── CommandProcessor.java
```

---

## ▶️ How to Run

### 1️⃣ Compile the project
```
./gradlew build
```

### 2️⃣ Run the project with input file (VS Code / Terminal)
```
gradlew run --args="sample_input/input1.txt"
```

or using Java directly:

```
java com.example.geektrust.Main sample_input/input1.txt
```

---

## 📁 Sample Input

```
BALANCE MC1 600
BALANCE MC2 500
CHECK_IN MC1 ADULT CENTRAL
CHECK_IN MC1 ADULT AIRPORT
PRINT_SUMMARY
```

---

## 🚀 Output Example

```
TOTAL_COLLECTION CENTRAL 200 0
PASSENGER_TYPE_SUMMARY
ADULT 1

TOTAL_COLLECTION AIRPORT 100 100
PASSENGER_TYPE_SUMMARY
ADULT 1
```

---

## 👨‍💻 Author

**Pushpak Fasate**  
MetroCard — Geektrust Java Challenge Implementation  

---

