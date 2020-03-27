-- 创建数据库
CREATE DATABASE demo;

-- 创建用户表
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(20) NOT NULL COMMENT '用户姓名',
  `age` tinyint(3) NULL COMMENT '年龄',
  `phone` varchar(11) NOT NULL COMMENT '手机号',
  `password` varchar(128) DEFAULT NULL COMMENT '密码',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_phone` (`phone`)
  ) ENGINE=InnoDB  COMMENT='用户表';

-- 添加测试用户信息
insert into user (name,age,phone,password) values
('超哥',18,'18111111111','12345'),
('影帝',28,'15111111111','12345'),
('阿龙',38,'13111111111','12345');