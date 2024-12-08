package com.example.petmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HouseholdDto(
        @NotBlank(message = "Eircode is required")
        String eircode,

        @NotNull(message = "Number of occupants is required")
        Integer numberOccupants,

        @NotNull(message = "Maximum number of occupants is required")
        Integer maxNumberOccupants,

        @NotBlank(message = "Address is required")
        String address
) {}
