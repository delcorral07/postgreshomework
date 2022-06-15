package com.dcgteam.postgresHomework.repositories;

import com.dcgteam.postgresHomework.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, String> {

    @Query(value = "SELECT * FROM sc_kings.t_country c where c.name = ?1", nativeQuery = true)
    Optional<Country> findByName(String name);
}
