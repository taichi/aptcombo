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
import examples.domain.Salary;
import examples.entity.Employee;

@Module(injects = BatchUpdateTest.class, includes = DaoModule.class)
public class BatchUpdateTest {

	@Rule
	public DbResource resource = new DbResource();

	@Inject
	EmployeeDao dao;

	@Inject
	Config config;

	@Test
	public void testBatchUpdate() throws Exception {
		TransactionManager tm = config.getTransactionManager();

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
