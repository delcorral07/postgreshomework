package com.dcgteam.postgreshomework.services.services;

import com.dcgteam.postgreshomework.model.dto.KingDTO;

import java.util.List;

public interface KingService {

    KingDTO retrieveKingById(String id);

    List<KingDTO> retrieveKings();


    String persistKing(KingDTO king);

    int deleteKing(String id);

    int updateKing(String id, KingDTO king);

    KingDTO retrieveKingByName(String name);

    List<KingDTO> retrieveKingsByNameLike(String name);

    List<KingDTO> retrieveKingByCountry(String country);











}
