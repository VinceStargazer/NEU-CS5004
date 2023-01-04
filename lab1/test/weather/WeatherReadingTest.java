package weather;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the WeatherReading class.
 */

public class WeatherReadingTest {
  private WeatherReading weatherReading;
  private static final double c1 = -8.78469475556, c2 = 1.61139411, c3 = 2.33854883889, c4 = -0.14611605,
          c5 = -0.012308094, c6 = -0.0164248277778, c7 = 0.002211732, c8 = 0.00072546, c9 = -0.000003582;
  private int T, D, v, rain, R;

  @Before
  public void setUp() {
    T = 23;
    D = 12;
    v = 3;
    rain = 13;
    weatherReading = new WeatherReading(T, D, v, rain);
    R = 100 - 5 * (T - D);
  }

  @Test
  public void testTemperature() {
    assertEquals(T, weatherReading.getTemperature());
  }

  @Test
  public void testDewPoint() {
    assertEquals(D, weatherReading.getDewPoint());
  }

  @Test
  public void testWindSpeed() {
    assertEquals(v, weatherReading.getWindSpeed());
  }

  @Test
  public void testTotalRain() {
    assertEquals(rain, weatherReading.getTotalRain());
  }

  @Test
  public void testRelativeHumidity() {
    assertEquals(R, weatherReading.getRelativeHumidity());
  }

  @Test
  public void testHeatIndex() {
    assertEquals(c1 + c2 * T + c3 * R + c4 * T * R + c5 * T * T + c6 * R * R +
            c7 * T * T * R + c8 * T * R * R + c9 * T * T * R * R, weatherReading.getHeatIndex(), 0.0001);
  }

  @Test
  public void testWindChill() {
    assertEquals(35.74 + 0.6215 * T - 35.75 * Math.pow(v, 0.16) +
            0.4275 * T * Math.pow(v, 0.16), weatherReading.getWindChill(), 0.0001);
  }

  @Test
  public void testToString() {
    System.out.println(weatherReading.toString());
    assertEquals("Reading: T = " + T + ", D = " + D + ", v = " + v + ", rain = " + rain,
            weatherReading.toString());
  }
}