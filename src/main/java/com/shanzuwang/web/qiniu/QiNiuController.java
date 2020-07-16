package com.shanzuwang.web.qiniu;


import com.qiniu.util.Auth;
import com.shanzuwang.bean.res.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LiWeijie
 * 20/04/01 16:48
 */
@Api(tags = "图片上传")
@Slf4j
@RestController
@RequestMapping("/api")
public class QiNiuController {

    //七牛图片访问域名
    public static String domain="http://cdn.img.shanzuwang.com";
    //七牛图片url后缀
    public static String suffix="?imageView2/1/w/200/h/200/q/100|imageslim";

    @ApiOperation("获取七牛token")
    @GetMapping("/get_qiniu_token")
    public ApiResult getQiNiuToken(String key){
        String ACCESS_KEY="LpTol8R5JC1dchYbe1jswbO7fZNNKp1eveDDNSDg";
        String SECRET_KEY="N_Pc_giS1YW6CsIBbobDEzJ_ziIgpu2Duxr5RYMR";
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String upToken = auth.uploadToken("cdnimgshan");
        return ApiResult.success(upToken);
    }
}
