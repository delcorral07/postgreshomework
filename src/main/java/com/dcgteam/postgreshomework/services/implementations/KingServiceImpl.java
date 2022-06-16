package com.dcgteam.postgreshomework.services.implementations;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.afford;

import com.dcgteam.postgreshomework.controller.CountryController;
import com.dcgteam.postgreshomework.controller.HouseController;
import com.dcgteam.postgreshomework.controller.KingController;
import com.dcgteam.postgreshomework.model.dto.KingDTO;
import com.dcgteam.postgreshomework.model.King;
import com.dcgteam.postgreshomework.repositories.KingRepository;
import com.dcgteam.postgreshomework.services.services.KingService;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.Affordance;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mediatype.AffordanceModelFactory;
import org.springframework.hateoas.mediatype.Affordances;
import org.springframework.hateoas.server.core.SpringAffordanceBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class KingServiceImpl implements KingService {

    private KingRepository kingRepository;

    private ModelMapper modelMapper;

    private final int SUCCESS_CODE = 200;
    private final int BAD_REQUEST = 400;
    private final int NOT_FOUND = 404;

    public KingServiceImpl(KingRepository kingRepository) {
        this.kingRepository = kingRepository;
        this.modelMapper = new ModelMapper();

    }

    @Override
    public KingDTO retrieveKingById(String id) {
        KingDTO kingDTO = modelMapper.map(kingRepository.findById(id)
                .orElse(new King()), KingDTO.class);
        if(!kingDTO.getId().equals("")) {
            addLinksToKingDTO(kingDTO);
        }
        return kingDTO;
    }

    public void addLinksToKingDTO(KingDTO kingDTO){
        kingDTO.add(linkTo(methodOn(KingController.class).retrieveKings()).withRel("kings"));
        kingDTO.add(linkTo(methodOn(KingController.class).updateKing(kingDTO.getId(),kingDTO)).withRel("update"));
        kingDTO.add(linkTo(methodOn(KingController.class).deleteKing(kingDTO.getId())).withRel("delete").withType("delete"));
    }

    @Override
    public List<KingDTO> retrieveKings() {
        List<KingDTO> kingList = new ArrayList<>();
        kingRepository.findAll().forEach(king -> {
            KingDTO kingDTO = modelMapper.map(king, KingDTO.class);
            addLinksToKingDTO(kingDTO);
            kingList.add(kingDTO);
        });
        return kingList;
    }

    @Override
    public String persistKing(KingDTO king) {
        king.setId(UUID.randomUUID().toString());
        King tempKing = modelMapper.map(king, King.class);
        King savedKing = kingRepository.save(tempKing);
        return tempKing.getId().equals(savedKing.getId()) ? tempKing.getId() : "";
    }

    @Override
    public int deleteKing(String id) {
        KingDTO tempKing = this.retrieveKingById(id);
        if (!tempKing.getId().isEmpty()) {
            kingRepository.deleteById(id);
            return SUCCESS_CODE;
        }
        return NOT_FOUND;
    }

    @Override
    public int updateKing(String id, KingDTO king) {
        int status = NOT_FOUND;
        KingDTO tempKing = this.retrieveKingById(id);
        if (!tempKing.getId().isEmpty()) {
            king.setId(id);
            King temp = modelMapper.map(king, King.class);
            tempKing = modelMapper.map(kingRepository.save(temp), KingDTO.class);
            status = tempKing.getId().equals(temp.getId()) ? SUCCESS_CODE : BAD_REQUEST;
        }
        return status;
    }

    @Override
    public KingDTO retrieveKingByName(String name) {
        King tempKing = kingRepository.findByName(name).orElse(new King());
        if(!tempKing.getName().isEmpty()) {
            return modelMapper.map(tempKing, KingDTO.class);
        }
        return new KingDTO();
    }

    @Override
    public List<KingDTO> retrieveKingsByNameLike(String name) {
        List<King> tempKings = kingRepository.findByNameContaining(name).orElse(new ArrayList<>());
        return !tempKings.isEmpty() ?
                tempKings.stream().map(king -> modelMapper.map(king,KingDTO.class)).collect(Collectors.toList())
                : new ArrayList<>();

    }

    @Override
    public List<KingDTO> retrieveKingByCountry(String country) {
        List<King> kingList = kingRepository.findByCountryName(country).orElse(new ArrayList<>());
        return kingList.stream().map(king -> modelMapper.map(king,KingDTO.class)).collect(Collectors.toList());
    }


}
