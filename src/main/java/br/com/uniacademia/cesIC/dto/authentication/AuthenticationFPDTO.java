package br.com.uniacademia.cesIC.dto.authentication;

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
public class AuthenticationFPDTO implements Serializable {

	private static final long serialVersionUID = 4852433818663915173L;

	@NotNull(message = "O campo 'Id' é obrigatório")
	private Long id;

	@Email(message = "O campo 'E-mail' é inválido")
	@NotNull(message = "O campo 'E-mail' é obrigatório")
	@Size(min = 6, max = 80, message = "O campo 'E-mail' deve conter entre 6 e 80 caracteres")
	private String email;

	@Password
	@Size(min = 8, max = 40, message = "O campo 'Senha' deve conter entre 8 a 40 caracteres")
	private String password;
}
