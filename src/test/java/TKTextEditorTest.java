import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.CharBuffer;

public class TKTextEditorTest {
    @Test
    public void test() throws Exception {
        String strUserInput = "Hello, World...";

    }
}

interface TKCreatable {

}

interface TKAccessible {

}

interface TKStorable {
    void save() throws Exception;
    void saveAs() throws Exception;
}

class TKDocument implements Appendable, Readable {
    // 문서 내에 문자열로 구성된 본분
    protected StringBuilder stringBuilder;

    public TKDocument() {
        this.stringBuilder = new StringBuilder();
    }

    @Override
    public Appendable append(char c) throws IOException {
        if (c != '\0') {
            this.stringBuilder.append(c);
        }

        return this;
    }

    @Override
    public Appendable append(CharSequence csq) throws IOException {
        return null;
    }

    @Override
    public Appendable append(CharSequence csq, int start, int end) throws IOException {
        return null;
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        return 0;
    }
}