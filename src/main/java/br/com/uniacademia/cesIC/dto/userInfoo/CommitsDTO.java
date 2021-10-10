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
public class CommitsDTO implements Serializable {

    private static final long serialVersionUID = -5205246532743817028L;

    private CommitFDTO commit;

}
