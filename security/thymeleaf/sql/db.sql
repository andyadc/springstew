DROP TABLE
    IF
        EXISTS `user`;
CREATE TABLE `user`
(
    `id`          BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `username`    VARCHAR(100) NOT NULL COMMENT '账号',
    `password`    VARCHAR(255)          DEFAULT NULL COMMENT '密码',
    `nickname`    VARCHAR(100)          DEFAULT '' COMMENT '昵称',
    `create_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create_time',
    `update_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update_time',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_username` (`username`) USING BTREE
) ENGINE = INNODB
  AUTO_INCREMENT = 1000
  DEFAULT CHARSET = utf8mb4;