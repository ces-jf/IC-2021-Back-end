package br.com.uniacademia.cesIC.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "contributors")
public class Contributor implements Serializable {

    private static final long serialVersionUID = -7868089948159126063L;

    @Id
    private String id;

    @Field(name = "name_repository")
    private String nameRepository;

    @Field(name = "name_contributor")
    private String login;


}
