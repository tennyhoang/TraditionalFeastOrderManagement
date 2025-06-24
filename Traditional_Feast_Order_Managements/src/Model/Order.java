/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ttuan
 */
import java.text.SimpleDateFormat;
import java.util.Date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
    private String orderID; 
    private String customerID; 
    private String menuID; 
    private int tableCount; 
    private String province;
    private Date eventDate; 
    private double totalCost; 

    // Constructor
    public Order(String orderID, String customerID, String menuID, int tableCount, String province, Date eventDate, double totalCost) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.menuID = menuID;
        this.tableCount = tableCount;
        this.province = province;
        this.eventDate = eventDate;
        this.totalCost = totalCost;
    }

    // Getters
    public String getOrderID() {
        return orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getMenuID() {
        return menuID;
    }

    public int getTableCount() {
        return tableCount;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    // Setters
    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public void setTableCount(int tableCount) {
        this.tableCount = tableCount;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return String.format(
                "Order ID: %s | Customer ID: %s | Menu ID: %s | Tables: %d | Province: %s | Event Date: %s | Total Cost: %.2f VND",
                orderID, customerID, menuID, tableCount,province, dateFormat.format(eventDate), totalCost);
    }
}

