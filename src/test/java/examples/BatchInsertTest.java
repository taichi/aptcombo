package examples;

import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.seasar.doma.jdbc.tx.TransactionManager;

import examples.dao.EmployeeDao;
import examples.dao.EmployeeDaoImpl;
import examples.domain.Salary;
import examples.entity.Employee;

public class BatchInsertTest {

    @Rule
    public final DbResource dbResource = new DbResource();

    private final EmployeeDao dao = new EmployeeDaoImpl();

    @Test
    public void testBatchInsert() throws Exception {
        TransactionManager tm = AppConfig.singleton().getTransactionManager();

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
