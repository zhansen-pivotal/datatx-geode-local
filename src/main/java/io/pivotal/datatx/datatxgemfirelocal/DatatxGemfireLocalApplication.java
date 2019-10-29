package io.pivotal.datatx.datatxgemfirelocal;

import io.pivotal.datatx.datatxgemfirelocal.config.GeodeConfig;
import org.apache.geode.cache.Region;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static org.jgroups.util.Util.assertEquals;
import static org.jgroups.util.Util.assertNotNull;

@SpringBootApplication
public class DatatxGemfireLocalApplication {

  public static void main(String[] args) {
    SpringApplication.run(DatatxGemfireLocalApplication.class, args);
  }

  @Bean
  ApplicationRunner runner(@Qualifier(GeodeConfig.TEST_REGION) Region<String, String> testRegion) {
    return args -> {
      testRegion.put("testKey", "testValue");
      String result = testRegion.get("testKey");
      assertNotNull(result);
      assertEquals("test", result);
    };
  }
}
