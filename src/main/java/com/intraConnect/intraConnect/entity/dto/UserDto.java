package com.intraConnect.intraConnect.entity.dto;

import com.intraConnect.intraConnect.enums.Departamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private  String username;
    private  String password;
    private  String name;

    private String email;
    private Departamento departamento;


}
