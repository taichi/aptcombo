package examples;

import javax.inject.Inject;

import org.junit.Rule;
import org.junit.Test;
import org.seasar.doma.jdbc.tx.TransactionManager;

import dagger.Module;
import examples.dao.DaoModule;
import examples.dao.EmployeeDao;
import examples.entity.Employee;

/**
 * @author nakamura-to
 * @author taichi
 */
@Module(injects = DeleteTest.class, includes = DaoModule.class)
public class DeleteTest {

	@Rule
	public DbResource resource = new DbResource();

	@Inject
	EmployeeDao dao;

	@Inject
	TransactionManager tm;

	@Test
	public void testDelete() throws Exception {
		tm.required(() -> {
			Employee employee = dao.selectById(1);
			dao.delete(employee);
		});
	}

	@Test
	public void testDeleteWithSqlFile() throws Exception {
		tm.required(() -> {
			Employee employee = dao.selectById(1);
			dao.deleteWithSqlFile(employee);
		});
	}
}
