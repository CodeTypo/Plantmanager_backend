package com.codetypo.plantmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
//A class representing a table of registered users , so far only a few parameters are added
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nickname;
    private String login;
    private String password;

    //Relation of type One to Many: One User can have many Plants, a plant belongs to a single user only
    @OneToMany(targetEntity = Plant.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "up_fk", referencedColumnName = "id")
    private List<Plant> plants;

}
