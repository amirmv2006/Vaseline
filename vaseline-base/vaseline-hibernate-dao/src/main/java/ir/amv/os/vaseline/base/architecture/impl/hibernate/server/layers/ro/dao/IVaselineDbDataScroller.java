package ir.amv.os.vaseline.base.architecture.impl.hibernate.server.layers.ro.dao;

import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dao.scroller.IVaselineDataScroller;
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
