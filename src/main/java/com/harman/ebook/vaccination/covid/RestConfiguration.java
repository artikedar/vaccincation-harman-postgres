package com.harman.ebook.vaccination.covid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import java.time.Duration;
import javax.persistence.metamodel.Type;

import static com.harman.ebook.vaccination.covid.constants.ApplicationConstants.CONNECTION_TIMEOUT;
import static com.harman.ebook.vaccination.covid.constants.ApplicationConstants.READ_TIMEOUT;

@Configuration
public class RestConfiguration implements RepositoryRestConfigurer {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(
            entityManager.getMetamodel().getEntities().stream()
                .map(Type::getJavaType)
                .toArray(Class[]::new));
        config.setReturnBodyOnCreate(true);
        config.setReturnBodyOnUpdate(true);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        //60 seconds for connection and read timeout
        return builder
                .setConnectTimeout(Duration.ofSeconds(CONNECTION_TIMEOUT))
                .setReadTimeout(Duration.ofSeconds(READ_TIMEOUT))
                .build();
    }
}