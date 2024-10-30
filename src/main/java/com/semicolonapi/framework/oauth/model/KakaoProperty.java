package com.semicolonapi.framework.oauth.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "kakao")
public class KakaoProperty {
    private String clientId;
    private String redirectUrl;
    private String tokenUrl;
    private String userUrl;

}
