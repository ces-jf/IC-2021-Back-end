package br.com.uniacademia.cesIC.dto.token;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.uniacademia.cesIC.constant.AuthenticationRole;
import br.com.uniacademia.cesIC.dto.user.UserTokenHRDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenFRDTO implements Serializable {

	private static final long serialVersionUID = -9191364978079948376L;

	@NotBlank(message = "O campo 'Token' é obrigatório")
	private String token;

	@NotNull(message = "O campo 'Usuário' é obrigatório")
	private UserTokenHRDTO user;

	@NotNull(message = "O campo 'Cargos' é obrigatório")
	@NotEmpty(message = "O usuário deve possuir um ou mais cargos")
	private List<AuthenticationRole> roles;
}
