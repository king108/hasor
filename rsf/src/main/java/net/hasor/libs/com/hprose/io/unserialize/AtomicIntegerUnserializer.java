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
 * AtomicIntegerUnserializer.java                         *
 *                                                        *
 * AtomicInteger unserializer class for Java.             *
 *                                                        *
 * LastModified: Aug 3, 2016                              *
 * Author: Ma Bingyao <andot@hprose.com>                  *
 *                                                        *
 \**********************************************************/
package net.hasor.libs.com.hprose.io.unserialize;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicInteger;

import static net.hasor.libs.com.hprose.io.HproseTags.TagNull;
public final class AtomicIntegerUnserializer implements Unserializer<AtomicInteger> {
    public final static AtomicIntegerUnserializer instance = new AtomicIntegerUnserializer();
    public AtomicInteger read(Reader reader, int tag, Type type) throws IOException {
        if (tag == TagNull)
            return null;
        return new AtomicInteger(IntObjectUnserializer.instance.read(reader, tag, Integer.class));
    }
    public AtomicInteger read(Reader reader, Type type) throws IOException {
        return read(reader, reader.stream.read(), type);
    }
    public AtomicInteger read(Reader reader) throws IOException {
        return read(reader, AtomicInteger.class);
    }
}
