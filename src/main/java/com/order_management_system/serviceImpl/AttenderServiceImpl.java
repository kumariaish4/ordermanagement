package com.order_management_system.serviceImpl;

import com.order_management_system.entities.Attender;
import com.order_management_system.exception.ResourceNotFoundException;
import com.order_management_system.payload.AttenderDto;
import com.order_management_system.repository.AttenderRepository;
import com.order_management_system.service.AttenderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttenderServiceImpl implements AttenderService {

    @Autowired
    private AttenderRepository attenderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AttenderDto> getAllAttenders() {
        List<Attender> attenders = attenderRepository.findAll();
        return attenders.stream()
                .map(attender -> modelMapper.map(attender, AttenderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AttenderDto getAttenderById(Long id) {
        Attender attender = attenderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attender", "id", id));
        return modelMapper.map(attender, AttenderDto.class);
    }

    @Override
    public AttenderDto createAttender(AttenderDto attenderDto) {
        Attender attender = modelMapper.map(attenderDto, Attender.class);
        Attender savedAttender = attenderRepository.save(attender);
        return modelMapper.map(savedAttender, AttenderDto.class);
    }

    @Override
    public AttenderDto updateAttender(Long id, AttenderDto attenderDto) {
        Attender existingAttender = attenderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attender", "id", id));
        existingAttender.setName(attenderDto.getName());
        Attender updatedAttender = attenderRepository.save(existingAttender);
        return modelMapper.map(updatedAttender, AttenderDto.class);
    }


@Override
    public void deleteAttender(Long id) {
        attenderRepository.deleteById(id);
    }
}

