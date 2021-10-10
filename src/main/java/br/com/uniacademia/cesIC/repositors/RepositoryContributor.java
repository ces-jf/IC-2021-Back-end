package br.com.uniacademia.cesIC.repositors;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.uniacademia.cesIC.models.Contributor;

@Repository
public interface RepositoryContributor extends MongoRepository<Contributor, String>{

}
