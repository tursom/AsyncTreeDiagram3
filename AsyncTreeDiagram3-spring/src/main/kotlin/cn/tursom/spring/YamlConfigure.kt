package cn.tursom.spring

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.core.io.ClassPathResource

@Configuration
open class YamlConfigure {
  @Bean
  open fun properties(): PropertySourcesPlaceholderConfigurer? {
    val propertySourcesPlaceholderConfigurer = PropertySourcesPlaceholderConfigurer()
    val yaml = YamlPropertiesFactoryBean()
    yaml.setResources(ClassPathResource("application.yml"))
    propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject())
    return propertySourcesPlaceholderConfigurer
  }
}