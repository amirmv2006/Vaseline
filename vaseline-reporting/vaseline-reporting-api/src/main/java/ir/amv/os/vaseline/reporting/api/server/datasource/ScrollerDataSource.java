package ir.amv.os.vaseline.reporting.api.server.datasource;

import ir.amv.os.vaseline.data.apis.dao.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.basics.apis.core.shared.util.callback.IBaseCallback;
import ir.amv.os.vaseline.basics.apis.core.shared.util.callback.defimpl.BaseCallbackImpl;
import ir.amv.os.vaseline.reporting.api.server.fieldpostprocessor.IBaseFieldPostProcessor;
import ir.amv.os.vaseline.reporting.api.server.fieldpostprocessor.impl.DefaultJasperFieldStringPostProcessor;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMV on 7/17/2016.
 */
public class ScrollerDataSource<D> extends JRAbstractBeanDataSource {

    private D currentRow;
    private List<IBaseFieldPostProcessor<?>> fieldsPostProcessors = new ArrayList<IBaseFieldPostProcessor<?>>();
    private IVaselineDataScroller<D> vaselineDataScroller;
    private IBaseCallback<IBaseCallback<Integer, Void>, Void> countCallback;

    public ScrollerDataSource(
            IBaseCallback<IBaseCallback<IVaselineDataScroller<D>, Void>, Void> fetchScrollerCallback,
            IBaseCallback<IBaseCallback<Integer, Void>, Void> countCallback) {
        super(true);
        this.countCallback = countCallback;
        addFieldPostProcessor(new DefaultJasperFieldStringPostProcessor());
        try {
            fetchScrollerCallback.onSuccess(new BaseCallbackImpl<IVaselineDataScroller<D>, Void>() {
                @Override
                public void onSuccess(IVaselineDataScroller<D> result) throws Exception {
                    vaselineDataScroller = result;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void moveFirst() throws JRException {
    }

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        String fieldName = jrField.getName();
        Object propertyByTreeName = ReflectionUtil.getPropertyByTreeName(currentRow, fieldName);
        for (IBaseFieldPostProcessor fieldPostProcessor : fieldsPostProcessors) {
            if (fieldPostProcessor.accept(jrField)) {
                propertyByTreeName = fieldPostProcessor.postProcess(propertyByTreeName);
            }
        }
        return propertyByTreeName;
    }

    @Override
    public boolean next() throws JRException {
        boolean nextRow = vaselineDataScroller.next();
        if (nextRow) {
            Object[] objects = vaselineDataScroller.get();
            currentRow = (D) objects[0];
        }
        return nextRow;
    }

    public void addFieldPostProcessor(IBaseFieldPostProcessor<?> fieldPostProcessor) {
        fieldsPostProcessors.add(fieldPostProcessor);
    }

    public IBaseCallback<IBaseCallback<Integer, Void>, Void> getCountCallback() {
        return countCallback;
    }
}
