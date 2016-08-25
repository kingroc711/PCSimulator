import com.google.gson.Gson;
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
    private String mIP = null;

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

                if(url.equals("/getallport")) {
                    responseContent = analysisDataGetAllPort();
                }else if(url.equals("/get_port")){
                    responseContent = analysisDataGetPort(sb.toString());
                }else if(url.equals("/get_module")){
                    responseContent = analysisDataGetModule(sb.toString());
                }else if(url.equals("/set_port")){
                    responseContent = analysisDataSetPort(sb.toString());
                }else if(url.equals("/set_module")){
                    responseContent = analysisDataSetModule(sb.toString());
                }else  {
                    responseContent = "Do not have this API.";
                }

                httpExchange.sendResponseHeaders(200, responseContent.length());
                OutputStream out = httpExchange.getResponseBody();
                out.write(responseContent.getBytes());
                out.flush();
                out.close();
                insert(responseContent, mTextArea);
            }else{
                String responseContent = "Please use get !";
                System.out.println("url " + httpExchange.getRequestURI().toString());
                System.out.println("not POST " + httpExchange.getRequestMethod());
                System.out.println("remoteAddr : " + httpExchange.getRemoteAddress().getAddress().getHostAddress());
                httpExchange.sendResponseHeaders(404, responseContent.length());
                OutputStream out = httpExchange.getResponseBody();
                out.write(responseContent.getBytes());
                out.flush();
                out.close();
            }

        }

        private String analysisDataGetAllPort(){

            return SimulateData.getAll();
        }

        private String analysisDataGetPort(String s){
            Gson gson = new Gson();
            PortDeviceInfo p = gson.fromJson(s, PortDeviceInfo.class);
            return SimulateData.get(p.getSerialName(), p.getPort());
        }

        private String analysisDataGetModule(String s){
            Gson gson = new Gson();
            ModuleDeviceInfo m = gson.fromJson(s, ModuleDeviceInfo.class);
            return SimulateData.get(m.getSerialName());
        }

        private String analysisDataSetPort(String s){
            Gson gson = new Gson();
            PortDeviceInfo p = gson.fromJson(s, PortDeviceInfo.class);
            return SimulateData.set(p);
        }

        private String analysisDataSetModule(String s){
            Gson gson = new Gson();
            ModuleDeviceInfo m = gson.fromJson(s, ModuleDeviceInfo.class);
            return SimulateData.set(m);
        }
    }
}
