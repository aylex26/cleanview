package alan.booking.services.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record User(
        @NotBlank
        String name,
        @NotBlank
        String surname,
        @NotBlank(message = "The email address cannot be empty")
        @Pattern(regexp = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email address")
        String email,
        @NotBlank
        String password,
        @NotBlank
        long phoneNumber,
        @NotBlank
        String address) {
}
