package examples;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.seasar.doma.jdbc.tx.TransactionManager;

import examples.dao.EmployeeDao;
import examples.entity.JobType;

/**
 * @author nakamura-to
 * @author taichi
 */
public class UpdateTest {
	
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
