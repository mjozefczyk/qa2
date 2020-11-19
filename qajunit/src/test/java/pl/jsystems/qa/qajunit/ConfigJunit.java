package pl.jsystems.qa.qajunit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class ConfigJunit {

    @BeforeEach
    public void setUpBeforeEach() {
        System.out.println("======Befor each test ==========");

    }

    @BeforeAll
    public static void setUpBeforeAll() {

        System.out.println("======Befor all test ==========");
    }

    @AfterEach
    public void teraDownAfterEach() {
        System.out.println("======After each test ==========");

    }

    @AfterAll
    public static void tearDowanAfterAll() {
        System.out.println("======After all test ==========");

    }
}
