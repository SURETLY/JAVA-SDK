package network;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class HttpInterceptor implements Interceptor {

    private Logger log = LoggerFactory.getLogger(HttpInterceptor.class);

    private final Session session;

    HttpInterceptor(Session session) {
        this.session = session;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        original = original.newBuilder()
                .addHeader("_auth", session.getVerify())
                .method(original.method(), original.body())
                .build();

        log.debug(original.url().toString());

        List<String> paths = original.url().pathSegments();
        Response response = chain.proceed(original);

        log.debug("responce: " + paths.get(paths.size() - 1) + " " + String.valueOf(response.code()));

        return response;
    }
}
