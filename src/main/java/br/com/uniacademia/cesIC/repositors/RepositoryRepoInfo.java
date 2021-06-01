package br.com.uniacademia.cesIC.repositors;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.com.uniacademia.cesIC.models.RepoInfo;

public interface RepositoryRepoInfo extends MongoRepository<RepoInfo, String> {

	@Query("{'full_name':?0}")
	public RepoInfo findByPorNome(String nome);
}
