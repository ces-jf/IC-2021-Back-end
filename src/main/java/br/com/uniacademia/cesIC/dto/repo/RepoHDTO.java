package br.com.uniacademia.cesIC.dto.repo;

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
public class RepoHDTO implements Serializable {

	private static final long serialVersionUID = 6465032036538155195L;

	@NotBlank(message = "O campo 'user' não pode estar em branco")
	private String user;
	
	@NotBlank(message = "O campo 'repo' não pode estar em branco")
	private String repo;
	
}
