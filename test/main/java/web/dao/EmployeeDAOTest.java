package web.dao;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
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
@TestPropertySource(locations = "classpath:application.properties")
@SpringBootTest
public class EmployeeDAOTest extends Assert {

    private Employee empl1;
    private long index;
    private long index2;
    private long index3;

    @Autowired
    @Qualifier("employeeDAO")
    private EmployeeDAO employeeDAO;

    @Before
    public void init() {
        empl1 = new Employee("name1", "surname1", Sex.MALE,
                City.CHERNIHIV, 11453, Position.ARCHITECT);
    }

    @After
    public void clear() {
        employeeDAO.delete(index);
    }

    @Test
    public void createEmployeeTest() {
       index = employeeDAO.create(empl1);

        assertEquals(empl1.getName(), "name1");
        assertEquals(empl1.getSurname(), "surname1");
        assertEquals(empl1.getSex(), Sex.MALE);
        assertEquals(empl1.getCity(), City.CHERNIHIV);
        assertEquals(empl1.getSalary(), Integer.valueOf("11453"));
        assertEquals(empl1.getPosition(), Position.ARCHITECT);
    }

    @Test
    public void readEmployeeTest() {
        index = employeeDAO.create(empl1);
        Employee empl2 = employeeDAO.read(index);

        assertEquals(empl2.getName(), empl1.getName());
        assertEquals(empl2.getSurname(), empl1.getSurname());
        assertEquals(empl2.getSex(), empl1.getSex());
        assertEquals(empl2.getCity(), empl1.getCity());
        assertEquals(empl2.getSalary(), empl1.getSalary());
        assertEquals(empl2.getPosition(), empl1.getPosition());
    }

    @Test
    public void updateEmployeeTest() {
        index = employeeDAO.create(empl1);
        Employee empl2 = employeeDAO.read(index);

        empl2.setName("blabla");
        employeeDAO.update(empl2);

        assertEquals(empl2.getName(), "blabla");
    }

    @Test
    public void deleteEmployeeTest() {
        Employee empl2 = new Employee("name1", "surname1", Sex.MALE,
                City.CHERNIHIV, 11453, Position.ARCHITECT);

        List<Employee> employeeList = new ArrayList<>();
        List<Employee> employeeListDAO;

        index = employeeDAO.create(empl1);
        index2 = employeeDAO.create(empl2);
        employeeList.add(empl2);

        employeeDAO.delete(index2);
        employeeListDAO = employeeDAO.getAll();

        assertEquals(employeeListDAO.size(), employeeList.size());
    }

    @Test
    public void getAllEmployeeTest() {
        Employee empl2 = new Employee("name2", "surname2", Sex.MALE,
                                      City.KIEV, 11453, Position.TEAM_LEADER);
        List<Employee> employeeListDAO;

        index = employeeDAO.create(empl1);
        long index2 = employeeDAO.create(empl2);
        employeeListDAO = employeeDAO.getAll();

        assertEquals(employeeListDAO.size(), 2);

        assertEquals(employeeListDAO.get(0).getName(), empl1.getName());
        assertEquals(employeeListDAO.get(0).getCity(), empl1.getCity());
        assertEquals(employeeListDAO.get(1).getName(), empl2.getName());
        assertEquals(employeeListDAO.get(1).getCity(), empl2.getCity());

        employeeDAO.delete(index2);
    }

    @Test
    public void searchEmployeeTest() {
        Employee empl2 = new Employee("name23", "surname29", Sex.MALE,
                                      City.CHERNIHIV, 11111, Position.ARCHITECT);
        Employee empl3 = new Employee("name23", "surname14", Sex.MALE,
                                     City.CHERNIHIV, 12222, Position.ARCHITECT);
        List<SearchCriteria> criteria = new ArrayList<>();
        List<Employee> employeeList;

        criteria.add(new SearchCriteria("name", ":", "name23"));
        index = employeeDAO.create(empl1);
        index2 = employeeDAO.create(empl2);
        index3 = employeeDAO.create(empl3);

        employeeList = employeeDAO.searchEmployee(criteria);

        assertEquals(employeeList.size(), 2);
        assertEquals(employeeList.get(0).getSurname(), "surname29");
        assertEquals(employeeList.get(1).getSurname(), "surname14");

        employeeDAO.delete(index2);
        employeeDAO.delete(index3);
    }

}
