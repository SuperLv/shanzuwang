package com.shanzuwang.web.bill;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.shanzuwang.bean.req.bill.esignature.CallbackSignflowsReq;
import com.shanzuwang.bean.req.bill.esignature.Organize;
import com.shanzuwang.bean.req.bill.esignature.TemplateReq;
import com.shanzuwang.bean.req.bill.esignature.component.*;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.config.pay.SignatureReq;
import com.shanzuwang.service.ICartService;
import com.shanzuwang.service.SignatureService;
import com.shanzuwang.service.impl.SignatureServiceImpl;
import com.shanzuwang.util.http.HttpClient;
import com.shanzuwang.util.http.esignature.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.text.MessageFormat;
import java.util.List;

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
        organize.setCreator("17ea5be88e6d42b083302f19195a6d34");
        organize.setName("美邦电脑租赁（深圳）有限公司");
        organize.setIdNumber("91440300359291078L");
        organize.setOrgLegalIdNumber("310230197611217211");
        organize.setOrgLegalName("陈东升");
        String result = SignUtils.AddOrganize(organize);
        return ApiResult.success(result);
    }

    @ApiOperation("合同文件上传")
    @PostMapping("/textTemplate")
    public  ApiResult<String> AddTemplate() throws DefineException {
        String filePath="C:\\Users\\Angell\\Downloads\\闪租合同-package-20200527-33452764.pdf";
        TemplateReq templateReq=new TemplateReq();
        String contentMd5=FileHelper.getContentMD5(filePath);
        String contentType=FileHelper.getContentType(filePath);
        templateReq.setContentMd5(contentMd5);
        templateReq.setContentType(contentType);
        templateReq.setConvert2Pdf(false);
        templateReq.setFileName("闪租合同-package-20200527-33452764.pdf");
        templateReq.setAccountId("203a7ec141ec42e39eb636c3a702be8b");
        File file=new File(filePath);
        templateReq.setFileSize((int) file.length());
        String result =SignUtils.AddTemplate(templateReq);
        JSONObject jsonObject = (JSONObject) JSONObject.parse(result);
        String url=jsonObject.getString("uploadUrl");
        byte[] bytes= FileHelper.getBytes(filePath);
        JSONObject jsonObject2= HttpCfgHelper.sendHttp(RequestType.PUT,url,HttpClient.buildUploadHeader(contentMd5,contentType),bytes);
        String json=SignUtils.download(jsonObject.getString("fileId"));
//        System.out.println(json);
        return ApiResult.success(json);
    }

    @ApiOperation("流程回调通知")
    @PostMapping("/callback/flowpath")
    public ApiResult<String> CallbackFlow(@RequestBody CallbackSignflowsReq callbackSignflowsReq){
        return  ApiResult.success(null);
    }

    @ApiOperation("发布合同签署流程")
    @PostMapping("/addsign")
    public ApiResult<String> CREATE_SIGN() throws DefineException {
        return ApiResult.success(signatureService.CREATE_SIGN());
    }


    public static void main(String[] args) throws DefineException {

//         Template template=new Template();
//         template.setTemplateId("62404533c4824a43b976250994fd79cd");
//         template.setName("闪租合同");
//         SimpleFormFields simpleFormFields=new SimpleFormFields();
//         simpleFormFields.setText1("1564156165165dbdbi");
//         template.setSimpleFormFields(simpleFormFields);
//         SignUtils.AddByTemplate(template);


        String url="https://smlopenapi.esign.cn/v1/docTemplates/createByUploadUrl";
        String filePath="D:\\应用软件\\闪租合同模板.pdf";
        String contentMd5=FileHelper.getContentMD5(filePath);
        TemplateReq templateReq=new TemplateReq();
        templateReq.setContentMd5(contentMd5);
        templateReq.setContentType("application/pdf");
        templateReq.setFileName("闪租合同模板");
        templateReq.setConvert2Pdf(false);
        String json= HttpCfgHelper.postSignatureData(JSON.toJSONString(templateReq),url,"utf-8");
        System.out.println(json);
        JSONObject jsonObject = (JSONObject) JSONObject.parse(json);
        String ur=jsonObject.getString("data");
        System.out.println(jsonObject);
        JSONObject jsonOb = (JSONObject) JSONObject.parse(ur);
        byte[] bytes= FileHelper.getBytes(filePath);
        JSONObject jsonObject2= HttpCfgHelper.sendHttp(RequestType.PUT,jsonOb.getString("uploadUrl"),HttpClient.buildUploadHeader(contentMd5,"application/pdf"),bytes);
        String json1=HttpCfgHelper.doSignatureGet("https://smlopenapi.esign.cn/v1/docTemplates/"+jsonOb.getString("templateId"));
        System.out.println(json1);

//        String url= MessageFormat.format(SignatureReq.QUERY_SIGN_URL,"4142222f641a49edba8164224fdb937b");
//        url=url+"?organizeId=0&flowId=4142222f641a49edba8164224fdb937b&accountId="+"17ea5be88e6d42b083302f19195a6d34";
//        String json=HttpCfgHelper.doSignatureGet(url);
//        System.err.println(json);
    }

    public static String addTemplateComponentsParam() {
        // 创建输入项组件信息集合，并转化为JSON数组
        JSONArray jarr = JSONArray.parseArray(JSON.toJSONString(buildComponents()));
        JSONObject json = new JSONObject();
        json.put("structComponent", jarr);
        return json.toString();
    }

    private static List<StructComponent> buildComponents() {
        // 位置信息
        Pos pos = new Pos(1, 170, 682);
        // 组件样式
        Style style = new Style(100, 24, null, null, null);
        // 上下文信息
        Context context = new Context("租用者111222", null, null, style, pos);
        // 组件包装
        StructComponent structComponent = new StructComponent(null, "Text1", 1, context);
        return Lists.newArrayList(structComponent);
    }

}
