package ru.nazarov.practice.organization.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.nazarov.practice.exception.DataNotFoundException;
import ru.nazarov.practice.organization.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager entityManager;

    @Autowired
    public OrganizationDaoImpl(EntityManager em) {
        this.entityManager = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> getListByFilter(Organization orgFilter) {
        String name = orgFilter.getName();
        String inn = orgFilter.getInn();
        Boolean active = orgFilter.isActive();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = builder.createQuery(Organization.class);
        Root<Organization> root = criteriaQuery.from(Organization.class);

        Predicate filter = builder.equal(root.get("name"), name);
        if (inn != null) {
            filter = builder.and(filter, builder.equal(root.get("inn"), inn));
        }

        if (active != null) {
            filter = builder.and(filter, builder.equal(root.get("isActive"), active));
        }

        criteriaQuery.select(root).where(filter);

        TypedQuery<Organization> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization getById(Long id) {
        TypedQuery<Organization> query = entityManager.createQuery("SELECT o FROM Organization o WHERE o.id =:id", Organization.class)
                .setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new DataNotFoundException("Organization with this id not found", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Organization organization) {
        entityManager.merge(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Organization organization) {
        entityManager.persist(organization);
    }
}
