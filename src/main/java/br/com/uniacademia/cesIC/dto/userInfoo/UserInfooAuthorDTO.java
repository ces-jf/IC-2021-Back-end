package br.com.uniacademia.cesIC.dto.userInfoo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfooAuthorDTO implements Serializable{

	private static final long serialVersionUID = 734252531158824168L;
	
	private AuthorDTO author;

}
