package examples.dao;

import org.seasar.doma.jdbc.Config;

import dagger.Module;
import dagger.Provides;
import examples.AppConfig;

/**
 * @author taichi
 */
@Module(library = true, includes = AppConfig.class)
public class DaoModule {

	@Provides
	public EmployeeDao employee(Config config) {
		return new EmployeeDaoImpl(config);
	}
}
