SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ssm_demo
-- 2018-9-28
-- ----------------------------

DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo` (
  `id`          BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `name`        VARCHAR(50)         NOT NULL
  COMMENT '用户名',
  `type`        INT(5) COMMENT '类型',
  `status`      TINYINT(2)          NOT NULL
  COMMENT '状态',
  `version`     INT(11)    UNSIGNED          DEFAULT '0'
  COMMENT '版本号',
  `create_time` DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  `update_time` DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_name` (`name`) USING BTREE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='ssm demo table';