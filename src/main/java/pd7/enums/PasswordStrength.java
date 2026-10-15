package pd7.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum PasswordStrength {
    WEAK("Password does not meet the requirements."),
    OK("Password is correct."),
    STRONG("Password is strong.");

    private final String description;

}
