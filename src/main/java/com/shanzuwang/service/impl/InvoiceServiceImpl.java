package com.shanzuwang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.extra.InvoiceReq;
import com.shanzuwang.bean.req.product.Query;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.dao.dos.BillDO;
import com.shanzuwang.dao.dos.InvoiceDO;
import com.shanzuwang.dao.mapper.InvoiceDao;
import com.shanzuwang.service.IBillService;
import com.shanzuwang.service.IInvoiceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class InvoiceServiceImpl extends ServiceImpl<InvoiceDao, InvoiceDO> implements IInvoiceService {

    @Autowired
    IInvoiceService iInvoiceService;
    @Autowired
    IBillService iBillService;

    @Override
    public PageInfo<InvoiceDO> ListInvoice(Query query) {
        Page<InvoiceDO> page=new Page<>(query.getPageNo(),query.getPageSize());
        page(page);
        List<InvoiceDO> invoiceDOS=page.getRecords();
        return new PageInfo<InvoiceDO>(page.getTotal(),invoiceDOS,query.getPageNo(),query.getPageSize(),page.getPages());
}

    @Override
    public InvoiceReq getInvoice(Integer id) {
        InvoiceReq invoiceReq=new InvoiceReq();
        InvoiceDO invoiceDO=iInvoiceService.getById(id);
        BeanUtils.copyProperties(invoiceDO,invoiceReq);
        //订单信息加载
        LambdaQueryWrapper<BillDO> billWrapper=new LambdaQueryWrapper<>();
        billWrapper.eq(BillDO::getInvoiceId,id);
        List<BillDO> billDOS=iBillService.list();
        invoiceReq.setBills(billDOS);
        return invoiceReq;
    }

}
