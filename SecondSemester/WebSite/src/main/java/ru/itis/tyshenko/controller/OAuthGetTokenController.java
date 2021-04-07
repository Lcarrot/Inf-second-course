package ru.itis.tyshenko.controller;

import com.squareup.okhttp.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class OAuthGetTokenController {

    @Value("github.client.id")
    private String clientId;

    @Value("github.secret")
    private String clientSecret;

    @GetMapping("/github")
    public String redirectToGithub() {
        return "https://github.com/login/oauth/authorize?client_id=" + clientId
                + "&redirect_uri="; // добавить свой адрес
    }
    @GetMapping("getUserToken")
    public String getToken(@RequestParam String code) throws IOException {
        OkHttpClient httpClient = new OkHttpClient();

        RequestBody requestBody = new FormEncodingBuilder()
                .add("clientId", clientId)
                .add("client_secret", clientSecret)
                .add("code", code).build();
        Request accessToken = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(requestBody).build();
        Response response = httpClient.newCall(accessToken).execute();
        String accessData = response.body().string();
        return null;
    }
}
