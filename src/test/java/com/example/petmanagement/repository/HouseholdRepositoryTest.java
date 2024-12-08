package com.example.petmanagement.repository;

import com.example.petmanagement.entity.Household;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class HouseholdRepositoryTest {

    @Autowired
    private HouseholdRepository householdRepository;

    @BeforeEach
    void setUp() {
        Household household1 = new Household("E12345", 3, 3, true, null);
        Household household2 = new Household("E67890", 2, 4, true, null);
        Household household3 = new Household("E11111", 1, 1, false, null);
        Household household4 = new Household("E22222", 0, 2, false, null);

        householdRepository.save(household1);
        householdRepository.save(household2);
        householdRepository.save(household3);
        householdRepository.save(household4);
    }

    @Test
    @DisplayName("Test findByEircode: Should return household for existing eircode")
    void testFindByEircode_HouseholdExists() {
        Optional<Household> household = householdRepository.findByEircode("E12345");
        assertThat(household).isPresent();
        assertThat(household.get().getNumberOccupants()).isEqualTo(3);
    }

    @Test
    @DisplayName("Test findByEircode: Should return empty for non-existent eircode")
    void testFindByEircode_HouseholdDoesNotExist() {
        Optional<Household> household = householdRepository.findByEircode("E99999");
        assertThat(household).isEmpty();
    }

    @Test
    @DisplayName("Test findAllByPetsIsNull: Should return households with no pets")
    void testFindAllByPetsIsNull() {
        List<Household> households = householdRepository.findAllByPetsIsNull();
        assertThat(households).hasSize(4);
    }

    @Test
    @DisplayName("Test findAllByOwnerOccupiedTrue: Should return owner-occupied households")
    void testFindAllByOwnerOccupiedTrue() {
        List<Household> households = householdRepository.findAllByOwnerOccupiedTrue();
        assertThat(households).hasSize(2);
        assertThat(households).extracting(Household::getEircode).contains("E12345", "E67890");
    }

    @Test
    @DisplayName("Test countAllByNumberOccupantsEqualsMaxOccupants: Should count households with max occupants")
    void testCountAllByNumberOccupantsEqualsMaxOccupants() {
        long count = householdRepository.countAllByNumberOccupantsEqualsMaxOccupants();
        assertThat(count).isEqualTo(2);
    }

    @Test
    @DisplayName("Test countAllByPetsIsNull: Should count households with no pets")
    void testCountAllByPetsIsNull() {
        long count = householdRepository.countAllByPetsIsNull();
        assertThat(count).isEqualTo(4);
    }
}
