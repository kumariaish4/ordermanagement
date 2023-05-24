package com.order_management_system.service;

import com.order_management_system.payload.UserDto;

public interface UserService {
        UserDto getUserById(Long id);
        UserDto createUser(UserDto userDTO);
        UserDto updateUser(Long id, UserDto userDTO);
        void deleteUser(Long id);



}
