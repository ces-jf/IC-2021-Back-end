package br.com.uniacademia.cesIC.dto.user;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserHDTO implements Serializable {

	private static final long serialVersionUID = 9010829941202355689L;
	
	@NotBlank(message = "O campo 'user' não pode estar em branco.")
	private String user;
	
	@NotBlank(message = "O campo 'repository' não pode estar em branco.")
	private String repo;

}
