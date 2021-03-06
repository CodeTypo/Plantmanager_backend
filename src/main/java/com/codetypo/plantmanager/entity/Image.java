package com.codetypo.plantmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.sql.SQLException;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Image {

    public Image(long userId){
        this.userId = userId;
    }

    @Id
    @GeneratedValue
    private Long id;
    @Lob
    private byte[] content;

    private Long userId;
}