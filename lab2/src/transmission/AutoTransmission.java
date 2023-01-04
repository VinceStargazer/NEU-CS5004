package transmission;

public class AutoTransmission implements Transmission {
  private final int t1, t2, t3, t4, t5;
  private int speed, gear;

  public AutoTransmission(int t1, int t2, int t3, int t4, int t5) {
    if (t1 <= 0 || t2 <= 0 || t3 <= 0 || t4 <= 0 || t5 <= 0)
      throw new IllegalArgumentException("Threshold must be positive!");
    if (t1 >= t2 || t2 >= t3 || t3 >= t4 || t4 >= t5)
      throw new IllegalArgumentException("Thresholds must be in increasing order!");
    this.t1 = t1;
    this.t2 = t2;
    this.t3 = t3;
    this.t4 = t4;
    this.t5 = t5;
  }

  public Transmission increaseSpeed() {
    speed += 2;
    initGear();
    AutoTransmission newOne = new AutoTransmission(t1, t2, t3, t4, t5);
    newOne.speed = speed;
    newOne.gear = gear;
    return newOne;
  }

  public Transmission decreaseSpeed() {
    if (speed < 2) throw new IllegalStateException("Speed already less than 2");
    speed -= 2;
    initGear();
    AutoTransmission newOne = new AutoTransmission(t1, t2, t3, t4, t5);
    newOne.speed = speed;
    newOne.gear = gear;
    return newOne;
  }

  public int getSpeed() {
    return speed;
  }

  public int getGear() {
    initGear();
    return gear;
  }

  public String toString() {
    initGear();
    return "Transmission (speed = " + speed + ", gear = " + gear + ")";
  }

  private void initGear() {
    if (speed < t1) gear = 0;
    else if (speed < t2) gear = 1;
    else if (speed < t3) gear = 2;
    else if (speed < t4) gear = 3;
    else if (speed < t5) gear = 4;
    else gear = 5;
  }
}
