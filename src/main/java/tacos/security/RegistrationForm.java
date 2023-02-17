package tacos.security;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import tacos.TacoUser;

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String fullName;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public TacoUser toUser(PasswordEncoder passwordEncoder) {
        return new TacoUser(username, passwordEncoder.encode(password), fullName, street, city, state, zip, phone);
    }
}
