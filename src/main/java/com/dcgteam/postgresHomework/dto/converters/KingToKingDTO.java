package com.dcgteam.postgresHomework.dto.converters;

import com.dcgteam.postgresHomework.dto.KingDTO;
import com.dcgteam.postgresHomework.model.King;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class KingToKingDTO implements Converter<King, KingDTO> {

    @Override
    public KingDTO convert(King king) {
        KingDTO kingDTO = new KingDTO();
        kingDTO.setId(king.getId());
        kingDTO.setName(king.getName());
        kingDTO.setCountry(new CountryToCountryDTO().convert(king.getCountry()));
        kingDTO.setHouse(new HouseToHouseDTO().convert(king.getHouse()));
        kingDTO.setReign(king.getReign());
        return kingDTO;
    }
}
