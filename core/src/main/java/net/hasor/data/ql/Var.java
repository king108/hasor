/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.hasor.data.ql;
import net.hasor.data.ql.dsl.domain.EqType;
/**
 * UDF 参数
 * @author 赵永春(zyc@hasor.net)
 * @version : 2017-03-23
 */
public class Var {
    private final EqType eqType;
    private final Object value;
    //
    public Var(EqType eqType, Object value) {
        this.eqType = eqType;
        this.value = value;
    }
    //
    public EqType getEqType() {
        return eqType;
    }
    public Object getValue() {
        return value;
    }
    //
    @Override
    public String toString() {
        return eqType.getTypeString() + " " + this.value;
    }
}