/*
 * Copyright 2014 SATO taichi
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
package examples.dao;

import javax.inject.Singleton;

import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.tx.TransactionManager;

import dagger.Module;
import dagger.Provides;
import examples.AppConfig;

/**
 * @author taichi
 */
@Module(library = true, complete = false)
public class DaoModule {

	@Provides
	@Singleton
	public Config config(AppConfig config) {
		return config;
	}

	@Provides
	public TransactionManager tm(Config config) {
		return config.getTransactionManager();
	}

	@Provides
	@Singleton
	public EmployeeDao employee(examples.dao.EmployeeDaoImpl dao) {
		// daggerはaptの実行順序によって存在しないものを対象としてしまった場合、
		// メソッドに宣言されている型だけで何とかしようとするので、引数にFQDNを書いておく。
		// see. dagger.internal.codegen
		// ModuleAdapterProcessor.generateProvidesAdapter
		// GeneratorKeys.get(VariableElement)
		return dao;
	}

}
