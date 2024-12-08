package com.example.petmanagement.service;

import com.example.petmanagement.entity.Household;
import com.example.petmanagement.repository.HouseholdRepository;
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

class HouseholdServiceTest {

    @Mock
    private HouseholdRepository householdRepository;

    @InjectMocks
    private HouseholdServiceImpl householdService;

    private Household household1;
    private Household household2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        household1 = new Household("E12345", 3, 3, true, null);
        household2 = new Household("E67890", 2, 4, false, null);
    }

    @Test
    @DisplayName("Test createHousehold: Should save and return the created household")
    void testCreateHousehold() {
        when(householdRepository.save(household1)).thenReturn(household1);

        Household createdHousehold = householdService.createHousehold(household1);

        assertThat(createdHousehold).isNotNull();
        assertThat(createdHousehold.getEircode()).isEqualTo("E12345");
        verify(householdRepository, times(1)).save(household1);
    }

    @Test
    @DisplayName("Test getAllHouseholds: Should return a list of all households")
    void testGetAllHouseholds() {
        when(householdRepository.findAll()).thenReturn(Arrays.asList(household1, household2));

        List<Household> households = householdService.getAllHouseholds();

        assertThat(households).hasSize(2);
        verify(householdRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test getHouseholdById: Should return a household if found")
    void testGetHouseholdById_Found() {
        when(householdRepository.findById("E12345")).thenReturn(Optional.of(household1));

        Optional<Household> foundHousehold = householdService.getHouseholdById("E12345");

        assertThat(foundHousehold).isPresent();
        assertThat(foundHousehold.get().getEircode()).isEqualTo("E12345");
        verify(householdRepository, times(1)).findById("E12345");
    }

    @Test
    @DisplayName("Test getHouseholdById: Should return empty if not found")
    void testGetHouseholdById_NotFound() {
        when(householdRepository.findById("E99999")).thenReturn(Optional.empty());

        Optional<Household> foundHousehold = householdService.getHouseholdById("E99999");

        assertThat(foundHousehold).isEmpty();
        verify(householdRepository, times(1)).findById("E99999");
    }

    @Test
    @DisplayName("Test updateHousehold: Should update and return the household")
    void testUpdateHousehold() {
        when(householdRepository.findById("E12345")).thenReturn(Optional.of(household1));
        when(householdRepository.save(household1)).thenReturn(household1);

        household1.setNumberOccupants(4);

        Household updatedHousehold = householdService.updateHousehold("E12345", household1);

        assertThat(updatedHousehold).isNotNull();
        assertThat(updatedHousehold.getNumberOccupants()).isEqualTo(4);
        verify(householdRepository, times(1)).save(household1);
    }

    @Test
    @DisplayName("Test deleteHouseholdById: Should delete a household by eircode")
    void testDeleteHouseholdById() {
        doNothing().when(householdRepository).deleteById("E12345");

        householdService.deleteHouseholdById("E12345");

        verify(householdRepository, times(1)).deleteById("E12345");
    }

    @Test
    @DisplayName("Test findHouseholdsWithNoPets: Should return households with no pets")
    void testFindHouseholdsWithNoPets() {
        when(householdRepository.findAllByPetsIsNull()).thenReturn(Arrays.asList(household1, household2));

        List<Household> households = householdService.findHouseholdsWithNoPets();

        assertThat(households).hasSize(2);
        verify(householdRepository, times(1)).findAllByPetsIsNull();
    }

    @Test
    @DisplayName("Test findOwnerOccupiedHouseholds: Should return owner-occupied households")
    void testFindOwnerOccupiedHouseholds() {
        when(householdRepository.findAllByOwnerOccupiedTrue()).thenReturn(List.of(household1));

        List<Household> households = householdService.findOwnerOccupiedHouseholds();

        assertThat(households).hasSize(1);
        assertThat(households.get(0).getEircode()).isEqualTo("E12345");
        verify(householdRepository, times(1)).findAllByOwnerOccupiedTrue();
    }
}
