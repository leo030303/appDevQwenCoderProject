package com.example.petmanagement.service;

import com.example.petmanagement.entity.Pet;
import com.example.petmanagement.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetServiceImpl petService;

    private Pet pet1;
    private Pet pet2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pet1 = new Pet("Bella", "Dog", "Labrador", 3, null);
        pet2 = new Pet("Milo", "Cat", "Siamese", 2, null);
    }

    @Test
    @DisplayName("Test createPet: Should save and return the created pet")
    void testCreatePet() {
        when(petRepository.save(pet1)).thenReturn(pet1);

        Pet createdPet = petService.createPet(pet1);

        assertThat(createdPet).isNotNull();
        assertThat(createdPet.getName()).isEqualTo("Bella");
        verify(petRepository, times(1)).save(pet1);
    }

    @Test
    @DisplayName("Test getAllPets: Should return a list of all pets")
    void testGetAllPets() {
        when(petRepository.findAll()).thenReturn(Arrays.asList(pet1, pet2));

        List<Pet> pets = petService.getAllPets();

        assertThat(pets).hasSize(2);
        verify(petRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test getPetById: Should return a pet if found")
    void testGetPetById_Found() {
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet1));

        Optional<Pet> foundPet = petService.getPetById(1L);

        assertThat(foundPet).isPresent();
        assertThat(foundPet.get().getName()).isEqualTo("Bella");
        verify(petRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Test updatePet: Should update and return the pet")
    void testUpdatePet() {
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet1));
        when(petRepository.save(pet1)).thenReturn(pet1);

        pet1.setAge(4);

        Pet updatedPet = petService.updatePet(1L, pet1);

        assertThat(updatedPet).isNotNull();
        assertThat(updatedPet.getAge()).isEqualTo(4);
        verify(petRepository, times(1)).save(pet1);
    }

    @Test
    @DisplayName("Test deletePetById: Should delete a pet by ID")
    void testDeletePetById() {
        doNothing().when(petRepository).deleteById(1L);

        petService.deletePetById(1L);

        verify(petRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Test deletePetsByName: Should delete pets by name")
    void testDeletePetsByName() {
        doNothing().when(petRepository).deleteAllByNameIgnoreCase("Bella");

        petService.deletePetsByName("Bella");

        verify(petRepository, times(1)).deleteAllByNameIgnoreCase("Bella");
    }

    @Test
    @DisplayName("Test findPetsByAnimalType: Should return pets by animal type")
    void testFindPetsByAnimalType() {
        when(petRepository.findByAnimalType("Dog")).thenReturn(List.of(pet1));

        List<Pet> pets = petService.findPetsByAnimalType("Dog");

        assertThat(pets).hasSize(1);
        assertThat(pets.get(0).getName()).isEqualTo("Bella");
        verify(petRepository, times(1)).findByAnimalType("Dog");
    }

    @Test
    @DisplayName("Test findPetsByBreed: Should return pets by breed")
    void testFindPetsByBreed() {
        when(petRepository.findByBreedOrderByAge("Labrador")).thenReturn(List.of(pet1));

        List<Pet> pets = petService.findPetsByBreed("Labrador");

        assertThat(pets).hasSize(1);
        assertThat(pets.get(0).getName()).isEqualTo("Bella");
        verify(petRepository, times(1)).findByBreedOrderByAge("Labrador");
    }

    @Test
    @DisplayName("Test getNameAndBreed: Should return names and breeds of all pets")
    void testGetNameAndBreed() {
        when(petRepository.findNameAndBreed()).thenReturn((List<Object[]>) List.of(new Object[] {"Bella", "Dog", "Labrador"}));

        List<Object[]> result = petService.getNameAndBreed();

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).contains("Bella", "Dog", "Labrador");
        verify(petRepository, times(1)).findNameAndBreed();
    }

    @Test
    @DisplayName("Test getPetStatistics: Should return average and max age of pets")
    void testGetPetStatistics() {
        when(petRepository.getAverageAgeAndMaxAge()).thenReturn(new Object[] {2.5, 4});

        Object[] stats = petService.getPetStatistics();

        assertThat(stats).isNotNull();
        assertThat(stats[0]).isEqualTo(2.5);
        assertThat(stats[1]).isEqualTo(4);
        verify(petRepository, times(1)).getAverageAgeAndMaxAge();
    }
}
