import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class ReadJson {

	public static void main(String[] args) throws InterruptedException, IOException {
		var uri="https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22";
        HttpRequest request=HttpRequest.newBuilder()
        		.uri(URI.create(uri))
        		.method("GET", HttpRequest.BodyPublishers.noBody())
        		.build();
        
        var client=HttpClient.newBuilder().build();
        HttpResponse<String> response=client.send(request,HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
        System.out.println(responseBody);
        
        JSONObject object=new JSONObject(responseBody);
        JSONArray listArray = object.getJSONArray("list");

        for (int i = 0; i < listArray.length(); i++) {
            JSONObject jsonObject = listArray.getJSONObject(i);
            double temp = jsonObject.getJSONObject("main").getDouble("temp");
            System.out.println("Temperature at index " + i + ": " + temp);
        }
   
	}

}
