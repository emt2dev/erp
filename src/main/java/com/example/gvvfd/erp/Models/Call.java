package com.example.gvvfd.erp.Models;

import com.example.gvvfd.erp.Models.enums.CallType;
import com.example.gvvfd.erp.Models.interfaces.Base;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Calls")
@NoArgsConstructor
@AllArgsConstructor
public class Call implements Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long agencyId;
    private Long shiftId;

    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "dispatchedAt")
    private Date dispatchedAt;

    @Column(name = "active")
    private Boolean active;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 25)
    private CallType type;

    @Column(name = "created_by_user_id")
    private Long createdByUserId;

    @Column(name = "cad_display")
    private String cadDisplay;

    @Column(name = "location")
    private String location;

    // One Call can have many CallAssignments
    @OneToMany(mappedBy = "call", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CallAssignment> callAssignments;

}
