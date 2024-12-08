package com.example.petmanagement.repository;

import com.example.petmanagement.entity.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PetRepositoryTest {

    @Autowired
    private PetRepository petRepository;

    @BeforeEach
    void setUp() {
        Pet pet1 = new Pet("Bella", "Dog", "Labrador", 3, null);
        Pet pet2 = new Pet("Milo", "Cat", "Siamese", 2, null);
        Pet pet3 = new Pet("Max", "Dog", "Beagle", 4, null);
        Pet pet4 = new Pet("Bella", "Cat", "Siamese", 1, null);

        petRepository.save(pet1);
        petRepository.save(pet2);
        petRepository.save(pet3);
        petRepository.save(pet4);
    }

    @Test
    @DisplayName("Test findById: Should return a pet when the ID exists")
    void testFindById_PetExists() {
        Optional<Pet> pet = petRepository.findById(1L);
        assertThat(pet).isPresent();
        assertThat(pet.get().getName()).isEqualTo("Bella");
    }

    @Test
    @DisplayName("Test findById: Should return empty when the ID does not exist")
    void testFindById_PetDoesNotExist() {
        Optional<Pet> pet = petRepository.findById(999L);
        assertThat(pet).isEmpty();
    }

    @Test
    @DisplayName("Test deleteById: Should delete a pet by ID")
    void testDeleteById() {
        petRepository.deleteById(1L);
        Optional<Pet> pet = petRepository.findById(1L);
        assertThat(pet).isEmpty();
    }

    @Test
    @DisplayName("Test deleteAllByNameIgnoreCase: Should delete all pets with the given name (case-insensitive)")
    void testDeleteAllByNameIgnoreCase() {
        petRepository.deleteAllByNameIgnoreCase("bella");
        List<Pet> pets = petRepository.findByAnimalType("Dog");
        assertThat(pets).doesNotContainAnyElementsOf(petRepository.findAll());
    }

    @Test
    @DisplayName("Test findByAnimalType: Should return pets of a specific animal type")
    void testFindByAnimalType() {
        List<Pet> pets = petRepository.findByAnimalType("Cat");
        assertThat(pets).hasSize(2);
        assertThat(pets).extracting(Pet::getName).contains("Milo", "Bella");
    }

    @Test
    @DisplayName("Test findByBreedOrderByAge: Should return pets of a specific breed ordered by age")
    void testFindByBreedOrderByAge() {
        List<Pet> pets = petRepository.findByBreedOrderByAge("Siamese");
        assertThat(pets).hasSize(2);
        assertThat(pets.get(0).getAge()).isLessThan(pets.get(1).getAge());
    }

    @Test
    @DisplayName("Test findNameAndBreed: Should return name, animalType, and breed for all pets")
    void testFindNameAndBreed() {
        List<Object[]> results = petRepository.findNameAndBreed();
        assertThat(results).hasSize(4);
        assertThat(results.get(0)).containsExactly("Bella", "Dog", "Labrador");
    }

    @Test
    @DisplayName("Test getAverageAgeAndMaxAge: Should return average and max age of pets")
    void testGetAverageAgeAndMaxAge() {
        Object[] result = petRepository.getAverageAgeAndMaxAge();
        Double avgAge = (Double) result[0];
        Integer maxAge = (Integer) result[1];

        assertThat(avgAge).isEqualTo((3 + 2 + 4 + 1) / 4.0);
        assertThat(maxAge).isEqualTo(4);
    }
}
