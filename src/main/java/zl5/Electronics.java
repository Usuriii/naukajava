package zl5;

public class Electronics extends Product {
    private double warrantInMonths;

    public Electronics(String name, double price, String category, double warrantInMonths) {
        super(name, price, category);
        this.warrantInMonths = warrantInMonths;
    }

    @Override
    protected String getDescription() {
        return super.getDescription() + " " + warrantInMonths;
    }
}
