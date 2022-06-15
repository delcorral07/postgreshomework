package com.dcgteam.postgreshomework.repositories;

import com.dcgteam.postgreshomework.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HouseRepository extends JpaRepository<House, String> {

    @Query(value = "SELECT h FROM House h where h.country.name = :country")
    Optional<List<House>> retrieveHouseByCountryName(@Param("country") String country);


}
