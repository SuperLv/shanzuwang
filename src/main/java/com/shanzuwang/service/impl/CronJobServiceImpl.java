package com.shanzuwang.service.impl;

import com.shanzuwang.dao.dos.CronJobDO;
import com.shanzuwang.dao.mapper.CronJobDao;
import com.shanzuwang.service.ICronJobService;
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
public class CronJobServiceImpl extends ServiceImpl<CronJobDao, CronJobDO> implements ICronJobService {

}
