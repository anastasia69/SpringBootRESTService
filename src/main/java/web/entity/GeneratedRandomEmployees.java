package web.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import web.enums.City;
import web.enums.Position;
import web.enums.Sex;
import web.service.EmployeeService;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Anastasia on 03.11.2017.
 */
public class GeneratedRandomEmployees {

    private static final Random RANDOM = new Random();
    private static boolean isFirstView = false;
    private ObjectMapper mapper = new ObjectMapper();
    private List<Employee> employees = new ArrayList<>();
    private List<String> surnamesList;
    private List<String> namesList;
    private List<String> temporarySurnamesList;
    private List<Sex> sexList;
    private long currentEmployeeId;
    private Converter converter = new Converter();
    private String newName = "";

    private static GeneratedRandomEmployees instance;

    public GeneratedRandomEmployees(){instance = this;}

    public static GeneratedRandomEmployees getInstance(){
        if(instance == null){
            return new GeneratedRandomEmployees();
        }

        return instance;
    }

    public void generatedRandomList(int listSize,
                                    String fileNameEmployeeName, String fileNameEmployeeSurname,
                                    String fileNameEmployees, EmployeeService service)
            throws IOException, SQLException, NoMoreSurnamesException, IllegalListSizeException {

        if (isFirstView) {
            //clear table
            employees = service.getAll();
            for (int i = 0; i < employees.size(); i++) {
                service.delete(employees.get(i).getId());
            }
        }

        int i = 0;
        sexList = Sex.getRandomSex60_40(listSize);

        if (listSize == 0) throw new IllegalListSizeException("Can`t create list with size = 0");

        try {
            namesList = mapper.readValue(new File(fileNameEmployeeName), List.class);
            surnamesList = mapper.readValue(new File(fileNameEmployeeSurname), List.class);
            temporarySurnamesList = new ArrayList<>(surnamesList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        currentEmployeeId = service.create(
                new Employee(
                        randomElement(namesList),
                        randomElement(temporarySurnamesList),
                        sexList.get(0),
                        City.getRandomCity(),
                        RANDOM.nextInt(10001) + 5000,
                        Position.getRandomPosition()
                ));

        employees.add(service.read(currentEmployeeId));

        do {
            i++;

            currentEmployeeId = service.create(
                    new Employee(
                            randomElement(namesList),
                            randomElement(temporarySurnamesList),
                            sexList.get(i),
                            City.getRandomCity(),
                            RANDOM.nextInt(10001) + 5000,
                            Position.getRandomPosition()
                    ));

            employees.add(service.read(currentEmployeeId));

            //if we already have the same surname 2 times
            if (isSurnameEqual() == 2) {
                service.update(service.read(currentEmployeeId));
            }

        }while (i < listSize - 1);

        isFirstView = true;

        converter.toJson(service.getAll(), fileNameEmployees);
    }

    private String randomElement(List<String> itemsList)
                                throws IOException, NoMoreSurnamesException {
        int randomItemIndex;

        if (itemsList.size() == 0) {
            throw new NoMoreSurnamesException("No More Surnames! " +
                    "Try to use less listSize in generatedRandomList()..." +
                    "or you need more surnames in your employeeSurname.json");

        } else {
            randomItemIndex = RANDOM.nextInt(itemsList.size());
        }

        return itemsList.get(randomItemIndex);
    }

    private int isSurnameEqual() throws IOException, NoMoreSurnamesException {
        int flag = 1;
        int listSize = employees.size() - 1;

        for (int i = 0; i < listSize; i++) {
            if (employees.get(listSize).getSurname()
                    .equals(employees.get(i).getSurname())) {
                flag++;

                temporarySurnamesList.remove( employees.get(listSize).getSurname() );

                if ( (flag == 2)
                        & (employees.get(listSize).getName()
                                .equals(employees.get(i).getName()))) {

                    newName = isNamesEqualTo(employees.get(listSize).getName());
                    employees.get(listSize).setName(newName);
                }
            }
        }

        return flag;
    }

    private String isNamesEqualTo(String equalSurnameNameEmployee)
                                    throws IOException, NoMoreSurnamesException {
        List<String> temporaryNamesList = new ArrayList<>(namesList);
        temporaryNamesList.remove(equalSurnameNameEmployee);

        return randomElement(temporaryNamesList);
    }

}
