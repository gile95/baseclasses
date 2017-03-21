package hr.fer.zemris.java.cstr;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import hr.fer.zemris.java.cstr.CString;

public class CStringTests {

	@Test
	public void testDefaultConstructor() {
		CString str = new CString("Cheesecake".toCharArray(), 1, 4);
		assertEquals("Expected hees", "hees", str.toString());
		assertEquals("Checking length", 4, str.length());
	}

	@Test
	public void testOnlyDataConstructor() {
		CString str = new CString("Cheesecake".toCharArray());
		assertEquals("Expected Cheesecake", "Cheesecake", str.toString());
		assertEquals("Checking length", 10, str.length());
	}
	
	@Test
	public void testOtherStringConstructor() {
		CString s = new CString("Cheesecake".toCharArray());
		CString str = new CString(s);
		assertEquals("Expected Cheesecake", "Cheesecake", str.toString());
		assertEquals("Checking length", 10, str.length());
	}
	
	@Test
	public void testIndexOf() {
		CString str = new CString("Cheesecake".toCharArray(), 2, 8);
		assertEquals("", 6, str.indexOf('k'));
	}
	
	@Test
	public void testStartsWith() {
		CString str = new CString("Cheesecake".toCharArray(), 2, 8);
		assertEquals("", true, str.startsWith(new CString("ee".toCharArray())));
	}
	
	@Test
	public void testEndsWith() {
		CString str = new CString("Cheesecake".toCharArray(), 2, 8);
		assertEquals("", true, str.endsWith(new CString("cake".toCharArray())));
	}
	
	@Test
	public void testContains() {
		CString str = new CString("Cheesecake".toCharArray(), 2, 8);
		assertEquals("", true, str.contains(new CString("eese".toCharArray())));
	}
	
	@Test
	public void testSubstring() {
		CString str = new CString("Cheesecake".toCharArray(), 2, 8);
		assertEquals("", "ee", str.substring(0, 2).toString());
	}
	
	@Test
	public void testLeft() {
		CString string = new CString(String.valueOf("Jogobella Cheescake").toCharArray(), 4, 8);
		assertEquals("", "bel", string.left(3).toString());
		assertEquals("", "", string.left(0).toString());
	}

	@Test
	public void testRight() {
		CString string = new CString(String.valueOf("Jogobella Cheescake").toCharArray(), 4, 8);
		assertEquals("", " Ch", string.right(3).toString());
		assertEquals("", "", string.right(0).toString());
	}
	
	@Test
	public void testAdd() {
		CString s = new CString("Muffin".toCharArray());
		CString string = new CString(String.valueOf("Jogobella Cheescake").toCharArray(), 4, 8);
		assertEquals("", "bella ChMuffin", string.add(s).toString());
	}
	
	@Test
	public void testReplaceAllChar() {
		CString string = new CString(String.valueOf("Jogobella Cheescake").toCharArray(), 4, 8);
		assertEquals("", "bella ch", string.replaceAll('C', 'c').toString());
	}
	
	@Test
	public void testReplaceAllString() {
		CString m1 = CString.fromString(" Ch");
		CString m2 = CString.fromString(" ch");
		CString string = new CString(String.valueOf("Jogobella Cheescake").toCharArray(), 4, 8);
		assertEquals("", "bella ch", string.replaceAll(m1, m2).toString());
	}
	
	@Test(expected = NullPointerException.class)
	public void testNullInputOnDefaultConstructor() {
		// must throw!
		new CString(null, 2, 5);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testInvalidMargins() {
		// must throw!
		new CString(String.valueOf("Cheesecake").toCharArray(), 2, 12);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testLeftInvalidIndex1() {
		// must throw!
		new CString(String.valueOf("Cheesecake").toCharArray(), 0, 3).left(-1);

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testLeftInvalidIndex2() {
		// must throw!
		new CString(String.valueOf("Cheesecake").toCharArray(), 0, 3).left(24);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRightInvalidIndex1() {
		// must throw!
		new CString(String.valueOf("Cheesecake").toCharArray(), 0, 3).right(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRightInvalidIndex2() {
		// must throw!
		new CString(String.valueOf("Cheesecake").toCharArray(), 0, 3).right(24);
	}

	@Test(expected = NullPointerException.class)
	public void testStartsWithNullValue() {
		// must throw!
		new CString(String.valueOf("Cheesecake").toCharArray()).startsWith(null);
	}

	@Test(expected = NullPointerException.class)
	public void testFromStringWithNullValue() {
		// must throw!
		CString.fromString(null);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSubstringWithInvalidArguments1() {
		// must throw!
		CString.fromString("Cheesecake").substring(-1, 3);
	}

	@Test(expected = NullPointerException.class)
	public void testAddWithNullValue() {
		// must throw!
		CString.fromString("Cheesecake").add(null);
	}

	@Test(expected = NullPointerException.class)
	public void testReplaceAllWithNullAsValue() {
		// must throw!
		CString.fromString("Cheesecake").replaceAll(null, null);

	}
}