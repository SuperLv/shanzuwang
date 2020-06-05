package com.shanzuwang.bean.req.bill.esignature;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Hw
 * 20/06/02 14:59
 */
@Data
public class SignflowsDocuments implements Serializable {
    private String flowId;

    private List<Docs> docs;


    @Data
    public static class Docs implements Serializable
    {
       private String  fileId;

       private  Integer encryption;

       private  String fileName;

       private  String filePassword;
    }
}
