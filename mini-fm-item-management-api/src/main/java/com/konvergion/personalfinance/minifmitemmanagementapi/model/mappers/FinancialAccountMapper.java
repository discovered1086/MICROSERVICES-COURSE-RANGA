package com.konvergion.personalfinance.minifmitemmanagementapi.model.mappers;

import com.konvergion.personalfinance.minifmitemmanagementapi.model.FinancialAccountEntity;
import com.konvergion.personalfinance.minifmitemmanagementapi.model.FinancialAccountLinkStatus;
import com.konvergion.personalfinance.minifmitemmanagementapi.model.FinancialAccountStatus;
import com.konvergion.personalfinance.minifmitemmanagementapi.model.dto.FinancialAccountDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        imports = FinancialAccountLinkStatus.class)
@DecoratedWith(FinancialAccountMappingDecorator.class)
public interface FinancialAccountMapper {

    @Mapping(target = "accountId", ignore = true)
    @Mapping(target = "customerId", source = "customerId")
    @Mapping(target = "accountStatus", source = "accountStatus")
    @Mapping(target = "accountLinkStatus", source = "accountLinkStatus")
    @Mapping(target = "accountType", ignore = true)
    FinancialAccountEntity convertDtoToEntity(FinancialAccountDto accountDto,
                                              String customerId,
                                              FinancialAccountStatus accountStatus,
                                              FinancialAccountLinkStatus accountLinkStatus);

    @Mapping(target = "accountSubType", ignore = true)
    @Mapping(target = "accountType", ignore = true)
    FinancialAccountDto convertEntityToDto(FinancialAccountEntity financialAccountEntity);
}
