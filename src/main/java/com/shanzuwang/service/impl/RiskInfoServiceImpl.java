package com.shanzuwang.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.user.RiskReq;
import com.shanzuwang.bean.req.product.FilterReq;
import com.shanzuwang.bean.req.product.Query;
import com.shanzuwang.dao.dos.RiskInfoDO;
import com.shanzuwang.dao.mapper.RiskInfoDao;
import com.shanzuwang.service.IRiskInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class RiskInfoServiceImpl extends ServiceImpl<RiskInfoDao, RiskInfoDO> implements IRiskInfoService {

    @Autowired
    IRiskInfoService iRiskInfoService;

    @Override
    public PageInfo<RiskReq> ListRisk(Query query) {
        Page<RiskInfoDO> page=new Page<>(query.getPageNo(),query.getPageSize());
        LambdaQueryWrapper<RiskInfoDO> riskWrapper=new LambdaQueryWrapper<>();
        FilterReq filterReq= JSON.parseObject(query.getFilter(),FilterReq.class);
        String[][] conditions =filterReq.getConditions();
        if (conditions.length>1){
            riskWrapper.like(RiskInfoDO::getCompanyName,conditions[0][2]).or();
            riskWrapper.like(RiskInfoDO::getManagerPhone,conditions[1][2]).or();
            riskWrapper.like(RiskInfoDO::getManager,conditions[2][2]);
        }else {
            riskWrapper.ne(RiskInfoDO::getStatus,conditions[0][2]);
        }
        page(page,riskWrapper);
        List<RiskInfoDO> riskInfoDOS=page.getRecords();
        List<RiskReq> riskReqs=new ArrayList<>();
        for (RiskInfoDO riskInfoDO:riskInfoDOS){
            RiskReq riskReq=new RiskReq();
            BeanUtils.copyProperties(riskInfoDO,riskReq);
            riskReqs.add(riskReq);
        }
        return new PageInfo<RiskReq>(page.getTotal(),riskReqs,query.getPageNo(),query.getPageSize(),page.getPages());
    }

    @Override
    public RiskReq getRisk(Integer id) {
        RiskInfoDO riskInfoDO=iRiskInfoService.getById(id);
        RiskReq riskReq=new RiskReq();
        BeanUtils.copyProperties(riskInfoDO,riskReq);
        return riskReq;
    }

    @Override
    public RiskReq UpdateRisk(Integer id, RiskReq riskReq) {
        RiskInfoDO riskInfoDO=new RiskInfoDO();
        BeanUtils.copyProperties(riskReq,riskInfoDO);
        riskInfoDO.setId(id);
        iRiskInfoService.updateById(riskInfoDO);
        riskInfoDO=iRiskInfoService.getById(id);
        BeanUtils.copyProperties(riskInfoDO,riskReq);
        return riskReq;
    }
}
