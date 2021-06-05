package projeto.radasolidario.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TokenRDTO {

	@NotBlank
	private String token;
}
