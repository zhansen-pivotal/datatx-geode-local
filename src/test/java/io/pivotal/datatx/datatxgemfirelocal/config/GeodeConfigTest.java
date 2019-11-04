package io.pivotal.datatx.datatxgemfirelocal.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.geode.cache.Region;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class GeodeConfigTest {

  private static final String TEST_KEY = "test-key";
  private static final String TEST_VALUE = "test-value";

  @Autowired
  @Qualifier(GeodeConfig.DEFAULT_REGION)
  Region<String, String> defaultRegion;

  @Test
  public void testThatOurCacheAndRegionAreConfigured() {
    defaultRegion.put(TEST_KEY, TEST_VALUE);
    String result = defaultRegion.get(TEST_KEY);
    assertNotNull(result);
    log.info("GeodeConfigTest.testThatOurCacheAndRegionAreConfigured : assertNotNull(result) -> " +
            "PASS");
    assertEquals(TEST_VALUE, result);
    log.info("GeodeConfigTest.testThatOurCacheAndRegionAreConfigured : assertEquals({},{}}) -> " +
            "PASS", TEST_VALUE, result);
  }
}