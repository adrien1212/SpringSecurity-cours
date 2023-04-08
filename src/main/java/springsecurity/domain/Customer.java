package springsecurity.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Customer {

	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String username;
	private String phoneNumber;
	private Role role;

}
