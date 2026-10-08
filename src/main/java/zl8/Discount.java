package zl8;

import java.util.Optional;

public class Discount {

    public static String getDiscountCode(User user) {
        return Optional.ofNullable(user)
                .map(User::getSubscription)
                .filter(Subscription::isActive)
                .map(Subscription::getDiscountCode)
                .map(code -> code.isBlank() ? "DEFAULT10" : code.toUpperCase())
                .orElse(null);
    }
}
