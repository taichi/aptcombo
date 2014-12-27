package examples;

import java.util.Arrays;

import javax.inject.Inject;

import org.junit.Rule;
import org.junit.Test;
import org.seasar.doma.jdbc.tx.TransactionManager;

import dagger.Module;
import examples.dao.EmployeeDao;
import examples.domain.Salary;
import examples.entity.Employee;

/**
 * @author nakamura-to
 * @author taichi
 */
@Module(injects = BatchInsertTest.class, includes = DbResource.class)
public class BatchInsertTest {

	@Rule
	public DbResource resource = new DbResource();

	@Inject
	EmployeeDao dao;

	@Inject
	TransactionManager tm;

	@Test
	public void testBatchInsert() throws Exception {
		tm.required(() -> {
			Employee employee1 = new Employee();
			employee1.setName("test-1");
			employee1.setAge(30);
			employee1.setSalary(new Salary(300));

			Employee employee2 = new Employee();
			employee2.setName("test-2");
			employee2.setAge(40);
			employee2.setSalary(new Salary(500));

			dao.batchInsert(Arrays.asList(employee1, employee2));
		});
	}
}
