package ru.nazarov.practice.organization.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.nazarov.practice.organization.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager em;

    @Autowired
    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Organization> getList() {
        return null;
    }

    @Override
    public Organization getById(long id) {
        TypedQuery<Organization> query = em.createQuery("SELECT o FROM Organization o WHERE o.id =:id", Organization.class)
                .setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void update(Organization organization) {

    }

    @Override
    public void save(Organization organization) {
    }
}
