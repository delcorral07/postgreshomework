package com.dcgteam.postgreshomework.model.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
public class HouseDTO extends RepresentationModel<HouseDTO>  implements Serializable {

    private String id;
    private String name;
    private CountryDTO country;

}
