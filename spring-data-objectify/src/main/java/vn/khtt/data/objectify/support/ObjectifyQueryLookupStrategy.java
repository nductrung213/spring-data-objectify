package vn.khtt.data.objectify.support;

import java.lang.reflect.Method;

import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.core.NamedQueries;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.EvaluationContextProvider;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.data.repository.query.RepositoryQuery;

import vn.khtt.data.objectify.repository.query.ObjectifyRepositoryQuery;

public class ObjectifyQueryLookupStrategy implements QueryLookupStrategy {
    public ObjectifyQueryLookupStrategy(Key key, EvaluationContextProvider evaluationContextProvider) {
    }

    @Override
    public RepositoryQuery resolveQuery(Method method, RepositoryMetadata metadata, ProjectionFactory factory,
                                        NamedQueries namedQueries) {
        QueryMethod queryMethod = new QueryMethod(method, metadata, factory);
        
        return new ObjectifyRepositoryQuery(queryMethod);
    }
}
