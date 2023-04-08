package springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import springsecurity.service.RegisterCustomerService;
import springsecurity.service.dto.LoginRequestModel;
import springsecurity.service.dto.LoginResponseModel;
import springsecurity.service.dto.RegisterCustomerRequestModel;
import springsecurity.service.exception.RegistrationMDPmatch;
import springsecurity.service.exception.RegistrationMailAlreadyExist;
import springsecurity.service.exception.RegistrationNoCustomerExist;
import springsecurity.service.exception.RegistrationPhoneNumberAlreadyExist;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {

	private RegisterCustomerService registerCustomerService;

	@PostMapping("/auth/register")
	public ResponseEntity create(@RequestBody RegisterCustomerRequestModel requestModel) {

		try {
			registerCustomerService.register(requestModel);
			return ResponseEntity.status(HttpStatus.CREATED).body("New account created");
		} catch (RegistrationMailAlreadyExist | RegistrationPhoneNumberAlreadyExist | RegistrationMDPmatch e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity update(@PathVariable("id") Long idCustomer, @RequestBody RegisterCustomerRequestModel dto) {
		try {
			registerCustomerService.update(dto, idCustomer);
			return ResponseEntity.status(HttpStatus.CREATED).body("Customer with id: " + idCustomer + " is update");
		} catch (RegistrationNoCustomerExist | RegistrationMDPmatch e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
		}
	}

	@GetMapping("/restricted")
	public String retricted() {
		return "Welcome to Restricted page";
	}
}
