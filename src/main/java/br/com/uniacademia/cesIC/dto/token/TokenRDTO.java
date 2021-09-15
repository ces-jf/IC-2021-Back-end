package br.com.uniacademia.cesIC.dto.token;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenRDTO implements Serializable {

	private static final long serialVersionUID = 8524015670453225051L;

	@NotEmpty(message = "O campo 'Token' é obrigatório")
	private String token;
}
