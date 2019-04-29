package ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.ro;

import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;
import org.hibernate.type.Type;

import java.sql.Blob;
import java.sql.Clob;

/**
 * Created by AMV on 7/17/2016.
 */
public interface IVaselineDbDataScroller<E>
        extends IVaselineDataScroller<E> {

    Type getType(int i);

    Blob getBlob(int col);

    Clob getClob(int col);
}