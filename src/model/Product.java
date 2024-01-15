package model;

import java.io.Serializable;

public class Product implements Serializable, IParser {
    public static long currentID = 0;
    private long id;
    private String name;
    private String description;
    private float price;
    private long quantity;

    public Product() {
    }

    public Product(long id, String name, String description, float price, long quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public static long getCurrentID() {
        return currentID;
    }

    public static void setCurrentID(long currentID) {
        Product.currentID = currentID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s", this.id, this.name, this.description, this.price,this.quantity );

    }

    @Override
    public void parse(String line) {
        String[] items = line.split(",");
        this.id = Long.parseLong(items[0]);
        this.name = items[1];
        this.description = items[2];
        this.price = Float.parseFloat(items[3]);
        this.quantity=Long.parseLong(items[4]);
    }
}
