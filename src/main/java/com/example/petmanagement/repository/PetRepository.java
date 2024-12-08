// PetRepository.java
package com.example.petmanagement.repository;

import com.example.petmanagement.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    Optional<Pet> findById(Long id);

    void deleteById(Long id);

    void deleteAllByNameIgnoreCase(String name);

    List<Pet> findByAnimalType(String animalType);

    List<Pet> findByBreedOrderByAge(String breed);

    @Query("SELECT p.name, p.animalType, p.breed FROM Pet p")
    List<Object[]> findNameAndBreed();

    @Query("SELECT AVG(p.age), MAX(p.age) FROM Pet p")
    Object[] getAverageAgeAndMaxAge();
}
