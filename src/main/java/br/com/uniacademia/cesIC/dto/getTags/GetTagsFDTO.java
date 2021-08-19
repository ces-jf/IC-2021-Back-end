package br.com.uniacademia.cesIC.dto.getTags;

import java.io.Serializable;

import br.com.uniacademia.cesIC.models.Commit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetTagsFDTO implements Serializable{
	
	
	private static final long serialVersionUID = -6805694027806824318L;
	
	private String name;
	private String zipball_url;
	private String tarball_url;
	private Commit commit;
	private String node_id;
}
