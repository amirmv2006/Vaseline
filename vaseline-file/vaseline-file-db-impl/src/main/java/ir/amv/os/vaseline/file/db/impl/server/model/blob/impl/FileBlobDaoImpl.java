package ir.amv.os.vaseline.file.db.impl.server.model.blob.impl;

import ir.amv.os.vaseline.base.architecture.impl.hibernate.server.layers.crud.dao.BaseCrudHibernateDaoImpl;
import ir.amv.os.vaseline.file.api.server.model.base.IFileEntity;
import ir.amv.os.vaseline.file.api.shared.model.base.IFileDto;
import ir.amv.os.vaseline.file.db.impl.server.model.blob.FileBlobEntity;
import ir.amv.os.vaseline.file.db.impl.server.model.blob.IFileBlobDao;
import org.apache.commons.io.IOUtils;
import org.hibernate.Criteria;
import org.hibernate.LobHelper;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Blob;

/**
 * Created by AMV on 2/9/2016.
 */
@Repository
public class FileBlobDaoImpl
        extends BaseCrudHibernateDaoImpl<IFileEntity, IFileDto, Long>
        implements IFileBlobDao {

    @Override
    public IFileEntity createFile() {
        return new FileBlobEntity();
    }

    @Override
    public Long saveFileUsingStream(IFileEntity fileEntity, InputStream inputStream) throws Exception {
        long dataSize;
        try {
            dataSize = inputStream.available();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            LobHelper lobHelper = session.getLobHelper();
            Blob dataBlob = lobHelper.createBlob(inputStream, dataSize);
            ((FileBlobEntity)fileEntity).setFileContent(dataBlob);
            Serializable id = session.save(fileEntity);
            session.getTransaction().commit();
            session.close();
            return (Long) id;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void writeFileContent(Long fileId, OutputStream outputStream) throws Exception {
        DetachedCriteria detCriteria = createCriteria();
        detCriteria.add(Restrictions.idEq(fileId));
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = detCriteria.getExecutableCriteria(session);
        IFileEntity entity = getEntityFromCriteria(criteria);
        InputStream inputStream = ((FileBlobEntity) entity).getFileContent().getBinaryStream();
        IOUtils.copy(inputStream, outputStream);
        session.getTransaction().commit();
        session.close();
    }

}
