package zzbot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Unit test for the {@link ToDos} class.
 * This test suite ensures the correct behavior of the {@link ToDos} constructor
 * and includes basic dummy tests for validation.
 */
class ToDosTest {

    /**
     * A dummy test to demonstrate basic assertion.
     * <p>
     * This test checks if the integers 2 and 2 are equal.
     * </p>
     */
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    /**
     * Another dummy test to demonstrate basic assertion.
     * <p>
     * This test checks if the integers 4 and 4 are equal.
     * </p>
     */
    @Test
    public void anotherDummyTest() {
        assertEquals(4, 4);
    }

    /**
     * Tests the {@link ToDos} constructor that accepts only the task name.
     * <p>
     * This test ensures that the task type is correctly set to 'T', the task name
     * is properly initialized, and the task's completion status (`isDone`) is set to false by default.
     * </p>
     */
    @Test
    void testToDosConstructorWithName() {
        // Test constructor with only name
        ToDos todo = new ToDos("Read a book");
        assertEquals("T", todo.type, "Type should be 'T'");
        assertEquals("Read a book", todo.getName(), "Name should be 'Read a book'");
        assertFalse(todo.isDone, "isDone should be false by default");
    }
}
