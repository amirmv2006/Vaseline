package ir.amv.os.vaseline.basics.core.api.layers.business.model.list;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.impl.BaseBusinessValueObjectImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PartialList<O>
        extends BaseBusinessValueObjectImpl<PartialList<O>>
        implements Iterable<O> {

    private final Queue<O> values;

    public PartialList(List<O> values) {
        this.values = new LinkedList<>(values);
    }

    /**
     * determines if two {@link PartialList}s are the same or not. Always returns false because {@link PartialList}s
     * are never fully loaded, so we can not know if the two lists are the same or not.
     *
     * @param other the other {@link PartialList} to compare.
     * @return false
     */
    @Override
    public boolean isSameAs(PartialList<O> other) {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        throw new RuntimeException();
    }

    @Override
    public int hashCode() {
        throw new RuntimeException();
    }

    @Override
    public Iterator<O> iterator() {
        return values.iterator();
    }

    public O head() {
        return values.peek();
    }

    public PartialList<O> add(O object) {
        ArrayList<O> copy = new ArrayList<>(values);
        copy.add(0, object);
        return new PartialList<>(copy);
    }
}
