package com.theironyard.invoicify.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.Authentication;

import com.theironyard.invoicify.models.Company;
import com.theironyard.invoicify.models.FlatFeeBillingRecord;
import com.theironyard.invoicify.models.User;
import com.theironyard.invoicify.repositories.BillingRecordRepository;
import com.theironyard.invoicify.repositories.CompanyRepository;

public class FlatFeeBillingRecordControllerTests {

	private BillingRecordRepository recordRepo;
	private CompanyRepository companyRepo;
	private FlatFeeBillingRecordController controller;

	@Before
	public void setup() {
		recordRepo = mock(BillingRecordRepository.class);
		companyRepo = mock(CompanyRepository.class);
		controller = new FlatFeeBillingRecordController(recordRepo, companyRepo);
	}
	
	/*
	@Test
	public void test_that_flat_fee_billing_record_test_gets_created() {
		// arrange
		FlatFeeBillingRecord ffbr = new FlatFeeBillingRecord();
		User tania = new User();
		
		ffbr.setClient(company);
		ffbr.setCreatedBy(tania);
		recordRepo.save(ffbr);
		
		// act
		Authentication auth = null;
		FlatFeeBillingRecord actualffbr = new FlatFeeBillingRecord();
		controller.create(actualffbr, 5l, auth);
		
		// assert
		assertThat(recordRepo.findOne(5l)).isSameAs(recordRepo.findOne(ffbr.getId()));
		
	}*/
	
}
