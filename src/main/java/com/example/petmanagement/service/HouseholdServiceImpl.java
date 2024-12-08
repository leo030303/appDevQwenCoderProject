package com.example.petmanagement.service;

import com.example.petmanagement.aop.LoggingAspect;
import com.example.petmanagement.entity.Household;
import com.example.petmanagement.repository.HouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseholdServiceImpl implements HouseholdService {

    @Autowired
    private HouseholdRepository householdRepository;

    @Override
    public Household createHousehold(Household household) {
        return householdRepository.save(household);
    }

    @Override
    public List<Household> getAllHouseholds() {
        return householdRepository.findAll();
    }

    @Override
    public Optional<Household> getHouseholdById(String eircode) {
        return householdRepository.findByEircode(eircode);
    }

    @Override
    public Household updateHousehold(String eircode, Household householdDetails) {
        Optional<Household> existingHousehold = householdRepository.findByEircode(eircode);
        if (existingHousehold.isPresent()) {
            existingHousehold.get().setNumberOccupants(householdDetails.getNumberOccupants());
            existingHousehold.get().setMaxOccupants(householdDetails.getMaxOccupants());
            existingHousehold.get().setOwnerOccupied(householdDetails.isOwnerOccupied());
            return householdRepository.save(existingHousehold.get());
        }
        return null;
    }

    @Override
    public void deleteHouseholdById(String eircode) {
        householdRepository.deleteById(eircode);
    }

    @Override
    public List<Household> findHouseholdsWithNoPets() {
        return householdRepository.findAllByPetsIsNull();
    }

    @Override
    public List<Household> findOwnerOccupiedHouseholds() {
        return householdRepository.findAllByOwnerOccupiedTrue();
    }

    @Override
    public Object[] getHouseholdStatistics() {
        long emptyHouses = householdRepository.countAllByPetsIsNull();
        long fullHouses = householdRepository.countAllByNumberOccupantsEqualsMaxOccupants();
        return new Object[]{emptyHouses, fullHouses};
    }
}
