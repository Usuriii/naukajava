package zl8;

import java.util.Optional;
import java.util.function.Predicate;

public class DiscountTask {

    public static void main(String[] args) {
        User user1 = new User(
                new Subscription(true, "  sub15 "),
                null,
                200
        );

        User user2 = new User(
                new Subscription(true, "   "),
                new ReferralProgram(true, " ref20 "),
                300
        );

        User user3 = new User(
                null,
                null,
                1500
        );

        User user4 = new User(
                new Subscription(false, "sub50"),
                new ReferralProgram(true, "   "),
                100
        );

        User user5 = null;

        System.out.println(resolveDiscountCode(user1)); // SUB15
        System.out.println(resolveDiscountCode(user2)); // REF20
        System.out.println(resolveDiscountCode(user3)); // LOYAL20
        System.out.println(resolveDiscountCode(user4)); // DEFAULT10
        System.out.println(resolveDiscountCode(user5)); // DEFAULT10
    }

    private static final Predicate<User> LOYALTY_PROGRAM_QUALIFIER =
            user -> user.getLoyaltyPoints() >= 1000;

    // LEGACY CODE DO PRZEPISANIA
    public static String resolveDiscountCode(User user) {

        return Optional.ofNullable(user)
                .map(User::getSubscription)
                .filter(Subscription::isActive)
                .map(Subscription::getDiscountCode)
                .flatMap(DiscountTask::normalizeCode)
                .or(() -> Optional.ofNullable(user)
                        .map(User::getReferralProgram)
                        .filter(ReferralProgram::isEnabled)
                        .map(ReferralProgram::getReferralCode)
                        .flatMap(DiscountTask::normalizeCode))
                .or(() -> Optional.ofNullable(user)
                        .filter(LOYALTY_PROGRAM_QUALIFIER)
                        .map(points -> "LOYAL20"))
                .orElse("DEFAULT10");
    }

    private static Optional<String> normalizeCode(String code) {
        return Optional.ofNullable(code)
                .map(String::trim)
                .filter(value -> !value.isBlank())
                .map(String::toUpperCase);
    }

    static class User {
        private final Subscription subscription;
        private final ReferralProgram referralProgram;
        private final int loyaltyPoints;

        User(Subscription subscription, ReferralProgram referralProgram, int loyaltyPoints) {
            this.subscription = subscription;
            this.referralProgram = referralProgram;
            this.loyaltyPoints = loyaltyPoints;
        }

        public Subscription getSubscription() {
            return subscription;
        }

        public ReferralProgram getReferralProgram() {
            return referralProgram;
        }

        public int getLoyaltyPoints() {
            return loyaltyPoints;
        }
    }

    static class Subscription {
        private final boolean active;
        private final String discountCode;

        Subscription(boolean active, String discountCode) {
            this.active = active;
            this.discountCode = discountCode;
        }

        public boolean isActive() {
            return active;
        }

        public String getDiscountCode() {
            return discountCode;
        }
    }

    static class ReferralProgram {
        private final boolean enabled;
        private final String referralCode;

        ReferralProgram(boolean enabled, String referralCode) {
            this.enabled = enabled;
            this.referralCode = referralCode;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public String getReferralCode() {
            return referralCode;
        }
    }
}
