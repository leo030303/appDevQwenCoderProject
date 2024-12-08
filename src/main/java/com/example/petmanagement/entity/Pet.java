package com.example.petmanagement.entity;

import com.example.petmanagement.dto.PetDto;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pets")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private String animalType;
    private String breed;

    @ManyToOne
    @JoinColumn(name = "eircode", nullable = false)
    private Household household;

    public Pet(String name, String animalType, String breed, int age, Household household) {
        this.name = name;
        this.animalType = animalType;
        this.breed = breed;
        this.age = age;
        this.household = household;
    }


    public PetDto convertToDto(Pet pet) {
        return new PetDto(
                pet.id,
                pet.name,
                pet.age,
                pet.animalType,
                pet.breed
        );
    }
}

