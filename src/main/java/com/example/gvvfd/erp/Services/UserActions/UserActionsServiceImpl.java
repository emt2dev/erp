package com.example.gvvfd.erp.Services.UserActions;

import com.example.gvvfd.erp.Models.UserActions;
import com.example.gvvfd.erp.Models.enums.Action;
import com.example.gvvfd.erp.Repositories.UserActionsRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserActionsServiceImpl {

    @Autowired
    private UserActionsRepository userActionsRepository;  // Autowired to save UserActions entities

    @PostConstruct
    public void init() {
        // Create a UserActions instance
        UserActions userAction = new UserActions();
        userAction.setAgencyId(1L);
        userAction.setCreated(new Date()); // Set created date
        userAction.setActive(true);         // Set active status
        userAction.setRosterId(1L);         // Example roster member ID
        userAction.setAction(Action.Promoted); // Set action to Promoted (or any action from the enum)
        userAction.setSubmittedUserId(1L); // Set the user ID (1 in this case)

        // Save the UserActions instance
        userActionsRepository.save(userAction);
    }
}
