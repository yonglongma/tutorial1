USE tutorial;
CREATE TABLE `SYS_PASSPORT` (
    `ID` BIGINT(20)  NOT NULL  COMMENT '主键',
    `IDENTITY_TYPE` VARCHAR(50)  DEFAULT NULL  COMMENT '身份类型',
    `USER_ID` BIGINT(20)  DEFAULT NULL  COMMENT '用户ID',
    `ISSUE_TIME` DATETIME  DEFAULT NULL  COMMENT '领用日期',
    `EXPIRE_TIME` DATETIME  DEFAULT NULL  COMMENT '过期时间',
    `REVOKE_TIME` DATETIME  DEFAULT NULL  COMMENT '注销时间',
    `REVOKE_TYPE` VARCHAR(50)  DEFAULT NULL  COMMENT '注销类型',
    `ISSUE_IP` VARCHAR(50)  DEFAULT NULL  COMMENT '领用IP',
    `ISSUE_CLIENT` VARCHAR(500)  DEFAULT NULL  COMMENT '领用设备',
    `ROW_VERSION` BIGINT(20)  DEFAULT NULL  COMMENT '行版本',
    `IS_DELETED` TINYINT(1)  DEFAULT NULL  COMMENT '是否已删除',
    `CREATED_BY` BIGINT(20)  NOT NULL  COMMENT '创建用户',
    `CREATION_TIME` DATETIME  NOT NULL  COMMENT '创建时间',
    `LAST_UPDATED_BY` BIGINT(20)  DEFAULT NULL  COMMENT '最后更新用户',
    `LAST_UPDATE_TIME` DATETIME  DEFAULT NULL  COMMENT '最后更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户护照';