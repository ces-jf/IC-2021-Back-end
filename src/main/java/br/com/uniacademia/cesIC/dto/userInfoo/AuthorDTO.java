package br.com.uniacademia.cesIC.dto.userInfoo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDTO implements Serializable {

    private static final long serialVersionUID = 225786658578700047L;

    private String login;
    private String name;
    private Date date;

}
