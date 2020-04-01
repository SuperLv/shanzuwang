package com.shanzuwang.service.impl;

import com.alibaba.fastjson.JSON;
import com.shanzuwang.config.Constants;
import com.shanzuwang.dao.dos.YunTo;
import com.shanzuwang.service.CCPRestSDKService;
import com.shanzuwang.util.DateUtils;
import com.shanzuwang.util.MD5;
import com.shanzuwang.util.RedisUtil;
import com.shanzuwang.util.http.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by LiWeijie
 * 20/03/31 17:44
 */
@Service
public class CCPRestSDKServiceImpl implements CCPRestSDKService {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public  String sendTemplateSMS(String phone) throws InterruptedException {
        String  dateTime= DateUtils.getNowDateTimeString();
        String SigParameter= MD5.encode(Constants.ACCOUNTSid+Constants.ACCOUNT_TOKEN+dateTime);
        String YUNUrl="https://app.cloopen.com:8883/2013-12-26/Accounts/8a48b5515350d1e201537968ad5642ea/SMS/TemplateSMS?sig="+SigParameter;
        YunTo yunTo=new YunTo();
        yunTo.setTo(phone);
        yunTo.setAppId("8a48b5515350d1e2015379d2f44643ff");
        yunTo.setTemplateId("74070");
        int authCode=getNumber();
        yunTo.setDatas("['"+authCode+"','5']");
        yunTo.setSubAppend("8888");
        yunTo.setReqId(createNo());
        String json= JSON.toJSONString(yunTo);
        String retu= HttpClient.postData(json,YUNUrl,"utf-8",dateTime);
        System.out.println(retu);

        //将验证码放入换成有效期五分钟
        redisUtil.set(phone,authCode);
        redisUtil.expire(phone, (long) (60*5));
        return retu;
    }

    public static String  createNo(){
        SimpleDateFormat simpleDateFormat;
        simpleDateFormat = new SimpleDateFormat("yyyyMMddHH");
        Date date =new Date();
        String str=simpleDateFormat.format(date);
        Random random=new Random();
        int rannum=(int) (random.nextDouble()*(99999-10000+1))+10000;//获取5位随机数
        String  No =str +"-"+  rannum;
        return  No;
    }

    /** 得到一个四位无重复数字的数 */
    private static int getNumber() {
        Set<Integer> set = new HashSet<Integer>();
        while (true) {
            int a = (int) ((Math.random() * 100) % 10);
            set.add(new Integer(a));//Set里面的元素是不重复的，如果重复是存不进去的。
            if(set.size()>3)
                break;
        }
        int index = (int) ((Math.random() * 100) % 4);
        if(index==0){index+=1;}
        Integer[] arr = new Integer[set.size()];
        set.toArray(arr);
        String s = "";
        if(arr[0].intValue()==0){//如果第一位是0，则随机和后面三位交换
            Integer temp = arr[0];
            arr[0] = arr[index];
            arr[index] = temp;
        }
        for(int i=0;i<arr.length;i++){
            s += arr[i].intValue();
        }
        return Integer.parseInt(s);
    }

    public static void main(String[] args) throws InterruptedException {
    }

}
