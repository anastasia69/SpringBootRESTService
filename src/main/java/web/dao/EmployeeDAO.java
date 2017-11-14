package web.dao;

import web.entity.Employee;
import web.search.SearchCriteria;

import java.util.List;

/**
 * Created by Anastasia on 06.11.2017.
 */
public interface EmployeeDAO {

    Long create(Employee employee);
    Employee read(Long id);
    void delete(Long id);
    void update(Employee employee);

    List<Employee> getAll();
    List<Employee> searchEmployee(List<SearchCriteria> params);

}
