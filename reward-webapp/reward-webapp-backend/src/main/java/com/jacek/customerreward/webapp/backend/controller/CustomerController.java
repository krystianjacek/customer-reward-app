package com.jacek.customerreward.webapp.backend.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jacek.customerreward.webapp.backend.controller.dto.CustomerDto;
import com.jacek.customerreward.webapp.backend.controller.mapper.CustomerDtoMapper;
import com.jacek.customerreward.webapp.backend.service.CustomerService;

@RestController
@RequestMapping("/api/internal/customer")
public class CustomerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	private final CustomerService customerService;
	
	private final CustomerDtoMapper customerDtoMapper;
	
	public CustomerController(final CustomerService customerService,
							  final CustomerDtoMapper customerDtoMapper) {
		this.customerService = customerService;
		this.customerDtoMapper = customerDtoMapper;
	}
	
	@PostMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	@ResponseStatus(HttpStatus.CREATED)
	public void addCustomer(@RequestBody final CustomerDto customerDto) {
		LOGGER.debug("Adding new customer {}", customerDto);
		this.customerService.addCustomer(this.customerDtoMapper.map(customerDto));
	}
	
	@GetMapping(
			path = "customerId/{customerId}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public CustomerDto getCustomer(@PathVariable final long customerId) {
		LOGGER.debug("Get customer for id {}", customerId);
		return this.customerDtoMapper.map(this.customerService.getCustomer(customerId));
	}
	
	@GetMapping(
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public List<CustomerDto> getCustomers() {
		LOGGER.debug("Get all customers");
		return this.customerDtoMapper.map(this.customerService.getCustomers());
	}
}
