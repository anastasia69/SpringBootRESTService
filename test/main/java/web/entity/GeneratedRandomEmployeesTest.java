package web.entity;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import web.service.EmployeeService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anastasia on 05.11.2017.
 */

@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner.class)
public class GeneratedRandomEmployeesTest extends Assert {

    private static GeneratedRandomEmployees randomEmployees = new GeneratedRandomEmployees();
    private static int listSize;
    private static Converter converter;
    private static List<Employee> employees;
    private static String fileNameEmployees;
    private static String fileNameEmployeeName;
    private static String fileNameEmployeeSurname;
    private static String fileNameEmployeeSurnameUnique;

    @Autowired
    @Qualifier("employeeService")
    private EmployeeService employeeService;

    @BeforeClass
    public static void initialization() throws IOException, NoMoreSurnamesException {
        converter = new Converter();
        employees = new ArrayList<>();
        fileNameEmployees = "test/main/resources/employeesTest.json";
        fileNameEmployeeName = "test/main/resources/employeeNameTest.json";
        fileNameEmployeeSurname = "test/main/resources/employeeSurnameTest.json";
        fileNameEmployeeSurnameUnique = "test/main/resources/employeeSurnameUniqueTest.json";
    }


    public void clear() {
        employees = employeeService.getAll();
        for (int i = 0; i < employees.size(); i++) {
            employeeService.delete(employees.get(i).getId());
        }
    }

    @Test
    public void zeroSizeListTest() throws IOException, SQLException,
            NoMoreSurnamesException, IllegalListSizeException{
        listSize = 0;

        try {
            randomEmployees
                    .generatedRandomList(
                            listSize,
                            fileNameEmployeeName,
                            fileNameEmployeeSurname,
                            fileNameEmployees,
                            employeeService
                    );
        }catch (IllegalListSizeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void canWeHaveMoreThan2SameSurnamesTest() throws IOException, SQLException,
            NoMoreSurnamesException, IllegalListSizeException {
        listSize = 5;

        try {
            randomEmployees
                    .generatedRandomList(
                            listSize,
                            fileNameEmployeeName,
                            fileNameEmployeeSurnameUnique,
                            fileNameEmployees,
                            employeeService
                    );

        }catch (NoMoreSurnamesException e) {
            e.printStackTrace();
        } finally {
            clear();
        }
    }

    @Test
    public void isNameSurnameUniqueTest() throws IOException, SQLException,
            NoMoreSurnamesException, IllegalListSizeException {
        listSize = 15;

        randomEmployees
                .generatedRandomList(
                        listSize,
                        fileNameEmployeeName,
                        fileNameEmployeeSurname,
                        fileNameEmployees,
                        employeeService
                );

        employees = converter.toJavaObject(fileNameEmployees);

        for (int i = 0; i < employees.size(); i++) {
            System.out.println(employees.get(i).getName()
                    + " " + employees.get(i).getSurname());
        }

        for (int i = 0; i < employees.size() - 1; i++) {
            for (int j = i + 1; j < employees.size(); j++) {
                if (employees.get(i).getSurname().equals(employees.get(j).getSurname())) {
                    assertNotEquals(employees.get(i).getName(), employees.get(j).getName());
                }
            }
        }
    }


}
