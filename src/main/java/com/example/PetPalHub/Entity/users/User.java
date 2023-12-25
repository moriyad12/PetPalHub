package com.example.PetPalHub.Entity.users;

import com.example.PetPalHub.Entity.enums.Gender;
import com.example.PetPalHub.Entity.enums.Role;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    protected String phoneNumber;
    protected Role role;
    protected String email;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected Gender gender;
}
