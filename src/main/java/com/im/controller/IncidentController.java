package com.im.controller;

import com.im.entities.Incident;
import com.im.entities.User;
import com.im.services.IncidentService;
import com.im.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class IncidentController {

    @Autowired
    private UserService userService;

    @Autowired
    private IncidentService incidentService;

    // Create Incident
    @PostMapping("/{userId}/create-incident")
    ResponseEntity<Incident> createIncident(@PathVariable("userId") String userId,@RequestBody Incident incident){
        Incident i = incidentService.createIncident(incident,userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(i);
    }

    //Get All Incidents
    @GetMapping("/{userId}/incidents")
    ResponseEntity<List<Incident>> getIncidents(@PathVariable String userId){
        List<Incident> incidents = incidentService.getAllIncidents(userId);
        return ResponseEntity.ok(incidents);
    }

    //Get Incident By Id
    @GetMapping("/{userId}/incident/{incidentId}")
    ResponseEntity<Incident> getIncident(@PathVariable String userId,@PathVariable String incidentId){
        Incident incident = incidentService.getIncidentById(userId,incidentId);
        return ResponseEntity.ok(incident);
    }

    //Edit Incident if status is not closed
    @PutMapping("/{userId}/incident/{incidentId}")
    ResponseEntity<Incident> editIncident(@PathVariable String userId,@PathVariable String incidentId,@RequestBody Incident incident){
        Incident i = incidentService.updateIncident(userId,incidentId,incident);
        return ResponseEntity.status(HttpStatus.CREATED).body(i);
    }
}
