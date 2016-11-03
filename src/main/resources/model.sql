CREATE TABLE user_info (
  `id`         INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '自增id',
  `card_id`    VARCHAR(20)      DEFAULT NULL
  COMMENT '身份证号码',
  `cardId`     VARCHAR(20)      DEFAULT NULL
  COMMENT '姓名',
  `department` VARCHAR(20)      DEFAULT NULL
  COMMENT '所在机构',
  `same_id`    VARCHAR(20)      DEFAULT NULL
  COMMENT '身份证号码相同的部门的人的id',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 24
  DEFAULT CHARSET = utf8mb4;