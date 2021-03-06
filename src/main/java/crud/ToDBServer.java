/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import java.io.IOException;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import java.lang.String;
import java.util.List;
/**
 *
 * @author Mike Chang
 */
public class ToDBServer {
    public static final  String END_POINT = "https://trello-clone-ppm.herokuapp.com";
  public static final OkHttpClient client = new OkHttpClient();
  public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

   public static String read(String url) {
    Request request = new Request.Builder()
            .url(END_POINT+url)
            .build();
    try {
      Response response = client.newCall(request).execute();
      return response.body().string();
    } catch(IOException e) {
      e.printStackTrace();
      return "";
    }
  }
public static String create(String url, Map<String, Object> list) {
    try {
      String listJsonString = new ObjectMapper().writeValueAsString(list);
      RequestBody body = RequestBody.create(listJsonString, JSON);
      Request request = new Request.Builder()
            .url(END_POINT + url)
            .post(body)
            .build();
      Response response = client.newCall(request).execute();
      return response.body().string();
    } catch(IOException e) {
      e.printStackTrace();
      return "";
    }
  }
public static void delete(String username,String url) {
    try {
      Request request = new Request.Builder()
      .url(END_POINT  + url + "/" + username)
      .delete()
      .build();
      client.newCall(request).execute();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static String update(String url, String username, Map<String, Object> list) {
    try {
      String listJsonStr = new ObjectMapper().writeValueAsString(list);
      RequestBody body = RequestBody.create(listJsonStr, JSON);
      Request request = new Request.Builder()
        .url(END_POINT + url + "/" + username)
        .put(body)
        .build();
      Response response = client.newCall(request).execute();
      return response.body().string();
    } catch (Exception e) {
      return "";
    }
  }
}
