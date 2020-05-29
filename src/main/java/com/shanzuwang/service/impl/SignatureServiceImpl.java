package com.shanzuwang.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shanzuwang.bean.req.bill.esignature.Organize;
import com.shanzuwang.bean.req.bill.esignature.Person;
import com.shanzuwang.bean.req.bill.esignature.TemplateReq;
import com.shanzuwang.config.pay.SignatureReq;
import com.shanzuwang.service.SignatureService;
import com.shanzuwang.util.http.HttpClient;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by Hw
 * 20/05/28 16:02
 */
@Service
public class SignatureServiceImpl implements SignatureService {


    @Override
    public String AddPerson(Person person) {
        String result =HttpClient.postSignatureData(JSON.toJSONString(person),SignatureReq.CREATE_PERSON,"utf-8");
        JSONObject jsonObject = (JSONObject) JSONObject.parse(result);
        JSONObject jsonObject1= (JSONObject) JSONObject.parse(jsonObject.getString("data"));
        return jsonObject1.getString("accountId");
    }

    /**
     * 创建企业账号
     *
     * @param organize
     */
    @Override
    public String AddOrganize(Organize organize) {
        String result =HttpClient.postSignatureData(JSON.toJSONString(organize),SignatureReq.CREATE_ORGANIZE,"utf-8");
        JSONObject jsonObject = (JSONObject) JSONObject.parse(result);
        JSONObject jsonObject1= (JSONObject) JSONObject.parse(jsonObject.getString("data"));
        return jsonObject1.getString("orgId");
    }

    /**
     * 文件上传
     *
     * @param templateReq
     */
    @Override
    public String AddTemplate(TemplateReq templateReq) {
       String result=HttpClient.postSignatureData(JSON.toJSONString(templateReq),SignatureReq.CREATE_TEMPLATE,"utf-8");
        System.out.println(result);
       JSONObject jsonObject = (JSONObject) JSONObject.parse(result);
       return jsonObject.getString("data");
    }

    public static void main(String[] args) {
        TemplateReq templateReq=new TemplateReq();
        templateReq.setContentMd5(SignatureReq.getStringContentMD5("C:\\Users\\Angell\\Downloads\\闪租合同-package-20200527-33452764.pdf"));
        templateReq.setContentType("application/pdf");
        templateReq.setConvert2Pdf(false);
        templateReq.setFileName("闪租合同-package-20200527-33452764.pdf");
        templateReq.setAccountId("203a7ec141ec42e39eb636c3a702be8b");
        File file=new File("C:\\Users\\Angell\\Downloads\\闪租合同-package-20200527-33452764.pdf");
        templateReq.setFileSize((int) file.length());
        String result=HttpClient.postSignatureData(JSON.toJSONString(templateReq),SignatureReq.CREATE_TEMPLATE,"utf-8");
        System.out.println(result);
        //{"code":0,"message":"成功","data":{"fileId":"a8adb00dbad347faa74c9d1080979773","uploadUrl":"https://esignoss.esign.cn/1111564182/814d3150-25f3-40f4-ba25-4bbea5606329/%E9%97%AA%E7%A7%9F%E5%90%88%E5%90%8C-package-20200527-33452764.pdf?Expires=1590749622&OSSAccessKeyId=STS.NSsAfJZsfUWTKYMiYRQKb9m57&Signature=iViL5lZ67kvrQSKJDQnLRGKNqio%3D&callback-var=eyJ4OmZpbGVfa2V5IjoiJDJmMjI3Y2I4NjFjMjQzMzE4MDA4OTdmM2FiNTE5M2Q4JDMzNTQxMTc3MTUkSCJ9%0A&callback=eyJjYWxsYmFja1VybCI6Imh0dHA6Ly80Ny45OS4yMjQuMjM1OjgwODAvZmlsZS1zeXN0ZW0vY2FsbGJhY2svYWxpb3NzIiwiY2FsbGJhY2tCb2R5IjogIntcIm1pbWVUeXBlXCI6JHttaW1lVHlwZX0sXCJzaXplXCI6ICR7c2l6ZX0sXCJidWNrZXRcIjogJHtidWNrZXR9LFwib2JqZWN0XCI6ICR7b2JqZWN0fSxcImV0YWdcIjogJHtldGFnfSxcImZpbGVfa2V5XCI6JHt4OmZpbGVfa2V5fX0iLCJjYWxsYmFja0JvZHlUeXBlIjogImFwcGxpY2F0aW9uL2pzb24ifQ%3D%3D%0A&security-token=CAIS%2BAF1q6Ft5B2yfSjIr5DGCtz%2Bt6xH4pW%2FSX%2F8jVkHXcRO1qie1Tz2IHtKdXRvBu8Xs%2F4wnmxX7f4YlqB6T55OSAmcNZEoI03wZs7nMeT7oMWQweEurv%2FMQBqyaXPS2MvVfJ%2BOLrf0ceusbFbpjzJ6xaCAGxypQ12iN%2B%2Fm6%2FNgdc9FHHPPD1x8CcxROxFppeIDKHLVLozNCBPxhXfKB0ca0WgVy0EHsPnvm5DNs0uH1AKjkbRM9r6ceMb0M5NeW75kSMqw0eBMca7M7TVd8RAi9t0t1%2FIVpGiY4YDAWQYLv0rda7DOltFiMkpla7MmXqlft%2BhzcgeQY0pc%2FRqAAWJU1HAeQutQW%2FjwIXK4pIE74gLQ27JjPdpEBI8qfLV%2B3KCfgNAb4EtpPJKBol1qwveArsiGOZB8yXfkREpsiW%2Bt34Qi7mas%2B%2Bzk71R57OhOOfEPDggUtV%2FrVG72umW3Bs70QBAkcuQBC65HFcKzTtt8ha2PM6Mh60dAhC6yX3Vt"}}
    }
}
