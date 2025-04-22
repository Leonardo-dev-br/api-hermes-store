package com.example.hermes.api_hermes_store.controller;

import com.example.hermes.api_hermes_store.model.Items;
import com.example.hermes.api_hermes_store.model.Items;
import com.example.hermes.api_hermes_store.repository.ItemRepository;
import com.example.hermes.api_hermes_store.repository.ItemsRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemsRepository itemRepository;

    @GetMapping
    public List<Items> getAllItems() {
        return itemRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Items> getItemById(@PathVariable Long id) {
        return itemRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



    @PostMapping
    public ResponseEntity<Items> createItem(@Valid @RequestBody Items item) {
        Items savedItem = itemRepository.save(item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }


   
    @PutMapping("/{id}")
    public ResponseEntity<Items> updateItem(@PathVariable Long id, @Valid @RequestBody Items updatedItem) {
        if (!itemRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedItem.setId(id); // Mantém o ID do item
        Items savedItem = itemRepository.save(updatedItem);
        return ResponseEntity.ok(savedItem);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        if (!itemRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        itemRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
