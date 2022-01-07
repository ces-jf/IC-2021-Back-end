package br.com.uniacademia.cesIC.repositors;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.uniacademia.cesIC.models.RepoInfo;

@Repository
public interface RepositoryRepoInfo extends MongoRepository<RepoInfo, String> {

    @Query("{'name':?0}")
    public Optional<RepoInfo> findByName(String nome);

    @Query("{'full_name':?0}")
    public Optional<RepoInfo> findByFullName(String user);
}
