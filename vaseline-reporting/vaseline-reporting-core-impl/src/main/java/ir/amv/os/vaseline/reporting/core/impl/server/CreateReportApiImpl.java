package ir.amv.os.vaseline.reporting.core.impl.server;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.parent.api.BaseApiImpl;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.util.date.DateUtil;
import ir.amv.os.vaseline.reporting.api.server.datasource.BaseBeansDataSource;
import ir.amv.os.vaseline.reporting.api.server.fieldpostprocessor.impl.DefaultJasperFieldStringPostProcessor;
import ir.amv.os.vaseline.reporting.api.server.model.CreateReportRequest;
import ir.amv.os.vaseline.reporting.api.server.model.IBaseReportSource;
import ir.amv.os.vaseline.reporting.api.server.model.ICreateReportApi;
import ir.amv.os.vaseline.reporting.api.shared.enums.ReportOutputType;
import ir.amv.os.vaseline.security.shared.api.IAuthenticationApi;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRStyledText;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by AMV on 2/13/2016.
 */
@Component
public class CreateReportApiImpl
        extends BaseApiImpl
        implements ICreateReportApi, ServletContextAware {

    private IAuthenticationApi authenticationApi;
    @Autowired
    Environment environment;
    private ServletContext servletContext;

    @PostConstruct
    public void initialize() {
        JasperReportsContext reportsContext = DefaultJasperReportsContext.getInstance();
        JRPropertiesUtil.getInstance(reportsContext).setProperty(JRStyledText.PROPERTY_AWT_IGNORE_MISSING_FONT, "true");
        File fontDir = new File(environment.getProperty("report.fonts.dir", ""));
        if (fontDir.exists() && fontDir.isDirectory()) {
            for (String fileName : fontDir.list()) {
                if (fileName.toLowerCase().endsWith(".ttf") || fileName.toLowerCase().endsWith(".fnt")) {
                    JRPropertiesUtil.getInstance(reportsContext).setProperty(
                            JRPdfExporter.PDF_FONT_FILES_PREFIX + fileName.substring(0, fileName.length() - 4), fontDir + File.separator + fileName);
                }
            }
        }
    }

    @Override
    public void generateReport(CreateReportRequest request, OutputStream outputStream, BaseBeansDataSource<?> dataSource) {
        try {
            // get report design name and load report design file
            JasperReport jasperReport = null;
            IBaseReportSource reportSource = request.getReportSource();
            try {
                jasperReport = reportSource.compile();
            } catch (Exception e) {
                throw e;
            }

            Map<String, Object> argsMap = request.getArgsMap();
            setDefaultParameters(argsMap);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                    argsMap, dataSource);
            if (jasperPrint == null) {
                IllegalStateException illegalStateException = new IllegalStateException(
                        "JasperPrint object is null.");
                throw illegalStateException;
            }

            List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
            jasperPrintList.add(jasperPrint);
            ReportOutputType outputType = request.getOutputType();
            JRAbstractExporter exporter = getJasperExporter(outputType);
            exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
            //		exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,
//					jasperPrintList);
//			exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
//			try {
//				fileName = setJasperOutputStream(exporter);
//			} catch (IOException e) {
//				throw e;
//			}
            try {
                exporter.exportReport();
                outputStream.flush();
                outputStream.close();
                reportSource.close();
            } catch (JRException e) {
                throw new RuntimeException(e);
            }

            outputStream.flush();
//			byte[] fileName = outputStream.toByteArray();

//			outputStream.close();
//			((OutputStream) exporter
//					.getParameter(JRExporterParameter.OUTPUT_STREAM)).flush();
//			((OutputStream) exporter
//					.getParameter(JRExporterParameter.OUTPUT_STREAM)).close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected JRAbstractExporter<?, ?, ?, ?> getJasperExporter(ReportOutputType outputType) {
        JRAbstractExporter<?, ?, ?, ?> exporter = null;
        if (outputType.equals(ReportOutputType.pdf)) {
            exporter = new JRPdfExporter();
        } else if (outputType.equals(ReportOutputType.html)) {
            exporter = new JRHtmlExporter();
        } else if (outputType.equals(ReportOutputType.xls)) {
            exporter = new JRXlsExporter();
            exporter.setParameter(
                    JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
                    true);
        } else if (outputType.equals(ReportOutputType.xlsx)) {
            exporter = new JRXlsxExporter();
            // } else if (outputFormat.equals("doc")) {
            // exporter = new AWDocExporter();
        } else if (outputType.equals(ReportOutputType.docx)) {
            exporter = new JRDocxExporter();
        } else if (outputType.equals(ReportOutputType.pptx)) {
            exporter = new JRPptxExporter();
        } else if (outputType.equals(ReportOutputType.csv)) {
            exporter = new JRCsvExporter();
        } else {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Not Supported:" + outputType);
            throw illegalArgumentException;
        }
        return exporter;
    }

    private void setDefaultParameters(Map<String, Object> reportParameters) throws BaseVaselineServerException {
        DefaultJasperFieldStringPostProcessor fieldStringPostProcessor = new DefaultJasperFieldStringPostProcessor();
        String currentUsername = authenticationApi.getCurrentUsername();
        reportParameters.put("currentUser", fieldStringPostProcessor.postProcess(currentUsername));
        String currentShamsiDate = DateUtil.toString(DateUtil.newJalaliCalendar());
        reportParameters.put("currentShamsiDate", fieldStringPostProcessor.postProcess(currentShamsiDate));
        InputStream logoStream = servletContext.getResourceAsStream("/logo.jpg");
        reportParameters.put("logo", logoStream);
    }

    @Autowired
    public void setAuthenticationApi(IAuthenticationApi authenticationApi) {
        this.authenticationApi = authenticationApi;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
