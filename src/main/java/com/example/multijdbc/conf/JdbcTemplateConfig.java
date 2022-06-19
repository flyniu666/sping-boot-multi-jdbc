package com.example.multijdbc.conf;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class JdbcTemplateConfig {

	@Primary
    @Bean
    JdbcTemplate jdbcTemplateOne(@Qualifier("dsOne") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    @Bean
    JdbcTemplate jdbcTemplateTwo(@Qualifier("dsTwo") DataSource ds) {
        return new JdbcTemplate(ds);
    }
}
