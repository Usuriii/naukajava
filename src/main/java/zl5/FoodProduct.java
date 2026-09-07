package zl5;

public class FoodProduct extends Product {
    private String dateExpire;

    public FoodProduct(String name, double price, String category, String dateExpire) {
        super(name, price, category);
        this.dateExpire = dateExpire;
    }

    @Override
    protected String getDescription() {
        return super.getDescription() + " " + dateExpire;
    }
}
