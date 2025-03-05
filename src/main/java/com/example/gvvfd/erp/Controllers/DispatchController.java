package com.example.gvvfd.erp.Controllers;

import com.example.gvvfd.erp.DTOs.*;
import com.example.gvvfd.erp.Models.Agency;
import com.example.gvvfd.erp.Models.Call;
import com.example.gvvfd.erp.Models.Location;
import com.example.gvvfd.erp.Models.Shift;
import com.example.gvvfd.erp.Services.Agencies.AgencyService;
import com.example.gvvfd.erp.Services.CallAssignments.CallAssignmentsService;
import com.example.gvvfd.erp.Services.Calls.CallService;
import com.example.gvvfd.erp.Services.IncidentReports.IncidentReportsService;
import com.example.gvvfd.erp.Services.Location.LocationService;
import com.example.gvvfd.erp.Services.ShiftRosters.ShiftRostersService;
import com.example.gvvfd.erp.Services.Shifts.ShiftService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@CrossOrigin
@RestController
@RequestMapping("/api/dispatch")
@AllArgsConstructor
public class DispatchController {
    @Autowired
    private final LocationService locationService;

    @Autowired
    private final CallService callService;
    @Autowired
    private final CallAssignmentsService callAssignmentsService;

    @Autowired
    private final AgencyService agencyService;

    @Autowired
    private final ShiftService svc;

    @Autowired
    private final ShiftRostersService srSvc;

    @Autowired
    private final IncidentReportsService irSvc;

    // Endpoint to update a new Shift with Assignments
    @PostMapping("/agency/{agencyId}/shift/update/{shiftId}/{isActive}")
    public ResponseEntity<Boolean> updateShift(@PathVariable Long agencyId, @PathVariable Long shiftId, @PathVariable Boolean isActive) {
        try {
            // Ensure the agency exists
            Optional<Agency> a = agencyService.findById(agencyId);
            if (a.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            Shift Found = svc.getShiftByIdAndAgencyId(shiftId, agencyId);

            if (Found == null) {
                return ResponseEntity.badRequest().build();  // Return 400 Bad Request if the shift is null
            }

            Boolean Result = svc.updateShift(shiftId, agencyId, isActive);
            if (Result)
                return ResponseEntity.ok(true);

            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Endpoint to create a new Shift with Assignments
    @PostMapping("/agency/{agencyId}/shift/roster/{shiftId}/roster/new")
    public ResponseEntity<Boolean> createShiftRoster(@PathVariable Long agencyId, @PathVariable Long shiftId, @RequestBody ShiftRosterDTO Incoming) {

        try {
            // Ensure the agency exists
            Optional<Agency> a = agencyService.findById(agencyId);
            if (a.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            Shift Found = svc.getShiftByIdAndAgencyId(shiftId, agencyId);

            if (Found == null) {
                return ResponseEntity.badRequest().build();  // Return 400 Bad Request if the shift is null
            }

            Boolean Result = srSvc.CreateOrUpdate(Incoming, Incoming.getIsNew(), agencyId, shiftId);
            if (Result)
                return ResponseEntity.ok(true);

            return ResponseEntity.badRequest().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Endpoint to create a new Shift with Assignments
    @PostMapping("/agency/{agencyId}/shift/incident/{shiftId}/new/{reportedById}")
    public ResponseEntity<Boolean> createIncidentReport(@PathVariable Long agencyId, @PathVariable Long shiftId, @PathVariable Long reportedById, @RequestBody IncidentReportDTO DTO) {

        try {
            // Ensure the agency exists
            Optional<Agency> a = agencyService.findById(agencyId);
            if (a.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            Boolean Result = irSvc.Create(agencyId, shiftId, reportedById, DTO);
            if (Result)
                return ResponseEntity.ok(true);

            return ResponseEntity.badRequest().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Endpoint to create a new Shift with Assignments
    @PostMapping("/agency/{agencyId}/shift/incident/{shiftId}/complete/{irId}")
    public ResponseEntity<Boolean> completeIncidentReport(@PathVariable Long agencyId, @PathVariable Long shiftId, @PathVariable Long irId, @RequestBody IncidentReportDTO DTO) {
        try {
            // Ensure the agency exists
            Optional<Agency> a = agencyService.findById(agencyId);
            if (a.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            Boolean Result = irSvc.Update(agencyId, shiftId, irId, DTO, true);
            if (Result)
                return ResponseEntity.ok(true);

            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Endpoint to create a new Shift with Assignments
    @PostMapping("/agency/{agencyId}/shift/incident/{shiftId}/update/{irId}")
    public ResponseEntity<Boolean> updateIncidentReport(@PathVariable Long agencyId, @PathVariable Long shiftId, @PathVariable Long irId, @RequestBody IncidentReportDTO DTO) {

        try {
            // Ensure the agency exists
            Optional<Agency> a = agencyService.findById(agencyId);
            if (a.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            Boolean Result = irSvc.Update(agencyId, shiftId, irId, DTO, false);
            if (Result)
                return ResponseEntity.ok(true);

            return ResponseEntity.badRequest().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Endpoint to create a new Shift with Assignments
    @PostMapping("/agency/{agencyId}/shift/create")
    public ResponseEntity<Boolean> createShift(@PathVariable Long agencyId, @RequestBody ShiftDTO Incoming) {


        try {
            // Ensure the agency exists
            Optional<Agency> a = agencyService.findById(agencyId);
            if (a.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            Boolean Result = svc.saveShiftWithAssignments(Incoming);
            if (Result)
                return ResponseEntity.ok(true);

            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/agency/{agencyId}/shift/details{shiftId}")
    public ResponseEntity<Shift> GetShiftDetails(@PathVariable Long agencyId, @PathVariable Long shiftId) {


        try {
            // Ensure the agency exists
            Optional<Agency> a = agencyService.findById(agencyId);
            if (a.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            Shift Found = svc.getShiftByIdAndAgencyId(shiftId, agencyId);

            if (Found == null) {
                return ResponseEntity.badRequest().build();  // Return 400 Bad Request if the shift is null
            }

            return ResponseEntity.ok(Found);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/agency/{agencyId}/call/new")
    public ResponseEntity<Boolean> CreateCall(@PathVariable Long agencyId, @RequestBody CallDTO Incoming) {

        try {
            // Ensure the agency exists
            Optional<Agency> a = agencyService.findById(agencyId);
            if (a.isEmpty())
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            callService.createCall(Incoming);

            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/agency/{agencyId}/call/{callId}/complete")
    public ResponseEntity<Boolean> CompleteCall(@PathVariable Long agencyId, @RequestBody CallUpdateDTO Incoming) {

        try {
            // Ensure the agency exists
            Optional<Agency> a = agencyService.findById(agencyId);
            if (a.isEmpty())
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            Call c = callService.GetByIdAndAgencyId(Incoming.getId(), agencyId);
            if (c == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

            callService.updateCall(Incoming, c, true);

            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/agency/{agencyId}/call/{callId}/update")
    public ResponseEntity<Boolean> UpdateCall(@PathVariable Long agencyId, @RequestBody CallUpdateDTO Incoming) {


        try {
            // Ensure the agency exists
            Optional<Agency> a = agencyService.findById(agencyId);
            if (a.isEmpty())
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            Call c = callService.GetByIdAndAgencyId(Incoming.getId(), agencyId);
            if (c == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

            callService.updateCall(Incoming, c, false);

            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/agency/{agencyId}/calls/details/{callId}")
    public ResponseEntity<Call> GetCallByIdAndAgencyId(@PathVariable Long agencyId, @PathVariable Long callId) {

        try {
            // Ensure the agency exists
            Optional<Agency> a = agencyService.findById(agencyId);
            if (a.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            Call call = callService.GetByIdAndAgencyId(callId, agencyId);
            if (call != null) {
                return ResponseEntity.ok(call); // If the call exists, return it with status 200
            } else {
                return ResponseEntity.noContent().build(); // If not, return no content
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/agency/{agencyId}/calls/all")
    public ResponseEntity<List<Call>> GetAllCalls(@PathVariable Long agencyId) {

        try {
            // Ensure the agency exists
            Optional<Agency> a = agencyService.findById(agencyId);
            if (a.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            List<Call> activeCalls = callService.getCallHistoryByAgencyId(agencyId);
            if (activeCalls.isEmpty()) {
                return ResponseEntity.noContent().build();  // or you could return a 404 if needed
            }

            return ResponseEntity.ok(activeCalls);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/agency/{agencyId}/calls/all/{shiftId}")
    public ResponseEntity<List<Call>> GetAllCallsByShiftId(@PathVariable Long agencyId, @PathVariable Long shiftId) {


        try {
            // Ensure the agency exists
            Optional<Agency> a = agencyService.findById(agencyId);
            if (a.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            List<Call> activeCalls = callService.getShiftCalls(agencyId, shiftId);
            if (activeCalls.isEmpty()) {
                return ResponseEntity.noContent().build();  // or you could return a 404 if needed
            }

            return ResponseEntity.ok(activeCalls);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/agency/{agencyId}/calls/next")
    public ResponseEntity<Call> GetNextCall(@PathVariable Long agencyId) {


        try {
            // Ensure the agency exists
            Optional<Agency> a = agencyService.findById(agencyId);
            if (a.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            // Get active calls by agencyId
            List<Call> activeCalls = callService.getActiveCallsByAgencyId(agencyId);
            if (activeCalls.isEmpty()) {
                return ResponseEntity.noContent().build();  // or you could return a 404 if needed
            }

            // Pick a random call from the list
            Random random = new Random();
            Call randomCall = activeCalls.get(random.nextInt(activeCalls.size()));

            return ResponseEntity.ok(randomCall);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/agency/{agencyId}/locations/all")
    public ResponseEntity<List<Location>> GetAllLocations(@PathVariable Long agencyId) {

        try {
            // Ensure the agency exists
            Optional<Agency> a = agencyService.findById(agencyId);
            if (a.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            return ResponseEntity.ok(locationService.GetAllByAgencyId(agencyId));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
