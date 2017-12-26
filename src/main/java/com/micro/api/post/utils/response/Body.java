package com.micro.api.post.utils.response;

import java.io.Serializable;
import java.util.HashMap;

public class Body extends HashMap<String,Object> implements Serializable {

    private Body(){}

     public static Body create(String name,Object value){
         Body body = new Body();
         body.put(name,value);
         return body;
     }

     public Body append(String name,Object value){
         this.put(name,value);
         return this;
     }


}
