package com.dcgteam.postgreshomework.model;

import lombok.*;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Log
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "t_kings")
public class King implements Serializable{

    @javax.persistence.Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "country",referencedColumnName = "name")
    private Country country;

    @ManyToOne(optional = false)
    @JoinColumn(name = "house",referencedColumnName = "name")
    private House house;

    @Column(name = "reign", nullable = false)
    private String reign;


}
