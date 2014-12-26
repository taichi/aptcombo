package examples;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Rule;
import org.junit.Test;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.tx.TransactionManager;

import dagger.Module;
import examples.dao.DaoModule;
import examples.dao.EmployeeDao;

@Module(injects = DelegateTest.class, includes = DaoModule.class)
public class DelegateTest {

	@Rule
	public DbResource resource = new DbResource();

	@Inject
	EmployeeDao dao;

	@Inject
	Config config;

	@Test
	public void testDelegate() throws Exception {
		TransactionManager tm = config.getTransactionManager();

		tm.required(() -> {
			assertEquals(14, dao.count());
		});
	}

}
