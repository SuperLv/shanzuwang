package com.shanzuwang.util.http.esignature;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shanzuwang.bean.req.bill.esignature.*;
import com.shanzuwang.bean.req.bill.esignature.component.SignSite;
import com.shanzuwang.bean.req.bill.esignature.component.Template;
import com.shanzuwang.config.pay.SignatureReq;
import com.shanzuwang.service.impl.SignatureServiceImpl;
import com.shanzuwang.util.http.HttpClient;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hw
 * 20/06/05 10:53
 */
public class SignUtils {

    /**
      * 创建个人账号
      * */
    public static String AddPerson(Person person) {
        String result =HttpCfgHelper.postSignatureData(JSON.toJSONString(person),SignatureReq.CREATE_PERSON,"utf-8");
        JSONObject jsonObject = (JSONObject) JSONObject.parse(result);
        JSONObject jsonObject1= (JSONObject) JSONObject.parse(jsonObject.getString("data"));
        System.out.println(result);
        return jsonObject1.getString("accountId");
    }

    /**
     * 创建企业账号
     * */
    public  static String AddOrganize(Organize organize) {
        String result =HttpCfgHelper.postSignatureData(JSON.toJSONString(organize),SignatureReq.CREATE_ORGANIZE,"utf-8");
        JSONObject jsonObject = (JSONObject) JSONObject.parse(result);
        JSONObject jsonObject1= (JSONObject) JSONObject.parse(jsonObject.getString("data"));
        return jsonObject1.getString("orgId");
    }


    /**
     * 文件上传
     *
     * @param templateReq
     */
    public static String AddTemplate(TemplateReq templateReq) throws DefineException {
        String result=HttpCfgHelper.postSignatureData(JSON.toJSONString(templateReq),SignatureReq.CREATE_TEMPLATE,"utf-8");
        System.out.println(result);
        JSONObject jsonObject = (JSONObject) JSONObject.parse(result);
        return jsonObject.getString("data");
    }

    /**
     * 下载文件
     *
     * @param fileId
     */
    public static String download(String fileId) {
        String json=HttpCfgHelper.doSignatureGet(SignatureReq.CREATE_DOWNLOAD+fileId);
        return json;
    }

    /**
     * 签署流程创建
     *
     * @param signflowsReq
     */
    public static String AddflowPath(SignflowsReq signflowsReq) {
        signflowsReq.setAutoArchive(true);
        signflowsReq.setBusinessScene("闪租合同");
        SignflowsReq.ConfigInfo configInfo=new SignflowsReq.ConfigInfo();
        configInfo.setNoticeDeveloperUrl("null");
        configInfo.setNoticeType("1");
        configInfo.setRedirectUrl("");
        configInfo.setSignPlatform("2");
        signflowsReq.setInitiatorAccountId("17ea5be88e6d42b083302f19195a6d34");
        signflowsReq.setInitiatorAuthorizedAccountId("e168c6bf2d1b475190702fc8a7756c38");
        String result=HttpCfgHelper.postSignatureData(JSON.toJSONString(signflowsReq),SignatureReq.CREATE_SIGNFLOWS,"utf-8");
        JSONObject jsonObject = (JSONObject) JSONObject.parse(result);
        JSONObject jsonObject1= (JSONObject) JSONObject.parse(jsonObject.getString("data"));
        return jsonObject1.getString("flowId");
    }


    /***
     * 流程文档添加
     *
     * @param signflowsDocuments*/
    public static String AddFlowsDocuments(SignflowsDocuments signflowsDocuments) {
        String url=MessageFormat.format(SignatureReq.CREATE_FLOW_DOCUMENTS,signflowsDocuments.getFlowId());
        System.out.println(url);
        String json=HttpCfgHelper.postSignatureData(JSON.toJSONString(signflowsDocuments),url,"utf-8");
        return json;
    }

    /**
     * 添加平台自动盖章签署区
     */
    public static String AddplatformSign(String flowId,String fileId) {
        SignflowsDocuments signflowsDocuments=new SignflowsDocuments();
        signflowsDocuments.setFlowId(flowId);
        SignflowsDocuments.Docs docs=new SignflowsDocuments.Docs();
        docs.setFileId(fileId);
        docs.setFileName("application/pdf");
        List<SignflowsDocuments.Docs> docs1=new ArrayList<>();
        docs1.add(docs);
        signflowsDocuments.setDocs(docs1);
        PlatformSign platformSign=new PlatformSign();
        platformSign.setFlowId(flowId);
        List<PlatformSign.Signfields> signfields=new ArrayList<>();
        PlatformSign.Signfields setf=new PlatformSign.Signfields();
        setf.setFileId(fileId);
        setf.setSignDateBeanType(0);
        setf.setSignType(1);

        //签章位置
        PlatformSign.PosBean posBean=new PlatformSign.PosBean();
        SignSite signSite= SignUtils.QuerySignSite(platformSign.getFlowId(),setf.getFileId(),"乙方（签章）");
        SignSite.PositionList positionList=signSite.getPositionList().get(0);
        posBean.setPosPage(String.valueOf(positionList.getPageIndex()));
        SignSite.CoordinateList coordinateList=positionList.getCoordinateList().get(0);
        Float posx=coordinateList.getPosx();
        posBean.setPosX(posx+100);
        posBean.setPosY(coordinateList.getPosy());
        setf.setPosBean(posBean);
        setf.setSealId("79c041fd-fde4-4078-95f6-5b0510beee72");
        setf.setThirdOrderNo("123456789");
        signfields.add(setf);
        platformSign.setSignfields(signfields);
        String url=MessageFormat.format(SignatureReq.CREATE_PLATFORMSIGN,flowId);
        String json1=HttpCfgHelper.postSignatureData(JSON.toJSONString(platformSign),url,"utf-8");
        return json1;
    }

    //添加手动签署区域
    public static void AddHandSign(PlatformSign platformSign)
    {
        List<PlatformSign.Signfields> signfields=new ArrayList<>();
        PlatformSign.Signfields setf=platformSign.getSignfields().get(0);
        setf.setAssignedPosbean(true);
        //  885079d04e614dc297bee360ea9eff4e
        setf.setSignDateBeanType(0);
        setf.setSignType(1);

        //签章位置
        PlatformSign.PosBean posBean=new PlatformSign.PosBean();
        SignSite signSite= SignUtils.QuerySignSite(platformSign.getFlowId(),setf.getFileId(),"甲方（签章）");
        SignSite.PositionList positionList=signSite.getPositionList().get(0);
        posBean.setPosPage(String.valueOf(positionList.getPageIndex()));
        SignSite.CoordinateList coordinateList=positionList.getCoordinateList().get(0);
        Float posx=coordinateList.getPosx();
        posBean.setPosX(posx+100);
        posBean.setPosY(coordinateList.getPosy());
        setf.setPosBean(posBean);
        setf.setSealType("1");
        signfields.add(setf);
        platformSign.setSignfields(signfields);
        String url=MessageFormat.format(SignatureReq.CREATE_HANDSIGN,platformSign.getFlowId());
        String json1=HttpCfgHelper.postSignatureData(JSON.toJSONString(platformSign),url,"utf-8");
    }

    /**
     * 获取签章位置
     * */
    public static SignSite  QuerySignSite(String flowId,String fileId,String signName)
    {
        String url= MessageFormat.format(SignatureReq.QUERY_KEYWORD,flowId,fileId);
        url=url+"?keywords="+signName;
        String json=HttpCfgHelper.doSignatureGet(url);
        JSONObject jsonObject= (JSONObject) JSON.parse(json);
        String data=jsonObject.getString("data").substring(1,jsonObject.getString("data").length()-1);
        SignSite signSite=JSON.parseObject(data,SignSite.class);
        return signSite;
    }

    /**通过模板创建文件*/
    public static JSONObject AddByTemplate(Template template) throws DefineException {
        SignatureServiceImpl signatureService=new SignatureServiceImpl();
        String contentMd5=FileHelper.getContentMD5("C:\\Users\\Angell\\Documents\\SLEASE 闪租合同.pdf");
        System.out.println(JSON.toJSONString(template));
        String json=HttpCfgHelper.postSignatureData(JSON.toJSONString(template),SignatureReq.CREATEBY_TEMPLEATE,"utf-8");
        JSONObject templatejson = (JSONObject) JSONObject.parse(json);
        String data=templatejson.getString("data");
        JSONObject templatedata= (JSONObject) JSONObject.parse(data);
        System.out.println(json);
        return templatedata;
    }


}
