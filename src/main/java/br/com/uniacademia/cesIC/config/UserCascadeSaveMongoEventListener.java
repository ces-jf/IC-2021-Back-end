package br.com.uniacademia.cesIC.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import br.com.uniacademia.cesIC.models.Authentication;

public class UserCascadeSaveMongoEventListener extends AbstractMongoEventListener<Object> {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
	Object source = event.getSource();
	if ((source instanceof Authentication) && (((Authentication) source).getUser() != null)) {
	    mongoOperations.save(((Authentication) source).getUser());
	}
    }

}
