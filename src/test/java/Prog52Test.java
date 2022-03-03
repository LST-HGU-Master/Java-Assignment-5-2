import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.*;

public class Prog52Test {

    @Test
    public void testAddInt()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        StandardInputStream in = new StandardInputStream();
        System.setIn(in);

        // action
        // in.inputln("2"); // 標準入力をテストする場合
        // Hello.main(new String[]{"1", "2", "3"}); // 実行時引数をテストする場合

        // assertion
        // TODO: int型であることを調べるべき
        int result = Prog52.add(10, 55);
        assertEquals(65, result);

        // undo the binding in System
        System.setOut(originalOut);
    }

    @Test
    public void testAddDouble()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        StandardInputStream in = new StandardInputStream();
        System.setIn(in);

        // action
        // in.inputln("2"); // 標準入力をテストする場合
        // Hello.main(new String[]{"1", "2", "3"}); // 実行時引数をテストする場合

        // assertion
        double result = Prog52.add(10.1, 55.0);
        assertEquals(65.1, result, 0.0001f);

        // undo the binding in System
        System.setOut(originalOut);
    }

    @Test
    public void testMain()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        StandardInputStream in = new StandardInputStream();
        System.setIn(in);

        // action
        Prog52.main(new String[]{"31", "6"});

        // assertion
        String[] prints = bos.toString().split("\n");
        assertEquals("37", prints[1]);
        assertEquals("37.0", prints[3]);

        // undo the binding in System
        System.setOut(originalOut);
    }
}
