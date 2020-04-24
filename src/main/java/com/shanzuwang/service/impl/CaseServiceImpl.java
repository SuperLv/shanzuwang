package com.shanzuwang.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanzuwang.bean.req.website.CaseReq;
import com.shanzuwang.bean.req.PageReq;
import com.shanzuwang.dao.dos.CaseDO;
import com.shanzuwang.dao.mapper.CaseDao;
import com.shanzuwang.service.ICaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
@Service
public class CaseServiceImpl extends ServiceImpl<CaseDao, CaseDO> implements ICaseService {

    @Autowired
    ICaseService iCaseService;

    @Override
    public CaseReq AddCase(CaseReq caseReq) {
        CaseDO caseDO=new CaseDO();
        BeanUtils.copyProperties(caseReq,caseDO);
        caseDO.setCreatedAt(new Date());
        caseDO.setImages(JSON.toJSONString(caseReq.getImages()));
        caseDO.setExtra(JSON.toJSONString(caseReq.getExtra()));
        iCaseService.save(caseDO);
        caseReq.setId(caseDO.getId());
        return caseReq;
    }

    @Override
    public CaseReq UpdateCase(Integer id, CaseReq caseReq) {
        CaseDO caseDO=new CaseDO();
        BeanUtils.copyProperties(caseReq,caseDO);
        if (caseReq.getImages().size()>0)
            caseDO.setImages(JSON.toJSONString(caseReq.getImages()));
        if (caseReq.getExtra().size()>0)
            caseDO.setExtra(JSON.toJSONString(caseReq.getExtra()));
        caseDO.setUpdatedAt(new Date());
        caseDO.setId(id);
        iCaseService.updateById(caseDO);
        caseReq.setId(caseDO.getId());
        return caseReq;
    }

    @Override
    public CaseReq getCase(Integer id) {
        CaseReq caseReq=new CaseReq();
        CaseDO caseDO=iCaseService.getById(id);
        BeanUtils.copyProperties(caseDO,caseReq);
        ArrayList<Object> extra  = JSON.parseObject(caseDO.getExtra(), new TypeReference<ArrayList<Object>>(){});
        caseReq.setExtra(extra);
        ArrayList<Object> images  = JSON.parseObject(caseDO.getImages(), new TypeReference<ArrayList<Object>>(){});
        caseReq.setImages(images);
        return caseReq;
    }

    @Override
    public List<CaseReq> ListCase(PageReq pageReq) {
        Page<CaseDO> page = new Page<>(pageReq.getPageNo(),pageReq.getPageSize());
        page(page);
        List<CaseDO> caseDOS = page.getRecords();
        List<CaseReq> caseReqs=new ArrayList<>();
        for (CaseDO caseDO:caseDOS){
            CaseReq caseReq=new CaseReq();
            BeanUtils.copyProperties(caseDO,caseReq);
            ArrayList<Object> extra  = JSON.parseObject(caseDO.getExtra(), new TypeReference<ArrayList<Object>>(){});
            caseReq.setExtra(extra);
            ArrayList<Object> images  = JSON.parseObject(caseDO.getImages(), new TypeReference<ArrayList<Object>>(){});
            caseReq.setImages(images);
            caseReqs.add(caseReq);
        }
        return caseReqs;
    }



}
