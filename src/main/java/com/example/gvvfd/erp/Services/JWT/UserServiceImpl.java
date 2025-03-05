package com.example.gvvfd.erp.Services.JWT;

import com.example.gvvfd.erp.DTOs.NewUserDTO;
import com.example.gvvfd.erp.Models.User;
import com.example.gvvfd.erp.Models.enums.UserRole;
import com.example.gvvfd.erp.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repo;

    public void UpdateRole(Long UserId, Long AgencyId, String NewRole) {
        Optional<User> U = repo.findById(UserId);

        if (U.isPresent()) {
            User Obj = U.get();
            if (Objects.equals(Obj.getAgencyId(), AgencyId)) {
                Obj.setUserRole(UserRole.valueOf(NewRole));

                repo.saveAndFlush(Obj);
            }
        }
    }

    public void NewLogin(Long UserId) {
        Optional<User> U = repo.findById(UserId);

        if (U.isPresent()) {
            User Obj = U.get();
            Obj.setLastLogin(new Date());

            repo.saveAndFlush(Obj);
        }
    }

    public Boolean CreateNewUser(NewUserDTO Incoming) {
        UserRole role = UserRole.valueOf(Incoming.getUserRole());

        // Create new User entity
        User user = new User();
        user.setUsername(Incoming.getUsername());
        user.setCreatedByUsername(Incoming.getCreatedByUsername());
        user.setUserRole(role);
        user.setAgencyId(Incoming.getAgencyId());
        user.setPassword(new BCryptPasswordEncoder().encode("P@ssword1"));
        user.setLastLogin(null);  // You can set a value for lastLogin if required

        repo.saveAndFlush(user);

        return true;
    }

    @Override
    public UserDetailsService UDS() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return repo.findFirstByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }
}
