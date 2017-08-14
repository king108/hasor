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
package net.hasor.data;
import net.hasor.core.ApiBinder;
import net.hasor.core.BindInfo;
import net.hasor.core.Provider;
import net.hasor.data.ql.UDF;

import javax.sql.DataSource;
/**
 * 全面提供 “数据库 + 服务” 整合查询，并为查询结果提供全面的数据整合能力。您甚至可以通过一条 QL 混合 RPC 和数据库的查询结果。
 * 除此之外，Data 软件包，还为您提供全面的数据库事务控制能力，在底层上 Data 还为您提供了 DataSource、JDBC操作接口、等常见的功能。
 * 无论您是简单使用数据库查询，还是要整合数据库 + 服务。 Data 都将会您最有力的帮手。
 *
 * Data 提供的 DataQL
 */
public interface DataApiBinder extends ApiBinder {
    /** 添加 DataQL UDF */
    public void addUDF(String name, Class<? extends UDF> udfType);

    /** 添加 DataQL UDF */
    public void addUDF(String name, UDF dataUDF);

    /** 添加 DataQL UDF */
    public void addUDF(String name, Provider<? extends UDF> udfProvider);

    /** 添加 DataQL UDF */
    public void addUDF(String name, BindInfo<? extends UDF> udfInfo);

    /** 添加数据源 */
    public void addDataSource(DataSource dataSource);

    /** 添加数据源 */
    public void addDataSource(Provider<DataSource> dataSource);

    /** 添加数据源 */
    public void addDataSource(String name, DataSource dataSource);

    /** 添加数据源 */
    public void addDataSource(String name, Provider<DataSource> dataSource);
}