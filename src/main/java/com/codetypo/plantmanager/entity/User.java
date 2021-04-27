package com.codetypo.plantmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
@Indexed
//A class representing a table of registered users , so far only a few parameters are added
public class User extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @FullTextField(name = "firstName")
    private String fname;
    @FullTextField(name = "lastName")
    private String lname;
    private String email;
    private String password;
    private Role role;
    @OneToOne
    private Image avatar;

    //Relation of type One to Many: One User can have many Plants, a plant belongs to a single user only
    @OneToMany(mappedBy = "user", cascade = {
            CascadeType.ALL
    })
    @JsonIgnore
    private List<Plant> plants;

}
