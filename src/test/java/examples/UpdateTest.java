package examples;

import javax.inject.Inject;

import org.junit.Rule;
import org.junit.Test;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.tx.TransactionManager;

import dagger.Module;
import examples.dao.DaoModule;
import examples.dao.EmployeeDao;
import examples.entity.Employee;
import examples.entity.JobType;

@Module(injects = UpdateTest.class, includes = DaoModule.class)
public class UpdateTest {

	@Rule
	public DbResource resource = new DbResource();

	@Inject
	EmployeeDao dao;

	@Inject
	Config config;

	@Test
	public void testUpdate() throws Exception {
		TransactionManager tm = config.getTransactionManager();

		tm.required(() -> {
			Employee employee = dao.selectById(1);
			employee.setName("hoge");
			employee.setJobType(JobType.PRESIDENT);
			dao.update(employee);
		});
	}

	@Test
	public void testUpdateWithSqlFile() throws Exception {
		TransactionManager tm = config.getTransactionManager();

		tm.required(() -> {
			Employee employee = dao.selectById(1);
			employee.setName("hoge");
			employee.setJobType(JobType.PRESIDENT);
			dao.updateWithSqlFile(employee);
		});
	}
}
