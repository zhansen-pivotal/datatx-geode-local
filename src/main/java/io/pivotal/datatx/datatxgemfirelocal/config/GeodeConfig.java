package io.pivotal.datatx.datatxgemfirelocal.config;


import io.pivotal.datatx.datatxgemfirelocal.security.TestSecurityManager;
import org.apache.geode.security.SecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.gemfire.config.annotation.*;

@CacheServerApplication(name = "ServerApplication")
@EnableLocator
@EnableManager
@EnableLogging(logFile = "spring-geode-server.log")
@EnablePdx(readSerialized = true)
public class GeodeConfig {

  @Bean
  SecurityManager testSecurityManager(Environment environment) {
    return new TestSecurityManager(environment);
  }

}
