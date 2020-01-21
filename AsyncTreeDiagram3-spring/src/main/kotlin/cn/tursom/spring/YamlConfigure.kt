package cn.tursom.spring

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.PathResource

@Configuration
open class YamlConfigure {
  @Bean
  open fun properties(): PropertySourcesPlaceholderConfigurer? {
    val propertySourcesPlaceholderConfigurer = PropertySourcesPlaceholderConfigurer()
    val yaml = YamlPropertiesFactoryBean()
    yaml.setResources(
      PathResource("config/application.yml"),
      PathResource("application.yml"),
      ClassPathResource("config/application.yml"),
      ClassPathResource("application.yml")
    )
    propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject())
    return propertySourcesPlaceholderConfigurer
  }
}