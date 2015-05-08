
/* Maria L Martinez - mlmartinez85@gmail.com
 * CS 4311 - Week 2b Assignment
 * Winter 2015
 * 01/21/2015
 *
 * QueueTest.java
 * this is a JUnit Test for assignment 2a, class Queue.java
 */

import cs4311.queue.Queue;
import junit.framework.*;

/*
 * AllTests.java
 */
class AllTests {
    public static Test suite() {
        TestSuite suite = new TestSuite();
        TestSuite A = new TestSuite();
        suite.addTest(A);
        A.addTest(new QueueTest("testQueue"));
        return suite;
    }
    public static void main(String args[]) {
        Test allTests = suite();
        TestResult result = new TestResult();
        allTests.run(result);
        result.print();
    }
}

public class QueueTest extends TestCase {
	
    public QueueTest(String s) { 
        super(s); 
    }

    public void testQueue() {
        // a new Queue should be empty
        Queue testQ = new Queue();
        assertTrue("isEmpty", testQ.isEmpty());
        
        // after adding an element the queue should not be empty
        for (int i = 1; i < 11; i++) {
            testQ.enqueue(i);
            assertTrue("!isEmpty", !testQ.isEmpty());
        }
        
        // each dequeue should return 1...10 in this order to respect FIFO
        int expected = 1;
        int actual = 0;
        while (!testQ.isEmpty()) {
            actual = (int) testQ.dequeue();
            assertEquals(expected, actual);
            expected++;
        }
    }	
}

/* QueueTest Output

run:
Errors:
Failures:
BUILD SUCCESSFUL (total time: 0 seconds)

*/