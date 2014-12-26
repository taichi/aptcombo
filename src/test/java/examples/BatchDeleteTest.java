package examples;

import java.util.List;

import javax.inject.Inject;

import org.junit.Rule;
import org.junit.Test;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.tx.TransactionManager;

import dagger.Module;
import examples.dao.DaoModule;
import examples.dao.EmployeeDao;
import examples.entity.Employee;

@Module(injects = BatchDeleteTest.class, includes = DaoModule.class)
public class BatchDeleteTest {

	@Rule
	public DbResource resource = new DbResource();

	@Inject
	EmployeeDao dao;

	@Inject
	Config config;

	@Test
	public void testBatchDelete() throws Exception {
		TransactionManager tm = config.getTransactionManager();
		tm.required(() -> {
			List<Employee> list = dao.selectAll();
			dao.batchDelete(list);
		});
	}
}
