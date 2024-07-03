import com.timothy.bongsamoa.*;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URL;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TKTest {
    @Test
    public void testProxy() throws Exception {
        TKSubject subject = new TKSubjectProxy();
        subject.action();
    }

    @Test
    public void testDownloadImage() throws Exception {
        TKStreamConnector streamConnector = new TKStreamConnector();
        URL sourceURL = new URL("https://blog.kakaocdn.net/dn/MThfh/btrRtcbb2Xl/zR5vUkvvJNLOPo7kXhkQHK/img.png");
        InputStream inputStream = sourceURL.openStream();
        File destinationFile = new File("C:\\Users\\timothy\\IdeaProjects\\demo\\temp", "test1.png");
        FileOutputStream outputStream = new FileOutputStream(destinationFile);

        if (streamConnector.pipe(inputStream, outputStream)) {
            System.out.println("다운로드 성공, 총 다운로드 크기: " + streamConnector.getTotalDataSize() + "bytes");
        }

        inputStream.close();
        outputStream.close();
    }

    @Test
    public void testDownloadWebPage() throws Exception {
        TKStreamConnector streamConnector = new TKStreamConnector();
        URL sourceURL = new URL("https://inpa.tistory.com/entry/JAVA-%E2%98%95-%EC%97%90%EB%9F%ACError-%EC%99%80-%EC%98%88%EC%99%B8-%ED%81%B4%EB%9E%98%EC%8A%A4Exception-%F0%9F%92%AF-%EC%B4%9D%EC%A0%95%EB%A6%AC#checked_exception_/_unchecked_exception");
        InputStream inputStream = sourceURL.openStream();
        File destinationFile = new File("C:\\Users\\timothy\\IdeaProjects\\demo\\temp", "test2.html");
        FileOutputStream outputStream = new FileOutputStream(destinationFile);

        if (streamConnector.pipe(inputStream, outputStream)) {
            System.out.println("다운로드 성공, 총 다운로드 크기: " + streamConnector.getTotalDataSize() + "bytes");
        }

        inputStream.close();
        outputStream.close();
    }

    @Test
    public void testA() throws Exception {
//        String path1 = "C:\\Users\\timothy\\IdeaProjects\\a\\b\\test.txt";
//        String path2 = "C:\\Users\\timothy\\IdeaProjects\\demo\\temp\\zzz.txt";
//        String path3 = "C:\\Users\\timothy\\IdeaProjects\\a\\b";
        String path = "C:\\Users\\timothy\\IdeaProjects\\bongsamoa\\temp\\new\\a\\b\\c\\test.html"; // nope.txt
        File destinationFile = new File(path);

        TKMakeshift makeshift = new TKMakeshift(path);
        makeshift.prepareFile(destinationFile);
    }

    @Test
    public void testCollaborateWithBufferInputStream() throws Exception {
        File destinationFile = new File("C:\\Users\\timothy\\IdeaProjects\\bongsamoa\\temp", "test1.png");

        if (!destinationFile.exists()) {
            if (!destinationFile.createNewFile()) {
                throw new RuntimeException("이미지 파일 생성에 실패했습니다.");
            }
        }

        TKFileLoader fileLoader = new TKFileLoader(destinationFile);
        URL sourceURL = new URL("https://blog.kakaocdn.net/dn/MThfh/btrRtcbb2Xl/zR5vUkvvJNLOPo7kXhkQHK/img.png");
        BufferedInputStream urlStream = new BufferedInputStream(sourceURL.openStream());
        byte[] bytes = urlStream.readAllBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length + 1);
        byteBuffer.put(bytes);
        byteBuffer.flip();

        int length = fileLoader.getFileChannel().write(byteBuffer);
        System.out.println("Write bytes length: " + length);

        urlStream.close();
        fileLoader.close();
    }

    @Test
    public void testCollaborateWithFileInputStream() throws Exception {
        File destinationFile = new File("C:\\Users\\timothy\\IdeaProjects\\bongsamoa\\temp", "test2.png");

        if (!destinationFile.exists()) {
            if (!destinationFile.createNewFile()) {
                throw new RuntimeException("이미지 파일 생성에 실패했습니다.");
            }
        }

        TKFileLoader fileLoader = new TKFileLoader(destinationFile);
        URL sourceURL = new URL("https://blog.kakaocdn.net/dn/MThfh/btrRtcbb2Xl/zR5vUkvvJNLOPo7kXhkQHK/img.png");
        ReadableByteChannel readableByteChannel = Channels.newChannel(sourceURL.openStream());
        ByteBuffer buffer = ByteBuffer.allocate(1024); // 버퍼의 크기를 예상하기가 어려움

        readableByteChannel.read(buffer);

        int length = fileLoader.getFileChannel().write(buffer);
        System.out.println("Write bytes length: " + length);

        readableByteChannel.close();
        fileLoader.close();
    }

    @Test
    public void test() throws Exception {

    }

    @Test
    public void testMarkAndReset() throws Exception {
        File destinationFile = new File("C:\\Users\\timothy\\IdeaProjects\\bongsamoa\\temp", "untitled0.txt");
        FileInputStream fileInputStream = new FileInputStream(destinationFile);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

        if (bufferedInputStream.markSupported()) {
            System.out.println((char)bufferedInputStream.read());

            bufferedInputStream.mark(7);

            for (int i = 0; i < 11; i++) {
                System.out.print((char)bufferedInputStream.read());
            }

            System.out.println();
            bufferedInputStream.reset();

            for (int i = 0; i < 22; i++) {
                System.out.print((char)bufferedInputStream.read());
            }

            System.out.println();
            bufferedInputStream.reset();

            for (int i = 0; i < 5; i++) {
                System.out.print((char)bufferedInputStream.read());
            }

            System.out.println();
        }

        bufferedInputStream.close();
        fileInputStream.close();
    }

    @Test
    public void testFileTempLocation() throws Exception {
        File destinationFile = new File("C:\\Users\\timothy\\IdeaProjects\\bongsamoa\\temp\\test.html");
        File tempDirectory = new File("C:\\Users\\timothy\\IdeaProjects\\bongsamoa\\temp\\etc"); // aaaaa.txt
        TKFileLoader fileLoader = new TKFileLoader(destinationFile, tempDirectory);
        ByteBuffer buffer = ByteBuffer.allocate(128);
        buffer.put("ABCDE".getBytes());
        buffer.flip();

        int length = fileLoader.getFileChannel().write(buffer);
        System.out.println("Write bytes length: " + length);

        fileLoader.close();
    }

    @Test
    public void testFileSave() throws Exception {
        File destinationFile = new File("C:\\Users\\timothy\\IdeaProjects\\bongsamoa\\temp\\test.html");

        try (TKFileLoader fileLoader = new TKFileLoader(destinationFile)) {
            FileChannel fileChannel = fileLoader.getFileChannel();

            ByteBuffer buffer = ByteBuffer.allocate(16);
            buffer.put("Hello, World!".getBytes());
            buffer.flip();

            int length = fileChannel.write(buffer);
            System.out.println("Write bytes length: " + length);

            fileLoader.save();
        }
    }

    @Test void testFileSaveAs() throws Exception {
        File destinationFile = new File("C:\\Users\\timothy\\IdeaProjects\\bongsamoa\\temp\\test.html");
        TKFileLoader fileLoader = new TKFileLoader(destinationFile);
        ByteBuffer buffer = ByteBuffer.allocate(128);
        buffer.put("Test save as feature.".getBytes());
        buffer.flip();

        int length = fileLoader.getFileChannel().write(buffer);
        System.out.println("Write bytes length: " + length);

        File newFile = new File("C:\\Users\\timothy\\IdeaProjects\\bongsamoa\\temp\\a\\test.html");

        fileLoader.saveAs(newFile);
        fileLoader.close();
    }
}
