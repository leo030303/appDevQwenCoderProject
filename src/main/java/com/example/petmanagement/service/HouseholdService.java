package com.example.petmanagement.service;

import com.example.petmanagement.entity.Household;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface HouseholdService {
    Household createHousehold(Household household);
    List<Household> getAllHouseholds();
    Optional<Household> getHouseholdById(String eircode);
    Household updateHousehold(String eircode, Household householdDetails);
    void deleteHouseholdById(String eircode);
    List<Household> findHouseholdsWithNoPets();
    List<Household> findOwnerOccupiedHouseholds();
    Object[] getHouseholdStatistics();
}
