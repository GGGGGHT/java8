package com.ggggght.learningjava8;

import com.ggggght.learningjava8.po.User;
import java.util.List;
import java.util.function.Consumer;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.WithAssertions;
import org.assertj.core.description.Description;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.BDDAssertions.*;

public class AssertJTest  implements WithAssertions {
    static User user;

    static List<String> list = List.of("a","b","c");

    @BeforeAll
    public static void init() {
        Assertions.setPrintAssertionsDescription(true);
        user = new User("ggggght", 19, "123456");
    }

    @Test
    @DisplayName("basic assert")
    public void basic_assert() {
        assertThat(user.getName()).isNotEqualTo("other");
        assertThat(user.getName()).isEqualTo("ggggght");
    }

    @Test
    @DisplayName("chain assert")
    public void chain_assert() {
        assertThat(user.getName()).startsWith("ggg").endsWith("ght").isEqualToIgnoringCase("ggggght");
    }

    @Test
    @DisplayName("collection assert")
    public void collection_assert() {
        assertThat(list).hasSize(3).contains("a").doesNotContain("d");
    }

    @Test
    @DisplayName("as() used to describe the test and will be show when test failed")
    public void as() {
        assertThat(user.getAge()).as("check %s's age", user.getName()).isEqualTo(119);
    }

    @Test
    @DisplayName("throw exception")
    public void throw_exception() {
        assertThatThrownBy(() -> {
            throw new Exception("boom");
        }).isInstanceOf(Exception.class).hasMessageContaining("boom");
    }

    @Test
    public void extracting() {
        assertThat(user).extracting("name","age").contains("ggggght",19);
    }

    @Test
    public void withAssertions_examples() {
        then(user.getAge()).isEqualTo(19);
        then(user.getName()).isEqualTo("ggggght").isNotEqualTo("other");


        then("1").isIn(list);
        then(4).isNotIn(list);
    }

    @Test
    void a_few_simple_assertions() {
        assertThat("The Lord of the Rings").isNotNull()
            .startsWith("The")
            .contains("Lord")
            .endsWith("Rings");
    }

    @Test
    void desc() {
        Assertions.setPrintAssertionsDescription(true);

        assertThat(user.getAge()).as("check %s's age", user.getName()).isEqualTo(19);
    }

    @Test
    void description_consumer() {
        Assertions.setPrintAssertionsDescription(true);

        final StringBuilder descriptionBuilder = new StringBuilder(String.format("Assertions:%n"));
        Consumer<Description> descriptionConsumer = desc -> {
            descriptionBuilder.append(String.format("--- %s%n", desc));
        };

        Assertions.setDescriptionConsumer(descriptionConsumer);
        assertThat(user.getName()).as("check name")
            .isEqualTo("ggggght");
        assertThat(user.getAge()).as("check age")
            .isEqualTo(19);
    }

    @Test
    void overriding_error_message() {
        assertThat(user.getAge())
            .overridingErrorMessage("should be %s", user)
            // .withFailMessage("should be %s", user)
            .isEqualTo(10);
            // .overridingErrorMessage("check %s's age", user.getName()).isEqualTo(119);
    }

    @Test
    void best_practices() {
        // DO THIS
        assertThat(user.getAge()).isEqualTo(19);
        // OR THIS (less classy but ok)
        assertThat("abc".equals("abc")).isTrue();
        // DO THIS (fails as expected)
        assertThat(1).isNotEqualTo(2);
        // less classy but ok
        assertThat(1 == 2).isFalse();

        assertThat("abc").as("description").isEqualTo("abc");
        assertThat("abc").describedAs("description").isEqualTo("abc");

        // DO THIS: use overridingErrorMessage/withFailMessage before the assertion
        assertThat("abc").overridingErrorMessage("custom error message").isEqualTo("abc");
        assertThat("abc").withFailMessage("custom error message").isEqualTo("abc");

        // DO THIS:
        assertThat("abc").usingComparator((o1, o2) -> 0).isEqualTo("a");
    }

    @Test
    void bad_practices() {
        // DON'T DO THIS ! It does not assert anything
        assertThat("abc".equals("abc"));
        // It does not assert anything and passes
        assertThat(1 == 2);

        // DON'T DO THIS ! as/describedAs have no effect after the assertion
        assertThat("abc").isEqualTo("abc").as("description");
        assertThat("abc").isEqualTo("abc").describedAs("description");

        // DON'T DO THIS ! overridingErrorMessage/withFailMessage have no effect after the assertion
        assertThat("abc").isEqualTo("abc").overridingErrorMessage("custom error message");
        assertThat("abc").isEqualTo("abc").withFailMessage("custom error message");

        // DON'T DO THIS ! Comparator is not used
        assertThat("abc").isEqualTo("abc").usingComparator((o1, o2) -> 0);
    }

    private static Object[] getValues() {
        return new Object[] {
            new Object[]{1,2,2},
            new Object[]{2,3,6},
            new Object[]{0,3,0}
        };
    }
    @ParameterizedTest
    @MethodSource("getValues")
    public void multiplyTest(int a,int b, int expected) {
        Assertions.setPrintAssertionsDescription(true);
        assertThat(a * b).as("assert %d * %d = %d", a, b, expected).isEqualTo(expected);
    }
}
