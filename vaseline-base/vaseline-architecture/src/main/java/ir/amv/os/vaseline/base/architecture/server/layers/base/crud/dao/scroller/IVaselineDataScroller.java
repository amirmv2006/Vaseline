package ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dao.scroller;

import ir.amv.os.vaseline.basics.apis.core.api.shared.util.callback.IBaseCallback;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by AMV on 7/17/2016.
 */
public interface IVaselineDataScroller<E> {
    boolean next();

    boolean previous();

    boolean scroll(int positions);

    boolean last();

    boolean first();

    void beforeFirst();

    void afterLast();

    boolean isFirst();

    boolean isLast();

    int getRowNumber();

    boolean setRowNumber(int rowNumber);

    void close();

    Object[] get();

    Object get(int i);

    Integer getInteger(int col);

    Long getLong(int col);

    Float getFloat(int col);

    Boolean getBoolean(int col);

    Double getDouble(int col);

    Short getShort(int col);

    Byte getByte(int col);

    Character getCharacter(int col);

    byte[] getBinary(int col);

    String getText(int col);

    String getString(int col);

    BigDecimal getBigDecimal(int col);

    BigInteger getBigInteger(int col);

    Date getDate(int col);

    Locale getLocale(int col);

    Calendar getCalendar(int col);

    TimeZone getTimeZone(int col);

    void addAfterFetchObject(IBaseCallback<E, Void> callback);
}
