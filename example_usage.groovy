//@GrabResolver(name='joaolourenco', root='https://dl.bintray.com/joaolourenco/maven')
@Grab(group='org.cloudec', module='paginator-java', version='0.1.1')

import org.cloudec.paginator.PaginatorWrapper;

class DataSource{
    List data = ['samsung','lg','sony', 'motorola', 'nokia','iphone','blackberry']

    PaginatorWrapper<String> getData(int offset, int limit){
        new PaginatorWrapper<String>(
                query(offset,limit), // query paginate
                data.size(), // size of all data
                offset, // index of currente page
                limit) // limit of items for page
    }

    def query(int offset, int limit){
        if (!offset) offset = 0
        //slice data
        data[Math.min(offset, data.size())..<Math.min(offset + limit, data.size())]
    }
}

def dataSource = new DataSource()

PaginatorWrapper<String> page1 = new DataSource().getData(0,2)

assert page1.items == ['samsung','lg']
println "Page 1: $page1.items"

PaginatorWrapper<String> page2 = new DataSource().getData(page1.next, 2)

assert page2.items == ['sony','motorola']
println "Page 2: $page2.items"

PaginatorWrapper<String> page3 = new DataSource().getData(page2.next,2)

assert page3.items == ['nokia','iphone']
println "Page 3: $page3.items"

PaginatorWrapper<String> page4 = new DataSource().getData(page3.next,2)

assert page4.items == ['blackberry']
println "Page 4: $page4.items"




