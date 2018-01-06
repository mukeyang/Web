import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by CS on 2018/1/4.
 */
@WebServlet(name = "FileUpload",urlPatterns = {"/submit"})
@MultipartConfig
public class FileUpload extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("123r");
        response.setContentType("text/html,charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        Part file = request.getPart("mFile");
        request.getParts().forEach(a-> System.out.println(a.getName()));
        System.out.println("size="+request.getParts().size());
        printWriter.println("type" + file.getContentType());
        printWriter.println("name" + file.getName());
        printWriter.println("name" + file.getSize());
        file.write("D:\\1.pdf");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
