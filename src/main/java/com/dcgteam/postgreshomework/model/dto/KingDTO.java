package com.dcgteam.postgreshomework.model.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
public class KingDTO extends RepresentationModel<KingDTO> implements Serializable {

    private String id;
    private String name;
    private CountryDTO country;
    private HouseDTO house;
    private String reign;


}
