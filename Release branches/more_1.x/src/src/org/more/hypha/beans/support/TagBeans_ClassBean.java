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
package org.more.hypha.beans.support;
import java.util.Map;
import org.more.hypha.beans.define.ClassBeanDefine;
import org.more.hypha.context.XmlDefineResource;
/**
 * ���ڽ���/beans/classBean��ǩ
 * @version 2010-9-16
 * @author ������ (zyc@byshell.org)
 */
public class TagBeans_ClassBean extends TagBeans_AbstractBeanDefine<ClassBeanDefine> {
    /**����{@link TagBeans_ClassBean}����*/
    public TagBeans_ClassBean(XmlDefineResource configuration) {
        super(configuration);
    }
    /**����{@link ClassBeanDefine}����*/
    protected ClassBeanDefine createDefine() {
        return new ClassBeanDefine();
    }
    /**��������Bean�������ԡ�*/
    public enum PropertyKey {
        source,
    };
    /**����������xml�����Զ�Ӧ��ϵ��*/
    protected Map<Enum<?>, String> getPropertyMappings() {
        Map<Enum<?>, String> propertys = super.getPropertyMappings();
        propertys.put(PropertyKey.source, "class");
        return propertys;
    }
}