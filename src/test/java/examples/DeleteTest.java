package examples;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.seasar.doma.jdbc.tx.TransactionManager;

import examples.dao.EmployeeDao;
import examples.entity.Employee;

/**
 * @author nakamura-to
 * @author taichi
 */
public class DeleteTest {
	
	static final UnitTestInjector injector = UnitTestInjector.create();
	
	@Rule
	public DbResource resource = injector.resource();

	@Inject
	EmployeeDao dao;

	@Inject
	TransactionManager tm;
	
	@Before
	public void setUp() {
		injector.inject(this);
	}

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
