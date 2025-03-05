package com.example.gvvfd.erp.Services.Auth;

import com.example.gvvfd.erp.DTOs.UserPasswordUpdateDTO;
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

    public Boolean ResetPassword(Long UserId, String PasswordNew) {
        Optional<User> U = repo.findById(UserId);
        if (U.isPresent()) {
            User Exists = U.get();
            Exists.setPassword(passwordEncoder.encode(PasswordNew));

            return true;
        }

        return false;
    }

    public Boolean UpdatePassword(UserPasswordUpdateDTO DTO) {
        Optional<User> Exists = repo.findFirstByUsername(DTO.getUsername());
        if (Exists.isPresent()) {
            User user = Exists.get();

            // Check if the old password matches the stored password
            boolean isOldPasswordMatch = passwordEncoder.matches(DTO.getOldPassword(), user.getPassword());

            if (isOldPasswordMatch) {
                // If the old password matches, hash the new password and save the user
                String hashedNewPassword = passwordEncoder.encode(DTO.getNewPassword());
                user.setPassword(hashedNewPassword);

                // Save the updated user entity back to the repository
                repo.saveAndFlush(user);
                return true;
            }

            return false;
        }

        return false;
    }

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
            repo.saveAndFlush(u);
            System.out.println("Command account created successfully");
        } else {
            System.out.println("Command Account already exists");
        }
    }
}
