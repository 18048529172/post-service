package com.micro.api.post.utils.response;

import com.google.common.base.Preconditions;
import com.micro.api.post.exceptions.AbsException;
import com.micro.api.post.exceptions.ExceptionCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ResponseTemplate {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseTemplate.class);

    public Response doResponse(ResponseHandler handler){
        Response response = new Response();
        Meta meta = new Meta();
        long startTime = System.currentTimeMillis();
        try{
            Body body = handler.execute();
            meta.setCode("200");
            meta.setMessage("success");
            response.setBody(body);
        }catch (Exception e){
            LOGGER.error("请求处理异常：",e);
            if( e instanceof AbsException ){
                AbsException absException = (AbsException) e;
                meta.setMessage(absException.getMessage());
                meta.setCode(absException.getExceptionCodes().getCode());
            }else{
                meta.setMessage("请求失败，请联系管理员");
                meta.setCode(ExceptionCodes.ERROR.getCode());
            }
        }finally {
            long endTime = System.currentTimeMillis();
            meta.setRequestTime(endTime - startTime);
            response.setMeta(meta);
        }
        return response;
    }

    public interface ResponseHandler{
        Body execute() throws Exception;
    }

}
