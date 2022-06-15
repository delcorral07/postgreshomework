package com.dcgteam.postgreshomework.controller;

import com.dcgteam.postgreshomework.model.dto.HouseDTO;
import com.dcgteam.postgreshomework.services.services.HouseService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1.0/houses")
public class HouseController {

    private HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping({"","/"})
    public ResponseEntity<List<HouseDTO>> retrieveHouses() {
        return new ResponseEntity<>(houseService.retrieveAll(), HttpStatus.OK);
    }

    @GetMapping("/{houseId}")
    public ResponseEntity<HouseDTO> retrieveHouse(@PathVariable("houseId") String houseId) {
        HouseDTO tempHouse = houseService.retrieveHouseById(houseId);
        return tempHouse.getId().isEmpty() ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(tempHouse, HttpStatus.OK);
    }

    @GetMapping("/country/{countryName}")
    public List<HouseDTO> retrieveByCountryName(@PathVariable("countryName") String countryName) {
        return houseService.retrieveByCountryName(countryName);
    }

    @PostMapping({"","/"})
    public ResponseEntity<Void> persistHouse(@RequestBody HouseDTO houseDTO) {
        String savedHousePath = houseService.persistHouse(houseDTO);
        if(!savedHousePath.isEmpty()){
            HttpHeaders header = new HttpHeaders();
            header.add("Location", savedHousePath);
            return new ResponseEntity<>(header,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping({"/{houseId}"})
    public ResponseEntity<Void> updateHouse(@PathVariable("houseId") String houseId, @RequestBody HouseDTO houseDTO) {
        return houseService.updateHouse(houseId, houseDTO) == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{houseId}"})
    public ResponseEntity<Void> deleteHouse(@PathVariable("houseId") String houseId) {
        return switch (houseService.deleteHouse(houseId)) {
            case 404 -> new ResponseEntity<>(HttpStatus.NOT_FOUND);
            case 400 -> new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            case 409 -> new ResponseEntity<>(HttpStatus.CONFLICT);
            default -> new ResponseEntity<>(HttpStatus.NO_CONTENT);
        };
    }


}
