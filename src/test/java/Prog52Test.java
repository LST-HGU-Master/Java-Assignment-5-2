import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.*;
/**
 * @version (20220501)
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
    public void testAddInt()
    {
        // action
        // in.inputln("2"); // 標準入力をテストする場合
        // Hello.main(new String[]{"1", "2", "3"}); // 実行時引数をテストする場合

        // assertion
        // TODO: int型であることを調べるべき
        int result = Prog52.add(10, 55);
        try {
            assertEquals(65, result, "「戻り値がint型のadd()」に不正なところがあります");
        } catch (AssertionError err) {
            after();
            throw err;     
        }
    }

    @Test
    public void testAddDouble()
    {
        // action
        // in.inputln("2"); // 標準入力をテストする場合
        // Hello.main(new String[]{"1", "2", "3"}); // 実行時引数をテストする場合

        // assertion
        double result = Prog52.add(10.1, 55.0);
        try {
            assertEquals(65.1, result, 0.0001f, "「戻り値がdouble型のadd()」に不正なところがあります");
        } catch (AssertionError err) {
            after();
            throw err;
        }
    }

    @Test
    public void testMain()
    {
        // action
        Prog52.main(new String[]{"31", "6"});

        // assertion
        String[] prints = bos.toString().split(System.lineSeparator());
        try {
            assertEquals("37", prints[1],  "mainメソッド内での「戻り値がint型のaddメソッド」の使い方が不正です!");
            assertEquals("37.0", prints[3],"mainメソッド内での「戻り値がdouble型のaddメソッド」の使い方が不正です!");
        } catch (AssertionError err) {
            after();
            throw err;     
        } catch (ArrayIndexOutOfBoundsException aIOBExcpt) {
            after();
            throw new AssertionError("実行結果の行数が正しくありません!");
        }
    }
}
