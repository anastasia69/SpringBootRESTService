package web.service;

import web.entity.Employee;
import web.search.SearchCriteria;

import java.util.List;

/**
 * Created by Anastasia on 06.11.2017.
 */
public interface EmployeeService {

    Long create(Employee employee);
    Employee read(Long id);
    void update(Employee employee);
    void delete(Long id);

    List<Employee> getAll();
    List<Employee> searchEmployee(List<SearchCriteria> params);
}
