package web.dao;

import org.hibernate.Session;
import org.springframework.data.jpa.repository.Modifying;
import web.entity.Employee;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import web.search.SearchCriteria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Anastasia on 06.11.2017.
 */

@Repository("employeeDAO")
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory factory;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Long create(Employee employee) {
        return (Long) factory.getCurrentSession().save(employee);
    }

    @Override
    @Transactional
    public Employee read(Long id) {
        return factory.getCurrentSession().get(Employee.class, id);
    }

    @Override
    @Transactional
    @Modifying
    public void update(Employee employee) {
        factory.getCurrentSession().update(employee);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Employee employee = factory.getCurrentSession().get(Employee.class, id);
        factory.getCurrentSession().delete(employee);
    }

    @Override
    @Transactional
    public List<Employee> getAll() {
        try (Session session = factory.openSession()) {
            return session
                    .createQuery("FROM web.entity.Employee e")
                    .list();
        }
    }

    @Override
    public List<Employee> searchEmployee(List<SearchCriteria> params) {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
        final Root root = query.from(Employee.class);
        Predicate predicate = builder.conjunction();

        for (final SearchCriteria param : params) {

            if (param.getOperation().equalsIgnoreCase(">")) {
                predicate = builder
                        .and(predicate, builder
                                .greaterThanOrEqualTo( root.get(param.getKey()), param.getValue().toString() )
                        );
            } else if (param.getOperation().equalsIgnoreCase("<")) {
                predicate = builder
                        .and(predicate, builder
                                .lessThanOrEqualTo( root.get(param.getKey()), param.getValue().toString() )
                        );
            } else if (param.getOperation().equalsIgnoreCase(":")) {

                if (root.get(param.getKey()).getJavaType() == String.class) {
                    predicate = builder
                            .and(predicate, builder
                                    .like( root.get(param.getKey()), "%" + param.getValue() + "%")
                            );
                } else {
                    predicate = builder
                            .and(predicate, builder
                                    .equal( root.get(param.getKey()), param.getValue() )
                            );
                }
            }
        }
        query.where(predicate);

        return entityManager.createQuery(query).getResultList();
    }

}
