package br.com.uniacademia.cesIC.models;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringExclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import br.com.uniacademia.cesIC.constant.AuthenticationRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "authentication")
public class Authentication implements Serializable {

    private static final long serialVersionUID = 7756030482371353422L;

    @Id
    private String id;

    @Email(message = "O e-mail digitado não é valido.")
    @NotNull(message = "O campo email é obrigatorio.")
    @Field(name = "email")
    @Indexed(unique = true)
    private String email;

    @ToString.Exclude
    @Size(min = 8, message = "O campo 'Senha' deve conter 8 ou mais caracteres")
    private String password;

    @NotNull
    private Boolean isLocked;

    @NotNull
    @ToStringExclude
    private List<AuthenticationRole> role;

    @NotNull
    private User user;

}
