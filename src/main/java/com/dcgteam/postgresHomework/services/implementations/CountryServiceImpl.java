package com.dcgteam.postgresHomework.services.implementations;

import com.dcgteam.postgresHomework.dto.CountryDTO;
import com.dcgteam.postgresHomework.dto.converters.CountryDTOtoCountry;
import com.dcgteam.postgresHomework.dto.converters.CountryToCountryDTO;
import com.dcgteam.postgresHomework.model.Country;
import com.dcgteam.postgresHomework.repositories.CountryRepository;
import com.dcgteam.postgresHomework.services.services.CountryService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    private CountryToCountryDTO countryToCountryDTO;
    private CountryRepository countryRepository;
    private CountryDTOtoCountry countryDTOtoCountry;

    private final int SUCCESS_CODE = 200;
    private final int BAD_REQUEST = 400;
    private final int NOT_FOUND = 404;
    private final int CONSTRAINT_VIOLATION = 409;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, CountryDTOtoCountry countryDTOtoCountry,
                              CountryToCountryDTO countryToCountryDTO) {
        this.countryRepository = countryRepository;
        this.countryDTOtoCountry = countryDTOtoCountry;
        this.countryToCountryDTO = countryToCountryDTO;
    }

    @Override
    public List<CountryDTO> retrieveAll() {
        return countryRepository.findAll().stream()
                .map(country -> countryToCountryDTO.convert(country)).collect(Collectors.toList());
    }

    @Override
    public CountryDTO retrieveCountryById(String id) {
        Country tempCountry = countryRepository.findById(id).orElse(new Country());
        return tempCountry.getId().isEmpty() ? new CountryDTO() : countryToCountryDTO.convert(tempCountry);
    }

    @Override
    public CountryDTO retrieveByCountryName(String name) {
        Country tempCountry = countryRepository.findByName(name).orElse(new Country());
        return tempCountry.getId().isEmpty() ? new CountryDTO() : countryToCountryDTO.convert(tempCountry);
    }

    @Override
    public String persistCountry(CountryDTO countryDTO) {
        countryDTO.setId(UUID.randomUUID().toString());
        Country country = countryDTOtoCountry.convert(countryDTO);
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
            Country countryToUpdate = countryDTOtoCountry.convert(country);
            countryToUpdate.setId(id);
            tempCountry = countryToCountryDTO.convert(countryRepository.save(countryToUpdate));
            status = tempCountry.getId().equals(country.getId()) ? SUCCESS_CODE : BAD_REQUEST;
        }
        return status;
    }


}
