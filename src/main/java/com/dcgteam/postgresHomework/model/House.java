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
@Table(name = "t_houses")
public class House implements Serializable {

    @Id
    private String id = "";
    private String name = "";

    @ManyToOne
    @JoinColumn(name = "country", referencedColumnName = "name")
    private Country country = new Country();

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        House house = (House) o;

        return id != null ? id.equals(house.id) : house.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "House{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", country=" + country +
                '}';
    }
}
