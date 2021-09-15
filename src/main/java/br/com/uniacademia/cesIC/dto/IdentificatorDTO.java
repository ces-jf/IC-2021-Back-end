package br.com.uniacademia.cesIC.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentificatorDTO implements Serializable {

	private static final long serialVersionUID = -6136766525818392534L;

	@NotNull(message = "O campo 'Id' é obrigatório")
	private Long id;
}
