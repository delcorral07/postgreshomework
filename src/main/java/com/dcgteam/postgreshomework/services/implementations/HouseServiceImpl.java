package com.dcgteam.postgreshomework.services.implementations;

import com.dcgteam.postgreshomework.model.dto.HouseDTO;
import com.dcgteam.postgreshomework.model.House;
import com.dcgteam.postgreshomework.repositories.HouseRepository;
import com.dcgteam.postgreshomework.services.services.HouseService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HouseServiceImpl implements HouseService {

    private HouseRepository houseRepository;

    private ModelMapper modelMapper;

    private final int SUCCESS_CODE = 200;
    private final int BAD_REQUEST = 400;
    private final int NOT_FOUND = 404;
    private final int CONSTRAINT_VIOLATION = 409;

    public HouseServiceImpl(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<HouseDTO> retrieveAll() {
        return houseRepository.findAll().stream()
                .map(house -> modelMapper.map(house,HouseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public HouseDTO retrieveHouseById(String id) {
        return modelMapper.map(houseRepository.findById(id).orElse(new House()), HouseDTO.class);
    }

    @Override
    public List<HouseDTO> retrieveByCountryName(String countryName) {
        List<House> houseList = houseRepository.retrieveHouseByCountryName(countryName).orElse(new ArrayList<>());
        return houseList.isEmpty() ?
                new ArrayList<>()
                : houseList.stream().map(house -> modelMapper.map(house,HouseDTO.class)).collect(Collectors.toList());

    }

    @Override
    public String persistHouse(HouseDTO houseDTO) {
        houseDTO.setId(UUID.randomUUID().toString());
        House house = modelMapper.map(houseDTO, House.class);
        House tempHouse = houseRepository.save(house);
        return house.getId().equals(tempHouse.getId()) ? house.getId() : "";
    }

    @Override
    public int deleteHouse(String id) {
        HouseDTO tempHouse = this.retrieveHouseById(id);
        if(!tempHouse.getId().isEmpty()){
            try{
                houseRepository.deleteById(id);
            }catch (DataIntegrityViolationException e){
                return CONSTRAINT_VIOLATION;
            }
            return SUCCESS_CODE;
        }
        return NOT_FOUND;
    }

    @Override
    public House updateHouse(String id, HouseDTO house) {
        if(houseRepository.findById(id).isPresent()){
            try{
                return houseRepository.save(modelMapper.map(house, House.class));
            }catch (IllegalArgumentException e){
                return null;
            }
        }else{
            return null;
        }
    }
}
