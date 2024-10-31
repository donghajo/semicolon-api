package com.semicolonapi.framework.oauth.controllers;

import com.semicolonapi.framework.models.ResponseDto;
import com.semicolonapi.framework.oauth.model.KakaoProperty;
import com.semicolonapi.framework.oauth.services.GoogleService;
import com.semicolonapi.framework.oauth.services.KakaoService;
import com.semicolonapi.framework.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final KakaoService kakaoService;
    private final GoogleService googleService;
    private final KakaoProperty kakaoProperty;

    @GetMapping("/kakao/autorize")
    public ResponseDto.Data<?> kakaoAuthority(){
        String uri = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="+kakaoProperty.getClientId()+"&redirect_uri="+kakaoProperty.getRedirectUrl()+"&prompt=login";
        return ResponseUtil.returnSingle(uri);
    }

    @GetMapping("/kakao/login")
    public ResponseDto.Data<?> kakaoLogin(HttpServletResponse response, @RequestParam("code") String code) {
        String accessToken = kakaoService.login(response, code);
        return ResponseUtil.returnSingle(accessToken);
    }

    @GetMapping("/kakao/logout")
    public ResponseDto.Signal kakaoLogout(HttpServletRequest request, HttpServletResponse response) {
        kakaoService.logout(request, response);
        return ResponseUtil.returnSignal(200, "complete logout");
    }


    @GetMapping("/google/login")
    public ResponseDto.Data<?> googleLogin(@RequestParam("code") String code) {
        return ResponseUtil.returnSingle(null);
    }

}
