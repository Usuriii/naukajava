package zl8;

public class Main {
    public static void main(String[] args) {

        User user1 = new User(new Subscription(true, "abc"));
        User user2 = new User(new Subscription(false, "abc"));
        User user3 = new User(new Subscription(true, ""));
        User user4 = new User(new Subscription(true, null));
        User user5 = new User(null);

        System.out.println(Discount.getDiscountCode(user4));
    }

}
