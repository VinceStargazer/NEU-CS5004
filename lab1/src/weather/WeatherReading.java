package weather;

import java.util.Arrays;

/**
 * This class represents a single reading of a weather station in a Stevenson Station.
 */

public class WeatherReading {
  private static final double c1 = -8.78469475556, c2 = 1.61139411, c3 = 2.33854883889, c4 = -0.14611605,
          c5 = -0.012308094, c6 = -0.0164248277778, c7 = 0.002211732, c8 = 0.00072546, c9 = -0.000003582;
  private final int T, D, v, rain, R;

  /**
   * Constructs a WeatherReading object and initializes it to the given params
   *
   * @param T    the air temperature in Celsius
   * @param D    the dew point temperature in Celsius which cannot be greater than the air temperature
   * @param v    the non-negative wind speed in miles per hour
   * @param rain the non-negative total rain received in millimeters
   */
  public WeatherReading(int T, int D, int v, int rain) {
    if (T < D)
      throw new IllegalArgumentException("The dew point temperature cannot be greater than the air temperature!");
    if (v < 0)
      throw new IllegalArgumentException("Wind speed cannot be negative!");
    if (rain < 0)
      throw new IllegalArgumentException("Total rain cannot be negative!");
    this.T = T;
    this.D = D;
    this.v = v;
    this.rain = rain;
    this.R = 100 - 5 * (T - D);
  }

  /**
   * @return the air temperature in Celsius
   */
  public int getTemperature() {
    return T;
  }

  /**
   * @return the dew point temperature in Celsius which cannot be greater than the air temperature
   */
  public int getDewPoint() {
    return D;
  }

  /**
   * @return the non-negative wind speed in miles per hour
   */
  public int getWindSpeed() {
    return v;
  }

  /**
   * @return the non-negative total rain received in millimeters
   */
  public int getTotalRain() {
    return rain;
  }

  /**
   * @return the relative humidity in percentage between 0 and 100 (R)
   */
  public int getRelativeHumidity() {
    return R;
  }

  /**
   * @return the Heat index which measures how hot it feels when relative humidity
   * is factored in with the actual temperature
   */
  public double getHeatIndex() {
    return c1 + c2 * T + c3 * R + c4 * T * R + c5 * T * T + c6 * R * R +
            c7 * T * T * R + c8 * T * R * R + c9 * T * T * R * R;
  }

  /**
   * @return the Wind chill which is used when the real-feel temperature is
   * lower than the actual temperature
   */
  public double getWindChill() {
    return 35.74 + 0.6215 * T - 35.75 * Math.pow(v, 0.16) + 0.4275 * T * Math.pow(v, 0.16);
  }

  /**
   * @return the temperature, dew point, wind speed, and total rain in String format
   */
  @Override
  public String toString() {
    return "Reading: T = " + T + ", D = " + D + ", v = " + v + ", rain = " + rain;
  }

  public static void main(String[] args) {
    WeatherReading weatherReading = new WeatherReading(23, 12, 3, 13);
    System.out.println(weatherReading.getRelativeHumidity());
    System.out.println(weatherReading.getHeatIndex());
    System.out.println(weatherReading.getWindChill());
  }
}
