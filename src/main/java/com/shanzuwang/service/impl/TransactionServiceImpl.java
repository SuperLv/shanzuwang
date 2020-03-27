package com.shanzuwang.service.impl;

import com.shanzuwang.dao.dos.TransactionDO;
import com.shanzuwang.dao.mapper.TransactionDao;
import com.shanzuwang.service.ITransactionService;
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
public class TransactionServiceImpl extends ServiceImpl<TransactionDao, TransactionDO> implements ITransactionService {

}
