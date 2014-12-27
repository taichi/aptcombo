package examples.dao;

import javax.inject.Singleton;

import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.tx.TransactionManager;

import dagger.Module;
import dagger.Provides;
import examples.AppConfig;

/**
 * @author taichi
 */
@Module(library = true, complete = false)
public class DaoModule {

	@Provides
	@Singleton
	public Config config(AppConfig config) {
		return config;
	}

	@Provides
	public TransactionManager tm(Config config) {
		return config.getTransactionManager();
	}

	@Provides
	@Singleton
	public EmployeeDao employee(EmployeeDaoImpl dao) {
		return dao;
	}

}
