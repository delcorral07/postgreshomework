package com.dcgteam.postgreshomework.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class KingDTO implements Serializable {

    private String id;
    private String name;
    private CountryDTO country;
    private HouseDTO house;
    private String reign;


}
