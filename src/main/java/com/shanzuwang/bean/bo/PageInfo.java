package com.shanzuwang.bean.bo;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 分页返回数据信息
 *
 * @author lv
 * @since 2020/1/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class PageInfo<T> implements Serializable {

    private static final long serialVersionUID = 4632283139895831107L;
    @NonNull
    private long total;
    private List<T> list;
    @NonNull
    private long pageNo;
    @NonNull
    private long pageSize;
    @NonNull
    private long pages;


}
