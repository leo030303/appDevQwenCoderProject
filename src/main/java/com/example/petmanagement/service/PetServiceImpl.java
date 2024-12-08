// PetServiceImpl.java
package com.example.petmanagement.service;

import com.example.petmanagement.entity.Pet;
import com.example.petmanagement.repository.PetRepository;
import com.example.petmanagement.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Override
    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Optional<Pet> getPetById(Long id) {
        return petRepository.findById(id);
    }

    @Override
    public Pet updatePet(Long id, Pet petDetails) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
        pet.setName(petDetails.getName());
        pet.setAge(petDetails.getAge());
        pet.setAnimalType(petDetails.getAnimalType());
        pet.setBreed(petDetails.getBreed());
        return petRepository.save(pet);
    }

    @Override
    public void deletePetById(Long id) {
        petRepository.deleteById(id);
    }

    @Override
    public void deletePetsByName(String name) {
        petRepository.deleteAllByNameIgnoreCase(name);
    }

    @Override
    public List<Pet> findPetsByAnimalType(String animalType) {
        return petRepository.findByAnimalType(animalType);
    }

    @Override
    public List<Pet> findPetsByBreed(String breed) {
        return petRepository.findByBreedOrderByAge(breed);
    }

    @Override
    public List<Object[]> getNameAndBreed() {
        return petRepository.findNameAndBreed();
    }

    @Override
    public Object[] getPetStatistics() {
        return petRepository.getAverageAgeAndMaxAge();
    }
}
