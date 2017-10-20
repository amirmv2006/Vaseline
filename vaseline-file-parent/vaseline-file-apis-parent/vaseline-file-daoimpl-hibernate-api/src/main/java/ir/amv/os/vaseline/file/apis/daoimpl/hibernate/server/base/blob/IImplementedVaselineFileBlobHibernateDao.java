package ir.amv.os.vaseline.file.apis.daoimpl.hibernate.server.base.blob;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.data.hibernate.apis.dao.server.crud.IBaseImplementedHibernateCrudDao;
import ir.amv.os.vaseline.file.apis.daogeneric.jpa.server.dao.base.blob.IVaselineFileBlobDao;
import ir.amv.os.vaseline.file.apis.daogeneric.jpa.server.model.base.blob.VaselineFileBlobEntity;
import ir.amv.os.vaseline.file.apis.model.server.base.IVaselineFileEntity;
import org.hibernate.Criteria;
import org.hibernate.LobHelper;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Blob;

import static ir.amv.os.vaseline.basics.apis.core.shared.util.stream.StreamUtils.copyStreams;

public interface IImplementedVaselineFileBlobHibernateDao
        extends IBaseImplementedHibernateCrudDao<VaselineFileBlobEntity, Long>, IVaselineFileBlobDao {

    @Override
    default Long saveFileUsingStream(VaselineFileBlobEntity fileEntity, InputStream inputStream) throws Exception {
        long dataSize;
        dataSize = inputStream.available();
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        LobHelper lobHelper = session.getLobHelper();
        Blob dataBlob = lobHelper.createBlob(inputStream, dataSize);
        fileEntity.setFileContent(dataBlob);
        Serializable id = session.save(fileEntity);
        session.getTransaction().commit();
        session.close();
        return (Long) id;
    }

    @Override
    default void writeFileContent(Long fileId, OutputStream outputStream) throws Exception {
        DetachedCriteria detCriteria = createCriteria();
        detCriteria.add(Restrictions.idEq(fileId));
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = detCriteria.getExecutableCriteria(session);
        IVaselineFileEntity entity = getEntityFromCriteria(criteria);
        try (InputStream inputStream = ((VaselineFileBlobEntity) entity).getFileContent().getBinaryStream()){
            copyStreams(inputStream, outputStream);
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    default IVaselineFileEntity createFile(String category) throws BaseVaselineServerException {
        return new VaselineFileBlobEntity();
    }
}
