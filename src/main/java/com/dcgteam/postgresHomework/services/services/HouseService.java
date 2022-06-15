package com.dcgteam.postgresHomework.services.services;

import com.dcgteam.postgresHomework.dto.HouseDTO;
import com.dcgteam.postgresHomework.model.House;

import java.util.List;

public interface HouseService {

    List<HouseDTO> retrieveAll();

    HouseDTO retrieveHouseById(String id);

    List<HouseDTO> retrieveByCountryName(String countryName);

    String persistHouse(HouseDTO houseDTO);

    int deleteHouse(String id);

    int updateHouse(String id, HouseDTO house);





}
