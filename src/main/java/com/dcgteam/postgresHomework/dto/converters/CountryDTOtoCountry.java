package com.dcgteam.postgresHomework.dto.converters;

import com.dcgteam.postgresHomework.dto.CountryDTO;
import com.dcgteam.postgresHomework.model.Country;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CountryDTOtoCountry implements Converter<CountryDTO, Country> {

    @Override
    public Country convert(CountryDTO countryDTO) {
        Country country = new Country();
//        country.setId(countryDTO.getId());
        country.setName(countryDTO.getName());
        return country;
    }



}
