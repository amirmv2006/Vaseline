package ir.amv.os.vaseline.file.dao.def.hibernate.base.blob;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.crud.IDefaultHibernateCrudDao;
import ir.amv.os.vaseline.file.dao.def.common.server.dao.base.blob.IVaselineFileBlobDao;
import ir.amv.os.vaseline.file.dao.def.common.server.model.base.blob.VaselineFileBlobEntity;
import ir.amv.os.vaseline.file.model.api.server.base.IVaselineFileEntity;
import org.hibernate.Criteria;
import org.hibernate.LobHelper;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Blob;

import static ir.amv.os.vaseline.basics.core.api.shared.util.stream.StreamUtils.copyStreams;

public interface IDefaultVaselineFileBlobHibernateDao
        extends IDefaultHibernateCrudDao<VaselineFileBlobEntity, Long>, IVaselineFileBlobDao {

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
        InputStream inputStream = ((VaselineFileBlobEntity) entity).getFileContent().getBinaryStream();
        copyStreams(inputStream, outputStream);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    default VaselineFileBlobEntity createFile(String category) throws BaseVaselineServerException {
        return new VaselineFileBlobEntity();
    }
}
