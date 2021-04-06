package application;
import static org.junit.Assert.*;
import org.junit.Test;

public class SandwichTest {
	@Test
	public void testAdd () {
		Sandwich s = new ChickenSandwich ();
		assertFalse (s.add(new Object ()));
		assertTrue (s.add(Extra.AVOCADO));
		assertFalse (s.add(Extra.AVOCADO));
		assertTrue (s.add(Extra.BACON));
		assertTrue (s.add(Extra.BALSAMIC));
		assertTrue (s.add(Extra.CHEDDAR));
		assertTrue (s.add(Extra.CUCUMBER));
		assertTrue (s.add(Extra.LETTUCE));
		assertFalse  (s.add(Extra.MAYO));
	}
	
	@Test
	public void testRemove () {
		Sandwich s = new ChickenSandwich ();
		s.add(Extra.AVOCADO);
		assertFalse (s.remove(new Object ()));
		assertFalse (s.remove(Extra.BACON));
		assertTrue (s.remove(Extra.AVOCADO));
	}
	
	@Test
	public void testPrice () {
		Sandwich s = new ChickenSandwich ();
		double expectedPrice = 8.99;
		assertEquals (expectedPrice, s.price(), 0.01);
		s.add(Extra.AVOCADO);
		expectedPrice += 1.99;
		assertEquals (expectedPrice, s.price(), 0.01);
		s.add(Extra.BACON);
		expectedPrice += 1.99;
		assertEquals (expectedPrice, s.price(), 0.01);
		s.add(Extra.BALSAMIC);
		expectedPrice += 1.99;
		assertEquals (expectedPrice, s.price(), 0.01);
		s.add(Extra.CHEDDAR);
		expectedPrice += 1.99;
		assertEquals (expectedPrice, s.price(), 0.01);
		s.add(Extra.CHIPOTLE_MAYO);
		expectedPrice += 1.99;
		assertEquals (expectedPrice, s.price(), 0.01);
		s.add(Extra.CUCUMBER);
		expectedPrice += 1.99;
		assertEquals (expectedPrice, s.price(), 0.01);
		s = new BeefSandwich ();
		expectedPrice = 10.99;
		assertEquals (expectedPrice, s.price(), 0.01);
		s.add(Extra.AVOCADO);
		expectedPrice += 1.99;
		assertEquals (expectedPrice, s.price(), 0.01);
		s.add(Extra.BACON);
		expectedPrice += 1.99;
		assertEquals (expectedPrice, s.price(), 0.01);
		s.add(Extra.BALSAMIC);
		expectedPrice += 1.99;
		assertEquals (expectedPrice, s.price(), 0.01);
		s.add(Extra.CHEDDAR);
		expectedPrice += 1.99;
		assertEquals (expectedPrice, s.price(), 0.01);
		s.add(Extra.CHIPOTLE_MAYO);
		expectedPrice += 1.99;
		assertEquals (expectedPrice, s.price(), 0.01);
		s.add(Extra.CUCUMBER);
		expectedPrice += 1.99;
		assertEquals (expectedPrice, s.price(), 0.01);
		s = new FishSandwich ();
		expectedPrice = 12.99;
		assertEquals (expectedPrice, s.price(), 0.01);
		s.add(Extra.AVOCADO);
		expectedPrice += 1.99;
		assertEquals (expectedPrice, s.price(), 0.01);
		s.add(Extra.BACON);
		expectedPrice += 1.99;
		assertEquals (expectedPrice, s.price(), 0.01);
		s.add(Extra.BALSAMIC);
		expectedPrice += 1.99;
		assertEquals (expectedPrice, s.price(), 0.01);
		s.add(Extra.CHEDDAR);
		expectedPrice += 1.99;
		assertEquals (expectedPrice, s.price(), 0.01);
		s.add(Extra.CHIPOTLE_MAYO);
		expectedPrice += 1.99;
		assertEquals (expectedPrice, s.price(), 0.01);
		s.add(Extra.CUCUMBER);
		expectedPrice += 1.99;
		assertEquals (expectedPrice, s.price(), 0.01);
	}
}
