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
package org.more.submit;
import java.lang.reflect.Field;
import org.more.core.error.DefineException;
import org.more.core.error.ExistException;
import org.more.util.StringConvertUtil;
/**
 * ������{@link ActionContext}�ӿڵ�һ������ʵ�֡�����ֻ��ʵ����findAction��������������Թ���actionbean����Ĵ������������ڡ�
 * ����AbstractActionContext�໹��findAction������ʵ����ע���֧�֡�
 * @version 2010-7-26
 * @author ������ (zyc@byshell.org)
 */
public abstract class AbstractActionContext implements ActionContext {
    public ActionInvoke findAction(String actionName, String invoke) throws ExistException {
        if (this.containsAction(actionName) == false)
            throw new ExistException("�Ҳ�������Ϊ[" + actionName + "]�Ķ���");
        //
        boolean isAction = true;
        //-----------1.ע������
        Class<?> actionType = this.getActionType(actionName);
        Action act = actionType.getAnnotation(Action.class);
        if (act == null || act.isAction() == false)
            isAction = false;
        //-----------2.Ԫ��Ϣ��������
        if (isAction == false) {
            Object obj = this.getActionProperty(actionName, "isAction");
            if (obj != null)
                isAction = StringConvertUtil.parseBoolean(obj.toString());
        }
        //-----------3.�ֶ�����, public boolean isAction=true;
        try {
            Field field = actionType.getField("isAction");
            if (field.getType() == Boolean.class)
                isAction = (Boolean) field.get(null);
        } catch (Exception e) {}
        //-----------
        if (isAction == false)
            throw new DefineException("����Ϊ[" + actionName + "]�Ķ�����һ��Action����");
        return this.getAction(actionName, invoke);
    }
    /**�÷���ָ����{@link AbstractActionContext}��ľ������{@link ActionInvoke}�ķ�ʽ��*/
    protected abstract ActionInvoke getAction(String actionName, String invoke);
}