package io.delivery.config;

import io.delivery.wsclient.WSClient;
import io.delivery.dao.DocumentDao;
import io.delivery.dao.ProductCatalogSectionDao;
import io.delivery.dao.ProductDao;
import io.delivery.dao.ProductImageDao;
import io.delivery.dao.impl.DocumentDaoImpl;
import io.delivery.dao.impl.ProductCatalogSectionDaoImpl;
import io.delivery.dao.impl.ProductDaoImpl;
import io.delivery.dao.impl.ProductImageDaoImpl;
import io.delivery.entity.Document;
import io.delivery.entity.Product;
import io.delivery.entity.ProductCatalogSection;
import io.delivery.entity.ProductImage;
import io.delivery.dao.*;
import io.delivery.dao.impl.*;
import io.delivery.entity.*;
import io.delivery.model.Answer;
import io.delivery.model.NewsCreator;
import io.delivery.model.impl.NewsCreatorImpl;
import io.delivery.service.*;
import io.delivery.service.impl.*;
import net.yandex.speller.services.spellservice.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@PropertySource(value = {"classpath:util.properties"})
@PropertySource(value = {"classpath:auth.properties"})
public class AppConfig {
    @Autowired
    private Environment environment;

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl jdbcImpl = new JdbcDaoImpl();
        jdbcImpl.setDataSource(dataSource());
        jdbcImpl.setUsersByUsernameQuery(environment.getRequiredProperty("usersByQuery"));
        jdbcImpl.setAuthoritiesByUsernameQuery(environment.getRequiredProperty("rolesByQuery"));
        return jdbcImpl;
    }

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc.postgresql.driverClass"));
        dataSource.setUrl(environment.getProperty("jdbc.postgresql.url"));
        dataSource.setUsername(environment.getProperty("jdbc.postgresql.username"));
        dataSource.setPassword(environment.getProperty("jdbc.postgresql.password"));
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    public CreateTable createTable() {
        return new CreateTableImpl(jdbcTemplate());
    }

    @Bean
    public InsertUser insertUser() {
        return new InsertUserImpl(jdbcTemplate());
    }

    @Bean
    public UpdateTable updateTable() {
        return new UpdateTableImpl(jdbcTemplate());
    }

    @Bean
    public SelectTable selectTable() {
        return new SelectTableImpl(jdbcTemplate());
    }

    @Bean
    public PreparedSQL preparedSQL() {
        return new PreparedSQLImpl(jdbcTemplate());
    }

    @Bean
    public DumpDB dumpDB() {
        return new DumpDbImpl();
    }

    @Bean
    public Test test() {
        return new TestImpl("test");
    }

    @Bean
    public Answer answer() {
        return new Answer();
    }

    @Bean
    public DocumentDao documentDao() {
        return new DocumentDaoImpl(Document.class);
    }

    @Bean
    ProductDao productDao() {
        return  new ProductDaoImpl(Product.class);
    }

    @Bean
    ProductCatalogSectionDao productCatalogSectionDao() {
        return new ProductCatalogSectionDaoImpl(ProductCatalogSection.class);
    }

    @Bean
    ProductImageDao productImageDao() { return new ProductImageDaoImpl(ProductImage.class);}

    @Bean
    CommonsMultipartResolver multipartResolver() {return new CommonsMultipartResolver();}

    @Bean
    public NewsCreator newsCreator() {
        return new NewsCreatorImpl(jdbcTemplate());
    }

    @Bean
    NewsDao newsDao() {
        return new NewsDaoImpl(News.class);
    }

    @Bean
    NoRegistrationCustomerDao noRegistrationCustomerDao() {
        return new NoRegistrationCustomerDaoImpl(NoRegistrationCustomer.class);
    }

    @Bean
    public OfficeDao officeDao(){
        return new OfficeDaoImpl(Office.class);
    }

    @Bean
    CustomerDao customerDao() {
        return new CustomerDaoImpl(Customer.class);
    }

    @Bean
    public Client client() {
        return new Client();
    }

    @Bean
    public WSClient wsClient() {
        return new WSClient();
    }
}

