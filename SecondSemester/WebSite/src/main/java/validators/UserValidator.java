package validators;

import java.util.regex.Pattern;

public class UserValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+" +
            "(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    public boolean checkEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
