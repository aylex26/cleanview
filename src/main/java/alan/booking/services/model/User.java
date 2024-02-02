package alan.booking.services.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record User(
        String name,
        String surname,
        @NotBlank(message = "The email address cannot be empty")
        @Pattern(regexp = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email address")
        String email,
        String password,
        long phoneNumber,
        String address) {


}

