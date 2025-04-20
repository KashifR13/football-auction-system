package com.bidr.auction.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String position;
    private String profilePicture; // URL or path to the profile picture
    private String addressCity;
    private String nationality;
    private String email;
    private String phoneNumber;
}