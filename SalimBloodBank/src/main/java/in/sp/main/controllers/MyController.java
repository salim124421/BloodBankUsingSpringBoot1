package in.sp.main.controllers;

import in.sp.main.dto.BloodBankDTO;
import in.sp.main.dto.LoginDTO;
import in.sp.main.dto.PasswordResetDTO;
import in.sp.main.dto.RegisterDTO;
import in.sp.main.service.*;
import in.sp.main.util.LoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class MyController
{
	@Autowired
	LoginServiceImpl loginService;

	@Autowired
	DatabaseSetupService databaseSetupService;

	@Autowired
	RegisterServiceImpl registerService;

	@Autowired
	RegisterDTO user;

	@Autowired
	BloodBankServiceImpl bloodBankService;

	@GetMapping("/")
	public String openHomePage()
	{
		databaseSetupService.setupDatabase();
		return "home-page";
	}
	
	@GetMapping("/homePage")
	public String openHomePagee()
	{
		return "home-page";
	}
	
	@GetMapping("/aboutUsPage")
	public String openAboutUsPage()
	{
		return "about-us-page";
	}
	
	@GetMapping("/contactUsPage")
	public String openContactUsPage()
	{
		return "contact-us-page";
	}
	
	@GetMapping("/loginPage")
	public String openLoginPage()
	{
		return "login-page";
	}
	
	@GetMapping("/registerPage")
	public String openRegisterPage()
	{
		return "register-page";
	}

	@GetMapping("/createAgent")
	public String createAgent()
	{
		return "agent-registration";
	}

	@PostMapping("/returnProfile")
	public String backToHome()
	{
		return "profile";
	}

	@GetMapping("/donateAndReceive")
	public String createRequest()
		{
			return "bloodRequest-form";
		}

	@PostMapping("/bloodRequest")
	public String handleBloodRequest(@ModelAttribute BloodBankDTO bloodBankDTO,
									 HttpServletRequest httpServletRequest)
	{
		HttpSession session = httpServletRequest.getSession();
		RegisterDTO userDTO = (RegisterDTO)session.getAttribute("user");
		bloodBankService.bloodBankService(bloodBankDTO,userDTO);
		return "profile";
	}

	
	@PostMapping("/loginForm")
	public String login(
			@ModelAttribute @Validated LoginDTO loginDTO,BindingResult bindingResult,Model model
			,HttpServletRequest httpServletRequest) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("formatError", bindingResult.getFieldError().getDefaultMessage());
			return "login-page";
		}
		LoginResult loginResult = loginService.loginService(loginDTO);
		user = loginResult.getUser();

		switch (loginResult.getStatus()) {
			case "success":
				// Handle successful login
				if (user.getBlockStatus().equalsIgnoreCase("blocked")) {
					model.addAttribute("blockStatus", "User Blocked");
					return "login-page";
				}
				HttpSession session = httpServletRequest.getSession();
				session.setAttribute("user",user);


				switch (loginResult.getUser().getRole()) {
				// Redirect to appropriate profile page based on user role
					case "admin":
						model.addAttribute("user", loginResult.getUser());
						model.addAttribute("signedupUsers", loginService.fetchSignedupUsers());
						model.addAttribute("agentUser",loginService.fetchAgentData());
						//model.addAttribute("blood",bloodBankService.getAllBloodRequest());
						return "admin-dashboard";

					case "agent":
						model.addAttribute("user", loginResult.getUser());
						return "agent-dashboard";

					case "user":
						model.addAttribute("user", loginResult.getUser());
						return "profile-page";}

			case "reset":
					// Redirect to password reset page if password reset is required
					model.addAttribute("username", loginResult.getUser().getUsername());
					return "passwordReset-page";

			case "invalid":
				// Handle invalid credentials
				model.addAttribute("status", "Invalid credentials");
				if (loginResult.getBlockStatus() == 1) {
					model.addAttribute("blockStatus", "User Blocked");
				} else if (loginResult.getBlockStatus() == 2) {
					model.addAttribute("blockStatus", "User cannot be blocked because user does not exist");
				}
				return "login-page";

		}
		return "";
	}
	
	@GetMapping("/profilePage")
	public String openProfilePage()
	{
		return "profile-page";
	}
	
	@GetMapping("/logoutPage")
	public String logout(HttpSession session)
	{
		session.invalidate();
		
		return "login-page";
	}

	
	@PostMapping("/regForm")
	public String register(
			Model model,
			@ModelAttribute @Validated RegisterDTO registerDTO, BindingResult bindingResult
			)
	{
		if(bindingResult.hasErrors())
		{
			model.addAttribute("formatError",bindingResult.getFieldError().getDefaultMessage());
			return "register-page";
		}
		try{

			registerService.registerService(registerDTO);
			return "login-page";
		}
		catch (Exception e)
		{
			model.addAttribute("error", e.getMessage());
			return "register-page";
		}
	}

	@PostMapping(value = "/passwordReset")
	public String changePassword(@ModelAttribute @Validated PasswordResetDTO passwordResetDTO, Model model,
								 BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			// If validation errors exist, return to the password reset page
			model.addAttribute("formarError", "Please fill all fields properly");
			return "passwordReset";
		}

		// Process password reset
		switch (loginService.updatePassword(user, passwordResetDTO)) {
			case "resetSuccess":
				// Password reset successful
				model.addAttribute("passwordResetStatus", "Password successfully changed");
				return "login-page";

			case "unmatchedPassword":
				// Unmatched passwords
				model.addAttribute("passwordResetStatus", "Passwords do not match");
				return "passwordReset-page";

			case "missingUser":
				// User does not exist
				model.addAttribute("passwordResetStatus", "User does not exist");
				return "passwordReset-page";
		}
		return "";
	}


	@PostMapping("/agentRegForm")
	public String agentRegister(
			Model model,
			@ModelAttribute @Validated RegisterDTO registerDTO, BindingResult bindingResult
	)
	{
		if(bindingResult.hasErrors())
		{
			model.addAttribute("formatError",bindingResult.getFieldError().getDefaultMessage());
			return "register-page";
		}
			registerDTO.setPassword(registerDTO.getDateOfBirth());
			registerDTO.setRole("agent");
			registerDTO.setCreatedBy("admin");
			registerService.registerService(registerDTO);

			model.addAttribute("user", user);
			model.addAttribute("signedupUsers", loginService.fetchSignedupUsers());
			return "admin-dashboard";

	}




	@GetMapping("/logout")
	public String performLogout( HttpServletRequest httpServletRequest)
	{
		HttpSession session = httpServletRequest.getSession();
		session.invalidate();
		return "login-page";
	}

}
