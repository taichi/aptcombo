package examples;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.seasar.doma.jdbc.tx.TransactionManager;

import examples.dao.EmployeeDao;

/**
 * @author nakamura-to
 * @author taichi
 */
public class DelegateTest {
	
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
	public void testDelegate() throws Exception {
		tm.required(() -> {
			assertEquals(14, dao.count());
		});
	}

}
