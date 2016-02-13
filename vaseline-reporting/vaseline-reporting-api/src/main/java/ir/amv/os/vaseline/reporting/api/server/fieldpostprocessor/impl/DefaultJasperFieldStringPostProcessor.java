package ir.amv.os.vaseline.reporting.api.server.fieldpostprocessor.impl;

import ir.amv.os.vaseline.reporting.api.server.fieldpostprocessor.IBaseFieldPostProcessor;
import net.sf.jasperreports.engine.JRField;

import java.util.HashMap;
import java.util.Map;

public class DefaultJasperFieldStringPostProcessor implements
		IBaseFieldPostProcessor<String> {

	private Map<Character, Character> toBeReplaced = new HashMap<Character, Character>();

	public DefaultJasperFieldStringPostProcessor() {
		toBeReplaced.put('ی', 'ي');
		toBeReplaced.put('0', '۰');
		toBeReplaced.put('1', '۱');
		toBeReplaced.put('2', '۲');
		toBeReplaced.put('3', '۳');
		toBeReplaced.put('4', '۴');
		toBeReplaced.put('5', '۵');
		toBeReplaced.put('6', '۶');
		toBeReplaced.put('7', '۷');
		toBeReplaced.put('8', '۸');
		toBeReplaced.put('9', '۹');
	}

	@Override
	public boolean accept(JRField field) {
		return field.getValueClass().equals(String.class);
	}

	@Override
	public String postProcess(String value) {
		if (value == null)
			return "";
		else {
//			try {
				for (Character toBeReplacedChar : toBeReplaced.keySet()) {
					value = value.replaceAll("" + toBeReplacedChar, "" + toBeReplaced.get(toBeReplacedChar));
				}
				return value;
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
		}
//		return null;
	}

}
