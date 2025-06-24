/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ttuan
 */
public class MenuItem {
    private String menuID; // MĂ£ thá»±c Ä‘Æ¡n (PWxxx)
    private String name; // TĂªn thá»±c Ä‘Æ¡n
    private double price; // GiĂ¡ thá»±c Ä‘Æ¡n
    private String ingredients; // Danh sĂ¡ch nguyĂªn liá»‡u

    // Constructor
    public MenuItem(String menuID, String name, double price, String ingredients) {
        this.menuID = menuID;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    // Getters
    public String getMenuID() {
        return menuID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getIngredients() {
        return ingredients;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    // toString() - Hiá»ƒn thá»‹ thĂ´ng tin thá»±c Ä‘Æ¡n
    @Override
    public String toString() {
        return String.format("Menu ID: %s | Name: %s | Price: %.2f VND | Ingredients: %s",
                menuID, name, price, ingredients);
    }
}

