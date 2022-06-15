package com.dcgteam.postgresHomework.controller;

import com.dcgteam.postgresHomework.dto.KingDTO;
import com.dcgteam.postgresHomework.model.King;
import com.dcgteam.postgresHomework.services.services.KingService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/kings")
@Log
public class KingController {

    private KingService kingService;

    @Autowired
    public KingController(KingService kingService) {
        this.kingService = kingService;
    }


    @GetMapping({"","/"})
    public ResponseEntity<List<KingDTO>> retrieveKings() {
       return new ResponseEntity<>(kingService.retrieveKings(), HttpStatus.OK);
    }

    @GetMapping("/{kingId}")
    public ResponseEntity<KingDTO> retrieveKing(@PathVariable("kingId") String kingId) {
        KingDTO tempKing = kingService.retrieveKingById(kingId);
        if(tempKing.getId().equals("")) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(tempKing, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Void> persistKing(@RequestBody KingDTO king) {
        String kingId = kingService.persistKing(king);
        if(!kingId.isEmpty()){
            HttpHeaders header = new HttpHeaders();
            header.add("Location", kingId);
            return new ResponseEntity<>(header,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{kingId}")
    public ResponseEntity<King> updateKing(@PathVariable("kingId") String kingId, @RequestBody KingDTO king) {
        return switch (kingService.updateKing(kingId, king)) {
            case 404 -> new ResponseEntity<>(HttpStatus.NOT_FOUND);
            case 400 -> new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            default -> new ResponseEntity<>(HttpStatus.OK);
        };
    }

    @DeleteMapping("/{kingId}")
    public ResponseEntity<Void> deleteKing(@PathVariable("kingId") String kingId) {
        return switch (kingService.deleteKing(kingId)) {
            case 404 -> new ResponseEntity<>(HttpStatus.NOT_FOUND);
            case 400 -> new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            default -> new ResponseEntity<>(HttpStatus.OK);
        };
    }

    @GetMapping({"/name/{kingName}"})
    public ResponseEntity<KingDTO> retrieveKingsByName(@PathVariable("kingName") String kingName) {
        KingDTO tempKing = kingService.retrieveKingByName(kingName);
        if(tempKing.getId().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(tempKing, HttpStatus.OK);
        }
    }

    @GetMapping({"/nameC/{nameContaining}"})
    public ResponseEntity<List<KingDTO>> retrieveKingsByNameContaining(@PathVariable("nameContaining") String nameContaining) {
        return new ResponseEntity<>(kingService.retrieveKingsByNameLike(nameContaining), HttpStatus.OK);
    }

    @GetMapping({"/country/{countryName}"})
    public ResponseEntity<List<KingDTO>> retrieveKingsByCountryLike(@PathVariable("countryName") String countryName) {
        List<KingDTO> kingDTOList = kingService.retrieveKingByCountry(countryName);
        return kingDTOList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(kingDTOList, HttpStatus.OK);
    }


}
