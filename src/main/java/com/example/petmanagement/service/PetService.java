// PetService.java
package com.example.petmanagement.service;

import com.example.petmanagement.entity.Pet;
import com.example.petmanagement.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PetService {
    Pet createPet(Pet pet);
    List<Pet> getAllPets();
    Optional<Pet> getPetById(Long id);
    Pet updatePet(Long id, Pet petDetails);
    void deletePetById(Long id);
    void deletePetsByName(String name);
    List<Pet> findPetsByAnimalType(String animalType);
    List<Pet> findPetsByBreed(String breed);
    List<Object[]> getNameAndBreed();
    Object[] getPetStatistics();
}
