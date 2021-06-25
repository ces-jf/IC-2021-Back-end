package br.com.uniacademia.cesIC.repositors;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.uniacademia.cesIC.models.RepoInfo;

@Repository
public interface RepositoryRepoInfo extends MongoRepository<RepoInfo, String> {

	@Transactional(readOnly = true)
	@Query("{'name':?0}")
	public Optional<RepoInfo> findByPorNome(String nome);
}
