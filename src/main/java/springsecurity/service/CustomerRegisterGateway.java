package springsecurity.service;

import springsecurity.domain.Customer;
import springsecurity.service.dto.RegisterCustomerRequestModel;

public interface CustomerRegisterGateway {
	void save(Customer user);

	boolean existsByPhoneNumber(String phoneNumber);

	boolean existsById(Long id);

	boolean existsByEmail(String email);

	boolean pwdMatchBdd(String pwd, Long id);

	void update(RegisterCustomerRequestModel dto, Long id);

	Customer findByEmail(String email);
}
