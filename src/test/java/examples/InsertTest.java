package examples;

import java.sql.Timestamp;

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
import examples.entity.JobType;

@Module(injects = InsertTest.class, includes = DaoModule.class)
public class InsertTest {

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
	public void testInsert() throws Exception {
		TransactionManager tm = config.getTransactionManager();

		tm.required(() -> {
			Employee employee = new Employee();
			employee.setName("test");
			employee.setAge(50);
			employee.setSalary(new Salary(300));
			employee.setJobType(JobType.PRESIDENT);
			dao.insert(employee);
		});
	}

	@Test
	public void testInsertWithSqlFile() throws Exception {
		TransactionManager tm = config.getTransactionManager();

		tm.required(() -> {
			Employee employee = new Employee();
			employee.setId(100);
			employee.setName("test");
			employee.setAge(50);
			employee.setSalary(new Salary(300));
			employee.setJobType(JobType.PRESIDENT);
			employee.setInsertTimestamp(new Timestamp(System
					.currentTimeMillis()));
			employee.setVersion(1);
			dao.insertWithSqlFile(employee);
		});
	}
}
