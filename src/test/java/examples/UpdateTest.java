package examples;

import javax.inject.Inject;

import org.junit.Rule;
import org.junit.Test;
import org.seasar.doma.jdbc.tx.TransactionManager;

import dagger.Module;
import examples.dao.EmployeeDao;
import examples.entity.JobType;

/**
 * @author nakamura-to
 * @author taichi
 */
@Module(injects = UpdateTest.class, includes = DbResource.class)
public class UpdateTest {

	@Rule
	public DbResource resource = new DbResource();

	@Inject
	EmployeeDao dao;

	@Inject
	TransactionManager tm;

	@Test
	public void testUpdate() throws Exception {
		tm.required(() -> {
			dao.update(dao.selectById(1).setName("hoge")
					.setJobType(JobType.PRESIDENT));
		});
	}

	@Test
	public void testUpdateWithSqlFile() throws Exception {
		tm.required(() -> {
			dao.updateWithSqlFile(dao.selectById(1).setName("hoge")
					.setJobType(JobType.PRESIDENT));
		});
	}
}
