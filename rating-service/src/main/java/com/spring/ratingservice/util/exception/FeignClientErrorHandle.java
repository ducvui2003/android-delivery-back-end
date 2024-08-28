package com.spring.ratingservice.util.exception;

import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class FeignClientErrorHandle implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        String body = null;
        try {
            body = Util.toString(response.body().asReader(Util.UTF_8));
        } catch (IOException e) {
            log.error("Body empty", e);
        }

        // Create a custom exception with the response status and body
        return new FeignClientError(response.status(), body);

    }
}
