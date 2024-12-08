package com.example.petmanagement.repository;

import com.example.petmanagement.entity.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HouseholdRepository extends JpaRepository<Household, String> {
    Optional<Household> findByEircode(String eircode);
    List<Household> findAllByPetsIsNull();
    List<Household> findAllByOwnerOccupiedTrue();
    long countAllByNumberOccupantsEqualsMaxOccupants();
    long countAllByPetsIsNull();
}
