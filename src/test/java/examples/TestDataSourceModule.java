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
package examples;

import javax.inject.Singleton;
import javax.sql.DataSource;

import org.seasar.doma.jdbc.SimpleDataSource;

import dagger.Module;
import dagger.Provides;

/**
 * @author taichi
 */
@Module(library = true)
public class TestDataSourceModule {

	@Provides
	@Singleton
	public DataSource dataSource() {
		SimpleDataSource ds = new SimpleDataSource();
		ds.setUrl("jdbc:h2:mem:tutorial;DB_CLOSE_DELAY=-1");
		ds.setUser("sa");
		return ds;
	}
}
