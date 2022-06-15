package com.dcgteam.postgreshomework.services.implementations;

import com.dcgteam.postgreshomework.model.dto.CountryDTO;
import com.dcgteam.postgreshomework.model.Country;
import com.dcgteam.postgreshomework.repositories.CountryRepository;
import com.dcgteam.postgreshomework.services.services.CountryService;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Log
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;
    private ModelMapper modelMapper;

    private final int SUCCESS_CODE = 200;
    private final int BAD_REQUEST = 400;
    private final int NOT_FOUND = 404;
    private final int CONSTRAINT_VIOLATION = 409;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
        modelMapper = new ModelMapper();
    }

    @Override
    public List<CountryDTO> retrieveAll() {
        return countryRepository.findAll().stream()
                .map(country -> modelMapper.map(country,CountryDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CountryDTO retrieveCountryById(String id) {
        return modelMapper.map(countryRepository.findById(id).orElse(new Country()),CountryDTO.class);
    }

    @Override
    public CountryDTO retrieveByCountryName(String name) {
        return modelMapper.map(countryRepository.findByName(name).orElse(new Country()), CountryDTO.class);
    }

    @Override
    public String persistCountry(CountryDTO countryDTO) {
        countryDTO.setId(UUID.randomUUID().toString());
        Country country = modelMapper.map(countryDTO, Country.class);
        Country tempCountry = countryRepository.save(country);
        return country.getId().equals(tempCountry.getId()) ? country.getId() : "";
    }

    @Override
    public int deleteCountry(String id) {
        CountryDTO tempCountry = this.retrieveCountryById(id);
        if(!tempCountry.getId().isEmpty()){
            try{
                countryRepository.deleteById(id);
            }catch (DataIntegrityViolationException e){
                e.printStackTrace();
                return CONSTRAINT_VIOLATION;
            }
            return SUCCESS_CODE;
        }
        return NOT_FOUND;
    }

    @Override
    public int updateCountry(String id, CountryDTO country) {
        int status = NOT_FOUND;
        CountryDTO tempCountry = this.retrieveCountryById(id);
        if(!tempCountry.getId().isEmpty()){
            Country countryToUpdate = modelMapper.map(country, Country.class);
            countryToUpdate.setId(id);
            tempCountry = modelMapper.map(countryRepository.save(countryToUpdate), CountryDTO.class);
            status = tempCountry.getId().equals(country.getId()) ? SUCCESS_CODE : BAD_REQUEST;
        }
        return status;
    }


}
