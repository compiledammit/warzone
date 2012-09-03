import grails.test.mixin.TestMixin
import com.compiledammit.Dump
/**
 * Created with IntelliJ IDEA.
 * User: 558848
 * Date: 8/23/12
 * Time: 5:33 PM
 * To change this template use File | Settings | File Templates.
 */
class DumpIntegrationTests extends GroovyTestCase {
    Dump dump
    void testDump()
    {

        def m = [key:'value',key2: 'value2']
        def l = [1,2,3]
        def b = true
        def d = new Dump()
        SortedSet s = new TreeSet<String>();
        s.add('z')
        s.add('a')
        s.add('j')

        def c = [1,[key: [l,2,true],key2:true],false,[m,l,b]]
        def test = dump.dump(c)
        def output = test
        def boo = true
    }
}
