package com.codetypo.plantmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
//A class representing a table of plants belonging to the user, so far only a few parameters are added
public class Plant extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int measured_humidity;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User user;
}
