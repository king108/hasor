/**********************************************************\
 |                                                          |
 |                          hprose                          |
 |                                                          |
 | Official WebSite: http://www.hprose.com/                 |
 |                   http://www.hprose.org/                 |
 |                                                          |
 \**********************************************************/
/**********************************************************\
 *                                                        *
 * FieldAccessor.java                                     *
 *                                                        *
 * FieldAccessor class for Java.                          *
 *                                                        *
 * LastModified: Aug 4, 2016                              *
 * Author: Ma Bingyao <andot@hprose.com>                  *
 *                                                        *
 \**********************************************************/
package net.hasor.libs.com.hprose.io.access;
import net.hasor.libs.com.hprose.io.HproseException;
import net.hasor.libs.com.hprose.io.serialize.Serializer;
import net.hasor.libs.com.hprose.io.serialize.SerializerFactory;
import net.hasor.libs.com.hprose.io.serialize.Writer;
import net.hasor.libs.com.hprose.io.unserialize.Reader;
import net.hasor.libs.com.hprose.io.unserialize.Unserializer;
import net.hasor.libs.com.hprose.io.unserialize.UnserializerFactory;
import net.hasor.libs.com.hprose.utils.ClassUtil;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

import static net.hasor.libs.com.hprose.io.HproseTags.TagNull;
public final class FieldAccessor implements MemberAccessor {
    private final long         offset;
    private final Type         fieldType;
    private final Serializer   serializer;
    private final Unserializer unserializer;
    public FieldAccessor(Type type, Field field) {
        field.setAccessible(true);
        offset = Accessors.unsafe.objectFieldOffset(field);
        fieldType = ClassUtil.getActualType(type, field.getGenericType());
        Class<?> cls = ClassUtil.toClass(fieldType);
        serializer = SerializerFactory.get(cls);
        unserializer = UnserializerFactory.get(cls);
    }
    @Override
    @SuppressWarnings({ "unchecked" })
    public void serialize(Writer writer, Object obj) throws IOException {
        Object value;
        try {
            value = Accessors.unsafe.getObject(obj, offset);
        } catch (Exception e) {
            throw new HproseException(e.getMessage());
        }
        if (value == null) {
            writer.stream.write(TagNull);
        } else {
            serializer.write(writer, value);
        }
    }
    @Override
    public void unserialize(Reader reader, Object obj) throws IOException {
        Object value = unserializer.read(reader, reader.stream.read(), fieldType);
        try {
            Accessors.unsafe.putObject(obj, offset, value);
        } catch (Exception e) {
            throw new HproseException(e.getMessage());
        }
    }
}