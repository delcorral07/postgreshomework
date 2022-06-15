package com.dcgteam.postgresHomework.dto;

import com.dcgteam.postgresHomework.model.Country;
import com.dcgteam.postgresHomework.model.House;
import lombok.Data;

import java.io.Serializable;

@Data
public class KingDTO implements Serializable {

    private String id = "";
    private String Name = "";
    private CountryDTO Country = new CountryDTO();
    private HouseDTO House = new HouseDTO();
    private String Reign = "";


}
