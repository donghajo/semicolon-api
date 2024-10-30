package com.semicolonapi.framework.config.exception;

import com.semicolonapi.framework.models.ResponseDto;
import com.semicolonapi.framework.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {
    private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

    @ExceptionHandler(CAccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseDto.Signal accessDeniedException(HttpServletRequest request, CAccessDeniedException e) {
        return ResponseUtil.returnSignal(HttpStatus.FORBIDDEN.value(), "접근 권한이 없습니다.");
    }

    @ExceptionHandler(CEntryPointException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseDto.Signal entryPointException(HttpServletRequest request, CEntryPointException e) {
        return ResponseUtil.returnSignal(HttpStatus.FORBIDDEN.value(), "잘못된 접근입니다.");
    }

    @ExceptionHandler(CUnAuthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseDto.Signal unAuthorizedException(HttpServletRequest request, CUnAuthorizedException e) {
        return ResponseUtil.returnSignal(HttpStatus.UNAUTHORIZED.value(), "엑세스 토큰이 만료되었습니다.");
    }

}
