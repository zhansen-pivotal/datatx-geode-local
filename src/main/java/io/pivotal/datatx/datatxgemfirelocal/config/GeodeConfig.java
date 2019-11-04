package io.pivotal.datatx.datatxgemfirelocal.config;


import io.pivotal.datatx.datatxgemfirelocal.security.TestSecurityManager;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class GeodeConfig {

  public static final String DEFAULT_REGION = "default";

  @Bean("securityManager")
  SecurityManager testSecurityManager(Environment environment) {
    return new TestSecurityManager(environment);
  }

  @Bean(DEFAULT_REGION)
  ReplicatedRegionFactoryBean<String, String> defaultRegion(GemFireCache gemFireCache) {
    log.info("GeodeConfig.defaultRegion : initializing REPLICATE region {}", DEFAULT_REGION);
    ReplicatedRegionFactoryBean<String, String> defaultRegion = new ReplicatedRegionFactoryBean<>();
    defaultRegion.setCache(gemFireCache);
    defaultRegion.setClose(false);
    defaultRegion.setName(DEFAULT_REGION);
    log.info("GeodeConfig.defaultRegion : initialized REPLICATE region : {}", DEFAULT_REGION);
    return defaultRegion;
  }

}
