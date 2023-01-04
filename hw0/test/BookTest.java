import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A JUnit test class for the Book class.
 */
public class BookTest {

  private Book book;

  @Before
  public void setUp() {
    Person author = new Person("George", "Orwell", 1903);
    book = new Book("1984", author, 7.48f);
  }

  @Test
  public void testTitle() {
    assertEquals("1984", book.getTitle());
  }

  @Test
  public void testAuthor() {
    Person author = book.getAuthor();
    assertEquals("George", author.getFirstName());
    assertEquals("Orwell", author.getLastName());
    assertEquals(1903, author.getYearOfBirth());
  }

  @Test
  public void testPrice() {
    assertEquals(7.48f, book.getPrice(), 0.0001);
  }
}