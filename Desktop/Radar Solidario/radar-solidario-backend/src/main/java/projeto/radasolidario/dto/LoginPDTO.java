package projeto.radasolidario.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginPDTO {

	@Size(min = 6, max = 8)
	@NotBlank
	private String email;
	@Size(min = 8, max = 40)
	@NotBlank
	private String password;

}
