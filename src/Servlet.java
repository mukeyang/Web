import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by CS on 2018/1/3.
 */
@WebServlet("/hello")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        response.getWriter().println("sadfafh3");
//        doRefer(response);
//        doGzip(response);
//        doType(response);
//        doFresh(response, "refresh", "1", "1234".getBytes());
        doDownload(response, "1.PNG", "content-disposition", "attachment;filename=yang.jpeg");
    }

    private void doDownload(HttpServletResponse response, String s, String s2, String s1) {
        try (InputStream inputStream = getServletContext().getResourceAsStream(s);) {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
//            response.setHeader("content-type","image/jpeg");
            response.setHeader(s2, s1);

            response.getOutputStream().write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doFresh(HttpServletResponse response, String refresh, String s1, byte[] bytes) throws IOException {
        response.setHeader(refresh, s1+";url='http://www.baidu.com'");
        response.getOutputStream().write(bytes);
    }

    private void doType(HttpServletResponse response) {
        try (InputStream inputStream = getServletContext().getResourceAsStream("/1.PNG");) {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            response.setHeader("content-type","image/jpeg");
            response.getOutputStream().write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doGzip(HttpServletResponse response) throws IOException {
        String name = "13141rjkwsjhrkfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffhsgggggggggggg";
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream stream = new GZIPOutputStream(byteArrayOutputStream);
        stream.write(name.getBytes());
        stream.close();
        System.out.println("压缩前" + name.getBytes().length);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        System.out.println("压缩后" + bytes.length);
        response.setHeader("Content-Encoding", "gzip");
        response.setHeader("Content-Length", bytes.length + "");
        response.getOutputStream().write(bytes);
//        response.setHeader("Content-Encoding", "gzip");
    }

    private void doRefer(HttpServletResponse response) {
        response.setStatus(302);
        response.setHeader("location", "http://www.baidu.com");
    }

}