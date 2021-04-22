package ru.nazarov.practice.organization.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.nazarov.practice.organization.model.Organization;
import ru.nazarov.practice.organization.view.OrganizationFilter;

import javax.persistence.EntityManager;
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

    @Override////Будет доделываться
    public List<Organization> getList(OrganizationFilter orgFilter) {
        String name = orgFilter.getName();
        String inn = orgFilter.getInn();
        Boolean active = orgFilter.isActive();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = builder.createQuery(Organization.class);
        Root<Organization> root = criteriaQuery.from(Organization.class);

        Predicate filter = builder.equal(root.get("name"),name);
        if (inn != null && inn.length()<=12) {
            filter = builder.and(builder.equal(root.get("inn"), inn));
        }

        if (active != null) {
           filter =  builder.and(builder.equal(root.get("active"), active));
        }

        criteriaQuery.select(root).where(filter);

        TypedQuery<Organization> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public Organization getById(Long id) {
        TypedQuery<Organization> query = entityManager.createQuery("SELECT o FROM Organization o WHERE o.id =:id", Organization.class)
                .setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void update(Organization organization) {
        entityManager.merge(organization);
    }

    @Override
    public void save(Organization organization) {
        entityManager.persist(organization);
    }
}
