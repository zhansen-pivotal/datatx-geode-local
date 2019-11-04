package io.pivotal.datatx.datatxgemfirelocal.config;

import io.pivotal.datatx.datatxgemfirelocal.security.TestSecurityManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.geode.cache.Region;
import org.apache.geode.security.SecurityManager;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Properties;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class GeodeConfigTest {

  private static final String TEST_KEY = "test-key";
  private static final String TEST_VALUE = "test-value";

  @Autowired
  @Qualifier(GeodeConfig.DEFAULT_REGION)
  Region<String, String> defaultRegion;

  @Autowired
  @Qualifier("securityManager")
  SecurityManager testSecurityManager;

  @Autowired
  Environment environment;


  private Properties getCredentials() {
    Properties provided = new Properties();
    provided.setProperty(TestSecurityManager.GEMFIRE_SECURITY_USERNAME_PROPERTY,
            environment.getProperty(TestSecurityManager.SPRING_SECURITY_USERNAME_PROPERTY));
    provided.setProperty(TestSecurityManager.GEMFIRE_SECURITY_PASSWORD_PROPERTY,
            environment.getProperty(TestSecurityManager.SPRING_SECURITY_PASSWORD_PROPERTY));
    return provided;
  }

  @Test
  public void testThatAuthenticationWired() {
    assertNotNull(testSecurityManager.authenticate(getCredentials()));
  }

  @Test
  public void testThatAuthorizationWired() {
    assertTrue(testSecurityManager.authorize(getCredentials(), null));
  }

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