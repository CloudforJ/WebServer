import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

public class MyHttpResponse {
    private OutputStream outputStream;

    public MyHttpResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void sendRedirect(String uri) {
        // judge if the resource exists
        // if (uri )
        String fileName = System.getProperty("user.dir") + "/ServerDemo/WebContent" + uri;
        File file = new File(fileName);
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bytes = new byte[(int)file.length()];
                fileInputStream.read(bytes);
                String result = new String(bytes);
                System.out.println(result);
                String response = getResponseMessage("200", result);
                this.outputStream.write(response.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // return 404
            try {
                String error = getResponseMessage("404", "404 File Not Found");
                this.outputStream.write(error.getBytes());
            } catch(Exception e) {
                e.printStackTrace();
            }

        }
        // System.out.println(fileName);
    }

    public String getResponseMessage(String code, String message) {
        return "HTTP/1.1 " + code + "\r\n"
                + "Content-type: text/html\r\n"
                + "Content-Length: " + message.length()
                + "\r\n"
                + "\r\n"
                + message;
    }
}
