package br.com.uniacademia.cesIC.dto.userInfoo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfooHDTO implements Serializable {

	private static final long serialVersionUID = -7635468124663847923L;

	@NotBlank
	private String user;

}
