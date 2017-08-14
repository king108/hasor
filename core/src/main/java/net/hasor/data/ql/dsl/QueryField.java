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
package net.hasor.data.ql.dsl;
import net.hasor.data.ql.dsl.domain.QueryDomain;
/**
 * 结果，作为字段
 * @author 赵永春(zyc@hasor.net)
 * @version : 2017-03-23
 */
class QueryField implements DataField {
    private String      name;
    private QueryDomain queryDomain;
    public QueryField(String name, QueryDomain queryDomain) {
        this.name = name;
        this.queryDomain = queryDomain;
    }
    @Override
    public String getName() {
        return this.name;
    }
    public QueryDomain getQueryDomain() {
        return queryDomain;
    }
}