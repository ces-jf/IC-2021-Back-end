package br.com.uniacademia.cesIC.models;

import java.io.Serializable;
import java.util.Calendar;

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
@Document(collection = "repoInfo")
public class RepoInfo implements Serializable {

	private static final long serialVersionUID = 6007022265994664598L;

	@Id
	private String id;
	private String node_id;
	private String avatar_url;
	private String gravatar_id;
	private String url;
	private String html_url;
	private String followers_url;
	private String following_url;
	private String gists_url;
	private String starred_url;
	private String subscriptions_url;
	private String organizations_url;
	private String repos_url;
	private String events_url;
	private String received_events_url;
	private String type;
	private boolean site_admin;
	private String name;
	private String company;
	private String blog;
	private String email;
	private boolean hireable;
	private String bio;
	private String twitter_username;
	private int public_repos;
	private int public_gists;
	private int followers;
	private int following;
	private Calendar created_at;
	private Calendar updated_at;
	
}
