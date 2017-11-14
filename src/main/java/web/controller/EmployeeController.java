package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.entity.*;
import web.search.SearchCriteria;
import web.service.EmployeeService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Anastasia on 31.10.2017.
 */

@Controller
public class EmployeeController {

    private Integer listSize = null;
    private Converter converter = new Converter();
    private List<SearchCriteria> params;
    private static GeneratedRandomEmployees randomEmployees =
                       GeneratedRandomEmployees.getInstance();

    @Autowired
    @Qualifier("employeeService")
    private EmployeeService employeeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcomePage() {

        return "index";
    }

    @RequestMapping(value = "/protected", method = RequestMethod.GET)
    public String protectedPage() {

        return "protected";
    }

    @RequestMapping(value = "/employeesTable", method = {RequestMethod.POST, RequestMethod.GET})
    public String employees(Integer listSize) throws IOException, SQLException,
                                           NoMoreSurnamesException, IllegalListSizeException {
        this.listSize = listSize;

        randomEmployees
                .generatedRandomList(
                        listSize,
                        "src/main/webApp/employeeName.json",
                        "src/main/webApp/employeeSurname.json",
                        "src/main/webApp/employees.json",
                        employeeService);

        return "employeesTable";
    }

    @RequestMapping(value = "/userPage", method = RequestMethod.GET)
    public String userPage() throws IOException, SQLException,
            NoMoreSurnamesException, IllegalListSizeException {

        if (listSize == null) {
            randomEmployees
                    .generatedRandomList(
                            10,
                            "src/main/webApp/employeeName.json",
                            "src/main/webApp/employeeSurname.json",
                            "src/main/webApp/employees.json",
                            employeeService);
        }

        return "employeesTable";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String findAll(@RequestParam(value = "search", required = false)
                                  String search) throws IOException,
            SQLException, NoMoreSurnamesException, IllegalListSizeException {

        params = new ArrayList<>();

        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");

            while (matcher.find()) {
                params.add(
                        new SearchCriteria(
                                matcher.group(1),
                                matcher.group(2),
                                matcher.group(3)
                        ));
            }
        }

        List<Employee> emplSearch = employeeService.searchEmployee(params);
        converter.toJson(emplSearch, "src/main/webApp/employeesSearch.json");

        return "search";
    }

}
