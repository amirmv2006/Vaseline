package ir.amv.os.vaseline.file.db.impl.server.model.path.impl;

import ir.amv.os.vaseline.base.architecture.impl.hibernate.server.layers.crud.dao.BaseCrudHibernateDaoImpl;
import ir.amv.os.vaseline.file.api.server.model.base.IFileEntity;
import ir.amv.os.vaseline.file.api.shared.model.base.IFileDto;
import ir.amv.os.vaseline.file.db.impl.server.model.path.FilePathEntity;
import ir.amv.os.vaseline.file.db.impl.server.model.path.IFilePathDao;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by AMV on 2/9/2016.
 */
@Repository
public class FilePathDaoImpl
        extends BaseCrudHibernateDaoImpl<IFileEntity, IFileDto, Long>
        implements IFilePathDao {

    @Autowired
    Environment environment;

    @Override
    public IFileEntity createFile() {
        return new FilePathEntity();
    }

    @Override
    public Long saveFileUsingStream(IFileEntity fileEntity, InputStream inputStream) throws Exception {
        FilePathEntity filePathEntity = (FilePathEntity) fileEntity;
        String absoluteFilePath = resolveFilePath(filePathEntity);
        FileOutputStream fileOutputStream = new FileOutputStream(absoluteFilePath);
        IOUtils.copy(inputStream, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        filePathEntity.setFilePath(absoluteFilePath);
        return super.save(fileEntity);
    }

    private String resolveFilePath(FilePathEntity filePathEntity) {
        String filePath = filePathEntity.getFilePath();
        String userHome = System.getProperty("user.home");
        String baseFilePath = environment.getProperty("vaseline.file.path.base", userHome);
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
    public void writeFileContent(Long fileId, OutputStream outputStream) throws Exception {
        FilePathEntity fileEntity = (FilePathEntity) super.getById(fileId);
        String absoluteFilePath = resolveFilePath(fileEntity);
        FileInputStream fileInputStream = new FileInputStream(absoluteFilePath);
        IOUtils.copy(fileInputStream, outputStream);
        fileInputStream.close();
    }
}
