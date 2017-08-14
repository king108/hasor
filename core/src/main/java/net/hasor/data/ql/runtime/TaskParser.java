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
package net.hasor.data.ql.runtime;
import net.hasor.data.ql.dsl.domain.*;

import java.util.List;
/**
 * 解析查询模型，将其转换成为执行任务树
 * @author 赵永春(zyc@hasor.net)
 * @version : 2017-03-23
 */
public class TaskParser {
    //
    /** 解析查询模型，将其转换成为执行任务树 */
    public AbstractPrintTask doParser(QueryDomain domain) {
        return doParser("$", null, domain);
    }
    private AbstractPrintTask doParser(String nameOfParent, AbstractPrintTask parent, QueryDomain domain) {
        AbstractPrintTask dataSource = null;
        if (domain.getUDF() != null) {
            dataSource = this.parserUDF(nameOfParent, parent, domain.getUDF());
        }
        //
        ReturnType returnType = domain.getReturnType();
        switch (returnType) {
        case Object: {
            List<String> fieldNames = domain.getFieldNames();
            ObjectTask ost = new ObjectTask(nameOfParent, parent, dataSource);
            for (String name : fieldNames) {
                DValue domainField = domain.getField(name);
                AbstractPrintTask fieldTask = this.parseField(name, ost, domainField);
                ost.addField(name, fieldTask);
            }
            return ost;
        }
        case ListObject: {
            ListTask lst = new ListTask(nameOfParent, parent, dataSource);
            //
            List<String> fieldNames = domain.getFieldNames();
            ObjectTask ost = new ObjectTask(nameOfParent, lst, null);
            for (String name : fieldNames) {
                DValue domainField = domain.getField(name);
                AbstractPrintTask fieldTask = this.parseField(name, ost, domainField);
                ost.addField(name, fieldTask);
            }
            //
            lst.setListBody(ost);
            return lst;
        }
        case ListValue: {
            ListTask lst = new ListTask(nameOfParent, parent, dataSource);
            //
            List<String> fieldNames = domain.getFieldNames();
            String fieldName = fieldNames.get(0);
            DValue domainField = domain.getField(fieldName);
            AbstractPrintTask fieldTask = this.parseField(fieldName, lst, domainField);
            //
            lst.setListBody(fieldTask);
            return lst;
        }
        case Original:
            if (dataSource != null) {
                return dataSource;
            }
            throw new UnsupportedOperationException("dataSource is null.");
        default:
            throw new UnsupportedOperationException("unsupported ReturnType.");
        }
        //
    }
    private AbstractPrintTask parserUDF(String nameOfParent, AbstractPrintTask parent, DataUDF graphUDF) {
        if (graphUDF == null) {
            return null;
        }
        CallerTask caller = new CallerTask(nameOfParent, parent, graphUDF.getName());
        List<String> paramNames = graphUDF.getParamNames();
        for (String name : paramNames) {
            DValue field = graphUDF.getParam(name);
            caller.addParam(name, field.getEqType(), parseField(name, parent, field));
        }
        return caller;
    }
    private AbstractPrintTask parseField(String nameOfParent, AbstractPrintTask parent, DValue defSource) {
        if (defSource instanceof QueryValue) {
            //
            QueryValue queryValue = (QueryValue) defSource;
            QueryDomain queryDomain = queryValue.getQueryDomain();
            return this.doParser(nameOfParent, parent, queryDomain);
        } else if (defSource instanceof FixedValue) {
            //
            FixedValue varValue = (FixedValue) defSource;
            Object value = varValue.getValue();
            ValueType valueType = varValue.getValueType();
            return new ValueTask(nameOfParent, parent, value, valueType);
        } else if (defSource instanceof RouteValue) {
            //
            RouteTask rst = new RouteTask(nameOfParent, parent, null);
            String routeExpression = ((RouteValue) defSource).getRouteExpression();
            rst.setRouteExpression(routeExpression);
            return rst;
        }
        throw new UnsupportedOperationException("unsupported DValue. type is = " + defSource.getClass());
    }
}