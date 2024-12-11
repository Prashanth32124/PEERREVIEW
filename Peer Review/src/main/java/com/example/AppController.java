package com.example;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		userRepo.save(user);
		
		return "register_success";
	}
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);
		
		return "users";
	}
	
	@GetMapping("/fitness")
	public String viewFitnessProgramsPage() {
	    return "fitnessprograms"; // This corresponds to the fitness_programs.html template
	}
	
	@GetMapping("/fitness/{program}")
	public String viewProgramDetails(@PathVariable("program") String program, Model model) {
	    model.addAttribute("program", program);
	    return "fitness_details";
	}
	
	
	
	@GetMapping("/nutrition")
	public String viewNutritionPage() {
	    return "nutrition"; // This corresponds to the fitness_programs.html template
	}
	
	@GetMapping("/nutrition/{program}")
	public String viewNutritionDetails(@PathVariable("program") String program, Model model) {
	    model.addAttribute("program", program);
	    return "nutrition_details";
	}



} 