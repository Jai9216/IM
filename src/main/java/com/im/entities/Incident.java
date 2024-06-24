package com.im.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "incident")
public class Incident {

    @Id
    @Column(name = "incident_id")
    private String id;

    @Column(name = "reporter_name")
    private String reporterName;

    @Column(name="reporter_type")
    private String reporterType;

    @Column(name = "incident_detail",length = 1000)
    private String incidentDetail;

    @Column(name="incident_date_time")
    private String incidentDateAndTime;

    private String priority;

    private String status;

    @ManyToOne
    @JsonIgnore
    private User user;
}
