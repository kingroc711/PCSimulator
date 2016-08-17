import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtils {
	public static void main(String[] args) {
	}
	
	public static String sendPost(String url, String data) {
		System.out.println("url : " + url + ", data : " + data);
		try {
			URL realUrl = new URL(url);// 通过传入的url创建URL类，记得一定是http开头的。
			HttpURLConnection httpConn = (HttpURLConnection) realUrl.openConnection();
			httpConn.setDoOutput(true);// 需要输入，输入就是我们要上传服务器的数据。
			httpConn.setDoInput(true);// 需要输出，输出的就是我们上传数据给服务器后服务器会给我们返回什么。
			httpConn.setUseCaches(false); // 不允许缓存
			httpConn.setRequestMethod("POST");// 微妙之处就在这里，写啥就是啥。

			/* 下面这几句就是设置Header的地方， */
			httpConn.setRequestProperty("accept", "*/*");
			httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			httpConn.setRequestProperty("Charset", "UTF-8");

			/* 获得输出流，这个流就是输出给服务器的 */
			DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());
			dos.writeBytes(data);// 填写输出的数据。
			dos.flush();
			dos.close();

			/* 这里等待服务器的回复信息，resultCode就是200、 302、 404、 500啥的 */
			int resultCode = httpConn.getResponseCode();
			System.out.println("resultCode : " + resultCode);
			/* 看这里只有在HTTP_OK的时候才读取数据。因为这个才是一个真确的服务器返回 */
			if (HttpURLConnection.HTTP_OK == resultCode) {
				StringBuffer sb = new StringBuffer();
				String readLine = new String();
				BufferedReader responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
				while ((readLine = responseReader.readLine()) != null) {
					sb.append(readLine).append("\n");
				}
				responseReader.close();
				System.out.println(sb.toString());
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
