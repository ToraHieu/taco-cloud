package tacos;

import lombok.Data;

import org.hibernate.validator.constraints.CreditCardNumber;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class TacoOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private TacoUser user;

    private Date placeAt;

    @Size(max = 50, message = "Character limit exceeded")
    @NotBlank(message = "Deliver is required")
    private String deliveryName;

    @Size(max = 50, message = "Character limit exceeded")
    @NotBlank(message = "Street is required")
    private String deliveryStreet;

    @Size(max = 50, message = "Character limit exceeded")
    @NotBlank(message = "City is required")
    private String deliveryCity;

    @Size(min = 2, max = 2, message = "State abbreviation is required")
    private String deliveryState;

    @Size(max = 10, message = "Invalid Zip code")
    @NotBlank(message = "Zip code is required")
    private String deliveryZip;

    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])/([2-9][0-9]$)", message = "Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        tacos.add(taco);
    }
}
