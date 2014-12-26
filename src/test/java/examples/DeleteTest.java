package examples;

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
import examples.entity.Employee;

@Module(injects = DeleteTest.class, includes = DaoModule.class)
public class DeleteTest {

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
	public void testDelete() throws Exception {
		TransactionManager tm = config.getTransactionManager();

		tm.required(() -> {
			Employee employee = dao.selectById(1);
			dao.delete(employee);
		});
	}

	@Test
	public void testDeleteWithSqlFile() throws Exception {
		TransactionManager tm = config.getTransactionManager();

		tm.required(() -> {
			Employee employee = dao.selectById(1);
			dao.deleteWithSqlFile(employee);
		});
	}
}
