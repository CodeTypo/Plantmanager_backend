package com.codetypo.plantmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Indexed
//A class representing a table of plants belonging to the user, so far only a few parameters are added
public class Plant extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @FullTextField
    private String name;
    @FullTextField(analyzer = "stop")
    private String description;
    private int measured_humidity;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @IndexedEmbedded
    private User user;
}
