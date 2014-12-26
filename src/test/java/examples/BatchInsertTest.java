package examples;

import java.util.Arrays;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.tx.TransactionManager;

import dagger.Module;
import dagger.ObjectGraph;
import examples.dao.DaoModule;
import examples.dao.EmployeeDao;
import examples.domain.Salary;
import examples.entity.Employee;

@Module(injects = BatchInsertTest.class, includes = DaoModule.class)
public class BatchInsertTest {

	@Inject
	EmployeeDao dao;

	@Inject
	Config config;

	@Inject
	DbResource resource;

	@Before
	public void setUp() {
		ObjectGraph.create(this).inject(this);
		resource.before();
	}

	@After
	public void tearDown() {
		resource.after();
	}

	@Test
	public void testBatchInsert() throws Exception {
		TransactionManager tm = config.getTransactionManager();

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
