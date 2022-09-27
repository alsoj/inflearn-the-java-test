package me.alsoj.inflearnthejavatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@ExtendWith(FindSlowTestExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudyTest {

    int value = 1;

    @Order(2)
    @FastTest
    @Test
    @DisplayName("스터디 만들기 fast")
    void create_new_study() {
        System.out.println("value = " + value++);
    }

    @Order(1)
    @SlowTest
    @Test
    @DisplayName("스터디 한 번 더 만들기 slow")
    void create_new_study_again() throws InterruptedException {
        Thread.sleep(1005L);
        System.out.println("value = " + value++);
    }

    @DisplayName("스터디 만들기")
    @RepeatedTest(value=10, name= "{displayName}, {currentRepetition}/{totalRepetition}")
    void repeatTest(RepetitionInfo repetitionInfo) {
        System.out.println("test" + repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("스터디 만들기 파라미터")
    @ParameterizedTest(name="{index} {displayName}, message={0}")
    @ValueSource(ints = {10, 20, 40})
    void parameterizedTest(Study study){
        System.out.println(study);
    }


    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("before each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("after each");
    }
}