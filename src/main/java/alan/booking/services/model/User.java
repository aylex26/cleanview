package alan.booking.services.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record User(
        String name,
        String surname,
        @NotBlank(message = "Adresa de email nu poate fi goală")
        @Pattern(regexp = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", message = "Adresa de email invalidă")
        String email,
        String password,
        long phoneNumber,
        String address) {


}

