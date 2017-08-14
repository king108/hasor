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
 * CharUnserializer.java                                  *
 *                                                        *
 * char unserializer class for Java.                      *
 *                                                        *
 * LastModified: Aug 3, 2016                              *
 * Author: Ma Bingyao <andot@hprose.com>                  *
 *                                                        *
 \**********************************************************/
package net.hasor.libs.com.hprose.io.unserialize;
import java.io.IOException;
import java.lang.reflect.Type;

import static net.hasor.libs.com.hprose.io.HproseTags.TagNull;
public final class CharUnserializer extends CharObjectUnserializer {
    public final static CharUnserializer instance = new CharUnserializer();
    @Override
    public Character read(Reader reader, int tag, Type type) throws IOException {
        if (tag == TagNull)
            return (char) 0;
        return super.read(reader, tag, type);
    }
    @Override
    public Character read(Reader reader) throws IOException {
        return read(reader, char.class);
    }
}
