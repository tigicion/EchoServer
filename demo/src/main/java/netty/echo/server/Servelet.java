package netty.echo.server;

import java.io.IOException;

public interface Servelet {
    public void service(Request req, Response resp) throws IOException;
}
