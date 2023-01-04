import java.util.*;

public class SecretSanta implements Iterable<SecretSanta.Person> {

  private final Set<Person> everyone;

  public SecretSanta() {
    everyone = new HashSet<>();
  }

  public Iterator<Person> iterator() {
    return new PersonIterator();
  }

  public class PersonIterator implements Iterator<SecretSanta.Person> {
    private final Set<String> seen = new HashSet<>();
    private final Iterator<Person> iter = everyone.iterator();
    private Person person = iter.next();

    public boolean hasNext() {
      return seen.size() < everyone.size();
    }

    public Person next() {
      if (!hasNext()) return null;
      Person temp = person;
      seen.add(temp.name);
      if (!hasNext()) person = null;
      else if (!seen.contains(person.recipient.name))
        person = person.recipient;
      else {
        while (true) {
          Person next = iter.next();
          if (!seen.contains(next.name)) {
            person = next;
            break;
          }
        }
      }
      return temp;
    }
  }

  public static class Person {

    private final String name;
    private Person recipient;

    private Person(String name, Person recipient) {
      this.name = name;
      this.recipient = recipient;
    }

    private Person(String name) {
      this(name, null);
    }
  }

  private void addPerson(Person person) {
    everyone.add(person);
  }

  public boolean allOneLoop() {
    Set<String> seen = new HashSet<>();
    Person first = everyone.iterator().next();
    dfs(first, seen);
    return seen.size() == everyone.size();
  }

  private void dfs(Person person, Set<String> seen) {
    seen.add(person.name);
    if (!seen.contains(person.recipient.name))
      dfs(person.recipient, seen);
  }

  public static void main(String[] args) {
    SecretSanta secretSanta = new SecretSanta();
    Person cat = new Person("Cat"), dog = new Person("Dog"), elephant = new Person("Elephant"),
            giraffe = new Person("Giraffe"), horse = new Person("Horse");
    cat.recipient = dog;
    dog.recipient = elephant;
    elephant.recipient = cat;
    giraffe.recipient = horse;
    horse.recipient = giraffe;
    for (Person p : new Person[]{cat, dog, elephant, giraffe, horse})
      secretSanta.addPerson(p);
    System.out.println(secretSanta.allOneLoop());
    for (Person p : secretSanta)
      System.out.print(p.name + " -> ");
    System.out.println();

    elephant.recipient = giraffe;
    horse.recipient = cat;
    System.out.println(secretSanta.allOneLoop());
    for (Person p : secretSanta)
      System.out.print(p.name + " -> ");
    System.out.println();
  }
}