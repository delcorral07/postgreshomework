package com.dcgteam.postgresHomework.repositories;

import com.dcgteam.postgresHomework.dto.KingDTO;
import com.dcgteam.postgresHomework.model.King;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface KingRepository extends JpaRepository<King, String> {

    Optional<King> findByName(String name);

    Optional<List<King>> findByNameContaining(String name);

    Optional<List<King>> findByCountryName(String country);







}
