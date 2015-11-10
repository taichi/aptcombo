package examples;

import java.util.List;

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
public class BatchDeleteTest {
	
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
	public void testBatchDelete() throws Exception {
		tm.required(() -> {
			List<Employee> list = dao.selectAll();
			dao.batchDelete(list);
		});
	}
}
