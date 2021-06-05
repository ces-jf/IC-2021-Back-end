package projeto.radasolidario.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import projeto.radasolidario.constats.AuthenticationRoleEnum;

@Entity
@Table(name = "perfis")
public class Perfil implements GrantedAuthority {

	private static final long serialVersionUID = 5928656372533714555L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private AuthenticationRoleEnum role;
	 
	@Override
	public String getAuthority() {
		return role.toString();
	}

}
