import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;

/**
 * Created by kingroc on 16-8-22.
 */
public class MyHTTPServer {
        private JTextArea mTextArea;

    public MyHTTPServer(int port, JTextArea textArea) {
        mTextArea = textArea;

        HttpServer hs;
        try {
            hs = HttpServer.create(new InetSocketAddress(port), 0);
            hs.createContext("/", new MyHandler());// 用MyHandler类内处理到/的请求
            hs.setExecutor(null); // creates a default executor
            hs.start();
            System.out.println("my Server start !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class MyHandler implements HttpHandler {
        private void insert(String string, JTextArea textArea){
            Document doc = textArea.getDocument();
            SimpleAttributeSet attrSet = new SimpleAttributeSet();
            StyleConstants.setForeground(attrSet, Color.blue);

            try {
                doc.insertString(doc.getLength(), string + "\r\n", attrSet);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

            if(httpExchange.getRequestMethod().equalsIgnoreCase("POST")){
                InetAddress remoteAddr = httpExchange.getRemoteAddress().getAddress();
                System.out.println("remoteAddr : " + remoteAddr.toString());
                URI url = httpExchange.getRequestURI();
                System.out.println("url : " + url);

                BufferedReader requestReader = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody(), "UTF-8"));
                StringBuffer sb = new StringBuffer();
                String readLine;
                while ((readLine = requestReader.readLine()) != null) {
                    sb.append(readLine).append("\n");
                }
                requestReader.close();
                System.out.println(sb.toString());
                insert(sb.toString(), mTextArea);

                String responseContent = "";

                if(url.equals("getallport")){
                    responseContent = analysisDataGetAllPort();
                }else {
                    responseContent = analysisData(sb.toString());
                }

                httpExchange.sendResponseHeaders(200, responseContent.length());
                OutputStream out = httpExchange.getResponseBody();
                out.write(responseContent.getBytes());
                out.flush();
                out.close();
                insert(responseContent, mTextArea);
            }else{
                System.out.println("not POST " + httpExchange.getRequestMethod());
            }

        }

        private String analysisDataGetAllPort(){

            return SimulateData.getAll();
        }

        private String analysisData(String s) {
            String response = "";
            String method = null;
            String message = null;

            if(method.equalsIgnoreCase("GET")){
                if(message.equalsIgnoreCase("ALL")){

                }else if(message.equalsIgnoreCase("PORT")){

                }else{
                    response = "message illegittimate.";
                }
            }else if (method.equalsIgnoreCase("SET")){

            }else{
                response = "method illegitimate.";
            }

            return response;
        }
    }
}
