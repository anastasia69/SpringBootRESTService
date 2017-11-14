package web.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;
import web.enums.City;
import web.enums.Position;
import web.enums.Sex;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anastasia on 11.11.2017.
 */

@RunWith(SpringRunner.class)
@JsonTest
public class ConverterTest {

    @Autowired
    private JacksonTester<Employee> json;

    @Test
    public void testSerialize() throws Exception {
        Employee empl1 = new Employee("name1", "surname1", Sex.MALE,
                City.CHERNIHIV, 11453, Position.ARCHITECT);

        assertThat(this.json.write(empl1)).hasJsonPathStringValue("@.name");
        assertThat(this.json.write(empl1)).extractingJsonPathStringValue("@.name")
                .isEqualTo("name1");
        assertThat(this.json.write(empl1)).extractingJsonPathStringValue("@.surname")
                .isEqualTo("surname1");
        assertThat(this.json.write(empl1)).extractingJsonPathStringValue("@.sex")
                .isEqualTo("MALE");
        assertThat(this.json.write(empl1)).extractingJsonPathStringValue("@.city")
                .isEqualTo("CHERNIHIV");
        assertThat(this.json.write(empl1)).extractingJsonPathStringValue("@.position")
                .isEqualTo("ARCHITECT");
    }

    @Test
    public void testDeserialize() throws Exception {
        String str = "{\"name\":\"Name5\",\"surname\":\"Surname16\",\"sex\":\"MALE\"," +
                "\"city\":\"ZAPORIZHIA\",\"salary\":8501,\"position\":\"TESTER\"}";

        assertThat(this.json.parseObject(str).getName()).isEqualTo("Name5");
        assertThat(this.json.parseObject(str).getSurname()).isEqualTo("Surname16");
        assertThat(this.json.parseObject(str).getSex()).isEqualTo(Sex.MALE);
        assertThat(this.json.parseObject(str).getCity()).isEqualTo(City.ZAPORIZHIA);
        assertThat(this.json.parseObject(str).getSalary()).isEqualTo(8501);
        assertThat(this.json.parseObject(str).getPosition()).isEqualTo(Position.TESTER);

    }
}
