package springsecurity.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RegisterCustomerRequestModel {
	private String email;
	private String password;
	private String matchedPassword;
	private String firstName;
	private String lastName;
	private String phoneNumber;

}
