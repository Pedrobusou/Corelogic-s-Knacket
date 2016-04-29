package com.example.pedroramos.testtab2.data.model;

import java.util.ArrayList;

/**
 * Created by pedroramos on 28.04.16.
 */
public class Buyer {
    String name, category, date, description, price;
    int id, rating;

    public Buyer(){
    }

    public static ArrayList<Buyer> add3Buyers(){
        ArrayList<Buyer> buyers = new ArrayList<>();
        buyers.add(new Buyer("Andreas Furst", "Hardcoded Buyer", "3-mar 17:05", "Soffa fatolj och sjrivbord skal slangas i grovsopor, bor pa 3tr grovosporna ligger isuefhisuhfiusehifuhseifuhseiufhseiufhiseuh", "300kr", 3));
        buyers.add(new Buyer("Pedro", "Hardcoded Buyer", "3-mar 17:05", "Soffa fatolj och sjrivbord skal slangas i grovsopor, bor pa 3tr grovosporna ligger isuefhisuhfiusehifuhseifuhseiufhseiufhiseuh", "3000kr", 5));
        buyers.add(new Buyer("Marcos", "Hardcoded Buyer", "3-mar 17:05", "Soffa fatolj och sjrivbord skal slangas i grovsopor, bor pa 3tr grovosporna ligger isuefhisuhfiusehifuhseifuhseiufhseiufhiseuh", "300kr", 2));
        return buyers;
    }

    public Buyer(String name, String category, String date, String description, String price, int rating){
        this.name = name;
        this.category = category;
        this.date = date;
        this.description = description;
        this.price = price;
        this.rating = rating;
    }

    public Buyer(String name, String category, String date, String description, String price, int rating, int id){
        this.name = name;
        this.category = category;
        this.date = date;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}