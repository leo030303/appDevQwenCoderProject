package com.example.petmanagement.dto;

import com.example.petmanagement.entity.Pet;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PetDto(
        @NotNull(message = "ID cannot be null")
        Long id,

        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotNull(message = "Age cannot be null")
        Integer age,

        @NotBlank(message = "Animal type cannot be blank")
        String animalType,

        @NotBlank(message = "Breed cannot be blank")
        String breed
) {
        public Pet convertToEntity(PetDto petDto) {
                return new Pet(
                        petDto.name(),
                        petDto.animalType(),
                        petDto.breed(),
                        petDto.age(),
                        null
                );
        }
}
