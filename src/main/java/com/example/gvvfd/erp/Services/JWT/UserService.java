package com.example.gvvfd.erp.Services.JWT;

import com.example.gvvfd.erp.DTOs.NewUserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService UDS();
    Boolean CreateNewUser(NewUserDTO Incoming);
    void NewLogin(Long UserId);
    void UpdateRole(Long UserId, Long AgencyId, String NewRole);
}
