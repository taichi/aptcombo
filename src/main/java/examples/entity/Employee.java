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
package examples.entity;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.OriginalStates;
import org.seasar.doma.SequenceGenerator;
import org.seasar.doma.Version;

import examples.domain.Salary;

/**
 * @author nakamura-to
 */
@Entity(listener = EmployeeListener.class)
@Data
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(sequence = "EMPLOYEE_SEQ")
	Integer id;

	String name;

	int age;

	Salary salary;

	@Column(name = "JOB_TYPE")
	JobType jobType;

	Date hiredate;

	@Column(name = "DEPARTMENT_ID")
	Integer departmentId;

	@Version
	@Column(name = "VERSION")
	Integer version;

	Timestamp insertTimestamp;

	Timestamp updateTimestamp;

	@OriginalStates
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	Employee originalStates;

}
