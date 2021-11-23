package com.ggggght.learningjava8.junit;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
// import org.junit.jupiter.params.ParameterizedTest;
// import org.junit.jupiter.params.provider.ValueSource;
// @ExtendWith(Sleep.class)
public class JunitTest {
  @Test
  void moduleNameIsTestBase() {
    var expected = "learningjava";
    var actual = getClass().getModule().getName();
    // assertEquals(expected, actual);
  }
}
