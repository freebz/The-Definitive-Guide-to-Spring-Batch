// Listing 13-2. Test of Foo

...
public class FooTest {

    private Foo fooInstance;

    @BeforeEach
    public void setUp() {
	fooInstance = new Foo();
    }

    @Test
    public void testBar() {
	String results = fooInstance.bar();

	assertNotNull("Results were null", results);
	assertEquals("The test was not a success", "success", results);
    }

    @AfterEach
    public void tearDown() {
	fooInstance.close();
    }
}
