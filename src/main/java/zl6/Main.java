package zl6;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> nameList = List.of("Jan", "Staś");

        OperationResult<User> user = OperationResult.success(new User("Hubert"));
        OperationResult<Double> number = OperationResult.success(12.1);
        OperationResult<List<String>> list = OperationResult.success(nameList);

        System.out.println(user);
        System.out.println(number);
        System.out.println(list);

//        OperationResult<Product> product = OperationResult.success(new User("Ktos"));
    }
}
