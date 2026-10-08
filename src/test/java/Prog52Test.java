import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.*;
/**
 * @version (20220501)
 * @version (20230417) suporting both println and print("\n") on Windows
 * @version (20262008) revised
 * 
 * (注意) Prog52クラス内に ２つのaddメソッド が適切に宣言されるまで、
 * 　　　　このテストクラスは「シンボルを見つけられません」または「不適合な型：精度が失われる変換」
 * 　　　　というエラーが表示される
 **/
public class Prog52Test {
    InputStream originalIn;
    PrintStream originalOut;
    ByteArrayOutputStream bos;
    StandardInputStream in;
    
    @BeforeEach
    void before() {
        //back up binding
        originalIn  = System.in;
        originalOut = System.out;
        //modify binding
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        
        in = new StandardInputStream();
        System.setIn(in);
    }
    
    @AfterEach
    void after() {
       System.setOut(originalOut);
       System.setIn(originalIn);
    }

    @Test
    public void testAddInt() {
        int result = Prog52.add(10, 55);
        assertEquals(65, result, "「戻り値がint型のadd()」の計算結果または戻り値の型が正しくありません!");
    }

    @Test
    public void testAddDouble() {
        double result = Prog52.add(10.1, 55.0);
        assertEquals(65.1, result, 0.0001, "「戻り値がdouble型のadd()」の計算結果または戻り値の型が正しくありません!");
    }

    @Test
    public void testMain() {
        Prog52.main(new String[]{"31", "6"});

        String[] prints = bos.toString().replace("\r\n", "\n").split("\n");

        assertTrue(prints.length >= 4, "実行結果が4行分ありません! プロンプト表示や各add()の出力結果・改行漏れがないか確認してください。");
        assertEquals("37", prints[1].trim(), "mainメソッド内での「戻り値がint型のaddメソッド」の呼び出し・出力結果が不正です!");
        assertEquals("37.0", prints[3].trim(), "mainメソッド内での「戻り値がdouble型のaddメソッド」の呼び出し・出力結果が不正です!");
    }
}
