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
@Table(name = "t_houses")
public class House implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country", referencedColumnName = "name", nullable = false)
    private Country country;

}
