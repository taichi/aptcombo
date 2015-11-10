package examples;

import java.util.Arrays;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.seasar.doma.jdbc.tx.TransactionManager;

import examples.dao.EmployeeDao;
import examples.domain.Salary;
import examples.entity.Employee;

/**
 * @author nakamura-to
 * @author taichi
 */
public class BatchInsertTest {
	
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
	public void testBatchInsert() throws Exception {
		tm.required(() -> {
			Employee employee1 = new Employee().setName("test-1").setAge(30)
					.setSalary(new Salary(300));

			Employee employee2 = new Employee().setName("test-2").setAge(40)
					.setSalary(new Salary(500));

			dao.batchInsert(Arrays.asList(employee1, employee2));
		});
	}
}
