package com.order_management_system.payload;

import com.order_management_system.entities.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private UserRole role;
}
