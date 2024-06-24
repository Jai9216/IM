package com.im.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    // It can be only Individual, Enterprise, Government
    private String userType;

    @Id
    private String id;

    @NotBlank(message = "First Name is required!!")
    @Size(min = 3,max=30,message="Min 3 and Max 30 characters are allowed !!")
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 3,max=30,message="Min 3 and Max 30 characters are allowed !!")
    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    @Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "Invalid Email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(length = 255)
    private String address;

    private String pincode;

    private String city;

    private String country;

    private String password;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user",fetch = FetchType.LAZY)
    private List<Incident> incidents = new ArrayList<>();

}
