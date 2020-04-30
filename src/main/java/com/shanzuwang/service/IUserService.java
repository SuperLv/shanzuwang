package com.shanzuwang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.dto.UserDTO;
import com.shanzuwang.bean.req.user.UserQueryReq;
import com.shanzuwang.dao.dos.UserDO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lv
 * @since 2020-01-31
 */
public interface IUserService extends IService<UserDO> {
    /**
     * 分页查询用户列表
     * @param queryReq 查询参数
     * @return 用户列表
     */
    PageInfo<UserDTO> getUserByPage(UserQueryReq queryReq);

    /**
     * 处理用户登录
     * @param phone 用户手机号
     * @param pwd 用户密码
     * @return
     */
    UserDTO login(String phone, String pwd);
}
