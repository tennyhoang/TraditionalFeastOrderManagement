package Business;

import Model.MenuItem;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MenuItems {
    private ArrayList<MenuItem> menuList = new ArrayList<>();
    private static final String FILE_NAME = "FeastMenu.csv";

    public void readFromFile(){
        menuList.clear();
        try(BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))){
            br.readLine();  
            String line;
            while((line = br.readLine()) != null){
                String parts[] = line.split(",");
                if(parts.length == 4) {
                    String menuID = parts[0].trim();
                    String name = parts[1].trim();
                    double price = Double.parseDouble(parts[2].trim());
                    String ingredients = parts[3].trim();
                    menuList.add(new MenuItem(menuID, name, price, ingredients));
                }
            }
            System.out.println("Menu data loaded successfully");
        }catch(IOException e){
            System.out.println("Cannot read from file " + FILE_NAME + "please try again");
        }
    }
    public void displayAllMenus() {
        if (menuList.isEmpty()) {
            System.out.println("No menu data available.");
            return;
        }

        Collections.sort(menuList, Comparator.comparingDouble(MenuItem::getPrice));

        System.out.println("\n===== AVAILABLE MENU =====");
        System.out.println("+----------------------------------------------------------------------------------------------------+");
        System.out.println("| Menu ID | Name                     | Price (VND)   | Ingredients                                      |");
        System.out.println("+----------------------------------------------------------------------------------------------------+");

        for (MenuItem item : menuList) {
            System.out.printf("| %-7s | %-23s | %,13.2f | %-48s |\n", 
                item.getMenuID(), 
                item.getName(), 
                item.getPrice(),
                item.getIngredients().substring(0, Math.min(item.getIngredients().length(), 48)));
        }

        System.out.println("+----------------------------------------------------------------------------------------------------+");
    }

    public MenuItem findMenuByID(String menuID) {
        for (MenuItem item : menuList) {
            if (item.getMenuID().equalsIgnoreCase(menuID)) {
                return item;
            }
        }
        return null; // Không tìm thấy thực đơn
    }
}
