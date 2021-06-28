package br.com.uniacademia.cesIC.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "getTags")
public class GetTags implements Serializable{
	 
	private static final long serialVersionUID = -8148900287430556356L;

	@Id
	private String id;
	
	private String name;
	private String zipball_url;
	private String tarball_url;
	private Commit commit;
	private String node_id;
}
