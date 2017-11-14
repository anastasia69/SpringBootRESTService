package web.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import web.entity.Employee;
import web.enums.City;
import web.enums.Position;
import web.enums.Sex;
import web.search.SearchCriteria;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anastasia on 11.11.2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest extends Assert {

    private Employee empl1;
    private long index;
    private long index2;
    private long index3;

    @Autowired
    @Qualifier("employeeService")
    private EmployeeService employeeService;

    @Before
    public void init() {
        empl1 = new Employee("name1", "surname1", Sex.MALE,
                City.CHERNIHIV, 8453, Position.ARCHITECT);
    }

    @After
    public void clear() {
        employeeService.delete(index);
    }

    @Test
    public void createEmployeeTest() {
        index = employeeService.create(empl1);

        assertEquals(empl1.getName(), "name1");
    }

    @Test
    public void readEmployeeTest() {
        index = employeeService.create(empl1);
        Employee empl2 = employeeService.read(index);

        assertEquals(empl2.getName(), empl1.getName());
        assertEquals(empl2.getSurname(), empl1.getSurname());
    }

    @Test
    public void updateEmployeeTest() {
        index = employeeService.create(empl1);
        Employee empl2 = employeeService.read(index);

        empl2.setSurname("blabla");
        employeeService.update(empl2);

        assertEquals(empl2.getSurname(), "blabla");
    }

    @Test
    public void deleteEmployeeTest() {
        Employee empl2 = new Employee("name1", "surname1", Sex.MALE,
                City.CHERNIHIV, 11453, Position.ARCHITECT);
        List<Employee> employeeList = new ArrayList<>();
        List<Employee> employeeListDAO;

        index = employeeService.create(empl1);
        index2 = employeeService.create(empl2);
        employeeService.delete(index2);

        employeeList.add(empl2);
        employeeListDAO = employeeService.getAll();

        assertEquals(employeeListDAO.size(), employeeList.size());
    }

    @Test
    public void getAllEmployeeTest() {
       Employee empl2 = new Employee("name2", "surname2", Sex.MALE,
                City.CHERNIHIV, 11453, Position.TEAM_LEADER);
        List<Employee> employeeListDAO;

        index = employeeService.create(empl1);
        index2 = employeeService.create(empl2);
        employeeListDAO = employeeService.getAll();

        assertEquals(employeeListDAO.size(), 2);

        assertEquals(employeeListDAO.get(0).getName(), empl1.getName());
        assertEquals(employeeListDAO.get(1).getName(), empl2.getName());

        employeeService.delete(index2);
    }

    @Test
    public void searchEmployeeTest() {
        Employee empl2 = new Employee("name23", "surname29", Sex.MALE,
                City.CHERNIHIV, 11111, Position.ARCHITECT);
        Employee empl3 = new Employee("name23", "surname14", Sex.MALE,
                City.CHERNIHIV, 12222, Position.ARCHITECT);
        List<SearchCriteria> criteria = new ArrayList<>();
        List<Employee> employeeList;

        criteria.add(new SearchCriteria("salary", ">", "10000"));
        index = employeeService.create(empl1);
        index2 = employeeService.create(empl2);
        index3 = employeeService.create(empl3);

        employeeList = employeeService.searchEmployee(criteria);

        assertEquals(employeeList.size(), 2);
        assertEquals(employeeList.get(0).getSalary(), Integer.valueOf("11111"));
        assertEquals(employeeList.get(1).getSalary(), Integer.valueOf("12222"));

        employeeService.delete(index2);
        employeeService.delete(index3);
    }

}
