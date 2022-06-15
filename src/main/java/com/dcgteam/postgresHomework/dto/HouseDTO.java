package com.dcgteam.postgresHomework.dto;

import com.dcgteam.postgresHomework.model.Country;
import lombok.Data;

import java.io.Serializable;

@Data
public class HouseDTO implements Serializable {

    private String id = "";
    private String name = "";
    private CountryDTO country = new CountryDTO();

}
