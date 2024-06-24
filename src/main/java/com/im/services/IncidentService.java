package com.im.services;

import com.im.entities.Incident;
import com.im.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IncidentService {

    //create Incident
    Incident createIncident(Incident incident, String userId);

    List<Incident> getAllIncidents(String userId);

    Incident getIncidentById(String userId,String incidentId);

    Incident updateIncident(String userId,String IncidentId,Incident incident);


}
