package br.com.uniacademia.cesIC.dto.user;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.uniacademia.cesIC.dto.authentication.AuthenticationRPDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPIDTO implements Serializable {

    private static final long serialVersionUID = 473950750193819445L;

    @NotNull(message = "O campo 'Nome' é obrigatório")
    @Size(min = 1, max = 200, message = "O campo 'Nome' deve conter entre 1 e 200 caracteres")
    private String name;

    @NotNull(message = "O campo 'Autenticação' é obrigatório")
    private AuthenticationRPDTO authentication;
}
