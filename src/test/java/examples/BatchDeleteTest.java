package examples;

import java.util.List;

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
@Module(injects = BatchDeleteTest.class, includes = DaoModule.class)
public class BatchDeleteTest {

	@Rule
	public DbResource resource = new DbResource();

	@Inject
	EmployeeDao dao;

	@Inject
	TransactionManager tm;

	@Test
	public void testBatchDelete() throws Exception {
		tm.required(() -> {
			List<Employee> list = dao.selectAll();
			dao.batchDelete(list);
		});
	}
}
