package ir.amv.os.vaseline.reporting.api.server.fieldpostprocessor;

import net.sf.jasperreports.engine.JRField;

public interface IBaseFieldPostProcessor<C> {

	boolean accept(JRField field);
	C postProcess(C value);
}
