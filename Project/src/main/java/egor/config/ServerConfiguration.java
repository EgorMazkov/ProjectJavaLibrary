package egor.config;

import egor.jFrame.utils.repositories.JdbcDataSource;
import egor.jFrame.utils.repositories.MessagesRepository;
import egor.jFrame.utils.repositories.MessagesRepositoryJdbcImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfiguration {
    public ServerConfiguration() {
    }

    @Bean(name = "jdbcDataSource")
    public JdbcDataSource jdbcDataSource() {
        return new JdbcDataSource();
    }

    @Bean
    public MessagesRepository messagesRepository () {
        return new MessagesRepositoryJdbcImpl(jdbcDataSource().getDataSource());
    }
}
