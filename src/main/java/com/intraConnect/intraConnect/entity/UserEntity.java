package com.intraConnect.intraConnect.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private  String username;
    private  String password;

    @Column(length = 150)
    private  String name;

    //ROL
    //private Departamento departamento;

    @Column(unique = true)
    private String correo;


}
