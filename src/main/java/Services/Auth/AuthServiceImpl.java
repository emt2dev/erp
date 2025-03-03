package Services.Auth;

import Models.User;
import Models.enums.UserRole;
import Repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository repo;

    @PostConstruct
    public void createCommandAccount() {
    Optional<User> cmd = repo.findByUserRole(UserRole.Command);
    if (cmd.isEmpty()) {
        User u = new User();
        u.setUserName("cmdTest");
        u.setRole(UserRole.Command);
        u.setPassword(new BCryptPasswordEncoder().encode("commandTest"));
        repo.save(u);
        System.out.println("Command account created successfully");
    } else {
        System.out.println("Command Account already exists");
    }
    }
}
