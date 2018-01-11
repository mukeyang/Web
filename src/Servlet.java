import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.zip.GZIPOutputStream;

/**
 * Created by CS on 2018/1/3.
 */
@WebServlet(name = "Servlet", value = "/hello", asyncSupported = true, loadOnStartup = -1, initParams = {@WebInitParam(name = "username", value = "yang")})
public class Servlet extends HttpServlet {
//private ServletConfig config;
public static final int WIDTH=120;
    public static final int HEIGHT=35;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        response.getWriter().println("sadfafh3");
//        doRefer(response);
//        doGzip(response);
//        doType(response);
//        doFresh(response, "refresh", "1", "1234".getBytes());
//        doDownload(response, "1.PNG", "content-disposition", "attachment;filename=yang.jpeg");
//        test(request, response);
//        readInfo();
//        t1();
//        t2(request, response);
//        t3(response);
//        response.sendRedirect();
//        response.setCharacterEncoding("utf-8");
//        response.setHeader("content-type","text/html;charset=utf-8");
//        response.setContentType("text/html;charset=utf-8");
//        response.getWriter().println("中国");
//        String path = getServletContext().getRealPath("/download/1.PNG");
//        download(response);
//        checkcode(response);
//        ServletOutputStream stream = response.getOutputStream();
//        stream.write("1wddfff".getBytes());
//        PrintWriter writer = new PrintWriter(stream,true);
//        writer.println("gggggghsgdjdh");
//        writer.close();
//        System.out.println(request.getHeader("Accept-encoding"));
//        System.out.println(request.getParameter("name"));//1
        Map<String, String[]> map = request.getParameterMap();
        map.forEach((a,b)-> System.out.println(a+"   "+ Arrays.deepToString(b)));
//        request.getParameterValues("");
    }


    private void checkcode(HttpServletResponse response) throws IOException {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        setBackGround(graphics);
//        setBorder(graphics);
        drawRandomLine(graphics);
        drawRandomNum(graphics);
        response.setContentType("image/jpeg");
        response.setHeader("Cache-Control","max-age=0");
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

    private void drawRandomNum(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.setFont(new Font("宋体", Font.BOLD, 20));
        String base = "年底还好滴哦好好哈客户发卡机邓丽君你很嗲和肯德基和空间流口水的卡就能打开";
        int x=5;
        for (int i = 0; i < 4; i++) {
            int degree = new Random().nextInt()%30;
            String ch = base.charAt(new Random().nextInt(base.length())) + "";
            ((Graphics2D) graphics).rotate(degree*Math.PI/180,x,20);
            graphics.drawString(ch, x, 20);
            ((Graphics2D) graphics).rotate(-degree*Math.PI/180,x,20);

            x+=20;
        }
    }

    private void drawRandomLine(Graphics graphics) {
        graphics.setColor(Color.CYAN);
        for (int i = 0; i < 5; i++) {
            int x1 = new Random().nextInt(WIDTH);
            int y1 = new Random().nextInt(HEIGHT);
            int x2 = new Random().nextInt(WIDTH);
            int y2 = new Random().nextInt(HEIGHT);
            graphics.drawLine(x1,y1,x2,y2);
        }
    }

    private void setBorder(Graphics graphics) {
        graphics.setColor(Color.blue);
        graphics.fillRect(1,1,WIDTH-2,HEIGHT-2);
    }

    private void setBackGround(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect(0,0,WIDTH,HEIGHT);

    }

    private void download(HttpServletResponse response) throws IOException {
        String path = getServletContext().getRealPath("/download/图片.png");
        System.out.println(path);
        String name = path.substring(path.lastIndexOf("\\") + 1);
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(name,"utf-8"));
        response.getOutputStream().write(bytes);
    }

    private void t3(HttpServletResponse response) throws IOException {
        byte[] bytes = "中国".getBytes("UTF-8");
//        response.setHeader("content-type","text/html;charset=utf-8");
//        response.setCharacterEncoding("utf-8");

        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write("<meta http-equiv='content-type'content='text/html;charset=utf-8'>".getBytes("utf-8"));

        outputStream.write(bytes);
    }

    private void t2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request.getMethod());
        Enumeration<String> names = request.getHeaderNames();

        while (names.hasMoreElements()) {
            String s = names.nextElement();
            System.out.println(LocalDateTime.now() + s + "=" + request.getParameter(s));
//            System.out.println("element"+names.nextElement());
        }
        response.getWriter().println("hello");
        Enumeration<String> names1 = request.getParameterNames();
        while (names1.hasMoreElements()) {
            System.out.println(request.getParameter(names1.nextElement()));
        }
    }

    private void t1() throws IOException {
        System.out.println(getClass().getResource("info.properties"));
        System.out.println(getServletContext().getRealPath("/WEB-INF/classes/info.properties"));
        InputStream stream = getClass().getClassLoader().getResourceAsStream("info.properties");
        Properties properties = new Properties();
        properties.load(stream);
        System.out.println("classloader" + properties.getProperty("chen"));
        System.out.println(stream);
    }

    private void readInfo() throws IOException {
        InputStream inputStream = getServletContext().getResourceAsStream("/WEB-INF/classes/info.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        properties.list(System.out);
        System.out.println(System.getProperty("user.dir"));
    }

    private void test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(getServletConfig().getServletContext().getMajorVersion());
        System.out.println(getServletConfig().getServletContext().getResourcePaths("/"));
        System.out.println(getServletContext().getInitParameter("dat"));
        getServletContext().setAttribute("chen", "chen");
//        getServletContext()
//        getInit(response);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/2.jsp");
        dispatcher.forward(request, response);
    }

    private void getInit(HttpServletResponse response) throws IOException {

        System.out.println(this.getServletConfig());
        PrintWriter out = response.getWriter();
        String name = getServletConfig().getInitParameter("name");
        String names = getServletConfig().getInitParameter("username");
        out.println("name" + name);
        out.println("username" + names);
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
        response.setHeader(refresh, s1 + ";url='http://www.baidu.com'");
        response.getOutputStream().write(bytes);
    }

    private void doType(HttpServletResponse response) {
        try (InputStream inputStream = getServletContext().getResourceAsStream("/1.PNG");) {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            response.setHeader("content-type", "image/jpeg");
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
