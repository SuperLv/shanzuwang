package com.shanzuwang.service.impl;

import com.shanzuwang.dao.dos.TransactionLogDO;
import com.shanzuwang.dao.mapper.TransactionLogDao;
import com.shanzuwang.service.ITransactionLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
@Service
public class TransactionLogServiceImpl extends ServiceImpl<TransactionLogDao, TransactionLogDO> implements ITransactionLogService {

}
