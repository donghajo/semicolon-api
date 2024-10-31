package com.semicolonapi.framework.oauth.services;

import com.semicolonapi.framework.oauth.model.KakaoDto;
import com.semicolonapi.framework.oauth.model.KakaoProperty;
import com.semicolonapi.server.user.domains.Role;
import com.semicolonapi.server.user.models.UserDto;
import com.semicolonapi.server.user.services.UserService;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {
    private final KakaoProperty kakaoProperty;
    private final UserService userService;
    @Value("${jwt.refresh.header}")
    private String REFRESH_HEADER;
    @Value("${jwt.access.header}")
    private String ACCESS_HEADER;

    public String login(HttpServletResponse response, String code) {
        KakaoDto.Token tokenDto = WebClient.create(kakaoProperty.getTokenUrl()).post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/oauth/token")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", kakaoProperty.getClientId())
                        .queryParam("code", code)
                        .build(true))
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .bodyToMono(KakaoDto.Token.class)
                .block();
        KakaoDto.UserInfo userInfo = getUserInfo(tokenDto.getAccessToken());

        UserDto.Domain user = UserDto.Domain.builder()
                .userId(userInfo.getId().toString())
                .email(userInfo.getKakaoAccount().getEmail())
                .phone(userInfo.getKakaoAccount().getPhoneNumber())
                .name(userInfo.getKakaoAccount().getName())
                .nickname(userInfo.getProperties().get("nickname"))
                .profile(userInfo.getProperties().get("profile_image"))
                .birthday(userInfo.getKakaoAccount().getBirthDay())
                .type("KAKAO")
                .role(Role.ROLE_USER).build();

        // 기존 회원 여부 확인
        UserDto.Info exist = userService.findByUserId(user.getUserId().toString());
        if(exist == null) {
            userService.create(user);
        }

        setRefreshToken(response, tokenDto);

        return tokenDto.getAccessToken();
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        String accessToken = request.getHeader("Authorization");

        WebClient webClient = WebClient.builder()
                .baseUrl(kakaoProperty.getUserUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        KakaoDto.UserInfo obj =  webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v1/user/logout")
                        .build(true))
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .header(ACCESS_HEADER, "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(KakaoDto.UserInfo.class)
                .block();
    }

    /**
     * RefreshToken 쿠키 설정
     */
    public void setRefreshToken(HttpServletResponse response, KakaoDto.Token tokenDto) {
        // 쿠키
        ResponseCookie cookie = ResponseCookie.from(REFRESH_HEADER, tokenDto.getRefreshToken())
                                             .maxAge(tokenDto.getRefreshTokenExpiresIn())
                                            .path("/")
                                            .secure(false)
                                            .httpOnly(true)
                                            .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }

    public KakaoDto.UserInfo getUserInfo(String accessToken) {

        KakaoDto.UserInfo userInfo = WebClient.create(kakaoProperty.getUserUrl())
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v2/user/me")
                        .build(true))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken) // access token 인가
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                //TODO : Custom Exception
                .bodyToMono(KakaoDto.UserInfo.class)
                .block();

        return userInfo;
    }

}
