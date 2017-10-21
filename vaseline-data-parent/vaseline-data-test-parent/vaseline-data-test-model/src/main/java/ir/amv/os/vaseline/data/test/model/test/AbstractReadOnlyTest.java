package ir.amv.os.vaseline.data.test.model.test;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
import org.junit.Test;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Amir
 */
@Transactional
public abstract class AbstractReadOnlyTest
        extends BaseDataModelTest {

    protected abstract IBaseReadOnlyDao<TestCountryEntity, Long> getCountryDao();

    @Test
    public void testGetById() {
        TestCountryEntity iran = countriesMap.get("Iran");
        TestCountryEntity byId = getCountryDao().getById(iran.getId());
        assertEquals(iran, byId);
    }

    @Test
    public void testGetByIdDetached() {
        TestCountryEntity iran = countriesMap.get("Iran");
        TestCountryEntity byId = getCountryDao().getById(iran.getId());
        assertEquals(iran, byId);
    }

//    public void testCountAllApproximately();

    @Test
    public void testCountAll() {
        Long countriesCount = getCountryDao().countAll();
        assertEquals(3, countriesCount.longValue());
    }

    @Test
    public void testGetAll() {
        List<TestCountryEntity> all = getCountryDao().getAll();
        assertEquals(3, all.size());
        for (TestCountryEntity anAll : all) {
            assertEquals(countriesMap.get(anAll.getCountryName()), anAll);
        }
    }

    @Test
    public void testGetAllPaging() {
        PagingDto pagingDto = new PagingDto(Collections.singletonList(new SortDto("countryName", true)), 0, 1);
        List<TestCountryEntity> page = getCountryDao().getAll(pagingDto);
        assertEquals(1, page.size());
        assertEquals("Canada", page.get(0).getCountryName());
    }

    @Test
    public void testScrollAll() {
        IVaselineDataScroller<TestCountryEntity> scroller = getCountryDao().scrollAll(Collections.singletonList(new SortDto("countryName", true)));
        boolean next = scroller.next();
        assertTrue(next);
        assertEquals(countriesMap.get("Canada"), scroller.get()[0]);
        next = scroller.next();
        assertTrue(next);
        assertEquals(countriesMap.get("Iran"), scroller.get()[0]);
        next = scroller.next();
        assertTrue(next);
        assertEquals(countriesMap.get("Malaysia"), scroller.get()[0]);
    }
}
