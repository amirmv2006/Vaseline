package ir.amv.os.vaseline.reporting.api.server.datasource;

import ir.amv.os.vaseline.basics.apis.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.api.shared.util.callback.IBaseCallback;
import ir.amv.os.vaseline.basics.apis.core.api.shared.util.pager.IBaseAsyncListPager;
import ir.amv.os.vaseline.basics.apis.core.api.shared.util.pager.defimpl.DefaultStaticListPager;
import ir.amv.os.vaseline.reporting.api.server.fieldpostprocessor.IBaseFieldPostProcessor;
import ir.amv.os.vaseline.reporting.api.server.fieldpostprocessor.impl.DefaultJasperFieldStringPostProcessor;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSource;

import java.util.ArrayList;
import java.util.List;

public class PaginatorDataSource<D> extends JRAbstractBeanDataSource {

	private static final int PAGE_SIZE = 10000;
	private D currentRow;
	private IBaseAsyncListPager<D> pager;
	private DefaultStaticListPager<D> currentPager;
	private List<IBaseFieldPostProcessor<?>> fieldsPostProcessors = new ArrayList<IBaseFieldPostProcessor<?>>();
	
	public PaginatorDataSource(IBaseAsyncListPager<D> pager) {
		super(true);
		this.pager = pager;
		addFieldPostProcessor(new DefaultJasperFieldStringPostProcessor());
	}

	@Override
	public void moveFirst() throws JRException {
		if (pager.getPageSize() == 0) {
			pager.setPageSize(PAGE_SIZE);
		}
		pager.reset();
		resetCurrentPager();
	}

	private void resetCurrentPager() throws JRException {
		if (!pager.isInitialized()) {
			moveFirst();
		}
		currentPager = new DefaultStaticListPager<D>(pager.getCurrentPage(), 1);
		currentPager.reset();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
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
		if (currentPager == null) {
			resetCurrentPager();
		}
		boolean nextRow = currentPager.nextPage();
		if (nextRow) {
			currentRow = currentPager.getCurrentPage().get(0);
		} else {
			boolean nextPage = pager.nextPage();
			if (nextPage) {
				resetCurrentPager();
				nextRow = currentPager.nextPage();
				if (nextRow) {
					currentRow = currentPager.getCurrentPage().get(0);
				}
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	public PagingDto getCurrentPagingDTO() {
		return new PagingDto(null, pager.getCurrentPageNo(), pager.getPageSize());
	}
	
	public void addFieldPostProcessor(IBaseFieldPostProcessor<?> fieldPostProcessor) {
		fieldsPostProcessors.add(fieldPostProcessor);
	}

	public IBaseCallback<IBaseCallback<Integer, Void>, Void> getCountCallback() {
        return pager.getCountDataCallback();
    }
}
