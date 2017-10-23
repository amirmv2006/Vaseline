package ir.amv.os.vaseline.file.apis.daoimpl.hibernate.server.base.path;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.util.file.FileUtils;
import ir.amv.os.vaseline.data.hibernate.apis.dao.server.crud.IBaseImplementedHibernateCrudDao;
import ir.amv.os.vaseline.file.apis.daogeneric.jpa.server.dao.base.path.IVaselineFilePathDao;
import ir.amv.os.vaseline.file.apis.daogeneric.jpa.server.model.base.path.VaselineFilePathEntity;

import java.io.*;

import static ir.amv.os.vaseline.basics.apis.core.shared.util.stream.StreamUtils.copyStreams;

public interface IImplementedVaselineFilePathHibernateDao
        extends IBaseImplementedHibernateCrudDao<VaselineFilePathEntity, Long>, IVaselineFilePathDao {

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
