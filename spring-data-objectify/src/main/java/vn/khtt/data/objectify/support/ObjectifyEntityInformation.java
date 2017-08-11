package vn.khtt.data.objectify.support;

import com.googlecode.objectify.ObjectifyService;
import static com.googlecode.objectify.ObjectifyService.ofy;
import com.googlecode.objectify.Work;
import com.googlecode.objectify.impl.EntityMetadata;

import com.googlecode.objectify.impl.KeyMetadata;

import java.io.Serializable;

import org.springframework.data.repository.core.support.AbstractEntityInformation;

public class ObjectifyEntityInformation <T, ID extends Serializable> extends AbstractEntityInformation<T, ID> {
    public ObjectifyEntityInformation(Class<T> domainClass) {
        super(domainClass);
    }

    @Override
    public ID getId(T entity) {
        unsupportedOperation();
        
        // TODO Implement this method
        return null;
    }

    @Override
    public Class<ID> getIdType() {
        return ObjectifyService.run(new Work<Class<ID>>(){
            @Override
            public Class<ID> run() {
                EntityMetadata<T> entityMetadata = ofy().factory().getMetadata(getJavaType());
                KeyMetadata<T> keyMetadata = entityMetadata.getKeyMetadata();
                
                return (Class<ID>)keyMetadata.getIdFieldType();
            }
        });
    }

    private void unsupportedOperation(){
        throw new UnsupportedOperationException();
    }
}
