package examples;

import java.util.List;

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
public class BatchUpdateTest {
	
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
	public void testBatchUpdate() throws Exception {
		tm.required(() -> {
			List<Employee> list = dao.selectAll();
			for (Employee employee : list) {
				Salary salary = employee.getSalary();
				if (salary != null) {
					employee.setSalary(salary.add(new Salary(100)));
				}
			}
			dao.batchUpdate(list);
		});
	}
}
