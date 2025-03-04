package com.example.gvvfd.erp.Models;

import com.example.gvvfd.erp.Models.enums.CallType;
import com.example.gvvfd.erp.Models.interfaces.Base;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "calls")
public class Call implements Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long agencyId;

    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "active")
    private Boolean active;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 25)
    private CallType type;

    @Column(name = "created_by_user_id")
    private Long createdByUserId;

    @Column(name = "cad_display")
    private String cadDisplay;

    // One Call can have many CallAssignments
    @OneToMany(mappedBy = "call", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CallAssignment> callAssignments;

}
