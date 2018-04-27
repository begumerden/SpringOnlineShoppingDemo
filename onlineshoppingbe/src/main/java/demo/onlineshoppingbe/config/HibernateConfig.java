package demo.onlineshoppingbe.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author: b.erden
 * @date: 15.4.2018
 */

// This config can be also done via properties file

@Configuration
@ComponentScan(basePackages = {"demo.onlineshoppingbe"})
@EnableTransactionManagement
public class HibernateConfig {

    private final static String DATABASE_URL = "jdbc:h2:tcp://localhost/~/onlineshopping";
    private final static String DATABASE_DRIVER = "org.h2.Driver";
    private final static String DATABASE_DIALECT = "org.hibernate.dialect.H2Dialect";
    private final static String DATABASE_USERNAME = "sa";
    private final static String DATABASE_PASSWORD = "";

    @Bean("dataSource")
    public DataSource getDataSource(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(DATABASE_DRIVER);
        basicDataSource.setUrl(DATABASE_URL);
        basicDataSource.setUsername(DATABASE_USERNAME);
        basicDataSource.setPassword(DATABASE_PASSWORD);
        return basicDataSource;
    }

    @Bean
    public SessionFactory getSessionFactory(DataSource dataSource){
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
        builder.addProperties(getHibernateProperties());
        builder.scanPackages("demo.onlineshoppingbe.dto");
        return builder.buildSessionFactory();
    }

    @Bean
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }


    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect",DATABASE_DIALECT);
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update"); // generating tables / updates according to entities
        return properties;
    }
}
