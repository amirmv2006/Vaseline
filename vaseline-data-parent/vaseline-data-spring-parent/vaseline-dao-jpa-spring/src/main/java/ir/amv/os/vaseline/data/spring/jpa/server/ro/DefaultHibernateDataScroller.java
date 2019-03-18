package ir.amv.os.vaseline.data.spring.jpa.server.ro;

import ir.amv.os.vaseline.basics.core.api.shared.util.callback.IBaseCallback;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;
import org.hibernate.ScrollableResults;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by AMV on 7/17/2016.
 */
public class DefaultHibernateDataScroller<E>
        implements IVaselineDataScroller<E> {

    private ScrollableResults scrollableResults;
    private List<IBaseCallback<E, Void>> callbacks = new ArrayList<IBaseCallback<E, Void>>();

    public DefaultHibernateDataScroller(ScrollableResults scrollableResults) {
        this.scrollableResults = scrollableResults;
    }


    @Override
    public boolean next() {
        return scrollableResults.next();
    }

    @Override
    public boolean previous() {
        return scrollableResults.previous();
    }

    @Override
    public boolean scroll(int positions) {
        return scrollableResults.scroll(positions);
    }

    @Override
    public boolean last() {
        return scrollableResults.last();
    }

    @Override
    public boolean first() {
        return scrollableResults.first();
    }

    @Override
    public void beforeFirst() {
        scrollableResults.beforeFirst();
    }

    @Override
    public void afterLast() {
        scrollableResults.afterLast();
    }

    @Override
    public boolean isFirst() {
        return scrollableResults.isFirst();
    }

    @Override
    public boolean isLast() {
        return scrollableResults.isLast();
    }

    @Override
    public int getRowNumber() {
        return scrollableResults.getRowNumber();
    }

    @Override
    public boolean setRowNumber(int rowNumber) {
        return scrollableResults.setRowNumber(rowNumber);
    }

    @Override
    public void close() {
        scrollableResults.close();
    }

    @Override
    public Object[] get() {
        Object[] objects = scrollableResults.get();
        if (objects != null) {
            for (Object object : objects) {
                for (IBaseCallback<E, Void> callback : callbacks) {
                    try {
                        callback.onSuccess((E) object);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return objects;
    }

    @Override
    public Object get(int i) {
        return scrollableResults.get(i);
    }

//    @Override
//    public Type getType(int i) {
//        return scrollableResults.getType(i);
//    }

    @Override
    public Integer getInteger(int col) {
        return scrollableResults.getInteger(col);
    }

    @Override
    public Long getLong(int col) {
        return scrollableResults.getLong(col);
    }

    @Override
    public Float getFloat(int col) {
        return scrollableResults.getFloat(col);
    }

    @Override
    public Boolean getBoolean(int col) {
        return scrollableResults.getBoolean(col);
    }

    @Override
    public Double getDouble(int col) {
        return scrollableResults.getDouble(col);
    }

    @Override
    public Short getShort(int col) {
        return scrollableResults.getShort(col);
    }

    @Override
    public Byte getByte(int col) {
        return scrollableResults.getByte(col);
    }

    @Override
    public Character getCharacter(int col) {
        return scrollableResults.getCharacter(col);
    }

    @Override
    public byte[] getBinary(int col) {
        return scrollableResults.getBinary(col);
    }

    @Override
    public String getText(int col) {
        return scrollableResults.getText(col);
    }

//    @Override
//    public Blob getBlob(int col) {
//        return scrollableResults.getBlob(col);
//    }

//    @Override
//    public Clob getClob(int col) {
//        return scrollableResults.getClob(col);
//    }

    @Override
    public String getString(int col) {
        return scrollableResults.getString(col);
    }

    @Override
    public BigDecimal getBigDecimal(int col) {
        return scrollableResults.getBigDecimal(col);
    }

    @Override
    public BigInteger getBigInteger(int col) {
        return scrollableResults.getBigInteger(col);
    }

    @Override
    public Date getDate(int col) {
        return scrollableResults.getDate(col);
    }

    @Override
    public Locale getLocale(int col) {
        return scrollableResults.getLocale(col);
    }

    @Override
    public Calendar getCalendar(int col) {
        return scrollableResults.getCalendar(col);
    }

    @Override
    public TimeZone getTimeZone(int col) {
        return scrollableResults.getTimeZone(col);
    }

    @Override
    public void addAfterFetchObject(IBaseCallback<E, Void> callback) {
        callbacks.add(callback);
    }
}
