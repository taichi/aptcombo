/*
 * Copyright 2015 SATO taichi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package examples;

import javax.inject.Singleton;

import dagger.Component;
import examples.dao.DaoModule;

/**
 * @author taichi
 */
@Singleton
@Component(modules = { TestDataSourceModule.class, DaoModule.class })
public interface UnitTestInjector {

	DbResource resource();
	
	void inject(BatchDeleteTest test);

	void inject(BatchInsertTest test);

	void inject(BatchUpdateTest test);

	void inject(DelegateTest test);

	void inject(DeleteTest test);

	void inject(InsertTest test);

	void inject(SelectTest test);

	void inject(UpdateTest test);

	static UnitTestInjector create() {
		return DaggerUnitTestInjector.create();
	}
}
