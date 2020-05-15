package com.shanzuwang.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.product.*;
import com.shanzuwang.dao.dos.*;
import com.shanzuwang.dao.mapper.SkuDao;
import com.shanzuwang.service.*;
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
public class SkuServiceImpl extends ServiceImpl<SkuDao, SkuDO> implements ISkuService {

    @Autowired
    SkuDao skuDao;
    @Autowired
    ISkuService iSkuService;
    @Autowired
    ICategoryService iCategoryService;
    @Autowired
    IPeriodsService iPeriodsService;
    @Autowired
    IProductPropertyService iProductPropertyService;
    @Autowired
    IPropertyService iPropertyService;

    @Override
    public List<SkuQueryReq> ListSpuSkus(Integer spuId) {
        LambdaQueryWrapper<SkuDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SkuDO::getSpuId,spuId);
        List<SkuDO> skuDOS=iSkuService.list(queryWrapper);
        List<SkuQueryReq> skuQueryReqs=new ArrayList<>();
        for (SkuDO s:skuDOS){
            SkuQueryReq skuQueryReq=new SkuQueryReq();
            BeanUtils.copyProperties(s,skuQueryReq);
            //image
            skuQueryReq.setImages(null);
            skuQueryReq.setSimages(Strings(s.getImages()));
            //eplatform
            skuQueryReq.setSePlatform(Strings(s.getEPlatform()));
            skuQueryReq.setEPlatform(null);
            skuQueryReqs.add(skuQueryReq);
        }

        return skuQueryReqs;
    }


    @Override
    public SkuQueryReq getSku(Integer spuid, Integer skuid) {
        SkuDO skuDO=iSkuService.getById(skuid);
        SkuQueryReq skuQueryReq=new SkuQueryReq();
        BeanUtils.copyProperties(skuDO,skuQueryReq);
        String image=skuDO.getImages().replaceAll(" ","");
        skuQueryReq.setImages(null);
        skuQueryReq.setSimages(Strings(image));
        //eplatform
        String eplatfor=skuDO.getEPlatform().replaceAll(" ","");
        skuQueryReq.setSePlatform(Strings(eplatfor));
        skuQueryReq.setEPlatform(null);
        //分期计划
        LambdaQueryWrapper<PeriodsDO> periodsWrapper=new LambdaQueryWrapper<>();
        periodsWrapper.eq(PeriodsDO::getSkuId,skuid);
        List<PeriodsDO> periodsDO=iPeriodsService.list(periodsWrapper);
        skuQueryReq.setPeriodsDO(periodsDO);
        //属性绑定
        LambdaQueryWrapper<ProductPropertyDO> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductPropertyDO::getActorId,skuid);
        List<ProductPropertyDO> productPropertyDO=iProductPropertyService.list(queryWrapper);
        List<Properties> list=new ArrayList<>();
        for (ProductPropertyDO s:productPropertyDO){
            Properties properties=new Properties();
            BeanUtils.copyProperties(s,properties);
            properties.setValue(Strings(s.getValue()));
            PropertyDO propertyDO=iPropertyService.getById(s.getTargetId());
            properties.setName(propertyDO.getName());
            list.add(properties);
        }
        skuQueryReq.setPropertiesef(list);
        return skuQueryReq;
    }

    @Override
    public SkuQueryReq UpdateSku(Integer spuid, Integer skuid, SkuQueryReq skuQueryReq) {
        SkuDO skuDO=new SkuDO();
        BeanUtils.copyProperties(skuQueryReq,skuDO);
        skuDO.setEPlatform(Stringa(skuQueryReq.getSePlatform()));
        if (skuQueryReq.getSimages().length>0){
            skuDO.setImages(Stringa(skuQueryReq.getSimages()));
        }

        //更新属性绑定
        List<Properties> properties=skuQueryReq.getPropertiesef();
        for (Properties p:properties){
            LambdaQueryWrapper<ProductPropertyDO> periodsWrapper=new LambdaQueryWrapper<>();
            periodsWrapper.eq(ProductPropertyDO::getActorId,skuid);
            periodsWrapper.eq(ProductPropertyDO::getTargetId,p.getId());
            List<ProductPropertyDO> productPropertyDOs=iProductPropertyService.list(periodsWrapper);
            ProductPropertyDO productPropertyDO=new ProductPropertyDO();
            if (productPropertyDOs.size()>0){
                productPropertyDO.setValue(Stringa(p.getValue()));
                iProductPropertyService.update(productPropertyDO,periodsWrapper);
            }else {
                productPropertyDO.setValue(Stringa(p.getValue()));
                productPropertyDO.setTargetId(p.getId());
                productPropertyDO.setActorId(skuid);
                productPropertyDO.setType("SkuProperty");
                iProductPropertyService.save(productPropertyDO);
            }
        }

        //periods
        List<PeriodsDO> periodsDOS=skuQueryReq.getPeriodsDO();
        LambdaQueryWrapper<PeriodsDO> periodsDOSWrapper=new LambdaQueryWrapper<>();
        periodsDOSWrapper.eq(PeriodsDO::getSkuId,skuid);
        if (periodsDOS.size()>0){
            //循环删除
            List<PeriodsDO>  lsir=iPeriodsService.list(periodsDOSWrapper);
            for (PeriodsDO periodsDO:lsir){
                boolean state=periodsDOS.contains(periodsDO);
                System.out.println(state);
                if (!state){
                    iPeriodsService.removeById(periodsDO.getId());
                }
            }

            //新增
            for (PeriodsDO p:periodsDOS){
                if (p.getId()==null){
                    p.setSkuId(skuid);
                    iPeriodsService.save(p);
                }
            }
        }else {

            iPeriodsService.remove(periodsDOSWrapper);
        }
        skuDO.setId(skuid);
        skuQueryReq.setId(skuid);
        skuQueryReq.setPropertiesef(null);
        skuQueryReq.setPeriodsDO(null);
        iSkuService.updateById(skuDO);
        return skuQueryReq;
    }

    @Override
    public SkuQueryReq AddSku(Integer spuid, SkuQueryReq skuQueryReq) {
        SkuDO skuDO=new SkuDO();
        BeanUtils.copyProperties(skuQueryReq,skuDO);
        if (skuQueryReq.getSePlatform().length>0){
            skuDO.setEPlatform(Stringa(skuQueryReq.getSePlatform()));
        }
        if (skuQueryReq.getSimages().length>0){
            skuDO.setImages(Stringa(skuQueryReq.getSimages()));
        }
        skuDO.setSpuId(spuid);
        iSkuService.save(skuDO);


        //新增属性
        List<Properties> properties=skuQueryReq.getPropertiesef();
        for (Properties p:properties){

            ProductPropertyDO productPropertyDO=new ProductPropertyDO();
            productPropertyDO.setValue(Stringa(p.getValue()));
            productPropertyDO.setTargetId(p.getId());
            productPropertyDO.setActorId(skuDO.getId());
            productPropertyDO.setType("SkuProperty");
            iProductPropertyService.save(productPropertyDO);
        }

        //periods
        List<PeriodsDO> periodsDOS=skuQueryReq.getPeriodsDO();
        for (PeriodsDO p:periodsDOS){
            if (p.getId()==null){
                p.setSkuId(skuDO.getId());
                iPeriodsService.save(p);
            }
        }
        skuQueryReq.setId(skuDO.getId());
        skuQueryReq.setPropertiesef(null);
        skuQueryReq.setPeriodsDO(null);
        return skuQueryReq;
    }

    @Override
    public PageInfo<SkuQueryReq> ListSkus(Query query) {
        Page<SkuDO> skuDOPageInfo=new Page<>(query.getPageNo(),query.getPageSize());
        FilterReq filterReq= JSON.parseObject(query.getFilter(),FilterReq.class);
        String[][] conditions=filterReq.getConditions();
        SkuQueryReq skuQueryReq=new SkuQueryReq();
        for(int i=0;i<conditions.length;i++){
          if(conditions[i][0].equals("sku.status")){
              skuQueryReq.setStatus(conditions[i][2]);
          }else if(conditions[i][0].equals("periods.type")){
              skuQueryReq.setType(conditions[i][2]);
          }else if(conditions[i][0].equals("spu.category_id")){
              skuQueryReq.setCategoryId(conditions[i][2]);
          }else if(conditions[i][0].equals("name")){
              skuQueryReq.setName(conditions[i][2]);
          }
        }
        List<SkuQueryReq> skuDOS=skuDao.ListSkus(skuDOPageInfo,skuQueryReq);
        List<SkuQueryReq> skuQueryReqs=new ArrayList<>();
        for (SkuQueryReq SkuQueryReq:skuDOS){
            //分期计划
            String image=SkuQueryReq.getImages().replaceAll(" ","");
            skuQueryReq.setImages(null);
            skuQueryReq.setSimages(Strings(image));


            //eplatform
            String eplatfor=SkuQueryReq.getEPlatform().replaceAll(" ","");
            skuQueryReq.setSePlatform(Strings(eplatfor));
            skuQueryReq.setEPlatform(null);
            //分期计划
            LambdaQueryWrapper<PeriodsDO> periodsWrapper=new LambdaQueryWrapper<>();
            periodsWrapper.eq(PeriodsDO::getSkuId,SkuQueryReq.getId());
            List<PeriodsDO> periodsDO=iPeriodsService.list(periodsWrapper);
            skuQueryReq.setPeriodsDO(periodsDO);

            //category
            CategoryDO categoryDO=iCategoryService.getById(SkuQueryReq.getCategoryId());
            CategoryReq categoryReq=new CategoryReq();
            BeanUtils.copyProperties(categoryDO,categoryReq);
            skuQueryReq.setCategory(categoryReq);
            skuQueryReqs.add(skuQueryReq);
        }
        return new PageInfo<SkuQueryReq>(skuQueryReqs.size(),skuQueryReqs,query.getPageNo(),query.getPageSize(),skuQueryReqs.size()/query.getPageSize());
    }


    public static String[] Strings(String im)
    {
        String values=im.replaceAll(" ","");
        String ima=values.substring(1,values.length()-1);
        if (!ima.equals("")&&ima!=null){
            String[] arrl= ima.split(",");
            String[] list=new String[arrl.length];
            for (int i=0;i<arrl.length;i++){
                String  s=arrl[i].substring(1,arrl[i].length()-1);
                list[i]=s;
            }
            return  list;
        }else {
            return null;
        }
    }

    public static String Stringa(String[] im)
    {
        String lis="";
        for (int i=0;i<im.length;i++){
            if(i!=im.length-1){
                lis=lis+"\""+im[i]+"\",";
            }else {
                lis=lis+"\""+im[i]+"\"";
            }
        }
        lis="["+lis+"]";
        return lis;
    }

}
