package com.niumo.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result <T>{
    private Integer code;
    private String msg;
    private T data;

    public static <E> Result<E> success(E data){
        return new Result<>(200,"success",data);
    }

    public static Result success(){
        return new Result(200,"SUCCESS","null");
    }

    public static Result error(String message){
        return new Result(400,message,"null");
    }

}
