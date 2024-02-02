package alan.booking.services.model;

public record User(
        String name,
        String surname,
        String email,
        String password,
        long phoneNumber,
        String address) {


}

