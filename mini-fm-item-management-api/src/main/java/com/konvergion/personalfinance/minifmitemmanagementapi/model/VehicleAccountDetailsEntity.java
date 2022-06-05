package com.konvergion.personalfinance.minifmitemmanagementapi.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "vehicle_account_details")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class VehicleAccountDetailsEntity extends PropertyAccountDetailsEntity {

    @Column(name = "vehicle_identification_number", nullable = false, unique = true)
    private String vinNumber;

    @Column(name = "vehicle_make", nullable = false)
    private String vehicleMake;

    @Column(name = "vehicleModel", nullable = false)
    private String vehicleModel;

    @Column(name = "vehicle_mileage", nullable = false)
    private BigInteger vehicleMileage;

    @Column(name = "vehicle_year", nullable = false)
    private Integer vehicleYear;

    @Builder
    public VehicleAccountDetailsEntity(String accountId, FinancialAccountEntity accountEntity,
                                       BigDecimal propertyValuation, String vinNumber, String vehicleMake,
                                       String vehicleModel, BigInteger vehicleMileage, Integer vehicleYear) {
        super(accountId, accountEntity, propertyValuation);
        this.vinNumber = vinNumber;
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.vehicleMileage = vehicleMileage;
        this.vehicleYear = vehicleYear;
    }
}
