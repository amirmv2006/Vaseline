package ir.amv.os.vaseline.data.test.model.test;

import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.sort.SortDto;
import ir.amv.os.vaseline.data.dao.basic.api.ro.IBasePersistentModelRepository;
import ir.amv.os.vaseline.data.dao.basic.api.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryBusinessModel;
import org.junit.Test;

import javax.transaction.Transactional;
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

    protected abstract IBasePersistentModelRepository<Long, TestCountryBusinessModel> getCountryDao();

    @Test
    public void testGetById() {
        TestCountryBusinessModel iran = countriesMap.get("Iran");
        TestCountryBusinessModel byId = getCountryDao().getById(iran.getId());
        assertEquals(iran, byId);
    }

    @Test
    public void testGetByIdDetached() {
        TestCountryBusinessModel iran = countriesMap.get("Iran");
        TestCountryBusinessModel byId = getCountryDao().getById(iran.getId());
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
        List<TestCountryBusinessModel> all = getCountryDao().getAll();
        assertEquals(3, all.size());
        for (TestCountryBusinessModel anAll : all) {
            assertEquals(countriesMap.get(anAll.getCountryName()), anAll);
        }
    }

    @Test
    public void testGetAllPaging() {
        PagingDto pagingDto = new PagingDto(Collections.singletonList(new SortDto("countryName", true)), 0, 1);
        List<TestCountryBusinessModel> page = getCountryDao().getAll(pagingDto);
        assertEquals(1, page.size());
        assertEquals("Canada", page.get(0).getCountryName());
    }

    @Test
    public void testScrollAll() {
        IVaselineDataScroller<TestCountryBusinessModel> scroller = getCountryDao().scrollAll(Collections.singletonList(new SortDto("countryName", true)));
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
