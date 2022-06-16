package com.dcgteam.postgreshomework.model.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
public class CountryDTO extends RepresentationModel<CountryDTO> implements Serializable {

    private String name;
    private String id;


}
