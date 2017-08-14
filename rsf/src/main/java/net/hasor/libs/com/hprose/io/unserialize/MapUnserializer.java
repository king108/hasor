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
 * MapUnserializer.java                                   *
 *                                                        *
 * Map unserializer class for Java.                       *
 *                                                        *
 * LastModified: Aug 4, 2016                              *
 * Author: Ma Bingyao <andot@hprose.com>                  *
 *                                                        *
 \**********************************************************/
package net.hasor.libs.com.hprose.io.unserialize;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

import static net.hasor.libs.com.hprose.io.HproseTags.*;
public final class MapUnserializer extends BaseUnserializer<Map> {
    public final static MapUnserializer instance = new MapUnserializer();
    @Override
    public Map unserialize(Reader reader, int tag, Type type) throws IOException {
        switch (tag) {
        case TagList:
            return ReferenceReader.readListAsMap(reader, type);
        case TagMap:
            return ReferenceReader.readMap(reader, type);
        case TagObject:
            return ReferenceReader.readObjectAsMap(reader, type);
        }
        return super.unserialize(reader, tag, type);
    }
    public Map read(Reader reader) throws IOException {
        return read(reader, LinkedHashMap.class);
    }
}
