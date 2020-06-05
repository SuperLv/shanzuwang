package com.shanzuwang.bean.req.bill.esignature.component;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Hw
 * 20/06/05 10:39
 */
@Data
public class SignSite implements Serializable {
    private  String keyword;

    private  String fileId;

    private  List<PositionList> positionList;

    @Data
    public static class  PositionList implements Serializable
    {
        //页码
        private Integer pageIndex;

        private List<CoordinateList> coordinateList;
    }

    @Data
    public static class CoordinateList implements Serializable
    {
        private  Float posx;

        private  Float posy;
    }

}
