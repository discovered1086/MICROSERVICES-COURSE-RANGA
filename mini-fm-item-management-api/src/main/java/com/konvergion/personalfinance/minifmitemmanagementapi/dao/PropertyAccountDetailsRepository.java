package com.konvergion.personalfinance.minifmitemmanagementapi.dao;

import com.konvergion.personalfinance.minifmitemmanagementapi.model.DepositoryAccountDetailsEntity;
import com.konvergion.personalfinance.minifmitemmanagementapi.model.PropertyAccountDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyAccountDetailsRepository extends JpaRepository<PropertyAccountDetailsEntity, String> {


}
