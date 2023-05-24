package com.order_management_system.service;

import com.order_management_system.payload.ItemDto;

import java.util.List;

public interface ItemsService {
    List<ItemDto> getAllItems();
    ItemDto getItemById(Long id);
    ItemDto createItem(ItemDto itemDto);
    ItemDto updateItem(Long id, ItemDto itemDto);
    void deleteItem(Long id);
}

