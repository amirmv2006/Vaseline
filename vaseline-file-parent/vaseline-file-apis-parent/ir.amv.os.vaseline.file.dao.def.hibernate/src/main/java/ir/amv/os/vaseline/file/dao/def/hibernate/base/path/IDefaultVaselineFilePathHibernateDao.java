package ir.amv.os.vaseline.file.dao.def.hibernate.base.path;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.core.api.shared.util.file.FileUtils;
import ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.crud.IDefaultHibernateCrudDao;
import ir.amv.os.vaseline.file.dao.def.common.server.dao.base.path.IVaselineFilePathDao;
import ir.amv.os.vaseline.file.dao.def.common.server.model.base.path.VaselineFilePathEntity;

import java.io.*;

import static ir.amv.os.vaseline.basics.core.api.shared.util.stream.StreamUtils.copyStreams;

public interface IDefaultVaselineFilePathHibernateDao
        extends IDefaultHibernateCrudDao<Long, VaselineFilePathEntity>, IVaselineFilePathDao {

    String getBaseFilePath();// vaseline.file.path.base

    @Override
    default Long saveFileUsingStream(VaselineFilePathEntity fileEntity, InputStream inputStream) throws Exception {
        String absoluteFilePath = resolveFilePath(fileEntity);
        FileOutputStream fileOutputStream = FileUtils.openOutputStream(new File(absoluteFilePath), false);
        copyStreams(inputStream, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        fileEntity.setFilePath(absoluteFilePath);
        return save(fileEntity);
    }

    default String resolveFilePath(VaselineFilePathEntity filePathEntity) {
        String baseFilePath = getBaseFilePath();
        String pathSeparator = File.separator;
        if (!baseFilePath.endsWith(pathSeparator)) {
            baseFilePath += pathSeparator;
        }

        String filePath = baseFilePath + filePathEntity.getCategory() + pathSeparator + filePathEntity.getFileName();
        filePathEntity.setFilePath(filePath);
        return filePath;
    }

    @Override
    default void writeFileContent(Long fileId, OutputStream outputStream) throws Exception {
        VaselineFilePathEntity fileEntity = getById(fileId);
        String absoluteFilePath = fileEntity.getFilePath();
        try (FileInputStream fileInputStream = new FileInputStream(absoluteFilePath)) {
            copyStreams(fileInputStream, outputStream);
        }
    }

    @Override
    default VaselineFilePathEntity createFile(String category) throws BaseVaselineServerException {
        return new VaselineFilePathEntity();
    }
}
