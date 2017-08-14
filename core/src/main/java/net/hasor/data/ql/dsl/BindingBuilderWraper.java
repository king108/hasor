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
/**
 * {@link BindingBuilder} 接口实现类，用于协助构造 DataQL 查询模型。
 * @author 赵永春(zyc@hasor.net)
 * @version : 2017-03-23
 */
class BindingBuilderWraper implements BindingBuilder {
    private BindingBuilder wraper;
    public BindingBuilderWraper(BindingBuilder wraper) {
        this.wraper = wraper;
    }
    //
    @Override
    public BindingBuilder addField(DataField dataField) {
        return this.wraper.addField(dataField);
    }
    @Override
    public UDFBindingBuilder byUDF(String udfName) {
        return this.wraper.byUDF(udfName);
    }
    @Override
    public BindingBuilder asListObject() {
        return this.wraper.asListObject();
    }
    @Override
    public BindingBuilder asListValue() {
        return this.wraper.asListValue();
    }
    @Override
    public BindingBuilder asObject() {
        return this.wraper.asObject();
    }
    @Override
    public BindingBuilder asOriginal() {
        return this.wraper.asOriginal();
    }
    @Override
    public DataField asField() {
        return this.wraper.asField();
    }
    @Override
    public DataParam asParam() {
        return this.wraper.asParam();
    }
    @Override
    public QueryModel buildQuery() {
        return this.wraper.buildQuery();
    }
    @Override
    public String getName() {
        return this.wraper.getName();
    }
}