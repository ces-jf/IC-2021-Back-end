package br.com.uniacademia.cesIC.models;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.uniacademia.cesIC.config.mongo.Cascade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class User implements Serializable {

    private static final long serialVersionUID = -1989956427907596075L;

    @Id
    private String id;

    @NotNull
    private String name;

    @DBRef
    @Cascade
    private Authentication authentication;

}
