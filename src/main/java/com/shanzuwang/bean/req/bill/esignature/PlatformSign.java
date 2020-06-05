package com.shanzuwang.bean.req.bill.esignature;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Hw
 * 20/06/02 16:22
 */
@Data
public class PlatformSign implements Serializable {
    private String flowId;

    private List<Signfields> signfields;


    @Data
    public static class  SignDateBean
    {
        private Integer fontSize;

        private  String format;

        private  Integer posPage;

        private Float posX;

        private Float posY;


    }

    @Data
    public static class Signfields
    {
        private String fileId;

        private Integer order;

        private PosBean posBean;

        private Integer signDateBeanType;

        private SignDateBean signDateBean;

        /**印章id*/
        private String sealId;

        private Integer signType;

        private String sealType;

        private String thirdOrderNo;

        private String signerAccountId;

        private String authorizedAccountId;

        private Integer actorIndentityType;

        private boolean assignedPosbean;
    }

    @Data
    public static  class PosBean
    {
        private String posPage;

        private Float posX;

        private Float posY;

        //签署区宽，默认印章宽度
        private Float width;
    }

}
