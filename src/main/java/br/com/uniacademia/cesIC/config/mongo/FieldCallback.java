package br.com.uniacademia.cesIC.config.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.util.ReflectionUtils;

public class FieldCallback implements ReflectionUtils.FieldCallback {
    private boolean idFound;

    @Override
    public void doWith(java.lang.reflect.Field field) throws IllegalArgumentException, IllegalAccessException {
	ReflectionUtils.makeAccessible(field);

	if (field.isAnnotationPresent(Id.class)) {
	    idFound = true;
	}

    }

    public boolean isIdFound() {
	return idFound;
    }
}
