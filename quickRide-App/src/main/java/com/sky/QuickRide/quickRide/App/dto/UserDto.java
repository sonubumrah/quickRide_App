package com.sky.QuickRide.quickRide.App.dto;

import com.sky.QuickRide.quickRide.App.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private Set<Role> roles;
}
