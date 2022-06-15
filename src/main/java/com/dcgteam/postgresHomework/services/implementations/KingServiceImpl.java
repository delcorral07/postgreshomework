package com.dcgteam.postgresHomework.services.implementations;

import com.dcgteam.postgresHomework.dto.KingDTO;
import com.dcgteam.postgresHomework.dto.converters.KingDTOtoKing;
import com.dcgteam.postgresHomework.dto.converters.KingToKingDTO;
import com.dcgteam.postgresHomework.model.King;
import com.dcgteam.postgresHomework.repositories.KingRepository;
import com.dcgteam.postgresHomework.services.services.KingService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class KingServiceImpl implements KingService {

    private KingRepository kingRepository;
    private KingDTOtoKing kingDTOtoKing;
    private KingToKingDTO kingToKingDTO;

    private final int SUCCESS_CODE = 200;
    private final int BAD_REQUEST = 400;
    private final int NOT_FOUND = 404;

    public KingServiceImpl(KingRepository kingRepository, KingDTOtoKing kingDTOtoKing, KingToKingDTO kingToKingDTO) {
        this.kingRepository = kingRepository;
        this.kingDTOtoKing = kingDTOtoKing;
        this.kingToKingDTO = kingToKingDTO;
    }

    @Override
    public KingDTO retrieveKingById(String id) {
        King tempKing = kingRepository.findById(id).orElse(new King());
        return tempKing.getId().isEmpty() ? new KingDTO() : kingToKingDTO.convert(tempKing);
    }

    @Override
    public List<KingDTO> retrieveKings() {
        List<KingDTO> kingList = new ArrayList<>();
        kingRepository.findAll().forEach(king -> kingList.add(kingToKingDTO.convert(king)));
        return kingList;
    }

    @Override
    public String persistKing(KingDTO king) {
        king.setId(UUID.randomUUID().toString());
        King tempKing = kingDTOtoKing.convert(king);
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
            King temp = kingDTOtoKing.convert(king);
            tempKing = kingToKingDTO.convert(kingRepository.save(temp));
            status = tempKing.getId().equals(temp.getId()) ? SUCCESS_CODE : BAD_REQUEST;
        }
        return status;
    }

    @Override
    public KingDTO retrieveKingByName(String name) {
        King tempKing = kingRepository.findByName(name).orElse(new King());
        if(!tempKing.getName().isEmpty()) {
            return kingToKingDTO.convert(tempKing);
        }
        return new KingDTO();
    }

    @Override
    public List<KingDTO> retrieveKingsByNameLike(String name) {
        List<King> tempKings = kingRepository.findByNameContaining(name).orElse(new ArrayList<>());
        return !tempKings.isEmpty() ?
                tempKings.stream().map(king -> kingToKingDTO.convert(king)).collect(Collectors.toList())
                : new ArrayList<>();

    }

    @Override
    public List<KingDTO> retrieveKingByCountry(String country) {
        List<King> kingList = kingRepository.findByCountryName(country).orElse(new ArrayList<>());
        return kingList.stream().map(king -> kingToKingDTO.convert(king)).collect(Collectors.toList());
    }


}
