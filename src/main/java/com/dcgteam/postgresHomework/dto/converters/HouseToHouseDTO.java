package com.dcgteam.postgresHomework.dto.converters;

import com.dcgteam.postgresHomework.dto.HouseDTO;
import com.dcgteam.postgresHomework.model.House;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class HouseToHouseDTO implements Converter<House, HouseDTO> {

    @Override
    public HouseDTO convert(House house) {
        HouseDTO houseDTO = new HouseDTO();
        houseDTO.setId(house.getId());
        houseDTO.setName(house.getName());
        houseDTO.setCountry(new CountryToCountryDTO().convert(house.getCountry()));
        return houseDTO;
    }

}
