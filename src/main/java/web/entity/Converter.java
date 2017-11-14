package web.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;

/**
 * Created by Anastasia on 01.11.2017.
 */
public class Converter {

    private ObjectMapper mapper = new ObjectMapper();

    public void toJson(List<Employee> list, String fileName) throws SQLException, IOException {
        mapper.writeValue(new File(fileName), list);
    }

    public List<Employee> toJavaObject(String fileName) throws IOException {
        return mapper.readValue(new File(fileName), new TypeReference<List<Employee>>() {});
    }

}


