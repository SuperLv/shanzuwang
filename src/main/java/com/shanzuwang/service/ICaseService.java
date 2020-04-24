package com.shanzuwang.service;

import com.shanzuwang.bean.req.website.CaseReq;
import com.shanzuwang.bean.req.PageReq;
import com.shanzuwang.dao.dos.CaseDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
public interface ICaseService extends IService<CaseDO> {
      public CaseReq AddCase(CaseReq caseReq);

      public CaseReq UpdateCase(Integer id,CaseReq caseReq);

      public  CaseReq getCase(Integer id);

      public List<CaseReq> ListCase(PageReq pageReq);
}
