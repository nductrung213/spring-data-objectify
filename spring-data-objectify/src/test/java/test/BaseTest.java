package test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import com.googlecode.objectify.ObjectifyService;

import org.junit.After;
import org.junit.Before;

public abstract class BaseTest {
    private final LocalServiceTestHelper helperForClass =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    private com.googlecode.objectify.util.Closeable closeableForClass;

    public BaseTest() {
    }

    @Before
    public void setUp() throws Exception {
        helperForClass.setUp();
        closeableForClass = ObjectifyService.begin();
    }

    @After
    public void tearDown() throws Exception {
        closeableForClass.close();
        helperForClass.tearDown();
    }
}
