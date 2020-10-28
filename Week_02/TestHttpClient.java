import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class TestHttpClient {
    public void get(String url) throws Exception {
        // 创建HttpClient实例
        HttpClient client =  HttpClientBuilder.create().build();
        // 根据URL创建HttpGet实例
        HttpGet get = new HttpGet(url);
        // 执行get请求，得到返回体
        HttpResponse response = client.execute(get);
        // 判断是否正常返回
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            // 解析数据
            String data = EntityUtils.toString(response.getEntity());
            System.out.println(data);
        }
    }


    public static void main(String[] args) throws Exception {
        TestHttpClient cl = new TestHttpClient();
        String url = "http://localhost:8088/test";
        cl.get(url);
    }
}
