
-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `age` int(11) NOT NULL COMMENT '年龄',
  `name` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '姓名',
  `birthday` date NOT NULL COMMENT '生日',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COMMENT='用户信息';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '9', 'tom', '2006-06-13');
INSERT INTO `user` VALUES ('2', '8', 'may', '2007-01-01');
INSERT INTO `user` VALUES ('3', '7', 'jobs', '2008-07-17');
