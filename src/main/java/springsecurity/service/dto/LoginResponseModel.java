package springsecurity.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseModel {
	private String accessToken;
	private String tokenType = "Bearer ";

	public LoginResponseModel(String accessToken) {
		this.accessToken = accessToken;
	}
}
