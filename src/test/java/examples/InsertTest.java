package examples;

import java.sql.Timestamp;

import javax.inject.Inject;

import org.junit.Rule;
import org.junit.Test;
import org.seasar.doma.jdbc.tx.TransactionManager;

import dagger.Module;
import examples.dao.EmployeeDao;
import examples.domain.Salary;
import examples.entity.Employee;
import examples.entity.JobType;

/**
 * @author nakamura-to
 * @author taichi
 */
@Module(injects = InsertTest.class, includes = DbResource.class)
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
			employee.setName("test").setAge(50).setSalary(new Salary(300))
					.setJobType(JobType.PRESIDENT);
			dao.insert(employee);
		});
	}

	@Test
	public void testInsertWithSqlFile() throws Exception {
		tm.required(() -> {
			Employee employee = new Employee();
			employee.setId(100)
					.setName("test")
					.setAge(50)
					.setSalary(new Salary(300))
					.setJobType(JobType.PRESIDENT)
					.setInsertTimestamp(
							new Timestamp(System.currentTimeMillis()))
					.setVersion(1);
			dao.insertWithSqlFile(employee);
		});
	}
}
