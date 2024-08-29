package zzbot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ToDosTest {

    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void anotherDummyTest() {
        assertEquals(4, 4);
    }

    @Test
    void testToDosConstructorWithName() {
        // Test constructor with only name
        ToDos todo = new ToDos("Read a book");
        assertEquals("T", todo.type, "Type should be 'T'");
        assertEquals("Read a book", todo.getName(), "Name should be 'Read a book'");
        assertFalse(todo.isDone, "isDone should be false by default");
    }
}
