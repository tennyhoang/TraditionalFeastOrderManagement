package Business;

import Model.MenuItem;
import Model.Order;
import Util.Inputter;
import Model.Customer;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Orders {
    private ArrayList<Order> orderList = new ArrayList<>();
    private static int orderCounter = 1;
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static final String FILE_NAME = "feast_order_service.dat";
    private boolean saved = true;

    public void placeOrder(Customers cuslists , MenuItems menulists){
        System.out.println("\n==== PLACE A NEW CUSTOMER ==== ");
        
        menulists.displayAllMenus();
        Customer customer;
        while(true){
            String CusID = Inputter.getString("Enter Customer ID: ");
            customer = cuslists.findCustomerByID(CusID);
            if(customer != null) break;
            System.out.println("Customer ID not found");
        }
        
        MenuItem menuItem;
        while(true){
            String menuId = Inputter.getString("Enter Set Menu ID: ");
            menuItem = menulists.findMenuByID(menuId);
            if(menuItem != null) break;
            System.out.println("Set Menu ID not found");
        }
        
        int tablecount;
        while(true){
            tablecount = Inputter.getInt("Enter number of table: ");
            if(tablecount >0) break;
            System.out.println("Must be greater than 0");
        }
        
        String pro = Inputter.getString("Enter a province: ");
        
        Date evenDate;
        while(true){
            evenDate = Inputter.getFutureDate("Enter even date (dd/MM/yy): ");
            if(!isDupOrder(customer.getCustomerID(), menuItem.getMenuID(), evenDate)) break;
            System.out.println("Duplicate order for the same customer ,menu and date.");
        }
        
        String orderID = "ORD" + (orderCounter++);
        double totalCost = menuItem.getPrice() * tablecount;
        Order neworder = new Order(orderID, customer.getCustomerID(),menuItem.getMenuID(), tablecount, pro, evenDate, totalCost);
        orderList.add(neworder);
        
        System.out.println("\n Order place successfully");
        System.out.println(neworder);
        saved = false;
    }

    public void updateOrder(MenuItems menuList) {
        System.out.println("1\n===== UPDATE ORDER =====");
        String orderID = Inputter.getString("Enter Order ID: ");
        Order order = findOrderByID(orderID);

        if (order == null) {
            System.out.println("Order not found!");
            return;
        }

        System.out.println("Current Info:\n" + order);

        String newMenuID = Inputter.getString("Enter new Menu ID (Enter to skip): ").trim();
        if (!newMenuID.isEmpty()) {
            MenuItem menu = menuList.findMenuByID(newMenuID);
            if (menu != null) {
                order.setMenuID(newMenuID);
                order.setTotalCost(menu.getPrice() * order.getTableCount());
            } else {
                System.out.println("Invalid Menu ID. Skipping.");
            }
        }

        String NTBCount = Inputter.getString("Enter new number of tables (Enter to skip): ").trim();
        if (!NTBCount.isEmpty()) {
            int newCount = Integer.parseInt(NTBCount);
            if (newCount > 0) {
                order.setTableCount(newCount);
                MenuItem menu = menuList.findMenuByID(order.getMenuID());
                order.setTotalCost(menu.getPrice() * newCount);
            }
        }
        String NPro = Inputter.getString("Enter new province: ");
        order.setProvince(NPro);

        String newDateStr = Inputter.getString("Enter new event date (dd/MM/yyyy) (Enter to skip): ").trim();
        if (!newDateStr.isEmpty()) {
            Date newDate = Inputter.getFutureDate("Re-enter new event date (dd/MM/yyyy): ");
            order.setEventDate(newDate);
        }

        System.out.println("Order updated.");
        saved = false;
    }

    public void displayOrders() {
        if (orderList.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }

        System.out.println("\n===== ORDER LIST =====");
        System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("| Order ID       | Customer ID        | Menu ID        | Tables    | Province           | Event Date             | Total Cost (VND)            |");
        System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------+");

        for (Order o : orderList) {
            System.out.println("| " + o.toString() + " |");
        }

        System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------+");
    }

    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Order o : orderList) {
                bw.write(String.join(",",
                        o.getOrderID(), o.getCustomerID(), o.getMenuID(),
                        String.valueOf(o.getTableCount()),
                        o.getProvince(),
                        dateFormat.format(o.getEventDate()),
                        String.valueOf(o.getTotalCost())));
                bw.newLine();
            }
            System.out.println("Orders saved to file.");
            saved = true;
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public void readFromFile() {
        orderList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    orderList.add(new Order(
                            parts[0], parts[1], parts[2],
                            Integer.parseInt(parts[3]), 
                            parts[4],
                            dateFormat.parse(parts[5]),
                            Double.parseDouble(parts[6])
                    ));
                }
            }
            System.out.println("Orders loaded.");
        } catch (IOException e) {
            System.out.println("No existing data.");
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private boolean isDupOrder(String cusID, String MenuID, Date evenDate){
        for (Order c : orderList){
            if(c.getCustomerID().equalsIgnoreCase(cusID) && c.getMenuID().equalsIgnoreCase(MenuID) 
                    &&  c.getEventDate().equals(evenDate)){
                return true;
            }
        }
        return false;
    }

    private Order findOrderByID(String orderID) {
        for (Order o : orderList) {
            if (o.getOrderID().equalsIgnoreCase(orderID)) {
                return o;
            }
        }
        return null;
    }

    public boolean isSaved() {
        return saved;
    }
}
