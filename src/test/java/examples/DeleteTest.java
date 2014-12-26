package examples;

import javax.inject.Inject;

import org.junit.Rule;
import org.junit.Test;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.tx.TransactionManager;

import dagger.Module;
import examples.dao.DaoModule;
import examples.dao.EmployeeDao;
import examples.entity.Employee;

@Module(injects = DeleteTest.class, includes = DaoModule.class)
public class DeleteTest {

	@Rule
	public DbResource resource = new DbResource();

	@Inject
	EmployeeDao dao;

	@Inject
	Config config;

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
