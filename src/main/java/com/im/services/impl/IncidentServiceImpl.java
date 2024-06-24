package com.im.services.impl;

import com.im.entities.Incident;
import com.im.entities.User;
import com.im.exceptions.IncidentNotFoundException;
import com.im.exceptions.UserNotFoundException;
import com.im.repositories.IncidentRepository;
import com.im.repositories.UserRepository;
import com.im.services.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Service
public class IncidentServiceImpl implements IncidentService {

    @Autowired
    private IncidentRepository incidentRepository;

    @Autowired
    private UserRepository userRepository;

    //Create incident
    @Override
    public Incident createIncident(Incident incident, String userId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User with given id is not found on server "+userId));

        //Id
        Random random = new Random();
        int digits = random.nextInt(90000)+10000;
        System.out.println(digits);
        int currentYear = Year.now().getValue();
        incident.setId("RMG"+digits+currentYear);
        // reporter name
        incident.setReporterName(user.getFirstName()+user.getLastName());
        //reporter type
        incident.setReporterType(user.getUserType());
        //date and time
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateAndTime = ldt.format(formatter);
        incident.setIncidentDateAndTime(formattedDateAndTime);
        //status
        incident.setStatus("Open");
        // mappping
        incident.setUser(user);

        return incidentRepository.save(incident);
    }

    // All Incident of an user
    @Override
    public List<Incident> getAllIncidents(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User with given id is not found on server "+userId));
        System.out.println(incidentRepository.findByUser(user));
        return incidentRepository.findByUser(user);
    }


    //View Incident By incidentId if and only if the user have this incidentId
    public Incident getIncidentById(String userId,String incidentId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User with given id is not found on server "+userId));
        List<Incident> incidents = incidentRepository.findByUser(user);
        for(Incident i : incidents){
            if(i.getId().equals(incidentId)){
                return i;
            }
        }
        throw new IncidentNotFoundException("Incident with given id "+incidentId+" is not found ");
    }

    //Edit Incident
    @Override
    public Incident updateIncident(String userId, String incidentId, Incident incident) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User with given id is not found on server "+userId));
        Incident i = incidentRepository.findById(incidentId).orElseThrow(()-> new IncidentNotFoundException("Incident with given id is not found "+incidentId));
        //Incident Not editable if status is closed
        if(i.getStatus().equals("closed")){
            return i;
        }
            i.setIncidentDetail(incident.getIncidentDetail());
            i.setPriority(incident.getPriority());
            i.setStatus(incident.getStatus());
            return incidentRepository.save(i);

    }


}
