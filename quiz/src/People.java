public class People {
  public static class Harry {
    public void method1() {
      System.out.println("harry 1");
    }

    public void method2() {
      method1();
      System.out.println("harry 2");
    }
  }

  public static class Larry extends Harry {
    public void method1() {
      System.out.println("larry 1");
      super.method1();
    }
  }

  public static class Mary extends Larry {
    public void method2() {
      System.out.println("mary 2");
    }

    public void method3() {
      super.method1();
      System.out.println("mary 3");
    }
  }

  public static class Jerry extends Mary {
    public void method2() {
      super.method2();
      System.out.println("jerry 2");
    }
  }

  public static void main(String[] args) {
    Harry var1 = new Harry();
    Harry var2 = new Larry();
    Larry var3 = new Jerry();
    Mary var4 = new Mary();
    Mary var5 = new Jerry();
    Object var6 = new Larry();

    ((Mary) var6).method3();
  }
}
