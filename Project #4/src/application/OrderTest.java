package application;

import static org.junit.Assert.*;

import org.junit.Test;

public class OrderTest {

	@Test
	public void testConstructor () {
		Order o;
		for (int i = 2; i < Integer.MAX_VALUE; i++) {
			o = new Order ();
			assertEquals (i + 1, o.lineNumber);
		}
	}
	
	@Test
	public void testAdd () {
		Order o = new Order ();
		assertFalse (o.add(new Object ()));
		assertTrue (o.add(new ChickenSandwich ()));
		assertTrue (o.add(new BeefSandwich ()));
		assertTrue (o.add(new FishSandwich ()));
	}
	
	@Test
	public void testRemove () {
		Order o = new Order ();
		assertFalse (o.remove(new Object ()));
		o.add(new ChickenSandwich ());
		assertFalse (o.remove(new Integer (2)));
		assertFalse (o.remove(new Integer (0)));
		assertTrue (o.remove(new Integer (1)));
		o.add(new ChickenSandwich ());
		o.add(new BeefSandwich ());
		o.add(new FishSandwich ());
		assertTrue (o.remove(new Integer (2)));
		assertFalse (o.remove(new Integer (3)));
	}
}
