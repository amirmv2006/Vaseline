package ir.amv.os.vaseline.reporting.async.rest.server.dozer;

import ir.amv.os.vaseline.basics.spring.core.config.VaselineCoreConfig;
import ir.amv.os.vaseline.file.api.server.model.base.IFileApi;
import ir.amv.os.vaseline.reporting.api.server.model.file.FileReportSourceImplServer;
import ir.amv.os.vaseline.reporting.api.shared.model.file.FileReportSourceImplClient;
import org.dozer.DozerConverter;

/**
 * Created by AMV on 2/17/2016.
 */
public class VaselineFileReportSourceCustomConverter
        extends DozerConverter<FileReportSourceImplServer, FileReportSourceImplClient> {

    public VaselineFileReportSourceCustomConverter() {
        super(FileReportSourceImplServer.class, FileReportSourceImplClient.class);
    }

    @Override
    public FileReportSourceImplClient convertTo(FileReportSourceImplServer source, FileReportSourceImplClient destination) {
        destination.setFileCategory(source.getFileCategory());
        destination.setFileId(source.getFileId());
        return destination;
    }

    @Override
    public FileReportSourceImplServer convertFrom(FileReportSourceImplClient source, FileReportSourceImplServer destination) {
        destination.setFileCategory(source.getFileCategory());
        destination.setFileId(source.getFileId());
        destination.setFileApi(VaselineCoreConfig.getBean(IFileApi.class));
        return destination;
    }
}
