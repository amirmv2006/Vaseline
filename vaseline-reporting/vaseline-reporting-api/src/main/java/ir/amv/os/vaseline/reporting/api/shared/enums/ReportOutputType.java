package ir.amv.os.vaseline.reporting.api.shared.enums;

public enum ReportOutputType {
	pdf, html, xls, xlsx, docx, pptx, csv;
	
	// http://www.sitepoint.com/web-foundations/mime-types-summary-list/
	public String getContentType() {
		switch (this) {
		case csv:
			return "text/csv";
		case html:
			return "text/html";
		case docx:
			return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
		case pdf:
			return "application/pdf";
		case pptx:
			return "application/vnd.openxmlformats-officedocument.presentationml.presentation";
		case xls:
			return "application/vnd.ms-excel";
		case xlsx:
			return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		default:
			break;
		}
		return null;
	}

	public String fileSuffix() {
		return name();
	}
}
