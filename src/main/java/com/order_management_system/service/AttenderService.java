package com.order_management_system.service;

import com.order_management_system.payload.AttenderDto;

import java.util.List;

public interface AttenderService {
    List<AttenderDto> getAllAttenders();
    AttenderDto getAttenderById(Long id);
    AttenderDto createAttender(AttenderDto attenderDto);
    AttenderDto updateAttender(Long id, AttenderDto attenderDto);
    void deleteAttender(Long id);
}
