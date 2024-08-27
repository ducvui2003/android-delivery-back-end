package com.spring.productservice.config.interceptor;

import feign.FeignException;
import feign.InvocationContext;
import feign.Response;
import feign.ResponseInterceptor;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;

//@Component
public class FeignResponseInterceptor implements Decoder {
    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        return null;
    }
}
