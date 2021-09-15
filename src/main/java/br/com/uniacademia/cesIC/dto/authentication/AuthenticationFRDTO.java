package br.com.uniacademia.cesIC.dto.authentication;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.uniacademia.cesIC.annotation.Password;
import br.com.uniacademia.cesIC.constant.AuthenticationRole;
import br.com.uniacademia.cesIC.dto.user.UserHRDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationFRDTO implements Serializable {

	private static final long serialVersionUID = 2932728917392452591L;

	@NotNull(message = "O campo 'Id' é obrigatório")
	private String id;

	@Email(message = "O campo 'E-mail' é inválido")
	@NotNull(message = "O campo 'E-mail' é obrigatório")
	@Size(min = 6, max = 80, message = "O campo 'E-mail' deve conter entre 6 e 80 caracteres")
	private String email;

	@Password
	@Size(min = 8, max = 40, message = "O campo 'Senha' deve conter entre 8 a 40 caracteres")
	private String password;

	@NotNull(message = "O campo 'Bloqueado' é obrigatório")
	private Boolean isLocked;

	@NotNull(message = "O campo 'Usuário' é obrigatório")
	private UserHRDTO user;

	@NotNull(message = "O campo 'Cargos' é obrigatório")
	private List<AuthenticationRole> role;
}
