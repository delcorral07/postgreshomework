package com.dcgteam.postgreshomework.repositories;

import com.dcgteam.postgreshomework.model.King;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KingRepository extends JpaRepository<King, String> {

    Optional<King> findByName(String name);

    Optional<List<King>> findByNameContaining(String name);

    Optional<List<King>> findByCountryName(String country);







}
