package com.staj.CarCommerceApp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity(name = "T_Car")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Car {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="t_car_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Brand brand;

//    Satılma/Kira durumu, uzunluk, yıl, model
//    Özellik başı 10 char
    @Column(name="specification", nullable=false, length = 40)
    private String specification;

    @Column(name="motor_litre", nullable=false)
    private Float litre;

//    Araç sıfır mı?
    @Column(name="is_new", nullable=false)
    private boolean isNew;

    @Column(name="price", nullable=false)
    private BigDecimal price;

    @Column(name="release_datetime", nullable=false)
    private Timestamp releaseDateTime;
//    sql timestamp

}
