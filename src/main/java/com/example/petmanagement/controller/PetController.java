package com.example.petmanagement.controller;

import com.example.petmanagement.dto.PetDto;
import com.example.petmanagement.entity.Pet;
import com.example.petmanagement.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    public ResponseEntity<List<PetDto>> getAllPets() {
        return ResponseEntity.ok(petService.getAllPets().stream().map(pet -> pet.convertToDto(pet)).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PetDto>> getPetById(@PathVariable Long id) {
        return ResponseEntity.ok(petService.getPetById(id).map(pet -> pet.convertToDto(pet)));
    }

    @PostMapping
    public ResponseEntity<PetDto> createPet(@Valid @RequestBody PetDto petDto) {
        var pet = petService.createPet(petDto.convertToEntity(petDto));
        return ResponseEntity.ok(pet.convertToDto(pet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePetById(@PathVariable Long id) {
        petService.deletePetById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/name")
    public ResponseEntity<PetDto> updatePetName(@PathVariable Long id, @RequestBody String name) {
        var pet = petService.getPetById(id).get();
        var newPet = petService.updatePet(id, new Pet(id, name, pet.getAge(), pet.getAnimalType(), pet.getBreed(), pet.getHousehold()));
        return ResponseEntity.ok(newPet.convertToDto(newPet));
    }
}

