package pl.dahdev.managementapp.component;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return null;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return false;
    }
}
