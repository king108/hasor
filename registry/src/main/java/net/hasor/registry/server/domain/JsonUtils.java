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
package net.hasor.registry.server.domain;
import com.alibaba.fastjson.JSONObject;
import net.hasor.rsf.utils.BeanUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @version : 2015年7月6日
 * @author 赵永春(zyc@hasor.net)
 */
public class JsonUtils {
    public static String converToString(Object object) {
        if (object == null) {
            return "null";
        }
        //
        List<String> propertys = BeanUtils.getPropertys(object.getClass());
        Map<String, Object> props = new HashMap<String, Object>();
        for (String prop : propertys) {
            Object val = BeanUtils.readProperty(object, prop);
            if (val == null)
                continue;
            if (val instanceof Class) {
                val = ((Class) val).getName();
            }
            if (val instanceof Enum) {
                val = ((Enum) val).name();
            }
            props.put(prop, val);
        }
        //
        return JSONObject.toJSONString(props);
    }
    public static <T> T converToService(String jsonData, Class<T> targetType) {
        return JSONObject.parseObject(jsonData, targetType);
    }
}