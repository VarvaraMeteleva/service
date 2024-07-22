package com.meteleva.microservice.entit;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "customers")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name;

    private String surname;

    private String gender;

    private int age;

    private String phone_number;

    public Customer(String name,String surname,String gender, int age, String phone_number){
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name +
                ", surname= " + surname + '\'' +
                ", gender= " + gender +
                ", age=" + age +
                ", phone_number=" + phone_number +
                '}';
    }
}

