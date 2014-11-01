package org.cloudec.paginator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Paginator Class
 *
 * @param <T> extends {@link java.lang.Object}
 * @author joaolourenco
 * @since 0.1
 */
public class PaginatorWrapper<T> {

    private Collection<T> items;
    private Integer limit;
    private Integer next;
    private Integer current;
    private Integer previus;
    private Integer first;
    private Integer last;
    private List<Integer> paginas;
    private final Integer totalCount;

    /**
     * Create new PaginatorWrapper with set of objects, total of objects and index of current page.
     * <p>
     * The default page size is 10.
     * </p>
     * @param items objects in current page
     * @param totalCount total objects
     * @param current index of current page
     */
    public PaginatorWrapper(Collection<T> items, Integer totalCount, Integer current) {
        this.items = items;
        this.totalCount = totalCount;
        this.current = current;
        this.limit = 10;
        this.first = 0;
        setup();
    }

    /**
     * Create new PaginatorWrapper with set of objects, total of objects, index of current page and page size.
     * @param items objects in current page
     * @param totalCount total objects in all pages
     * @param current index of current page
     * @param limit page size
     */
    public PaginatorWrapper(Collection<T> items, Integer totalCount, Integer current, Integer limit) {
        this.items = items;
        this.totalCount = totalCount;
        this.current = current;
        this.limit = limit;
        this.first = 0;
        setup();
    }

    /**
     * Configure pages and index.
     */
    private void setup() {
        this.paginas = new ArrayList<Integer>();
        for (Integer i = 0; i < totalCount; i++) {
            if (i == 0) paginas.add(i);
            if (i % limit == 0 && (i + limit) < totalCount) paginas.add(i + limit);
        }
        this.last = paginas.size() > 0 ? paginas.get(paginas.size()-1) : 0 ;
        this.next = totalCount == limit ? 0 : current + limit;
        this.previus = Math.max(0, current - limit);

    }

    public Integer getLimit() {
        return limit;
    }

    public Collection<T> getItems() {
        return items;
    }

    /**
     *
     * @return Index of current page.
     */
    public Integer getCurrent() {
        return current;
    }

    public Integer getPageCurrent() {
        return paginas != null && !paginas.isEmpty() ? paginas.indexOf(current) + 1 : 0;
    }

    /**
     *
     * @return Index of previus page.
     */
    public Integer getPrevius() {
        return previus;
    }

    /**
     *
     * @return Index of next page.
     */
    public Integer getNext() {
        return next;
    }

    /**
     *
     * @return Index of last page.
     */
    public Integer getLast() {
        return last;
    }

    /**
     *
     * @return Index of first page.
     */
    public Integer getFirst() {
        return first;
    }

    /**
     *
     * @return Total objects in currente page.
     */
    public Integer getTotalCount() {
        return totalCount;
    }

    /**
     *
     * @return All first index of pages.
     */
    public Collection<Integer> getPaginas() {
        return paginas;
    }
}

