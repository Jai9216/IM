package com.im.exceptions;

public class IncidentNotFoundException extends RuntimeException{

    public IncidentNotFoundException (){
        super("Incident not found on server!!");
    }

    public IncidentNotFoundException(String message){
        super(message);
    }
}
