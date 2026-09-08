package zl5;

public class Product {
    private String name;
    private double price;
    private String category;

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    protected String getDescription() {
        return name + " " + price + " " + category;
    }
}
