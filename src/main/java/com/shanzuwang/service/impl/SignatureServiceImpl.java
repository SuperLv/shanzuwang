package com.shanzuwang.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shanzuwang.bean.req.bill.esignature.*;
import com.shanzuwang.bean.req.bill.esignature.component.SignSite;
import com.shanzuwang.bean.req.bill.esignature.component.SimpleFormFields;
import com.shanzuwang.bean.req.bill.esignature.component.Template;
import com.shanzuwang.config.pay.SignatureReq;
import com.shanzuwang.service.SignatureService;
import com.shanzuwang.util.http.esignature.*;
import com.shanzuwang.util.http.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hw
 * 20/05/28 16:02
 */
@Service
public class SignatureServiceImpl implements SignatureService {

    @Autowired
    SignatureService signatureService;


    @Override
    public  String CREATE_SIGN() throws DefineException {
        //根据模板创建文件
        Template template=new Template();
        template.setTemplateId("83b6a4c1d7e84812938e1739358efc3c");
        template.setName("闪租合同");
        SimpleFormFields simpleFormFields=new SimpleFormFields();
        simpleFormFields.setText1("测试文本域填充");
        template.setSimpleFormFields(simpleFormFields);
        JSONObject templatedata=SignUtils.AddByTemplate(template);
        String  fileId=templatedata.getString("fileId");

        //根据文件创建签署流程
        String flowId= SignUtils.AddflowPath(new SignflowsReq());
        SignflowsDocuments signflowsDocuments=new SignflowsDocuments();
        signflowsDocuments.setFlowId(flowId);
        SignflowsDocuments.Docs docs=new SignflowsDocuments.Docs();
        docs.setFileId(fileId);
        docs.setFileName("application/pdf");
        List<SignflowsDocuments.Docs> docs1=new ArrayList<>();
        docs1.add(docs);
        signflowsDocuments.setDocs(docs1);
        SignUtils.AddFlowsDocuments(signflowsDocuments);
        //自动盖章
        SignUtils.AddplatformSign(flowId,fileId);
        //手动盖章
        PlatformSign platformSign=new PlatformSign();
        platformSign.setFlowId(flowId);
        List<PlatformSign.Signfields> signfields=new ArrayList<>();
        PlatformSign.Signfields setf=new PlatformSign.Signfields();
        setf.setFileId(fileId);
        setf.setSignerAccountId("293956be245b428584909d4c9389eb5f");
        setf.setAuthorizedAccountId("e168c6bf2d1b475190702fc8a7756c38");
        setf.setThirdOrderNo("12345678944788");
        signfields.add(setf);
        platformSign.setSignfields(signfields);
        SignUtils.AddHandSign(platformSign);

        //文件流上传并获取签署地址
        String starturl=MessageFormat.format(SignatureReq.START_SIGNFLOWS,flowId);
        JSONObject jsonObject=HttpCfgHelper.sendHttp(RequestType.PUT,starturl,HttpClient.buildHeader(),null);
        String url=MessageFormat.format(SignatureReq.QUERY_SIGN_URL,flowId);
        url=url+"?organizeId=0&flowId="+flowId+"&accountId="+"293956be245b428584909d4c9389eb5f";
        String json=HttpCfgHelper.doSignatureGet(url);
        System.err.println(json);
        return json;
    }

    public static void main(String[] args) throws DefineException {

    }


}
