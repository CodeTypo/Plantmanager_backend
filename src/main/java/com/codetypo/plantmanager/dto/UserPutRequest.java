package com.codetypo.plantmanager.dto;

import com.codetypo.plantmanager.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// This is a DTO - data transfer object: https://stackoverflow.com/questions/1051182/what-is-a-data-transfer-object-dto

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserPutRequest {

    private User user;

}
