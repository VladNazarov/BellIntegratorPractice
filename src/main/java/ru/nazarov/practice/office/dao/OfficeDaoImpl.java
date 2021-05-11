package ru.nazarov.practice.office.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nazarov.practice.exception.DataNotFoundException;
import ru.nazarov.practice.office.model.Office;
import ru.nazarov.practice.office.view.OfficeFilterView;
import ru.nazarov.practice.organization.dao.OrganizationDao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager entityManager;
    private final OrganizationDao orgDao;

    @Autowired
    public OfficeDaoImpl(EntityManager entityManager, OrganizationDao orgDao) {
        this.entityManager = entityManager;
        this.orgDao = orgDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> getListByFilter(OfficeFilterView officeFilter) {

        if(orgDao.getById(officeFilter.getOrgId()) == null){
            throw new DataNotFoundException("Organization with this id not found");

        }else {
            String name = officeFilter.getName();
            String phone = officeFilter.getPhone();
            Boolean isActive = officeFilter.getActive();

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Office> criteriaQuery = builder.createQuery(Office.class);
            Root<Office> root = criteriaQuery.from(Office.class);

            Predicate filter = builder.equal(root.get("organization").get("id"), officeFilter.getOrgId());
            if (name != null) {
                filter = builder.and(filter, builder.equal(root.get("name"), name));
            }

            if (phone != null) {
                filter = builder.and(filter, builder.equal(root.get("phone"), phone));
            }

            if (isActive != null) {
                filter = builder.and(filter, builder.equal(root.get("isActive"), isActive));
            }
            criteriaQuery.select(root).where(filter);

            TypedQuery<Office> query = entityManager.createQuery(criteriaQuery);
            return query.getResultList();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office getById(Long id) {
        Query query = entityManager.createNativeQuery("SELECT o.* FROM Office o WHERE o.id =:id", Office.class).setParameter("id", id);
        try {
            return (Office) query.getSingleResult();
        } catch (NoResultException e) {
            throw new DataNotFoundException("Office with this id not found",e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Office office) {
        entityManager.merge(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Office office) {
        entityManager.persist(office);
    }
}
