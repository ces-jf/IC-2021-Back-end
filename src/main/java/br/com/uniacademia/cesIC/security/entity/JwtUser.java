package br.com.uniacademia.cesIC.security.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtUser implements UserDetails {
    private static final long serialVersionUID = -268046329085485932L;

    @Getter
    private String id;

    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public String getUsername() {
	return email;
    }

    @Override
    public String getPassword() {
	return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	return authorities;
    }

    @Override
    public boolean isEnabled() {
	return true;
    }

    @Override
    public boolean isAccountNonLocked() {
	return true;
    }

    @Override
    public boolean isAccountNonExpired() {
	return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
	return true;
    }
}
