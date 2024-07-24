import com.timothy.bongsamoa.modules.TKDocument;
import com.timothy.bongsamoa.modules.TKIntegerRange;
import com.timothy.bongsamoa.modules.temp.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

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
    public void testRead() throws IOException {
        Path file = Path.of("C:\\Users\\timothy\\IdeaProjects\\bongsamoa\\temp\\aaa.txt");

        if (Files.exists(file)) {
            FileChannel fileChannel = FileChannel.open(file, StandardOpenOption.READ, StandardOpenOption.WRITE);
            TKCLIEditorAccessor accessor = new TKCLIEditorAccessor(fileChannel);
            accessor.setBufferSize(8);

            String result1 = accessor.read(10, 42);
            System.out.println("length:" + result1.length());
            System.out.println(result1);

            String result2 = accessor.readAll();
            System.out.println("length:" + result2.length());
            System.out.println(result2);

        } else {
            System.out.println("Fail testRead.");
        }
    }

    @Test
    public void testEdit() throws IOException {
        Path file = Path.of("C:\\Users\\timothy\\IdeaProjects\\bongsamoa\\temp\\aaa.txt");

        if (Files.exists(file)) {
            FileChannel fileChannel = FileChannel.open(file, StandardOpenOption.READ, StandardOpenOption.WRITE);
            TKCLIEditorAccessor accessor = new TKCLIEditorAccessor(fileChannel);
            accessor.append("Hello World, ");
            accessor.insert(3, "[INSERT]");
            accessor.replace(0, 3, "[REPLACE]");
            accessor.delete(17, 7);

        } else {
            System.out.println("Fail testEdit.");
        }
    }

    @Test
    public void testAdvancedEdit() throws IOException {
        Path file = Path.of("C:\\Users\\timothy\\IdeaProjects\\bongsamoa\\temp\\aaa.txt");

        if (Files.exists(file)) {
            FileChannel fileChannel = FileChannel.open(file, StandardOpenOption.READ, StandardOpenOption.WRITE);
            TKCLIEditorAccessor accessor = new TKCLIEditorAccessor(fileChannel);
            accessor.setBufferSize(4);
            accessor.test("test");

            String content = accessor.readAll();
            String[] lines = content.split("\r\n");

            System.out.println("");

        } else {
            System.out.println("Fail testEdit.");
        }
    }

    @Test
    public void testCopy() throws IOException {

    }
}