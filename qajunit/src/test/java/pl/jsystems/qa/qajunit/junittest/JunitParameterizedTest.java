package pl.jsystems.qa.qajunit.junittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.jsystems.qa.qajunit.ConfigJunit;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tags({@Tag("paramTest"), @Tag("junit")})
@DisplayName("Parameterized test")
public class JunitParameterizedTest extends ConfigJunit {

    @DisplayName("Parameterized test value {0}")
    @ParameterizedTest(name = "Parameter test with value {0}.")
    @ValueSource(ints = {5, 15, 25})
    public void firstParamTest(int number) {
        assertEquals(number % 5, 0);
    }

    @DisplayName("Param test for string say hello.")
    @ParameterizedTest(name ="Parameterized test: {0}")
    @ValueSource(strings = {"Hello", "Hello junit", "Hello students"})
    public void secondParamTest(String param) {
        assertThat(param).contains("Hello");

    }

    @DisplayName("Pram test with multi param.")
    @ParameterizedTest(name = "Parameterized test for multi param: {0} , {1} ")
    @CsvSource(value = {"Hello; 5", "Hello junit; 5", "'Hello, students'; 25"}, delimiter = ';')
    public void paramMultiTest(String text, int number) {
        assertThat(text).contains("Hello");
        assertEquals(number % 5, 0);

    }

    @DisplayName("Param test with value from csv file")
    @ParameterizedTest(name = "Param test for multi param: {0} , {1}")
    @CsvFileSource(resources = "/plik.csv", delimiter = ',')
    public void paramMultiValueFileTest(String text, int number) {
        assertThat(text).contains("Hello");
        assertEquals(number % 5, 0);
    }

    @DisplayName("Enum param test")
    @ParameterizedTest(name = "Enum param test {0}")
    @EnumSource(value = ParamEnum.class)
    public void enumTest(ParamEnum paramEnum) {
        assertThat(paramEnum.toString()).contains("ENUM");
    }

    String resultString = "Wordpress powers 100% of the internet";
    String expectedString = "Wordpress powers [number]% of the internet";

    @Tag("wordpress")
    @ParameterizedTest(name = "Wordpress test {0}")
    @ValueSource(strings = {"1", "1000", "10000"})
    public void worpressTest(String value) {
        String resultString = "Wordpress powers " + value + "% of the internet";

        assertThat(resultString).startsWith("Wordpress powers ");
        assertThat(resultString).endsWith("% of the internet");
        assertThat(resultString).matches("Wordpress powers \\d+% of the internet");
        String result = resultString.replace("Wordpress powers ", "").replace("% of the internet", "");
        assertThat(result).matches("^\\d+$");
        int resultNumber = Integer.parseInt(result);
        assertThat(resultNumber > 0);
    }

    enum ParamEnum {
        ENUM_ONE,
        ENUM_TWO
    }

}
