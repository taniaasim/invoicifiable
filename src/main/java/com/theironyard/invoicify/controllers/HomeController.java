package com.theironyard.invoicify.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.theironyard.invoicify.models.Company;
import com.theironyard.invoicify.models.Invoice;
import com.theironyard.invoicify.models.User;
import com.theironyard.invoicify.repositories.CompanyRepository;
import com.theironyard.invoicify.repositories.UserRepository;

@Controller
@RequestMapping("/")
public class HomeController {
	
	private UserRepository userRepository;
	private PasswordEncoder encoder;
	private CompanyRepository companyRepo;
	
	public HomeController(UserRepository userRepository, PasswordEncoder encoder, CompanyRepository companyRepo) {
		this.userRepository = userRepository;
		this.encoder = encoder;
		this.companyRepo = companyRepo;
		
	}

	@GetMapping("")
	public String home(Model model) {
		return "home/default";
	}
	
	@GetMapping("signup")
	public String signup() {
		return "home/signup";
	}
	
	@PostMapping("signup")
	public ModelAndView handleSignup(User user) {
		// TODO THIS IS REALLY DUMB; NEEDS REFACTORING
		String password = user.getPassword();
		String encryptedPassword = encoder.encode(password);
		user.setPassword(encryptedPassword);
		
		ModelAndView mv = new ModelAndView();
		try {
			userRepository.save(user);
			mv.setViewName("redirect:/login");
		} catch (DataIntegrityViolationException dive) {
			mv.setViewName("home/signup");
			mv.addObject("errorMessage", "Cannot signup with that username");
		}
		return mv;
	}
	
	@GetMapping("admin/companies")
	public ModelAndView list(User user) {
		ModelAndView mv = new ModelAndView("home/listofcompanies");
		mv.addObject("companies", companyRepo.findAll(new Sort("name")));
		return mv;
	}
	
	
	@PostMapping("/admin/companies")
	public String addCompany(String newCompany) {
		Company company = new Company();
		List<Invoice> invoices = new ArrayList<Invoice>();
		company.setName(newCompany);
		company.setInvoices(invoices);
		companyRepo.save(company);
		return "redirect:/admin/companies";
	}
	
	
}
















