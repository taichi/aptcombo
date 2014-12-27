package examples;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Rule;
import org.junit.Test;
import org.seasar.doma.jdbc.tx.TransactionManager;

import dagger.Module;
import examples.dao.EmployeeDao;

/**
 * @author nakamura-to
 * @author taichi
 */
@Module(injects = DelegateTest.class, includes = DbResource.class)
public class DelegateTest {

	@Rule
	public DbResource resource = new DbResource();

	@Inject
	EmployeeDao dao;

	@Inject
	TransactionManager tm;

	@Test
	public void testDelegate() throws Exception {
		tm.required(() -> {
			assertEquals(14, dao.count());
		});
	}

}
