package ru.nazarov.practice.office.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nazarov.practice.office.model.Office;
import ru.nazarov.practice.office.view.OfficeFilter;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class OfficeDaoImpl implements OfficeDao {
    private final EntityManager entityManager;

    @Autowired
    public OfficeDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Office> getList(OfficeFilter filter) {
        String name = filter.getName();
        long orgId = filter.getOrgId();
        String phone = filter.getPhone();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = builder.createQuery(Office.class);
        Root<Office> root = criteriaQuery.from(Office.class);

        criteriaQuery.select(root).where(builder.equal(root.get("name"), name));
        TypedQuery<Office> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public Office getById(long id) {
        TypedQuery<Office> query = entityManager.createQuery("SELECT o FROM Office o WHERE o.id =:id", Office.class).setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void update(Office office) {
        entityManager.merge(office);
    }

    @Override
    public void save(Office office) {
        entityManager.persist(office);
    }
}
