package com.salon.auth;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：xieshaowei
 * @Package：com.salon.auth
 * @Project：salon
 * @name：AuthenticationController
 * @Date：2023/11/14 14:39
 */
@RestController
public class AuthenticationController {
    @GetMapping("/token")
    @ResponseBody
    public OAuth2AuthorizedClient token(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient oAuth2AuthorizedClient) {
        return oAuth2AuthorizedClient;
    }

}
