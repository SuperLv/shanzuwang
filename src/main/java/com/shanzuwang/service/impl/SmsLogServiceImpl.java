package com.shanzuwang.service.impl;

import com.shanzuwang.dao.dos.SmsLogDO;
import com.shanzuwang.dao.mapper.SmsLogDao;
import com.shanzuwang.service.ISmsLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 短信记录 服务实现类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
@Service
public class SmsLogServiceImpl extends ServiceImpl<SmsLogDao, SmsLogDO> implements ISmsLogService {

}
