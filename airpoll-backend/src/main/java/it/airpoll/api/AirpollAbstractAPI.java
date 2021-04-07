package it.airpoll.api;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;

import com.google.common.collect.Multimap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Giacomo
 * 
 * Abstract class to call the OpenAQ API by callApi() method.
 * 
 */

@Slf4j
@Getter
@Setter
public abstract class AirpollAbstractAPI {
	
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	@Value("${airpoll.api.url}")
	private String url;
	
	@SneakyThrows
    protected Map<String, Object> callApi(Multimap<String,String> params, String url) {
		
		HttpUrl.Builder httpBuilder = HttpUrl.parse(this.url).newBuilder();
		
		httpBuilder.addPathSegments(url);
		
		if (params != null && !params.isEmpty()) {
			for(Map.Entry<String, String> param : params.entries()) {
				httpBuilder.addQueryParameter(param.getKey(), param.getValue());
			}
		}
		
		Request request = new Request.Builder()
			      .url(httpBuilder.build())
			      .addHeader("Content-Type", "application/json")
			      .build();
		
		OkHttpClient client = new OkHttpClient.Builder()
			        .connectTimeout(100, TimeUnit.SECONDS)
			        .readTimeout(300, TimeUnit.SECONDS)
			        .build();
		 
		Response response = client.newCall(request).execute();
		Map<String, Object> responseBody = gson.fromJson(response.body().charStream(), new TypeToken<Map<String, Object>>() {
	       }.getType());
		 
		log.trace("response body: " + responseBody.toString());
	    response.body().close();

	    return responseBody;
	}

}
