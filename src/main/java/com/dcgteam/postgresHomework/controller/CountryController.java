package com.dcgteam.postgresHomework.controller;

import com.dcgteam.postgresHomework.dto.CountryDTO;
import com.dcgteam.postgresHomework.model.Country;
import com.dcgteam.postgresHomework.services.services.CountryService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/countries")
public class CountryController {

    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping({"","/"})
    public ResponseEntity<List<CountryDTO>> retrieveCountries() {
        return new ResponseEntity<>(countryService.retrieveAll(), HttpStatus.OK);
    }

    @GetMapping("/{countryId}")
    public ResponseEntity<CountryDTO> retrieveCountry(@PathVariable("countryId") String countryId) {
        CountryDTO tempCountry = countryService.retrieveCountryById(countryId);
        return tempCountry.getId().isEmpty() ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(tempCountry, HttpStatus.OK);
    }

    @GetMapping("/name/{countryName}")
    public ResponseEntity<CountryDTO> retrieveByCountryName(@PathVariable("countryName") String countryName) {
        CountryDTO c = countryService.retrieveByCountryName(countryName);
        return c.getId().isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(c, HttpStatus.OK);
    }

    @PostMapping({"","/"})
    public ResponseEntity<Void> persistCountry(@RequestBody CountryDTO countryDTO) {
        String savedCountryPath = countryService.persistCountry(countryDTO);
        if(!savedCountryPath.isEmpty()){
            HttpHeaders header = new HttpHeaders();
            header.add("Location", savedCountryPath);
            return new ResponseEntity<>(header,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping({"/{countryId}"})
    public ResponseEntity<Void> updateCountry(@PathVariable("countryId") String countryId, @RequestBody CountryDTO countryDTO) {
        return switch (countryService.updateCountry(countryId, countryDTO)) {
            case 404 -> new ResponseEntity<>(HttpStatus.NOT_FOUND);
            case 400 -> new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            default -> new ResponseEntity<>(HttpStatus.NO_CONTENT);
        };
    }

    @DeleteMapping({"/{countryId}"})
    public ResponseEntity<Void> deleteCountry(@PathVariable("countryId") String countryId) {
        return switch (countryService.deleteCountry(countryId)) {
            case 404 -> new ResponseEntity<>(HttpStatus.NOT_FOUND);
            case 400 -> new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            case 409 -> new ResponseEntity<>(HttpStatus.CONFLICT);
            default -> new ResponseEntity<>(HttpStatus.NO_CONTENT);
        };
    }



}
