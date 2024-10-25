package Internal;

public class Product {
    private int price;
    private String name;
    private String description;
    private String category;

    public Product(int price, String name, String description, String category) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public Product() {

    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public void showProduct() {
        System.out.println("Name: " + name);
        System.out.println("Price: $" +  price);
        System.out.println("Category: " + category);
        System.out.println("Description: " + description);
    }

    @Override
    public String toString() {
        return name + " (" + category + ") - $" +  price ;
    }
}