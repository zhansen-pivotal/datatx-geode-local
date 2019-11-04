package io.pivotal.datatx.datatxgemfirelocal.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.geode.security.SecurityManager;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Properties;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class TestSecurityManagerTest {

  @Autowired
  Environment environment;

  @Autowired
  @Qualifier("securityManager")
  SecurityManager testSecurityManager;

  private Properties securityProperties;

  @Test
  public void testThatAuthenticationWired() {
    securityProperties = getProperties();
    assertNotNull(testSecurityManager.authenticate(securityProperties));
  }

  @Test
  public void testThatAuthorizationWired() {
    securityProperties = getProperties();
    assertTrue(testSecurityManager.authorize(securityProperties, null));
  }

  private Properties getProperties() {
    Properties provided = new Properties();
    provided.setProperty(TestSecurityManager.GEMFIRE_SECURITY_USERNAME_PROPERTY,
            environment.getProperty(TestSecurityManager.SPRING_SECURITY_USERNAME_PROPERTY));
    provided.setProperty(TestSecurityManager.GEMFIRE_SECURITY_PASSWORD_PROPERTY,
            environment.getProperty(TestSecurityManager.SPRING_SECURITY_PASSWORD_PROPERTY));
    return provided;
  }
}