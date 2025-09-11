package com.staj.CarCommerceApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity(name = "T_Car")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Car {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="t_car_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Brand brand;

//    Satılma/Kira durumu, uzunluk, yıl, model
    @Column(name="specification", nullable=false)
    private String specification;

    @Column(name="motor_litre", nullable=false)
    private Float litre;

//    Araç sıfır mı?
    @Column(name="is_new", nullable=false)
    private boolean used;

    @Column(name="price", nullable=false)
    private BigDecimal price;

    @Column(name="release_datetime", nullable=false)
    private Timestamp releaseDateTime;
//    sql timestamp
}
