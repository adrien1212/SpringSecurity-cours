package springsecurity.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import springsecurity.domain.Customer;
import springsecurity.domain.Role;
import springsecurity.service.dto.RegisterCustomerRequestModel;
import springsecurity.service.exception.RegistrationMDPmatch;
import springsecurity.service.exception.RegistrationMailAlreadyExist;
import springsecurity.service.exception.RegistrationNoCustomerExist;
import springsecurity.service.exception.RegistrationPhoneNumberAlreadyExist;

@AllArgsConstructor
@Service
public class RegisterCustomerServiceImpl implements RegisterCustomerService {

	@Autowired
	private PasswordEncoder encoder;

	private CustomerRegisterGateway customerRegisterGateway;

	@Override
	public void register(RegisterCustomerRequestModel dto)
			throws RegistrationMailAlreadyExist, RegistrationPhoneNumberAlreadyExist, RegistrationMDPmatch {
		if (!dto.getMatchedPassword().equals(dto.getPassword())) {
			throw new RegistrationMDPmatch("Password don't match");

		} else if (customerRegisterGateway.existsByEmail(dto.getEmail())) {
			throw new RegistrationMailAlreadyExist("Account with email " + dto.getEmail() + " already exist");
		} else if (customerRegisterGateway.existsByPhoneNumber(dto.getPhoneNumber())) {
			throw new RegistrationPhoneNumberAlreadyExist(
					"Account with phone number " + dto.getPhoneNumber() + " already exist");
		}

		customerRegisterGateway.save(dtoToDomain(dto));
	}

	@Override
	public void update(RegisterCustomerRequestModel dto, Long id)
			throws RegistrationNoCustomerExist, RegistrationMDPmatch {
		if (!customerRegisterGateway.existsById(id)) {
			throw new RegistrationNoCustomerExist("No customer exist for this id");
		}

		if (!customerRegisterGateway.pwdMatchBdd(dto.getPassword(), id)) {
			throw new RegistrationMDPmatch("Password don't match");
		}

		customerRegisterGateway.update(dto, id);

	}


	private Customer dtoToDomain(RegisterCustomerRequestModel dto) {
		return Customer.builder().email(dto.getEmail()).firstName(dto.getFirstName()).lastName(dto.getLastName())
				.phoneNumber(dto.getPhoneNumber()).password(encoder.encode(dto.getPassword())).role(Role.USER).build();
	}
}
