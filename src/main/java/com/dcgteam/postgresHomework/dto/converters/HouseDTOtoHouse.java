package com.dcgteam.postgresHomework.dto.converters;

import com.dcgteam.postgresHomework.dto.HouseDTO;
import com.dcgteam.postgresHomework.model.House;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class HouseDTOtoHouse implements Converter<HouseDTO, House> {

    @Override
    public House convert(HouseDTO houseDTO) {
        House house = new House();
        house.setId(houseDTO.getId());
        house.setName(houseDTO.getName());
        house.setCountry(new CountryDTOtoCountry().convert(houseDTO.getCountry()));
        return house;
    }


}

