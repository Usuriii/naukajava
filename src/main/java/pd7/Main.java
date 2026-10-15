package pd7;

import pd7.exceptions.DuplicateEmailException;
import pd7.exceptions.ValidationException;
import pd7.service.InputValidation;
import pd7.enums.PasswordStrength;
import pd7.service.UserRegistrationService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("MENU:");
            System.out.println("1. Rejestracja użytkownika");
            System.out.println("2. Wyświetl użytkowników");
            System.out.println("3. Wyjście");
            String option = scanner.nextLine();

            switch (option) {
                case "1" -> {
                    boolean isNameValid = false;
                    String name = "";
                    while (!isNameValid) {
                        System.out.println("Podaj nazwe [min 2 znaki, max 100, tylko litery/spacje/myślniki]: ");
                        try {
                            name = scanner.nextLine();
                            InputValidation.nameValidate(name);
                            isNameValid = true;
                        } catch (ValidationException e) {
                            System.err.println(e.getMessage());
                        }
                    }

                    boolean isEmailValid = false;
                    String email = "";
                    while (!isEmailValid) {
                        System.out.println("Podaj email [wzorzec x@y.z]:");
                        try {
                            email = scanner.nextLine().toLowerCase();
                            InputValidation.emailValidate(email);
                            UserRegistrationService.emailDuplicateValid(email);
                            isEmailValid = true;
                        } catch (ValidationException | DuplicateEmailException e) {
                            System.err.println(e.getMessage());
                        }
                    }

                    boolean isPasswordValid = false;
                    String password = "";
                    while (!isPasswordValid) {
                        System.out.println("Podaj hasło [min 8 znaków, wielka litera, cyfra, znak specjalny]: ");
                        password = scanner.nextLine();
                        PasswordStrength passwordStrength = InputValidation.validatePassword(password);
                        if (passwordStrength == PasswordStrength.WEAK) {
                            System.err.println(PasswordStrength.WEAK.getDescription());
                        } else if (passwordStrength == PasswordStrength.STRONG) {
                            System.out.println(PasswordStrength.STRONG.getDescription());
                            isPasswordValid = true;
                        } else {
                            System.out.println(PasswordStrength.OK.getDescription());
                            isPasswordValid = true;
                        }
                    }
                    UserRegistrationService.registerUser(name, email, password);
                    UserRegistrationService.printUsers();
                }

                case "2" -> UserRegistrationService.printUsers();

                case "3" -> {
                    System.out.println("NARA");
                    scanner.close();
                    running = false;
                }

                default -> System.err.println("Niepoprawna opcja!");

            }
        }
    }
}
