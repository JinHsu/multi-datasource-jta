package cn.sharit.ds;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jta.atomikos.AtomikosXADataSourceWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.mysql.cj.jdbc.MysqlXADataSource;

@Configuration
public class DataSourceConfig {

	private final AtomikosXADataSourceWrapper wrapper;

	@Autowired
	private Properties1 properties1;

	@Autowired
	private Properties2 properties2;

	public DataSourceConfig() {
		this.wrapper = new AtomikosXADataSourceWrapper();
	}

	@Primary
	@Bean("ds1")
	public DataSource getDs1() throws Exception {
		MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
		mysqlXADataSource.setUrl(properties1.getJdbcUrl());
		mysqlXADataSource.setUser(properties1.getUsername());
		mysqlXADataSource.setPassword(properties1.getPassword());
		mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
		return this.wrapper.wrapDataSource(mysqlXADataSource);
	}

	@Bean("ds2")
	public DataSource getDs2() throws Exception {
		MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
		mysqlXADataSource.setUrl(properties2.getJdbcUrl());
		mysqlXADataSource.setUser(properties2.getUsername());
		mysqlXADataSource.setPassword(properties2.getPassword());
		mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
		return this.wrapper.wrapDataSource(mysqlXADataSource);
	}

}
