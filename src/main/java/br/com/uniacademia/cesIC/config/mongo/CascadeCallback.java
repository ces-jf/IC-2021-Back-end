package br.com.uniacademia.cesIC.config.mongo;

import java.lang.reflect.Field;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.util.ReflectionUtils;

public class CascadeCallback implements ReflectionUtils.FieldCallback {

    private Object source;
    private MongoOperations mongoOperations;

    public CascadeCallback(final Object source, final MongoOperations mongoOperations) {
	this.source = source;
	this.setMongoOperations(mongoOperations);
    }

    @Override
    public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
	ReflectionUtils.makeAccessible(field);

	if (field.isAnnotationPresent(DBRef.class) && field.isAnnotationPresent(Cascade.class)) {
	    final Object fieldValue = field.get(getSource());

	    boolean insta = !(fieldValue instanceof String);
	    boolean instanull = fieldValue != null;

	    if (instanull && insta) {
		final FieldCallback callback = new FieldCallback();
		ReflectionUtils.doWithFields(fieldValue.getClass(), (org.springframework.util.ReflectionUtils.FieldCallback) callback);
		getMongoOperations().save(fieldValue);
	    }
	}

    }

    public Object getSource() {
	return source;
    }

    public void setSource(final Object source) {
	this.source = source;
    }

    public MongoOperations getMongoOperations() {
	return mongoOperations;
    }

    public void setMongoOperations(final MongoOperations mongoOperations) {
	this.mongoOperations = mongoOperations;
    }

}
