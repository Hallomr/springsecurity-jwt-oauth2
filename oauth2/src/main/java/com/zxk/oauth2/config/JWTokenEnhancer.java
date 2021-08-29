package com.zxk.oauth2.config;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class JWTokenEnhancer implements TokenEnhancer {
    /**
     * 将附加信息加入oAuth2AccessToken中
     *
     * @param oAuth2AccessToken
     * @param oAuth2Authentication
     * @return
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> map = new HashMap<>();
        map.put("jwt-ext", "JWT 扩展信息");
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(map);
        return oAuth2AccessToken;
    }
}
