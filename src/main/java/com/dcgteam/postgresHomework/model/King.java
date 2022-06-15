package com.dcgteam.postgresHomework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Log
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_kings")
public class King implements Serializable{
    @javax.persistence.Id
    @Column(name = "id", nullable = false)
    private String id = "";
    private String name = "";
    @ManyToOne(optional = false)
    @JoinColumn(name = "country",referencedColumnName = "name")
    private Country country = new Country();
    @ManyToOne(optional = false)
    @JoinColumn(name = "house",referencedColumnName = "name")
    private House house = new House();
    private String reign = "";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        King king = (King) o;

        return id != null ? id.equals(king.id) : king.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "King{" +
                "id='" + id + '\'' +
                ", Name='" + name + '\'' +
                ", Country='" + country + '\'' +
                ", House='" + house + '\'' +
                ", Reign='" + reign + '\'' +
                '}';
    }
}
