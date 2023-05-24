package com.order_management_system.serviceImpl;

import com.order_management_system.entities.Customer;
import com.order_management_system.entities.Items;
import com.order_management_system.exception.ResourceNotFoundException;
import com.order_management_system.payload.CustomerDto;
import com.order_management_system.payload.ItemDto;
import com.order_management_system.repository.ItemRepository;
import com.order_management_system.service.ItemsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemsService {

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, ModelMapper modelMapper) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ItemDto> getAllItems() {
        List<Items> items = itemRepository.findAll();
        return items.stream()
                .map(item -> modelMapper.map(item, ItemDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ItemDto getItemById(Long id) {
        Items item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));
        return modelMapper.map(item, ItemDto.class);
    }

    @Override
    public ItemDto createItem(ItemDto itemDto) {
        Items item = modelMapper.map(itemDto, Items.class);
        Items savedItem = itemRepository.save(item);
        return modelMapper.map(savedItem, ItemDto.class);
    }

@Override
public ItemDto updateItem(Long id, ItemDto itemDto){
    Items item = itemRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));
    //modelMapper.map(item, itemDto);
    item.setName(itemDto.getName());
    item.setPrice(itemDto.getPrice());
    Items savedItems=itemRepository.save(item);
    return modelMapper.map(savedItems,ItemDto.class);
}
    @Override
    public void deleteItem(Long id) {
        Items item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));
        itemRepository.delete(item);
    }
}

