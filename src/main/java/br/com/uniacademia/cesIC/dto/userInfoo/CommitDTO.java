package br.com.uniacademia.cesIC.dto.userInfoo;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommitDTO implements Serializable {

    private static final long serialVersionUID = 2851304523408948701L;

    private LocalDate date;

}
