package transmission;

import org.junit.Test;

import static org.junit.Assert.*;

public class AutoTransmissionTest {

  @Test
  public void testAll() {
    Transmission transmission = new AutoTransmission(1, 3, 5, 7, 9);
    assertEquals(0, transmission.getGear());
    transmission = transmission.increaseSpeed();
    assertEquals(1, transmission.getGear());
    transmission = transmission.increaseSpeed();
    assertEquals(2, transmission.getGear());
    transmission = transmission.increaseSpeed();
    assertEquals(3, transmission.getGear());
    transmission = transmission.increaseSpeed();
    assertEquals(4, transmission.getGear());
    transmission = transmission.increaseSpeed();
    assertEquals(5, transmission.getGear());
    assertEquals(10, transmission.getSpeed());
    assertEquals("Transmission (speed = 10, gear = 5)", transmission.toString());
    transmission = transmission.decreaseSpeed();
    assertEquals(4, transmission.getGear());
    transmission = transmission.decreaseSpeed();
    assertEquals(3, transmission.getGear());
    transmission = transmission.decreaseSpeed();
    assertEquals(2, transmission.getGear());
    transmission = transmission.decreaseSpeed();
    assertEquals(1, transmission.getGear());
    transmission = transmission.decreaseSpeed();
    assertEquals(0, transmission.getGear());
  }
}