package com.dcgteam.postgresHomework.services.implementations;

import com.dcgteam.postgresHomework.dto.CountryDTO;
import com.dcgteam.postgresHomework.dto.HouseDTO;
import com.dcgteam.postgresHomework.dto.converters.HouseDTOtoHouse;
import com.dcgteam.postgresHomework.dto.converters.HouseToHouseDTO;
import com.dcgteam.postgresHomework.model.Country;
import com.dcgteam.postgresHomework.model.House;
import com.dcgteam.postgresHomework.repositories.HouseRepository;
import com.dcgteam.postgresHomework.services.services.HouseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HouseServiceImpl implements HouseService {

    private HouseRepository houseRepository;

    private HouseToHouseDTO houseToHouseDTO;

    private HouseDTOtoHouse houseDTOToHouse;

    private final int SUCCESS_CODE = 200;
    private final int BAD_REQUEST = 400;
    private final int NOT_FOUND = 404;

    public HouseServiceImpl(HouseRepository houseRepository, HouseToHouseDTO houseToHouseDTO,
                            HouseDTOtoHouse houseDTOToHouse) {
        this.houseRepository = houseRepository;
        this.houseToHouseDTO = houseToHouseDTO;
        this.houseDTOToHouse = houseDTOToHouse;
    }

    @Override
    public List<HouseDTO> retrieveAll() {
        return houseRepository.findAll().stream()
                .map(house -> houseToHouseDTO.convert(house)).collect(Collectors.toList());
    }

    @Override
    public HouseDTO retrieveHouseById(String id) {
        House tempHouse = houseRepository.findById(id).orElse(new House());
        return tempHouse.getId().isEmpty() ? new HouseDTO() : houseToHouseDTO.convert(tempHouse);
    }

    @Override
    public List<HouseDTO> retrieveByCountryName(String countryName) {
        List<House> houseList = houseRepository.retrieveHouseByCountryName(countryName).orElse(new ArrayList<>());
        return houseList.isEmpty() ?
                new ArrayList<>()
                : houseList.stream().map(house -> houseToHouseDTO.convert(house)).collect(Collectors.toList());

    }

    @Override
    public String persistHouse(HouseDTO houseDTO) {
        houseDTO.setId(UUID.randomUUID().toString());
        House house = houseDTOToHouse.convert(houseDTO);
        House tempHouse = houseRepository.save(house);
        return house.getId().equals(tempHouse.getId()) ? house.getId() : "";
    }

    @Override
    public int deleteHouse(String id) {
        HouseDTO tempHouse = this.retrieveHouseById(id);
        if(!tempHouse.getId().isEmpty()){
            houseRepository.deleteById(id);
            return SUCCESS_CODE;
        }
        return NOT_FOUND;
    }

    @Override
    public int updateHouse(String id, HouseDTO house) {
        int status = NOT_FOUND;
        HouseDTO tempHouse = this.retrieveHouseById(id);
        if(!tempHouse.getId().isEmpty()){
            House houseToUpdate = houseDTOToHouse.convert(house);
            houseToUpdate.setId(id);
            tempHouse = houseToHouseDTO.convert(houseRepository.save(houseToUpdate));
            status = tempHouse.getId().equals(house.getId()) ? SUCCESS_CODE : BAD_REQUEST;
        }
        return status;
    }
}
