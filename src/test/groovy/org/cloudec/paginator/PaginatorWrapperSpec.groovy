package org.cloudec.paginator

import spock.lang.Specification

/**
 * Created by joao on 12/09/14.
 */
class PaginatorWrapperSpec extends Specification {


    def "deve validar paginator"() {
        given:
        def dados = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        when:
        def pagina = paginador(dados, offset, limit)
        def paginator = new PaginatorWrapper<Integer>(pagina, dados.size(), offset, limit)


        then:
        paginator.items == itemsEsperada
        paginator.next == next
        paginator.previus == previus
        paginator.current == current
        paginator.first == 0
        paginator.last == last
        paginator.paginas == paginasEsperada
        paginator.current == current

        where:
        offset | limit || next || previus || current || pageCurrent || last || itemsEsperada                  || paginasEsperada
        0      | 2     || 2    || 0       || 0       || 1           || 8    || [0, 1]                         || [0, 2, 4, 6, 8]
        2      | 2     || 4    || 0       || 2       || 2           || 8    || [2, 3]                         || [0, 2, 4, 6, 8]
        4      | 2     || 6    || 2       || 4       || 3           || 8    || [4, 5]                         || [0, 2, 4, 6, 8]
        3      | 2     || 5    || 1       || 3       || 2           || 8    || [3, 4]                         || [0, 2, 4, 6, 8]
        0      | 3     || 3    || 0       || 0       || 1           || 9    || [0, 1, 2]                      || [0, 3, 6, 9]
        0      | 10    || 0    || 0       || 0       || 1           || 0    || [0, 1, 2, 3, 4, 5, 6, 7, 8, 9] || [0]
    }


    def "deve validar paginador do test"() {
        given:
        def dados = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        when:
        def pagina = paginador(dados, offset, limit)

        then:
        pagina == paginaEsperada

        where:
        offset | limit || paginaEsperada
        0      | 2     || [0, 1]
        2      | 2     || [2, 3]
        3      | 2     || [3, 4]
    }


    def paginador(dados, offset, limit) {
        if (!offset) offset = 0
        dados[Math.min(offset, dados.size())..Math.min(offset + limit - 1, dados.size())]
    }


}
