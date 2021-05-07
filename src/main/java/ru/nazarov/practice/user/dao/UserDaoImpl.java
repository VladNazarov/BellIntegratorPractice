package ru.nazarov.practice.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nazarov.practice.user.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getListByFilter(User userFilter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);

        Predicate filter = builder.equal(userRoot.get("office").get("id"), userFilter.getOffice().getId());

        if (userFilter.getFirstName() != null) {
            filter = builder.and(filter, builder.equal(userRoot.get("firstName"), userFilter.getFirstName()));/////////Не нравится дублирование
        }

        if (userFilter.getLastName() != null) {
            filter = builder.and(filter, builder.equal(userRoot.get("lastName"), userFilter.getLastName()));
        }
        if (userFilter.getMiddleName() != null) {
            filter = builder.and(filter, builder.equal(userRoot.get("middleName"), userFilter.getMiddleName()));
        }
        if (userFilter.getPosition() != null) {
            filter = builder.and(filter, builder.equal(userRoot.get("position"), userFilter.getPosition()));
        }
        if (userFilter.getDocument() != null) {
            filter = builder.and(filter, builder.equal(userRoot.get("document").get("documentType").get("code"), userFilter.getDocument().getDocumentType().getCode()));

        }
        if (userFilter.getCountry() != null) {
            filter = builder.and(filter, builder.equal(userRoot.get("country").get("code"), userFilter.getCountry().getCode()));
        }

        criteriaQuery.select(userRoot).where(filter);

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

}
