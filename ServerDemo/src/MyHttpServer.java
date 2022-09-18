import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyHttpServer {
    // port
    private int port = 8080;

    // the method to receive request
    public void receiving() {

        try {
            // create socket service
            ServerSocket serverSocket = new ServerSocket(port);
            // always accept request
            for (;;) {
                // get the request
                Socket socket = serverSocket.accept();
                // get the request's inputstream
                InputStream inputStream = socket.getInputStream();
                System.out.println(inputStream);
                // create request
                MyHttpRequest myHttpRequest = new MyHttpRequest(inputStream);
                // perse request
                myHttpRequest.parse();
                // create response
                OutputStream outputStream = socket.getOutputStream();
                MyHttpResponse myHttpResponse = new MyHttpResponse(outputStream);
                // response to the client
                myHttpResponse.sendRedirect(myHttpRequest.getUri());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
