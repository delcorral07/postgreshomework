package com.dcgteam.postgreshomework.model;


import lombok.*;
import lombok.extern.java.Log;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Log
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_country")
public class Country implements Serializable{

    @Id
    private String id;

    @Column(name = "name", nullable = false)
    private String name;


}
