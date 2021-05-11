package ru.nazarov.practice.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import ru.nazarov.practice.country.model.Country;
import ru.nazarov.practice.document.model.Document;
import ru.nazarov.practice.user.view.UserOutByIdView;

@Component
public class CustomMapperFactory implements FactoryBean<MapperFactory> {

    @Override
    public MapperFactory getObject() {
        DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder()
                .constructorResolverStrategy(null)
                .mapNulls(false)
                .build();

        mapperFactory.classMap(Country.class, UserOutByIdView.class)
                .field("name", "citizenshipName")
                .field("code", "citizenshipCode")
                .byDefault()
                .register();

        mapperFactory.classMap(Document.class, UserOutByIdView.class)
                .field("number", "docNumber")
                .field("date", "docDate")
                .byDefault()
                .register();


        return mapperFactory;
    }

    @Override
    public Class<?> getObjectType() {
        return MapperFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
