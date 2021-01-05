package cn.sharit.tx;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = "cn.sharit.repository2", //
		entityManagerFactoryRef = "localContainerEntityManagerFactoryBean2", //
		transactionManagerRef = "transactionManager")
public class JpaConfig2 {

	@Autowired
	@Qualifier(value = "ds2")
	private DataSource ds2;

	@Autowired
	private JpaProperties jr;

	@Bean
	LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean2(
			EntityManagerFactoryBuilder builder) {
		return builder //
				.dataSource(ds2) //
				.jta(true) //
				.persistenceUnit("pu2") //
				.properties(jr.getProperties()) //
				.packages("cn.sharit.entity") //
				.build();
	}

}
