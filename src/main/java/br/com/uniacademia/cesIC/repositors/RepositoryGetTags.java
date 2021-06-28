package br.com.uniacademia.cesIC.repositors;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.uniacademia.cesIC.models.GetTags;

public interface RepositoryGetTags extends MongoRepository<GetTags, String>{

}
