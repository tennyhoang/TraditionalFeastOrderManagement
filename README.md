🎉 Traditional Feast Order Management System
This Java application is designed to manage traditional feast services for events like weddings, anniversaries, and traditional ceremonies. It allows staff to manage customers, menu items, and customer orders in a structured and efficient way using object-oriented principles.

⚙️ Features
✅ Register and manage customers (Customer, Customers.java)

✅ Manage feast menu options (MenuItem, MenuItems.java)

✅ Handle customer orders (Order, Orders.java)

✅ Update or cancel orders before the event

✅ Integrated order pricing and validation

✅ Save all data to a binary file (feast_order_service.dat)

✅ Clean separation of concerns with utility classes like Inputter and Acceptable

🧠 Technologies Used
Java SE (8+)

OOP (Abstraction, Inheritance, Encapsulation, Polymorphism)

File I/O (Binary Serialization)

Menu-based console UI

📁 Project Structure
bash
Copy
Edit
/model
  ├── Customer.java
  ├── MenuItem.java
  ├── Order.java

/business
  ├── Customers.java      # Manage customer collection
  ├── MenuItems.java      # Manage menu items
  ├── Orders.java         # Manage and validate orders
  ├── Menu.java           # App menu navigation

/tool
  ├── Inputter.java       # Input handling and validation
  ├── Acceptable.java     # Static validation rules

Main.java                 # Entry point of the application
🚀 How to Run
Clone this repository:

bash
Copy
Edit
git clone https:(https://github.com/tennyhoang/TraditionalFeastOrderManagement/tree/main)
Compile all files:

bash
Copy
Edit
javac Main.java
Run the program:

bash
Copy
Edit
java Main
🧾 Sample Flow
👤 Register a customer

🍽️ Add menu items

📝 Create an order and calculate total cost

💾 Save data to feast_order_service.dat

🔁 Load data and continue managing

📄 License
This project is licensed under the MIT License.
You are free to use, modify, and distribute with credit.

