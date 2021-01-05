package cn.sharit.tx;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;

@Configuration
public class TxConfig {

	@Bean(name = "transactionManager")
	@DependsOn({ "userTransaction", "userTransactionManager" })
	public PlatformTransactionManager transactionManager() throws Throwable {
		return new JtaTransactionManager(userTransaction(), userTransactionManager());
	}

	@Bean(name = "userTransactionManager", initMethod = "init", destroyMethod = "close")
	public TransactionManager userTransactionManager() throws Throwable {
		UserTransactionManager userTransactionManager = new UserTransactionManager();
		userTransactionManager.setTransactionTimeout(10000);
		userTransactionManager.setForceShutdown(true);
		return userTransactionManager;
	}

	@Bean(name = "userTransaction")
	public UserTransaction userTransaction() throws Throwable {
		UserTransaction userTransactionImp = new UserTransactionImp();
		userTransactionImp.setTransactionTimeout(10000);
		return userTransactionImp;
	}

}
