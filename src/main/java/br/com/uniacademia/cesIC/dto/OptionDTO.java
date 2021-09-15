package br.com.uniacademia.cesIC.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionDTO<T> implements Serializable {

	private static final long serialVersionUID = -8267287228611361678L;

	@NotEmpty(message = "O campo 'Texto' é obrigatório")
	private String text;
	
	@NotNull(message = "O campo 'Valor' é obrigatório")
	private transient T value;
}
