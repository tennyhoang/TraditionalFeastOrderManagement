package Business;

import Model.Customer;
import Util.Acceptable;
import Util.Inputter;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Customers {
    private ArrayList<Customer> customerList = new ArrayList<>();
    private static final String FILE_NAME = "customers.dat";
    private boolean saved = true;

    public void addCustomer(){
        System.out.println("\n==== ADD CUSTOMER INFORMATION =====");
        
        while(true){
            String cusID;
        
            do {
                cusID = Inputter.getStringWithPattern("Enter Customer ID:: ", Acceptable.CUSTOMER_ID);
                if(isCustomerExist(cusID)){
                    System.out.println("Customer ID already exits. Please enter a unique ID: ");
                }
            }while(isCustomerExist(cusID));
            String name = Inputter.getStringWithPattern("Enter Customer Name:: ", Acceptable.CUSTOMER_NAME);
            String phone = Inputter.getStringWithPattern("Enter Customer Phone:: ", Acceptable.PHONE_NUMBER);
            String email = Inputter.getStringWithPattern("Enter Customer Email:: ", Acceptable.EMAIL);
            
            Customer cus = new Customer(cusID,name,phone,email);
            customerList.add(cus);
            String choice = Inputter.getString("Do you want add another Customer (Y/N): ");
            if(!choice.equalsIgnoreCase("Y")) break;
        }
    }
    
    public void updateCustomer(){
        System.out.println("\n==== UPDATE CUSTOMER ====");
        String customerID = Inputter.getString("Enter a customer ID: ").trim();
        Customer cus = findCustomerByID(customerID);
        if ( cus == null ){
            System.out.println("Cannot found ID");
        }
        
        System.out.println("Current information: " + cus);
        
        String NName = Inputter.getString("Enter new name: ");
        if(!NName.isEmpty() && Acceptable.isValid(NName, Acceptable.CUSTOMER_NAME));
        cus.setName(NName);
        
        String NPhone = Inputter.getString("Enter new phone: ");
        if(!NPhone.isEmpty() && Acceptable.isValid(NPhone, Acceptable.PHONE_NUMBER));
        cus.setName(NPhone);
        
        String NEmail = Inputter.getString("Enter new mail: ");
        if(!NEmail.isEmpty() && Acceptable.isValid(NName, Acceptable.EMAIL));
        cus.setName(NEmail);
        
        System.out.println("Update Cutomer");
       
    }
    public void searchCustomerByName(){
        System.out.println("\n==== SEARCH CUSTOMER BY NAME");
        String Skey = Inputter.getString("Enter full or partial name").trim().toLowerCase();
        
        ArrayList<Customer> matchcus = new ArrayList<>();
        for(Customer cus : customerList){
            if(cus.getName().toLowerCase().equals(Skey)){
                matchcus.add(cus);
            }
        }
        
        if(matchcus.isEmpty()){
            System.out.println("No one matches the criteria!");
        }else{
            Collections.sort(matchcus, Comparator.comparing(Customer :: getName));
            System.out.println("\n==== MATCHING CUSTOMER ====");
            for(Customer cus : matchcus){
                System.out.println(cus);
            }
        }
    }

    public void saveToFile(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))){
            for(Customer c : customerList){
                bw.write(c.getCustomerID() + "," + c.getName() + "," + c.getPhoneNumber() + "," + c.getEmail());
                bw.newLine();
            }
            System.out.println("Customer data has been successfully saved to: ");
            saved = true;
        }catch(IOException e){
            System.out.println("Error saved data customer: " + e.getMessage());
        }
    }

    public void readFromFile(){
        customerList.clear();
        try(BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))){
            String line;
            while((line  = br.readLine()) != null){
                String parts[] = line.split(",");
                if (parts.length == 4){
                    customerList.add(new Customer(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim()));
                }
            }
            System.out.println("Customer data loaded successfully");
        }catch(IOException e){
            System.out.println("No existing data customer load ");
        }
    }

    private boolean isCustomerExist(String CustomerID){
        for (Customer c : customerList){
            if (c.getCustomerID().equals(CustomerID)){
                return true;
            }
        }
        return false;
    }
    
    public Customer findCustomerByID(String customerID){
        for(Customer c : customerList){
            if(c.getCustomerID().equals(customerID)){
                return c;
            }
        }
        return null;
    }

    public boolean isSaved() {
        return saved;
    }

    public void displayCustomers() {
        if (customerList.isEmpty()) {
            System.out.println("No customer data available.");
            return;
        }

        System.out.println("\n===== CUSTOMER LIST =====");
        System.out.println("+---------------------------------------------------------------------------------------------------+");
        System.out.println("| Customer ID | Name                    | Phone Number | Email                                      |");
        System.out.println("+---------------------------------------------------------------------------------------------------+");

        for (Customer c : customerList) {
            System.out.printf("| %-11s | %-23s | %-12s | %-42s |\n",
                    c.getCustomerID(),
                    c.getName(),
                    c.getPhoneNumber(),
                    c.getEmail());
        }

        System.out.println("+----------------------------------------------------------------------------------------------------+");
    }
}
