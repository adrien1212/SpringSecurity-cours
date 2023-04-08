package springsecurity.service;

import springsecurity.service.dto.RegisterCustomerRequestModel;
import springsecurity.service.exception.RegistrationMDPmatch;
import springsecurity.service.exception.RegistrationMailAlreadyExist;
import springsecurity.service.exception.RegistrationNoCustomerExist;
import springsecurity.service.exception.RegistrationPhoneNumberAlreadyExist;


public interface RegisterCustomerService {
	void register(RegisterCustomerRequestModel dto)
			throws RegistrationMailAlreadyExist, RegistrationPhoneNumberAlreadyExist, RegistrationMDPmatch;

	void update(RegisterCustomerRequestModel dto, Long id) throws RegistrationNoCustomerExist, RegistrationMDPmatch;

}
