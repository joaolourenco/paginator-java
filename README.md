paginator-java
==============

Pagination for Java collections, is a utility class for data pagination independent of data source.

## Gradle usage

Add dependency:

        compile 'org.cloudec:paginator-java:0.1.1'

## Maven usage

Add dependency:

        <dependency>
            <groupId>org.cloudec</groupId>
            <artifactId>paginator-java</artifactId>
            <version>0.1.1</version>
            <type>jar</type>
        </dependency>

Configure repository:

        <?xml version='1.0' encoding='UTF-8'?>
        <settings xsi:schemaLocation='http://maven.apache.org/SETTINGS/1.0.0 
        http://maven.apache.org/xsd/settings-1.0.0.xsd' xmlns='http://maven.apache.org/SETTINGS/1.0.0' 
        xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>
        
        <profiles>
        	<profile>
        		<repositories>
        			<repository>
        				<snapshots>
        					<enabled>false</enabled>
        				</snapshots>
        				<id></id>
        				<name>bintray</name>
        				<url></url>
        			</repository>
        		</repositories>
        		<pluginRepositories>
        			<pluginRepository>
        				<snapshots>
        					<enabled>false</enabled>
        				</snapshots>
        				<id></id>
        				<name>bintray-plugins</name>
        				<url></url>
        			</pluginRepository>
        		</pluginRepositories>
        		<id>bintray</id>
        	</profile>
        </profiles>
        <activeProfiles>
        	<activeProfile>bintray</activeProfile>
        </activeProfiles>
        </settings>

## Code example to use with groovy script:
 
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









