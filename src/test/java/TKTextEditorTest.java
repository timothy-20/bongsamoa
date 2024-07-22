import com.timothy.bongsamoa.modules.TKDocument;
import com.timothy.bongsamoa.modules.TKIntegerRange;
import com.timothy.bongsamoa.modules.temp.TKEditor;
import com.timothy.bongsamoa.modules.temp.TKTextCursor;
import com.timothy.bongsamoa.modules.temp.TKTextEditor;
import com.timothy.bongsamoa.modules.temp.TKTextEditorAccessor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class TKTextEditorTest {
//    @Test
//    public void testRead() throws Exception {
//        String strUserInput = "Hello, World...";
//        TKDocument document = new TKDocument();
//        document.append(strUserInput);
//
//        CharBuffer buffer = CharBuffer.allocate(4);
//        int length;
//
//        while((length = document.read(buffer)) != -1) {
//            buffer.flip();
//            System.out.print(buffer);
//            buffer.clear();
//
//        }
//
//        System.out.println();
//        System.out.println("Reading string length: " + length);
//    }

    @Test
    public void test() {
        StringBuilder stringBuilder = new StringBuilder("This is test.");
        TKTextCursor cursor = new TKTextCursor(stringBuilder::length);
        cursor.moveBack();

        TKIntegerRange range = cursor.getPosition();

        stringBuilder.append(" ABCDEF");
        cursor.mode = TKTextCursor.Mode.MULTI;
        cursor.moveBack();

        TKIntegerRange range1 = cursor.getPosition();

        System.out.println("");
    }

    @Test
    public void test2() throws IOException {
        String filePath = "C:\\Users\\timothy\\IdeaProjects\\bongsamoa\\temp\\a";


    }
}