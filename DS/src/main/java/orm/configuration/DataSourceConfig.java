package orm.configuration;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.h2.Driver")
                //mem is the name of an in-memory database and testdb is the name of schema that H2 provides, by default.
                .url("jdbc:h2:mem:testdb")
                .build();

    }
}
