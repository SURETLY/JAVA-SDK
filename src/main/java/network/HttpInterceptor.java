package network;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class HttpInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        original = original.newBuilder()
                .addHeader("_auth", SessionManager.getVerify())
                .method(original.method(), original.body())
                .build();

        Build.log(HttpInterceptor.class.getSimpleName(), original.url().toString());

        List<String> paths = original.url().pathSegments();
        Response response = chain.proceed(original);

        Build.log(HttpInterceptor.class.getSimpleName(), "responce: " + paths.get(paths.size() - 1) + " " + String.valueOf(response.code()));

        return response;
    }
}
