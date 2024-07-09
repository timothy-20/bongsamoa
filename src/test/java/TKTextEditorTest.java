import com.timothy.bongsamoa.modules.TKDocument;
import org.junit.jupiter.api.Test;

import java.nio.CharBuffer;

public class TKTextEditorTest {
    @Test
    public void test() throws Exception {
        String strUserInput = "Hello, World...";
        TKDocument document = new TKDocument();
        document.append(strUserInput);

        CharBuffer buffer = CharBuffer.allocate(4);
        int length;

        while((length = document.read(buffer)) != -1) {
            buffer.flip();
            System.out.print(buffer);
            buffer.clear();

        }

        System.out.println();
        System.out.println("Reading string length: " + length);
    }
}