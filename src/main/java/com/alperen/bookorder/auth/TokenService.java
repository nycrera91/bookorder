package com.alperen.bookorder.auth;

import com.alperen.bookorder.exception.AuthFailedException;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class TokenService {

    private JwtParserService jwtParserService;

    public TokenService(JwtParserService jwtParserService) {
        this.jwtParserService = jwtParserService;
    }

    public Claims getTokenClaims(HttpServletRequest httpServletRequest) {
        Claims claims = jwtParserService.decodeJWT(getTokenFromHeader(httpServletRequest));
        validateToken(claims);
        return claims;
    }

    private String getTokenFromHeader(HttpServletRequest httpServletRequest) {
        String authenticationHeader = httpServletRequest.getHeader("Authorization");
        boolean startWithBearer = StringUtils.startsWith(authenticationHeader, "Bearer");
        String[] headerParams = StringUtils.split(authenticationHeader, StringUtils.SPACE);
        boolean headerParamSizeIsTwo = ArrayUtils.getLength(headerParams) == 2;
        boolean isAuthenticationHeaderValid = authenticationHeader != null && startWithBearer && headerParamSizeIsTwo;
        if (!isAuthenticationHeaderValid) {
            throw new AuthFailedException("Authentication header not valid");
        }
        return authenticationHeader.split(StringUtils.SPACE)[1];
    }

    private void validateToken(Claims tokenData) {
        if (DateTime.now().isAfter(tokenData.getExpiration().getTime())) {
            throw new AuthFailedException("Token expired");
        }
    }
}
