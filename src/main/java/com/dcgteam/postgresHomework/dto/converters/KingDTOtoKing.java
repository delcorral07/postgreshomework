package com.dcgteam.postgresHomework.dto.converters;

import com.dcgteam.postgresHomework.dto.KingDTO;
import com.dcgteam.postgresHomework.model.King;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class KingDTOtoKing implements Converter<KingDTO, King> {

    @Override
    public King convert(KingDTO kingDTO) {
        King king = new King();
        king.setId(kingDTO.getId());
        king.setName(kingDTO.getName());
        king.setCountry(new CountryDTOtoCountry().convert(kingDTO.getCountry()));
        king.setHouse(new HouseDTOtoHouse().convert(kingDTO.getHouse()));
        king.setReign(kingDTO.getReign());
        return king;
    }

}
