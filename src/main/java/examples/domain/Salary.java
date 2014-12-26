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
package examples.domain;

import org.seasar.doma.Domain;

@Domain(valueType = Integer.class)
public class Salary {

    private final Integer value;

    public Salary(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public Salary add(Salary salary) {
        if (salary == null) {
            throw new NullPointerException("The salary parameter is null.");
        }
        if (this.value == null || salary.value == null) {
            return new Salary(null);
        }
        return new Salary(this.value + salary.value);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Salary other = (Salary) obj;
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return value != null ? String.valueOf(value) : null;
    }

}
