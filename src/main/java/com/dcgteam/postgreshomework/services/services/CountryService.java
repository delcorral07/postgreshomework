package com.dcgteam.postgreshomework.services.services;

import com.dcgteam.postgreshomework.model.dto.CountryDTO;

import java.util.List;

public interface CountryService {


    List<CountryDTO> retrieveAll();

    CountryDTO retrieveCountryById(String id);

    CountryDTO retrieveByCountryName(String name);
    String persistCountry(CountryDTO countryDTO);

    int deleteCountry(String id);

    int updateCountry(String id, CountryDTO country);

}
