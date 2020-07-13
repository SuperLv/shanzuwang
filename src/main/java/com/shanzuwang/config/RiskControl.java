package com.shanzuwang.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Hw
 * 20/06/13 15:27
 */
@Data
public class RiskControl implements Serializable {

    private  String  user_name;

    private  String  sign;


    private String api_name;


    private String  query_reason;

    private String params;

    @Data
    public static class  Params implements Serializable
    {
          @JsonProperty(value = "id_no")
          private  String idNo;

          private  String name;
    }
}
