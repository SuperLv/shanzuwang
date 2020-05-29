package com.shanzuwang.web.bill;

import com.shanzuwang.bean.req.bill.esignature.Organize;
import com.shanzuwang.bean.req.bill.esignature.TemplateReq;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.config.pay.SignatureReq;
import com.shanzuwang.service.ICartService;
import com.shanzuwang.service.SignatureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Hw
 * 20/05/28 17:39
 */
@Api(tags = "合同管理")
@Slf4j
@RestController
@RequestMapping("/api")
public class ContractController {
    @Autowired
    SignatureService signatureService;
    @Autowired
    ICartService iCartService;

    @ApiOperation("签署合同")
    @PostMapping("/packages/{id}/contract")
    public ApiResult<Object> Contract()
    {

        return ApiResult.success(null);
    }

    @ApiOperation("注册公司")
    @PostMapping("/tesxwst")
    public ApiResult<String>  AddOrganize()
    {
        Organize organize=new Organize();
        organize.setThirdPartyUserId("2355");
        organize.setCreator("203a7ec141ec42e39eb636c3a702be8b");
        organize.setName("美邦电脑租赁（深圳）有限公司");
        organize.setIdNumber("91440300359291078L");
        organize.setOrgLegalIdNumber("310230197611217211");
        organize.setOrgLegalName("陈东升");
        String result = signatureService.AddOrganize(organize);
        return ApiResult.success(result);
    }

    @ApiOperation("测试合同文件上传")
    @PostMapping("/textTemplate")
    public  ApiResult<String> AddTemplate(){
        TemplateReq templateReq=new TemplateReq();
        templateReq.setContentMd5(SignatureReq.getStringContentMD5("C:\\Users\\Angell\\Downloads\\闪租合同-package-20200527-33452764.pdf"));
        templateReq.setContentType("application/pdf");
        templateReq.setConvert2Pdf(false);
        templateReq.setFileName("闪租合同-package-20200527-33452764.pdf");
        templateReq.setAccountId("203a7ec141ec42e39eb636c3a702be8b");
        return ApiResult.success(signatureService.AddTemplate(templateReq));
    }
}
