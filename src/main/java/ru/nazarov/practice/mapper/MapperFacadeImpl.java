package ru.nazarov.practice.mapper;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nazarov.practice.country.model.Country;
import ru.nazarov.practice.document.model.Document;
import ru.nazarov.practice.user.view.UserOutByIdView;

import java.util.List;

/**
 * Фабрика для создания MapperFactory.
 */
@Component
public class MapperFacadeImpl implements MapperFacade {

    private final MapperFactory mapperFactory;

    @Autowired
    public MapperFacadeImpl(MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
        configureClassMap();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S, D> D map(S sourceObject, Class<D> destinationClass) {
        return mapperFactory.getMapperFacade().map(sourceObject, destinationClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S, D> void map(S sourceObject, D destinationObject) {
        mapperFactory.getMapperFacade().map(sourceObject, destinationObject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S, D> List<D> mapAsList(Iterable<S> source, Class<D> destinationClass) {
        return mapperFactory.getMapperFacade().mapAsList(source, destinationClass);
    }

    private void configureClassMap(){
        mapperFactory.classMap(Country.class, UserOutByIdView.class)
                .field("name","citizenshipName")
                .field("code","citizenshipCode")
                .byDefault()
                .register();

        mapperFactory.classMap(Document.class, UserOutByIdView.class)
                .field("number", "docNumber")
                .field("date","docDate")
                .byDefault()
                .register();
    }
}
