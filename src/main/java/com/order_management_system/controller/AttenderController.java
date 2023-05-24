package com.order_management_system.controller;

import com.order_management_system.payload.AttenderDto;
import com.order_management_system.service.AttenderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/attenders")
public class AttenderController {

    private final AttenderService attenderService;

    public AttenderController(AttenderService attenderService) {
        this.attenderService = attenderService;
    }

    @GetMapping
    public List<AttenderDto> getAllAttenders() {
        return attenderService.getAllAttenders();
    }

    @GetMapping("/{id}")
    public AttenderDto getAttenderById(@PathVariable Long id) {
        return attenderService.getAttenderById(id);
    }

    @PostMapping
    public AttenderDto createAttender(@RequestBody AttenderDto attenderDto) {
        return attenderService.createAttender(attenderDto);
    }

    @PutMapping("/{id}")
    public AttenderDto updateAttender(@PathVariable Long id, @RequestBody AttenderDto attenderDto) {
        return attenderService.updateAttender(id, attenderDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttender(@PathVariable Long id) {
        attenderService.deleteAttender(id);
        return ResponseEntity.noContent().build();
    }
}
