package com.dcgteam.postgresHomework.services.services;

import com.dcgteam.postgresHomework.dto.CountryDTO;
import com.dcgteam.postgresHomework.model.Country;

import java.util.List;

public interface CountryService {


    List<CountryDTO> retrieveAll();

    CountryDTO retrieveCountryById(String id);

    CountryDTO retrieveByCountryName(String name);
    String persistCountry(CountryDTO countryDTO);

    int deleteCountry(String id);

    int updateCountry(String id, CountryDTO country);

}
