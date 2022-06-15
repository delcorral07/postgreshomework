package com.dcgteam.postgresHomework.dto.converters;

import com.dcgteam.postgresHomework.dto.CountryDTO;
import com.dcgteam.postgresHomework.model.Country;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CountryToCountryDTO implements Converter<Country, CountryDTO> {

    @Override
    public CountryDTO convert(Country country) {
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setId(country.getId());
        countryDTO.setName(country.getName());
        return countryDTO;
    }
}
