package com.konvergion.personalfinance.minitransactionsearchapi.model.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Entity
@Table(name = "TRANSACTION_CATEGORY")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCategoryEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3478300589711095031L;

	@Id
	@Column(length = 15, name = "TRNSCTN_CTGRY_CD")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactionSeqGen")
	@GenericGenerator(name = "transactionSeqGen", strategy = "com.konvergion.personalfinance.minitransactionsearchapi.model.entity.CommonSequenceGenerator"
			, parameters = {
			@org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1"),
			@org.hibernate.annotations.Parameter(name = CommonSequenceGenerator.VALUE_PREFIX_PARAM, value = "TRNCTGRY") })
	private String transactionCategoryCode;

	@Enumerated(EnumType.STRING)
	@Column(name = "TRNSCTN_CTGRY_TYPE", length = 30)
	private TransactionCategoryTypeEnum categoryOfTransaction;

	@Column(name = "TRNSCTN_CTGRY_DFNTN", length = 200)
	private String transactionCategoryDefinition;

	@Column(name = "TRNSCTN_CTGRY_EFFCTV_DT", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	@Type(type = "org.hibernate.type.OffsetDateTimeType")
	private OffsetDateTime transactionCategoryEffectiveDate;

	@Column(name = "TRNSCTN_CTGRY_TRMNTN_DT", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	@Type(type = "org.hibernate.type.OffsetDateTimeType")
	private OffsetDateTime transactionCategoryTerminationDate;

}
