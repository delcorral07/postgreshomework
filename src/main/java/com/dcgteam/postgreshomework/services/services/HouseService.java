package com.dcgteam.postgreshomework.services.services;

import com.dcgteam.postgreshomework.model.dto.HouseDTO;
import com.dcgteam.postgreshomework.model.House;

import java.util.List;

public interface HouseService {

    List<HouseDTO> retrieveAll();

    HouseDTO retrieveHouseById(String id);

    List<HouseDTO> retrieveByCountryName(String countryName);

    String persistHouse(HouseDTO houseDTO);

    int deleteHouse(String id);

    House updateHouse(String id, HouseDTO house);





}
