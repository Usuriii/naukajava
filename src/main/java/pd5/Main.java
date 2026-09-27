package pd5;

public class Main {
    public static void main(String[] args) {
        var userService = UserService.inicialization();
        var productService = ProductService.inicialization();

        userService.addUser(new User("Jacek", 12L));
        userService.addUser(new User("Karol", 1L));
        userService.addUser(new User("Bogdan", 1121L));
        productService.addProduct(new Product("mleko", "122"));


        userService.printUser(12L);

        userService.changeUserName(12L, "Kamil");

        userService.printUser(12L);

        System.out.println(userService.getAllUsers());

        userService.deleteUser(1121L);

        System.out.println(userService.getAllUsers());

        productService.printProduct("122");

    }
}
