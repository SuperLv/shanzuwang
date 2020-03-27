package com.shanzuwang.service.impl;

import com.shanzuwang.dao.dos.MessageDO;
import com.shanzuwang.dao.mapper.MessageDao;
import com.shanzuwang.service.IMessageService;
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
public class MessageServiceImpl extends ServiceImpl<MessageDao, MessageDO> implements IMessageService {

}
