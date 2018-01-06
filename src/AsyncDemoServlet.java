import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * Created by CS on 2018/1/4.
 */
@WebServlet(name = "AsyncDemoServlet", asyncSupported = true, urlPatterns = {"/asy"})
public class AsyncDemoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("enter" + LocalDateTime.now());
        AsyncContext context = request.startAsync();
        context.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent asyncEvent) throws IOException {

            }

            @Override
            public void onTimeout(AsyncEvent asyncEvent) throws IOException {

            }

            @Override
            public void onError(AsyncEvent asyncEvent) throws IOException {

            }

            @Override
            public void onStartAsync(AsyncEvent asyncEvent) throws IOException {

            }
        });
        class Compute implements Runnable {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    PrintWriter writer = context.getResponse().getWriter();
                    writer.println("over" + LocalDateTime.now());
                    context.complete();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        new Thread(new Compute()).start();
        out.println("finish" + LocalDateTime.now());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
