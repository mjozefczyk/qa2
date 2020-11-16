package pl.jsystems.qa.qajunittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import pl.jsystems.qa.Configuration;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Params test")
public class paramsTest extends Configuration {

    @DisplayName("Params")
    @ParameterizedTest(name ="Params test with value {0}")
    @ValueSource(ints = { 5, 15, 25 })
    public void firstParamTest(int number)
    {
        assertTrue(number%5==0);
    }

    @DisplayName("Hello World for string")
    @ParameterizedTest(name ="say hello {0}")
    @ValueSource(strings = {"Hello","Hello junit","Hello students"})
    public void helloWorld(String text)
    {
        assertThat(text).contains("Hello");
    }

    @DisplayName("Multi param")
    @ParameterizedTest(name ="say hello {0}. {1}")
    @CsvSource(value = {"Hello; 5","Hello junit; 5","Hello students; 25"}, delimiter=';')
    public void multiParam(String text, int number)
    {
        assertThat(text).contains("Hello");
        assertTrue(number%5==0);
    }

    @DisplayName("Multi param")
    @ParameterizedTest(name ="say hello {0}. {1}")
    @CsvFileSource(resources = "/plik.csv", delimiter = ',')
    public void paramCSV(String text, int number)
    {
        assertThat(text).contains("hello");
        assertTrue(number%5==0);
    }

}
