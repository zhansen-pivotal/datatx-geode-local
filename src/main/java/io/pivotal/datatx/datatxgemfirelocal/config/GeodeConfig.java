package io.pivotal.datatx.datatxgemfirelocal.config;


import io.pivotal.datatx.datatxgemfirelocal.security.TestSecurityManager;
import org.apache.geode.cache.GemFireCache;
import org.apache.geode.security.SecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.gemfire.ReplicatedRegionFactoryBean;
import org.springframework.data.gemfire.config.annotation.*;

@CacheServerApplication(name = "ServerApplication")
@EnableLocator
@EnableManager
@EnableLogging(logFile = "spring-geode-server.log")
@EnablePdx(readSerialized = true)
public class GeodeConfig {

  public static final String TEST_REGION = "test";

  @Bean
  SecurityManager testSecurityManager(Environment environment) {
    return new TestSecurityManager(environment);
  }

  @Bean(TEST_REGION)
  ReplicatedRegionFactoryBean<String, String> testRegion(GemFireCache gemFireCache){
    ReplicatedRegionFactoryBean<String,String> testRegion = new ReplicatedRegionFactoryBean<>();
    testRegion.setCache(gemFireCache);
    testRegion.setClose(false);
    testRegion.setName(TEST_REGION);
    return testRegion;
  }

}
