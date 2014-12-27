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
package examples;

import javax.inject.Inject;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.seasar.doma.jdbc.tx.TransactionManager;

import dagger.Module;
import dagger.ObjectGraph;
import examples.dao.DaoModule;
import examples.dao.EmployeeDao;

/**
 * @author nakamura-to
 * @author taichi
 */
@Module(injects = DbResource.class, includes = { TestDataSourceModule.class,
		DaoModule.class })
public class DbResource implements MethodRule {

	@Inject
	TransactionManager tm;

	@Inject
	EmployeeDao dao;

	@Override
	public Statement apply(Statement base, FrameworkMethod method, Object target) {
		return new Statement() {

			@Override
			public void evaluate() throws Throwable {
				ObjectGraph og = ObjectGraph.create(target, DbResource.this);
				og.inject(DbResource.this);
				og.inject(target);
				try {
					tm.required(dao::create);
					base.evaluate();
				} finally {
					tm.required(dao::drop);
				}
			}
		};
	}
}
