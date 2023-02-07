package tacos;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import lombok.Data;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Table("orders")
public class TacoOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @PrimaryKey
    private UUID id = Uuids.timeBased();

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

    @Column("tacos")
    private List<TacoUDT> tacos = new ArrayList<>();

    public void addTaco(TacoUDT taco) {
        tacos.add(taco);
    }
}
