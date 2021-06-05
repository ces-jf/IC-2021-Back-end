package projeto.radasolidario.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projeto.radasolidario.constats.AuthenticationRoleEnum;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginRDTO {

	@NotBlank
	private String name;
	@NotBlank
	private String token;
	@NotBlank
	private AuthenticationRoleEnum role;

}
