package ir.amv.os.vaseline.file.apis.daoimpl.hibernate.server.base.path;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.data.hibernate.apis.dao.server.crud.IBaseImplementedHibernateCrudDao;
import ir.amv.os.vaseline.file.apis.daogeneric.jpa.server.dao.base.path.IFilePathDao;
import ir.amv.os.vaseline.file.apis.daogeneric.jpa.server.model.base.path.FilePathEntity;
import ir.amv.os.vaseline.file.apis.model.server.base.IFileEntity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static ir.amv.os.vaseline.basics.apis.core.shared.util.stream.StreamUtils.copyStreams;

public interface IImplementedFilePathHibernateDao
        extends IBaseImplementedHibernateCrudDao<FilePathEntity, Long>, IFilePathDao {

    String getBaseFilePath();// vaseline.file.path.base

    @Override
    default Long saveFileUsingStream(FilePathEntity fileEntity, InputStream inputStream) throws Exception {
        String absoluteFilePath = resolveFilePath(fileEntity);
        FileOutputStream fileOutputStream = new FileOutputStream(absoluteFilePath);
        copyStreams(inputStream, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        fileEntity.setFilePath(absoluteFilePath);
        return save(fileEntity);
    }

    default String resolveFilePath(FilePathEntity filePathEntity) {
        String filePath = filePathEntity.getFilePath();
        String baseFilePath = getBaseFilePath();
        String pathSeparator = System.getProperty("path.separator");
        if (!baseFilePath.endsWith("/") || !baseFilePath.endsWith("\\")) {
            baseFilePath += pathSeparator;
        }
        baseFilePath += "vaseline" + pathSeparator;
        if (filePath.startsWith("/") || filePath.startsWith("\\")) {
            filePath = filePath.substring(1);
        }
        return baseFilePath + filePath;
    }

    @Override
    default void writeFileContent(Long fileId, OutputStream outputStream) throws Exception {
        FilePathEntity fileEntity = getById(fileId);
        String absoluteFilePath = resolveFilePath(fileEntity);
        try (FileInputStream fileInputStream = new FileInputStream(absoluteFilePath)) {
            copyStreams(fileInputStream, outputStream);
        }
    }

    @Override
    default IFileEntity createFile(String category) throws BaseVaselineServerException {
        return new FilePathEntity();
    }
}
