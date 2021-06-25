package br.com.uniacademia.cesIC.models;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "repository")
public class RepoInfo implements Serializable {

	private static final long serialVersionUID = 6007022265994664598L;

	@Id
	private String id;
	private String node_id;
	private String name;
	private boolean privat;
	private String full_name;
	private Owner owner;
	private String language;
	private String default_branch;
	private int watchers_count;
	private int open_issues_count;
	private int forks;
	private String clone_url;
	private List<User> usList;

}
