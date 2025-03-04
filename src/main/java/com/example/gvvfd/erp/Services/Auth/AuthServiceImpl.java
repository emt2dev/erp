package com.example.gvvfd.erp.Services.Auth;

import com.example.gvvfd.erp.Models.User;
import com.example.gvvfd.erp.Models.enums.UserRole;
import com.example.gvvfd.erp.Repositories.UserRepository;
import com.example.gvvfd.erp.Services.JWT.UserService;
import com.example.gvvfd.erp.Utils.JwtUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository repo;
    private final UserService userService;
    private final JwtUtil utils;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public HashMap<Boolean, String> authenticate(String username, String password, Long agencyId) {
        HashMap<Boolean, String> Result = new HashMap<Boolean, String>();
        UserDetailsService uds = userService.UDS();
        UserDetails Deets = uds.loadUserByUsername(username);

        if (!(Deets instanceof User userFound)) {
            Result.put(false, "");
            return Result;
        }

        if (!userFound.getAgencyId().equals(agencyId)) {
            Result.put(false, "");
            return Result;
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(password, Deets.getPassword())) {
            Result.put(false, "");
            return Result;
        }

        Result.put(true, utils.generateToken(Deets, userFound.getUserRole()));

        return Result;
    }

    @PostConstruct
    public void createCommandAccount() {
        Optional<User> cmd = repo.findByUserRole(UserRole.Command);
        if (cmd.isEmpty()) {
            User u = new User();
            u.setCreatedByUsername("Candyroll93");
            u.setAgencyId(1L);
            u.setLastLogin(new Date());
            u.setUsername("cmdTest");
            u.setUserRole(UserRole.Command);
            u.setPassword(new BCryptPasswordEncoder().encode("commandTest"));
            repo.save(u);
            System.out.println("Command account created successfully");
        } else {
            System.out.println("Command Account already exists");
        }
    }
}
