# cleanview


1.graphql

http://localhost:8080/graphiql?path=/graphql


query bookDetails {
bookById(id: "book-3") {
id
name
pageCount
author {
id
firstName
lastName
}
}
}



@@@@

[14:50, 07/03/2024] Alex: import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(String username, String password) {
        // Generare salt
        String salt = generateSalt();

        // Concatenare parolă și salt
        String passwordWithSalt = password + salt;

        // Hash-ul parolei cu salt
        String hashedPassword = passwordEncoder.encode(passwordWithSalt);

        // Salvare utilizator în baza de date
        User user = new User(username, hashedPassword, salt);
        userRepository.save(user);
    }

    private String generateSalt() {
        // Implementarea generării unui salt aleatoriu
    }
}
[14:50, 07/03/2024] Alex: public class AuthenticationService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            // Obținerea salt-ului stocat pentru utilizator
            String salt = user.getSalt();
            
            // Concatenare parolă și salt
            String passwordWithSalt = password + salt;

            // Verificarea hash-ului
            return passwordEncoder.matches(passwordWithSalt, user.getPassword());
        }
        return false;
    }
}
[14:50, 07/03/2024] Alex: public class AuthRequest {
    private String username;
    private String password;

    // Constructor, getteri și setteri
}
[14:50, 07/03/2024] Alex: public class AuthResponse {
    private final String token;

    // Constructor, getter
}
[14:50, 07/03/2024] Alex: # Folosim imaginea de bază pentru PostgreSQL
FROM postgres:latest

# Adăugăm scriptul de inițializare pentru crearea bazei de date și a utilizatorului
ADD init.sql /docker-entrypoint-initdb.d/

# Portul pe care va rula PostgreSQL
EXPOSE 5432
[14:51, 07/03/2024] Alex: CREATE DATABASE mydatabase;
CREATE USER myuser WITH PASSWORD 'mypassword';
GRANT ALL PRIVILEGES ON DATABASE mydatabase TO myuser;
[14:51, 07/03/2024] Alex: # Configurare pentru baza de date PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/mydatabase
spring.datasource.username=myuser
spring.datasource.password=mypassword
spring.datasource.driver-class-name=org.postgresql.Driver

# Configurare pentru JWT
jwt.secret=your_secret_key
jwt.expirationMs=3600000
[14:52, 07/03/2024] Alex: import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    private final AuthenticationService authenticationService;

    public TokenController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest) {
        // Verificarea credențialelor utilizatorului
        boolean isAuthenticated = authenticationService.authenticateUser(authRequest.getUsername(), authRequest.getPassword());
        
        if (isAuthenticated) {
            // Generarea unui token valid pentru utilizator
            String token = authenticationService.generateToken(authRequest.getUsername());
            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Autentificare eșuată. Credențiale invalide.");
        }
    }
}
