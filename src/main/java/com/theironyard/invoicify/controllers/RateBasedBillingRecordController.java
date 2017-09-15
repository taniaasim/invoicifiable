package com.theironyard.invoicify.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.theironyard.invoicify.models.Company;
import com.theironyard.invoicify.models.RateBasedBillingRecord;
import com.theironyard.invoicify.models.User;
import com.theironyard.invoicify.repositories.BillingRecordRepository;
import com.theironyard.invoicify.repositories.CompanyRepository;

@Controller
@RequestMapping("/billing-records/rate-based")
public class RateBasedBillingRecordController {

	private BillingRecordRepository recordRepo;
	private CompanyRepository companyRepo;

	public RateBasedBillingRecordController(BillingRecordRepository recordRepo, CompanyRepository companyRepo) {
		this.recordRepo = recordRepo;
		this.companyRepo = companyRepo;
	}

	@PostMapping("")
	public ModelAndView create(RateBasedBillingRecord record, long clientId, Authentication auth) {
		User user = (User) auth.getPrincipal();
		Company client = companyRepo.findOne(clientId);
		record.setClient(client);
		record.setCreatedBy(user);
		recordRepo.save(record);
		return new ModelAndView("redirect:/billing-records");
	}

}
