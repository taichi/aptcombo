package examples;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.tx.TransactionManager;

import dagger.Module;
import dagger.ObjectGraph;
import examples.dao.DaoModule;
import examples.dao.EmployeeDao;

@Module(injects = DelegateTest.class, includes = DaoModule.class)
public class DelegateTest {

	@Inject
	EmployeeDao dao;

	@Inject
	Config config;

	@Inject
	DbResource resource;

	@Before
	public void setUp() {
		ObjectGraph.create(this).inject(this);
		resource.before();
	}

	@After
	public void tearDown() {
		resource.after();
	}

	@Test
	public void testDelegate() throws Exception {
		TransactionManager tm = config.getTransactionManager();

		tm.required(() -> {
			assertEquals(14, dao.count());
		});
	}

}
