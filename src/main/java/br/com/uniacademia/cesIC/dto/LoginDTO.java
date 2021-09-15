package br.com.uniacademia.cesIC.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.uniacademia.cesIC.annotation.Password;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO implements Serializable {

	private static final long serialVersionUID = 2379097295356063752L;

	@NotNull(message = "O campo 'Email' é obrigatório")
	@Size(min = 6, max = 80, message = "O campo 'Email' deve conter entre 6 e 80 caracteres")
	@Email(message = "O campo 'Email' é inválido")
	private String email;

	@Password
	@Size(min = 8, max = 40, message = "O campo 'Senha' deve conter entre 8 a 40 caracteres")
	private String password;
}
