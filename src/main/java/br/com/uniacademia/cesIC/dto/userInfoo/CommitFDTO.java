package br.com.uniacademia.cesIC.dto.userInfoo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommitFDTO implements Serializable {

    private static final long serialVersionUID = 2851304523408948701L;

    private AuthorDTO author;

}
