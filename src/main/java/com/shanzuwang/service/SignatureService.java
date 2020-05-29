package com.shanzuwang.service;

import com.shanzuwang.bean.req.bill.esignature.Organize;
import com.shanzuwang.bean.req.bill.esignature.Person;
import com.shanzuwang.bean.req.bill.esignature.TemplateReq;

/**
 * Created by Hw
 * 20/05/28 16:01
 */
public interface SignatureService {

    /**
     * 创建个人账户
     * */
    public  String  AddPerson(Person person);

    /**
     *创建企业账号
     */
    public  String AddOrganize(Organize organize);


    /**
     * 文件上传
     * */
    public String AddTemplate(TemplateReq templateReq);
}
