package com.example.hermes.api_hermes_store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.hermes.api_hermes_store.model.Character;
import com.example.hermes.api_hermes_store.repository.CharacterReposiory;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/character")
@Slf4j
@Cacheable(value = "characters") 
public class CharacterController {

    @Autowired
    private CharacterReposiory characterRepository;

    
    @GetMapping
    @Cacheable
    @Operation(summary = "Listar todos os personagens", description = "Lista todos os personagens salvos no banco", tags = "Character")
    public List<Character> getAllCharacters() {
        log.info("Listando todos os personagens");
        return characterRepository.findAll();
    }

    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar personagem por ID", description = "Busca um personagem pelo ID", tags = "Character")
    public Character getCharacterById(@PathVariable Long id) {
        log.info("Buscando personagem com id: " + id);
        return getCharacter(id);
    }

   
    @PostMapping
    @CacheEvict(allEntries = true) 
    @ResponseStatus(HttpStatus.CREATED) 
    @Operation(responses = @ApiResponse(responseCode = "400"))
    public Character createCharacter(@RequestBody @Valid Character character) {
        log.info("Criando novo personagem: " + character.getNameCharacter());
        return characterRepository.save(character);
    }

 
    @PutMapping("/{id}")
    @CacheEvict(allEntries = true)
    @Operation(summary = "Atualizar personagem", description = "Atualiza um personagem existente", tags = "Character")
    public Character updateCharacter(@PathVariable Long id, @RequestBody @Valid Character character) {
        log.info("Atualizando personagem com id: " + id);
        getCharacter(id);
        character.setId(id);
        return characterRepository.save(character);
    }

  
    @DeleteMapping("/{id}")
    @CacheEvict(allEntries = true) 
    @ResponseStatus(HttpStatus.NO_CONTENT) 
    public void deleteCharacter(@PathVariable Long id) {
        log.info("Deletando personagem com id: " + id);
        characterRepository.delete(getCharacter(id));
    }

    
    private Character getCharacter(Long id) {
        return characterRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personagem não encontrado com id: " + id));
    }
}
