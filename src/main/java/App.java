public class App {
  public static void main(String[] args) {
    Producer producer = new Producer("topico");
    try {
      producer.produce("123", "aleleeele");
      System.out.println("Hello World!");

    } catch (Exception e) {
      System.out.println("erro " + e.getMessage());
    }
  }
}
