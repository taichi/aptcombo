/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package examples.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.seasar.doma.BatchDelete;
import org.seasar.doma.BatchInsert;
import org.seasar.doma.BatchUpdate;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Script;
import org.seasar.doma.Select;
import org.seasar.doma.SelectType;
import org.seasar.doma.Update;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.SelectOptions;
import org.seasar.doma.jdbc.builder.SelectBuilder;

import examples.InjectConfig;
import examples.domain.Salary;
import examples.entity.Employee;
import examples.entity.EmployeeDepartment;

/**
 * @author nakamura-to
 * @author taichi
 */
@Dao
@InjectConfig
public interface EmployeeDao {

	@Select
	Employee selectById(Integer id);

	@Select
	List<Employee> selectByAgeRange(Integer min, Integer max);

	@Select
	List<Employee> selectByAges(List<Integer> ages);

	@Select
	List<Employee> selectByName(String name);

	@Select
	List<Employee> selectByNames(List<String> names);

	@Select
	List<Employee> selectByNotEmptyName(String name);

	@Select
	List<Employee> selectByNameWithPrefixMatching(String prefix);

	@Select
	List<Employee> selectByNameWithSuffixMatching(String suffix);

	@Select
	List<Employee> selectByNameWithInsideMatching(String inside);

	@Select
	List<Employee> selectByHiredateRange(Timestamp from, Timestamp to);

	@Select
	List<Employee> selectBySalary(Salary salary);

	@Select
	Salary selectSummedSalary();

	@Select
	List<Employee> selectByExample(Employee e);

	@Select
	List<Employee> selectAll();

	@Select
	List<Employee> selectAll(SelectOptions options);

	@Select(strategy = SelectType.STREAM)
	<R> R selectByAge(int age, Function<Stream<Employee>, R> mapper);

	default int count() {
		Config config = Config.get(this);
		SelectBuilder builder = SelectBuilder.newInstance(config);
		builder.sql("select count(*) from employee");
		return builder.getScalarSingleResult(int.class);
	}

	@Select
	List<EmployeeDepartment> selectAllEmployeeDepartment();

	@Insert
	int insert(Employee employee);

	@Insert(sqlFile = true)
	int insertWithSqlFile(Employee employee);

	@Update
	int update(Employee employee);

	@Update(sqlFile = true)
	int updateWithSqlFile(Employee employee);

	@Delete
	int delete(Employee employee);

	@Delete(sqlFile = true)
	int deleteWithSqlFile(Employee employee);

	@BatchInsert
	int[] batchInsert(List<Employee> employees);

	@BatchUpdate
	int[] batchUpdate(List<Employee> employees);

	@BatchDelete
	int[] batchDelete(List<Employee> employees);

	@Script
	void create();

	@Script
	void drop();

}
