package examples;

import java.sql.Timestamp;

import javax.inject.Inject;

import org.junit.Rule;
import org.junit.Test;
import org.seasar.doma.jdbc.tx.TransactionManager;

import dagger.Module;
import examples.dao.DaoModule;
import examples.dao.EmployeeDao;
import examples.domain.Salary;
import examples.entity.Employee;
import examples.entity.JobType;

@Module(injects = InsertTest.class, includes = DaoModule.class)
public class InsertTest {

	@Rule
	public DbResource resource = new DbResource();

	@Inject
	EmployeeDao dao;

	@Inject
	TransactionManager tm;

	@Test
	public void testInsert() throws Exception {
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
