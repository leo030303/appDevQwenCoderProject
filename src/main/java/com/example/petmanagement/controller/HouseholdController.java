package com.example.petmanagement.controller;

import com.example.petmanagement.entity.Household;
import com.example.petmanagement.service.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/households")
public class HouseholdController {

    @Autowired
    private HouseholdService householdService;

    @PostMapping
    public ResponseEntity<Household> createHousehold(@RequestBody Household household) {
        return ResponseEntity.ok(householdService.createHousehold(household));
    }

    @GetMapping
    public ResponseEntity<List<Household>> getAllHouseholds() {
        return ResponseEntity.ok(householdService.getAllHouseholds());
    }

    @GetMapping("/{eircode}")
    public ResponseEntity<Household> getHouseholdById(@PathVariable String eircode) {
        return ResponseEntity.ok(householdService.getHouseholdById(eircode).get());
    }

    @PutMapping("/{eircode}")
    public ResponseEntity<Household> updateHousehold(@PathVariable String eircode, @RequestBody Household householdDetails) {
        return ResponseEntity.ok(householdService.updateHousehold(eircode, householdDetails));
    }

    @DeleteMapping("/{eircode}")
    public ResponseEntity<Void> deleteHousehold(@PathVariable String eircode) {
        householdService.deleteHouseholdById(eircode);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/no-pets")
    public ResponseEntity<List<Household>> getHouseholdsNoPets() {
        return ResponseEntity.ok(householdService.findHouseholdsWithNoPets());
    }

    @GetMapping("/owner-occupied")
    public ResponseEntity<List<Household>> getOwnerOccupiedHouseholds() {
        return ResponseEntity.ok(householdService.findOwnerOccupiedHouseholds());
    }

    @GetMapping("/statistics")
    public ResponseEntity<Object> getHouseholdStatistics() {
        return ResponseEntity.ok(householdService.getHouseholdStatistics());
    }
}
