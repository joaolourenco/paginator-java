package org.cloudec.paginator

import spock.lang.Specification

/**
 * Created by joao on 12/09/14.
 */
class PaginatorWrapperSpec extends Specification {


    def "should calculate pages"() {
        given:
        def data = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        when:
        def visiblePage = mountPage(data, offset, limit)
        def paginator = new PaginatorWrapper<Integer>(visiblePage, data.size(), offset, limit)


        then:
        paginator.items == itemsExpected
        paginator.next == next
        paginator.previus == previus
        paginator.current == current
        paginator.first == 0
        paginator.last == last
        paginator.pages == pagesExpected
        paginator.current == current

        where:
        offset | limit || next || previus || current || pageCurrent || last || itemsExpected                  || pagesExpected
        0      | 2     || 2    || 0       || 0       || 1           || 8    || [0, 1]                         || [0, 2, 4, 6, 8]
        2      | 2     || 4    || 0       || 2       || 2           || 8    || [2, 3]                         || [0, 2, 4, 6, 8]
        4      | 2     || 6    || 2       || 4       || 3           || 8    || [4, 5]                         || [0, 2, 4, 6, 8]
        3      | 2     || 5    || 1       || 3       || 2           || 8    || [3, 4]                         || [0, 2, 4, 6, 8]
        0      | 3     || 3    || 0       || 0       || 1           || 9    || [0, 1, 2]                      || [0, 3, 6, 9]
        0      | 10    || 0    || 0       || 0       || 1           || 0    || [0, 1, 2, 3, 4, 5, 6, 7, 8, 9] || [0]
    }


    def "should validade mountPage"() {
        given:
        def data = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        when:
        def page = mountPage(data, offset, limit)

        then:
        page == pageExpected

        where:
        offset | limit || pageExpected
        0      | 2     || [0, 1]
        2      | 2     || [2, 3]
        3      | 2     || [3, 4]
    }


    def mountPage(data, offset, limit) {
        if (!offset) offset = 0
        data[Math.min(offset, data.size())..<Math.min(offset + limit, data.size())]
    }


}
