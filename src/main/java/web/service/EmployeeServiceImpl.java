package web.service;

import web.entity.Employee;
import web.dao.EmployeeDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import web.search.SearchCriteria;

import java.util.List;

/**
 * Created by Anastasia on 06.11.2017.
 */

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    @Qualifier("employeeDAO")
    private EmployeeDAO employeeDAO;

    @Override
    public Long create(Employee employee) {
        Long id = employeeDAO.create(employee);
        employee.setId(id);

        return id;
    }

    @Override
    public Employee read(Long id) {
        return employeeDAO.read(id);
    }

    @Override
    public void update(Employee employee) {
        employeeDAO.update(employee);
    }

    @Override
    public void delete(Long id) {
        employeeDAO.delete(id);
    }

    @Override
    public List<Employee> getAll() {
        return employeeDAO.getAll();
    }

    @Override
    public List<Employee> searchEmployee(List<SearchCriteria> params) {
        return employeeDAO.searchEmployee(params);
    }

}
