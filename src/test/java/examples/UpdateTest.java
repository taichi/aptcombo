package examples;

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
import examples.entity.Employee;
import examples.entity.JobType;

@Module(injects = UpdateTest.class, includes = DaoModule.class)
public class UpdateTest {

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
