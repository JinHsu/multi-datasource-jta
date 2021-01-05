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
@EnableJpaRepositories(basePackages = { "cn.sharit.repository1" }, // 设置Repository所在位置
		entityManagerFactoryRef = "localContainerEntityManagerFactoryBean1", //
		transactionManagerRef = "transactionManager")
public class JpaConfig1 {

	@Autowired
	@Qualifier(value = "ds1")
	DataSource ds1;

	@Autowired
	JpaProperties jr;

	@Bean
	LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean1(
			EntityManagerFactoryBuilder builder) {
		return builder //
				.dataSource(ds1) //
				.jta(true) //
				.persistenceUnit("pu1") //
				.properties(jr.getProperties()) //
				.packages("cn.sharit.entity") //
				.build();
	}

}
