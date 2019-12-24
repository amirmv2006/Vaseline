package ir.amv.os.vaseline.data.test.model.server.repository;

public interface IBaseTestRepository<E> {

    E save(E e);
    void delete(E e);
}
