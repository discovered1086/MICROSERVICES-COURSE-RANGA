package com.konvergion.personalfinance.minifmitemmanagementapi.dao;

import com.konvergion.personalfinance.minifmitemmanagementapi.model.PropertyAccountDetailsEntity;
import com.konvergion.personalfinance.minifmitemmanagementapi.model.VehicleAccountDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleAccountDetailsRepository extends JpaRepository<VehicleAccountDetailsEntity, String> {


}
