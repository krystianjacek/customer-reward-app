package com.jacek.customerreward.webapp.backend.controller.dto;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerDto {
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private List<TransactionDto> transactions;
}
