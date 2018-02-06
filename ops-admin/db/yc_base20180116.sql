/*
Navicat MySQL Data Transfer

Source Server         : 10.180.8.205(master)
Source Server Version : 50633
Source Host           : 10.180.8.205:3306
Source Database       : yc_base

Target Server Type    : MYSQL
Target Server Version : 50633
File Encoding         : 65001

Date: 2018-01-16 13:52:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_element
-- ----------------------------
DROP TABLE IF EXISTS `base_element`;
CREATE TABLE `base_element` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL COMMENT '资源编码',
  `type` varchar(255) DEFAULT NULL COMMENT '资源类型',
  `name` varchar(255) DEFAULT NULL COMMENT '资源名称',
  `uri` varchar(255) DEFAULT NULL COMMENT '资源路径',
  `menu_id` varchar(255) DEFAULT NULL COMMENT '资源关联菜单',
  `parent_id` varchar(255) DEFAULT NULL,
  `path` varchar(2000) DEFAULT NULL COMMENT '资源树状检索路径',
  `method` varchar(10) DEFAULT NULL COMMENT '资源请求类型',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  `attr6` varchar(255) DEFAULT NULL,
  `attr7` varchar(255) DEFAULT NULL,
  `attr8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of base_element
-- ----------------------------
INSERT INTO `base_element` VALUES ('3', 'userManager:btn_add', 'button', '新增', '/admin/user', '1', null, null, 'POST', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_element` VALUES ('4', 'userManager:btn_edit', 'button', '编辑', '/admin/user', '1', null, null, 'PUT', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_element` VALUES ('5', 'userManager:btn_del', 'button', '删除', '/admin/user', '1', null, null, 'DELETE', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_element` VALUES ('9', 'menuManager:element', 'uri', '按钮页面', '/admin/element', '6', null, null, 'GET', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_element` VALUES ('10', 'menuManager:btn_add', 'button', '新增', '/admin/menu', '6', null, null, 'POST', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_element` VALUES ('11', 'menuManager:btn_edit', 'button', '编辑', '/admin/menu', '6', '', '', 'PUT', '', '2017-06-24 00:00:00', '', '', '', '', '', '', '', '', '', '', '');
INSERT INTO `base_element` VALUES ('12', 'menuManager:btn_del', 'button', '删除', '/admin/menu', '6', '', '', 'DELETE', '', '2017-06-24 00:00:00', '', '', '', '', '', '', '', '', '', '', '');
INSERT INTO `base_element` VALUES ('13', 'menuManager:btn_element_add', 'button', '新增元素', '/admin/element', '6', null, null, 'POST', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_element` VALUES ('14', 'menuManager:btn_element_edit', 'button', '编辑元素', '/admin/element', '6', null, null, 'PUT', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_element` VALUES ('15', 'menuManager:btn_element_del', 'button', '删除元素', '/admin/element', '6', null, null, 'DELETE', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_element` VALUES ('16', 'groupManager:btn_add', 'button', '新增', '/admin/group', '7', null, null, 'POST', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_element` VALUES ('17', 'groupManager:btn_edit', 'button', '编辑', '/admin/group', '7', null, null, 'PUT', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_element` VALUES ('18', 'groupManager:btn_del', 'button', '删除', '/admin/group', '7', null, null, 'DELETE', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_element` VALUES ('19', 'groupManager:btn_userManager', 'button', '分配用户', '/admin/group/{*}/user', '7', null, null, 'PUT', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_element` VALUES ('20', 'groupManager:btn_resourceManager', 'button', '分配权限', '/admin/group/{*}/authority', '7', null, null, 'GET', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_element` VALUES ('21', 'groupManager:menu', 'uri', '分配菜单', '/admin/group/{*}/authority/menu', '7', null, null, 'POST', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_element` VALUES ('22', 'groupManager:element', 'uri', '分配资源', '/admin/group/{*}/authority/element', '7', null, null, 'POST', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_element` VALUES ('23', 'userManager:view', 'uri', '查看', '/admin/user', '1', '', '', 'GET', '', '2017-06-26 00:00:00', '', '', '', '', '', '', '', '', '', '', '');
INSERT INTO `base_element` VALUES ('24', 'menuManager:view', 'uri', '查看', '/admin/menu', '6', '', '', 'GET', '', '2017-06-26 00:00:00', '', '', '', '', '', '', '', '', '', '', '');
INSERT INTO `base_element` VALUES ('27', 'menuManager:element_view', 'uri', '查看', '/admin/element', '6', null, null, 'GET', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_element` VALUES ('28', 'groupManager:view', 'uri', '查看', '/admin/group', '7', null, null, 'GET', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_element` VALUES ('32', 'groupTypeManager:view', 'uri', '查看', '/admin/groupType', '8', null, null, 'GET', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_element` VALUES ('33', 'groupTypeManager:btn_add', 'button', '新增', '/admin/groupType', '8', null, null, 'POST', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_element` VALUES ('34', 'groupTypeManager:btn_edit', 'button', '编辑', '/admin/groupType', '8', null, null, 'PUT', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_element` VALUES ('35', 'groupTypeManager:btn_del', 'button', '删除', '/admin/groupType', '8', null, null, 'DELETE', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_element` VALUES ('41', 'gateLogManager:view', 'button', '查看', '/admin/gateLog', '27', null, null, 'GET', '', '2017-07-01 00:00:00', '1', 'admin', '0:0:0:0:0:0:0:1', null, null, null, null, null, null, null, null);
INSERT INTO `base_element` VALUES ('42', 'test:restful', 'uri', '查看', '/kkk/test', '27', null, null, 'GET', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_element` VALUES ('45', 'message/mq:page', 'URI', '浏览页面', '/message/mq/page', '51', null, null, 'POST', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_element` VALUES ('50', 'authprovider:btn_add', 'BUTTON', '新增', '/user', '60', null, null, 'POST', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_element` VALUES ('51', 'route:add', 'BUTTON', '新增', '/manager/routes', '54', null, null, 'POST', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_element` VALUES ('52', 'route:page', 'URI', '列表查询', '/manager/routes/page', '54', null, null, 'GET', null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for base_group
-- ----------------------------
DROP TABLE IF EXISTS `base_group`;
CREATE TABLE `base_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL COMMENT '角色编码',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `parent_id` int(11) NOT NULL COMMENT '上级节点',
  `path` varchar(2000) DEFAULT NULL COMMENT '树状关系',
  `type` char(1) DEFAULT NULL COMMENT '类型',
  `group_type` int(11) NOT NULL COMMENT '角色组类型',
  `description` varchar(255) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  `upd_time` datetime DEFAULT NULL,
  `upd_user` varchar(255) DEFAULT NULL,
  `upd_name` varchar(255) DEFAULT NULL,
  `upd_host` varchar(255) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  `attr6` varchar(255) DEFAULT NULL,
  `attr7` varchar(255) DEFAULT NULL,
  `attr8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of base_group
-- ----------------------------
INSERT INTO `base_group` VALUES ('1', 'adminRole', '管理员', '-1', '/adminRole', null, '1', '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group` VALUES ('4', 'visitorRole', '访客', '3', '/testGroup/visitorRole', null, '1', '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group` VALUES ('7', 'financeDepart', '财务部', '6', '/company/financeDepart', null, '2', '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group` VALUES ('8', 'hrDepart', '人力资源部', '6', '/company/hrDepart', null, '2', '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group` VALUES ('10', 'role1', '角色组1', '-1', '/role1', null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group` VALUES ('11', 'role-1', '角色1', '10', '/role1/role-1', null, '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group` VALUES ('12', 'yonyouauto', '用友汽车', '-1', '/yonyouauto', null, '2', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group` VALUES ('13', 'auto-1', '数字营销', '12', '/yonyouauto/auto-1', null, '2', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group` VALUES ('14', 'auto-1-1', '研发部', '13', '/yonyouauto/auto-1/auto-1-1', null, '2', '111', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group` VALUES ('15', '000000', '000000专用', '10', '/role1/000000', null, '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for base_group_leader
-- ----------------------------
DROP TABLE IF EXISTS `base_group_leader`;
CREATE TABLE `base_group_leader` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  `upd_time` datetime DEFAULT NULL,
  `upd_user` varchar(255) DEFAULT NULL,
  `upd_name` varchar(255) DEFAULT NULL,
  `upd_host` varchar(255) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  `attr6` varchar(255) DEFAULT NULL,
  `attr7` varchar(255) DEFAULT NULL,
  `attr8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of base_group_leader
-- ----------------------------
INSERT INTO `base_group_leader` VALUES ('6', '9', '4', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group_leader` VALUES ('13', '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group_leader` VALUES ('14', '8', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group_leader` VALUES ('18', '14', '15', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group_leader` VALUES ('19', '14', '18', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group_leader` VALUES ('20', '4', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group_leader` VALUES ('27', '11', '20', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group_leader` VALUES ('28', '15', '28', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for base_group_member
-- ----------------------------
DROP TABLE IF EXISTS `base_group_member`;
CREATE TABLE `base_group_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  `upd_time` datetime DEFAULT NULL,
  `upd_user` varchar(255) DEFAULT NULL,
  `upd_name` varchar(255) DEFAULT NULL,
  `upd_host` varchar(255) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  `attr6` varchar(255) DEFAULT NULL,
  `attr7` varchar(255) DEFAULT NULL,
  `attr8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of base_group_member
-- ----------------------------
INSERT INTO `base_group_member` VALUES ('9', '9', '4', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group_member` VALUES ('10', '1', '2', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group_member` VALUES ('15', '10', '21', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_group_member` VALUES ('16', '11', '24', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for base_group_type
-- ----------------------------
DROP TABLE IF EXISTS `base_group_type`;
CREATE TABLE `base_group_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL COMMENT '编码',
  `name` varchar(255) DEFAULT NULL COMMENT '类型名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `crt_time` datetime DEFAULT NULL COMMENT '创建时间',
  `crt_user` varchar(255) DEFAULT NULL COMMENT '创建人ID',
  `crt_name` varchar(255) DEFAULT NULL COMMENT '创建人',
  `crt_host` varchar(255) DEFAULT NULL COMMENT '创建主机',
  `upd_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `upd_user` varchar(255) DEFAULT NULL COMMENT '最后更新人ID',
  `upd_name` varchar(255) DEFAULT NULL COMMENT '最后更新人',
  `upd_host` varchar(255) DEFAULT NULL COMMENT '最后更新主机',
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  `attr6` varchar(255) DEFAULT NULL,
  `attr7` varchar(255) DEFAULT NULL,
  `attr8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of base_group_type
-- ----------------------------
INSERT INTO `base_group_type` VALUES ('1', 'role', '角色类型', 'role', null, null, null, null, '2017-08-25 17:52:37', '1', 'Mr.AG', '127.0.0.1', null, null, null, null, null, null, null, null);
INSERT INTO `base_group_type` VALUES ('2', 'depart', '部门类型', '部门角色描述', null, null, null, null, '2017-08-25 17:52:43', '1', 'Mr.AG', '127.0.0.1', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for base_menu
-- ----------------------------
DROP TABLE IF EXISTS `base_menu`;
CREATE TABLE `base_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL COMMENT '路径编码',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `parent_id` int(11) NOT NULL COMMENT '父级节点',
  `href` varchar(255) DEFAULT NULL COMMENT '资源路径',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `type` char(10) DEFAULT NULL,
  `order_num` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `path` varchar(500) DEFAULT NULL COMMENT '菜单上下级关系',
  `enabled` char(1) DEFAULT NULL COMMENT '启用禁用',
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  `upd_time` datetime DEFAULT NULL,
  `upd_user` varchar(255) DEFAULT NULL,
  `upd_name` varchar(255) DEFAULT NULL,
  `upd_host` varchar(255) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  `attr6` varchar(255) DEFAULT NULL,
  `attr7` varchar(255) DEFAULT NULL,
  `attr8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of base_menu
-- ----------------------------
INSERT INTO `base_menu` VALUES ('1', 'userManager', '用户管理', '5', '/admin/user', 'account', 'menu', '0', '', '/adminSys/baseManager/userManager', null, null, null, null, null, '2017-09-05 21:06:51', '1', 'Mr.AG', '127.0.0.1', '_import(\'admin/user/index\')', null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('5', 'baseManager', '基础配置', '13', '/admin', 'jichupeizhi', 'dirt', '0', '', '/adminSys/baseManager', null, null, null, null, null, '2017-09-05 21:46:11', '1', 'Mr.AG', '127.0.0.1', 'Layout', null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('6', 'menuManager', '菜单管理', '5', '/admin/menu', 'caidanguanli', 'menu', '0', '', '/adminSys/baseManager/menuManager', null, null, null, null, null, '2017-09-05 21:10:25', '1', 'Mr.AG', '127.0.0.1', '_import(\'admin/menu/index\')', null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('7', 'groupManager', '角色权限管理', '5', '/admin/group', 'quanxianjiaoseguanli', 'menu', '0', '', '/adminSys/baseManager/groupManager', null, null, null, null, null, '2017-09-05 21:11:34', '1', 'Mr.AG', '127.0.0.1', '_import(\'admin/group/index\')', null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('8', 'groupTypeManager', '角色类型管理', '5', '/admin/groupType', 'group8', 'menu', '0', '', '/adminSys/baseManager/groupTypeManager', null, null, null, null, null, '2017-09-05 21:12:28', '1', 'Mr.AG', '127.0.0.1', '_import(\'admin/groupType/index\')', null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('13', 'adminSys', '权限管理', '-1', '/base', 'setting', 'dirt', '0', '', '/adminSys', null, null, null, null, null, '2017-09-28 12:09:22', '1', 'Mr.AG', '127.0.0.1', 'Layout', null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('27', 'gateLogManager', '操作日志', '5', '/admin/gateLog', 'caozuorizhi', 'dirt', '0', '', '/adminSys/baseManager/gateLogManager', null, '2017-07-01 00:00:00', '1', 'admin', '0:0:0:0:0:0:0:1', '2017-09-05 22:32:55', '1', 'Mr.AG', '127.0.0.1', '_import(\'admin/gateLog/index\')', null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('33', 'message', 'MQ消息管理', '-1', '/base', 'zhuizongshijian', 'dirt', '0', null, '/messageManage', null, '2017-11-23 16:25:04', '1', 'admin', '127.0.0.1', '2017-11-23 16:25:04', '1', 'admin', '127.0.0.1', null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('39', 'monitor', '监控中心', '-1', '/base', 'monitor', 'menu', '0', null, '/baidu', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('40', 'eureka', '注册中心监控', '39', 'http://10.180.4.221:8761/', 'jiankongzhongxin', 'http', '0', null, '/baidu/er', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('41', 'pinpoint', 'pinpoint', '39', 'http://10.180.4.223:28080', 'zhuizong', 'http', '0', null, '/baidu/pinpoint', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('48', 'one', '一级菜单', '-1', '/base', null, null, '0', null, '/one', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('49', 'two', '二级菜单', '48', '/two', null, 'dirt', '0', null, '/one/two', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('50', 'three', '三级页面', '49', '/two/three', null, 'dirt', '2', '', '/one/two/three', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('51', 'mq', '消息轨迹跟踪', '33', '/message', 'zhuizong', 'dirt', '0', null, '/message/mq', null, null, null, null, null, null, null, null, null, '_import(\'message/index\')', null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('52', 'getway', '网关管理', '-1', '/base', 'fuwuluyoupeizhicopy3', 'dirt', '0', null, '/getway', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('53', 'router', '路由管理', '52', null, 'luyou', null, '0', null, '/getway/router', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('54', 'serviceRouting', '服务路由', '53', '', 'fuwu', null, '0', null, '/getway/router/serviceRouting', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('55', 'ignoreUri', 'ignoreUri', '53', null, 'hulue', null, '0', null, '/getway/router/ignoreUri', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('56', 'filter', '过滤器管理', '52', null, 'guolv', null, '0', null, '/getway/filter', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('57', 'groovyFilter', '动态过滤器配置', '56', null, 'xiaolvdashitubiao33304', 'menu', '0', null, '/getway/filter/groovyFilter', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('58', 'auth', '认证管理', '-1', null, 'renzhengguanli', null, '0', null, '/auth', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('60', 'authprovider', '自定义认证', '58', null, 'zidingyi', null, '0', null, '/auth/authprovider', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('62', 'three-2', '三级页面2', '49', '/two/three-2', null, null, '1', null, '/one/two/three-2', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('63', 'serviceManager', '服务治理', '39', '/monitor/serviceManager', 'iconform', 'dirt', '0', null, '/baidu/index', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('66', 'task', '任务中心', '-1', '/base', 'renwujihua', 'dirt', '0', '描述内容', '/task', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('68', 'tasklist', '任务管理', '66', null, 'liebiaochakan', 'menu', '0', null, '/task/tasklist', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('69', 'tasklog', '任务跟踪', '66', '/task/tasklog', 'luyou', 'dirt', '0', null, '/task/tasklog', null, null, null, null, null, null, null, null, null, '_import(\'task/tasklog\')', null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for base_resource_authority
-- ----------------------------
DROP TABLE IF EXISTS `base_resource_authority`;
CREATE TABLE `base_resource_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authority_id` varchar(255) DEFAULT NULL COMMENT '角色ID',
  `authority_type` varchar(255) DEFAULT NULL COMMENT '角色类型',
  `resource_id` varchar(255) DEFAULT NULL COMMENT '资源ID',
  `resource_type` varchar(255) DEFAULT NULL COMMENT '资源类型',
  `parent_id` varchar(255) DEFAULT NULL,
  `path` varchar(2000) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  `attr6` varchar(255) DEFAULT NULL,
  `attr7` varchar(255) DEFAULT NULL,
  `attr8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1980 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of base_resource_authority
-- ----------------------------
INSERT INTO `base_resource_authority` VALUES ('287', '1', 'group', '5', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('290', '1', 'group', '11', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('291', '1', 'group', '12', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('294', '1', 'group', '5', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('297', '1', 'group', '11', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('298', '1', 'group', '12', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('300', '1', 'group', '12', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('302', '1', 'group', '11', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('303', '1', 'group', '13', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('304', '1', 'group', '14', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('305', '1', 'group', '15', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('307', '1', 'group', '11', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('308', '1', 'group', '12', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('309', '1', 'group', '13', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('310', '1', 'group', '14', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('312', '1', 'group', '15', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('313', '1', 'group', '16', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('314', '1', 'group', '17', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('315', '1', 'group', '18', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('317', '1', 'group', '20', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('318', '1', 'group', '21', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('319', '1', 'group', '22', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('349', '4', 'group', '9', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('371', '1', 'group', '23', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('372', '1', 'group', '24', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('373', '1', 'group', '27', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('374', '1', 'group', '28', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('375', '1', 'group', '23', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('378', '1', 'group', '5', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('380', '1', 'group', '11', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('381', '1', 'group', '14', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('382', '1', 'group', '13', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('383', '1', 'group', '15', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('384', '1', 'group', '12', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('385', '1', 'group', '24', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('387', '1', 'group', '27', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('388', '1', 'group', '16', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('389', '1', 'group', '18', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('390', '1', 'group', '17', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('392', '1', 'group', '20', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('393', '1', 'group', '28', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('394', '1', 'group', '22', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('395', '1', 'group', '21', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('396', '4', 'group', '23', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('397', '4', 'group', '9', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('398', '4', 'group', '27', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('399', '4', 'group', '24', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('400', '4', 'group', '28', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('401', '1', 'group', '30', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('402', '1', 'group', '30', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('403', '1', 'group', '31', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('421', '1', 'group', '31', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('422', '1', 'group', '30', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('423', '4', 'group', '31', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('424', '4', 'group', '30', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('437', '1', 'group', '33', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('438', '1', 'group', '34', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('439', '1', 'group', '35', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('440', '4', 'group', '32', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('464', '1', 'group', '30', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('465', '1', 'group', '31', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('466', '1', 'group', '30', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('467', '1', 'group', '31', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('468', '1', 'group', '30', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('469', '1', 'group', '31', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('470', '1', 'group', '30', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('471', '1', 'group', '31', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('472', '1', 'group', '40', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('492', '1', 'group', '30', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('493', '1', 'group', '31', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('494', '1', 'group', '40', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('516', '4', 'group', '41', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('517', '4', 'group', '30', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('518', '4', 'group', '31', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('519', '4', 'group', '40', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('611', '4', 'group', '42', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('612', '4', 'group', '36', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('628', '4', 'group', '13', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('629', '4', 'group', '5', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('630', '4', 'group', '1', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('631', '4', 'group', '6', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('632', '4', 'group', '7', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('633', '4', 'group', '8', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('634', '4', 'group', '27', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('635', '4', 'group', '9', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('636', '4', 'group', '24', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('637', '4', 'group', '22', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('638', '4', 'group', '23', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('639', '4', 'group', '25', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('640', '4', 'group', '26', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('641', '4', 'group', '28', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('666', '1', 'group', '41', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('690', '1', 'group', '42', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('691', '1', 'group', '44', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('692', '1', 'group', '45', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('710', '9', 'group', '42', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('711', '9', 'group', '43', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('712', '9', 'group', '44', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('713', '9', 'group', '45', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('718', '9', 'group', '42', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('719', '9', 'group', '44', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('720', '9', 'group', '45', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('721', '9', 'group', '43', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('722', '1', 'group', '41', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('812', '1', 'group', '19', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('849', '9', 'group', '1', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('850', '9', 'group', '13', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('851', '9', 'group', '14', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('852', '9', 'group', '-1', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('853', '9', 'group', '5', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('854', '9', 'group', '17', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('855', '9', 'group', '18', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('856', '9', 'group', '20', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1252', '14', 'group', '48', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1253', '14', 'group', '-1', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1254', '14', 'group', '49', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1255', '14', 'group', '50', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1326', '1', 'group', '42', 'uri', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1602', '11', 'group', '3', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1603', '11', 'group', '4', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1604', '11', 'group', '5', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1611', '11', 'group', '16', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1612', '11', 'group', '17', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1613', '11', 'group', '18', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1614', '11', 'group', '19', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1615', '11', 'group', '20', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1631', '10', 'group', '41', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1632', '10', 'group', '42', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1633', '10', 'group', '3', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1635', '10', 'group', '4', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1636', '10', 'group', '5', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1666', '1', 'group', '10', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1667', '1', 'group', '9', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1668', '10', 'group', '23', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1669', '1', 'group', '43', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1705', '10', 'group', '43', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1706', '10', 'group', '44', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1732', '10', 'group', '1', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1733', '10', 'group', '13', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1734', '10', 'group', '48', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1735', '10', 'group', '-1', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1736', '10', 'group', '49', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1737', '10', 'group', '5', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1738', '10', 'group', '50', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1739', '10', 'group', '52', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1740', '10', 'group', '53', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1741', '10', 'group', '54', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1796', '1', 'group', '4', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1826', '1', 'group', '49', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1879', '1', 'group', '46', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1880', '11', 'group', '33', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1881', '11', 'group', '66', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1882', '11', 'group', '68', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1883', '11', 'group', '69', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1884', '11', 'group', '-1', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1885', '11', 'group', '51', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1886', '11', 'group', '52', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1887', '11', 'group', '53', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1888', '11', 'group', '54', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1889', '11', 'group', '51', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1890', '11', 'group', '52', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1891', '1', 'group', '47', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1918', '1', 'group', '53', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1944', '1', 'group', '66', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1945', '1', 'group', '68', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1946', '1', 'group', '69', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1947', '1', 'group', '27', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1948', '1', 'group', '51', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1949', '1', 'group', '52', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1950', '1', 'group', '53', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1951', '1', 'group', '54', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1952', '1', 'group', '33', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1953', '1', 'group', '55', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1954', '1', 'group', '56', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1955', '1', 'group', '13', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1956', '1', 'group', '57', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1957', '1', 'group', '58', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1958', '1', 'group', '-1', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1959', '1', 'group', '39', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1960', '1', 'group', '1', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1961', '1', 'group', '5', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1962', '1', 'group', '6', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1963', '1', 'group', '7', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1964', '1', 'group', '8', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1965', '1', 'group', '60', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1966', '1', 'group', '40', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1967', '1', 'group', '41', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1968', '1', 'group', '63', 'menu', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1970', '15', 'group', '4', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1971', '15', 'group', '5', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1972', '15', 'group', '23', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1975', '1', 'group', '4', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1977', '1', 'group', '3', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1978', '1', 'group', '51', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `base_resource_authority` VALUES ('1979', '1', 'group', '52', 'button', '-1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for base_user
-- ----------------------------
DROP TABLE IF EXISTS `base_user`;
CREATE TABLE `base_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `mobile_phone` varchar(255) DEFAULT NULL,
  `tel_phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `type` char(1) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  `upd_time` datetime DEFAULT NULL,
  `upd_user` varchar(255) DEFAULT NULL,
  `upd_name` varchar(255) DEFAULT NULL,
  `upd_host` varchar(255) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  `attr6` varchar(255) DEFAULT NULL,
  `attr7` varchar(255) DEFAULT NULL,
  `attr8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of base_user
-- ----------------------------
INSERT INTO `base_user` VALUES ('1', 'admin', '$2a$12$S/yLlj9kzi5Dgsz97H4rAekxrPlk/10eXp1lUJcAVAx.2M9tOpWie', 'Mr.admin', '', null, '136', '136', '', '男', null, null, '微服务架构师', null, null, null, null, '2017-07-25 14:54:21', '1', 'Mr.AG', '0:0:0:0:0:0:0:1', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for gate_auth_provider
-- ----------------------------
DROP TABLE IF EXISTS `gate_auth_provider`;
CREATE TABLE `gate_auth_provider` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `src_url` varchar(100) DEFAULT NULL,
  `auth_service` varchar(200) DEFAULT NULL,
  `accept_type` varchar(20) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of gate_auth_provider
-- ----------------------------
INSERT INTO `gate_auth_provider` VALUES ('2', '/auth/web/account/token', 'http://ops-admin/api/user/username', 'user', null, null, null, null);
INSERT INTO `gate_auth_provider` VALUES ('5', '/thirdAuth/app/account', 'http://ops-admin/api/user/username', 'user', null, null, null, null);
INSERT INTO `gate_auth_provider` VALUES ('7', '/update/token/token2jwt', 'http://ops-admin/api/user/token2jwt', 'userAndPassword', null, null, null, null);
INSERT INTO `gate_auth_provider` VALUES ('8', '/appservice/api/action/AppUserInfoAction/appUserLogin.json', 'http://10.180.4.46:8080/appservice/api/action/AppUserInfoAction/appUserLogin.json', 'userAndPassword', null, null, null, null);
INSERT INTO `gate_auth_provider` VALUES ('9', '/appservice/api/action/AppUserInfoAction/queryUserInfoByToken.json', 'http://10.180.4.46:8080/appservice/api/action/AppUserInfoAction/queryUserInfoByToken.json', 'userAndPassword', null, null, null, null);

-- ----------------------------
-- Table structure for gate_base_element
-- ----------------------------
DROP TABLE IF EXISTS `gate_base_element`;
CREATE TABLE `gate_base_element` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `uri` varchar(255) DEFAULT NULL,
  `menu_id` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `path` varchar(2000) DEFAULT NULL,
  `method` varchar(10) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  `attr6` varchar(255) DEFAULT NULL,
  `attr7` varchar(255) DEFAULT NULL,
  `attr8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of gate_base_element
-- ----------------------------
INSERT INTO `gate_base_element` VALUES ('44', 'user:system', 'uri', '获取用户系统', '/api/user/un/{*}/system', '-1', null, null, 'GET', '', null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for gate_client
-- ----------------------------
DROP TABLE IF EXISTS `gate_client`;
CREATE TABLE `gate_client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `secret` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `locked` char(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  `upd_time` datetime DEFAULT NULL,
  `upd_user` varchar(255) DEFAULT NULL,
  `upd_name` varchar(255) DEFAULT NULL,
  `upd_host` varchar(255) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  `attr6` varchar(255) DEFAULT NULL,
  `attr7` varchar(255) DEFAULT NULL,
  `attr8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of gate_client
-- ----------------------------
INSERT INTO `gate_client` VALUES ('1', 'ace-gate', '123456', 'ace-gate', '0', '', null, '', '', '', '2017-07-07 21:51:32', '1', '管理员', '0:0:0:0:0:0:0:1', '', '', '', '', '', '', '', '');
INSERT INTO `gate_client` VALUES ('3', 'ace-admin', '123456', 'ace-admin', '0', '', null, null, null, null, '2017-07-06 21:42:17', '1', '管理员', '0:0:0:0:0:0:0:1', null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('4', 'user-center', '123456', 'user-center', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('5', 'ag-gate', '123456', 'ag-gate', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('6', 'ace-auth', '123456', 'ace-auth', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('8', 'admin-back', '2qOT1MH4', 'admin-back', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('10', 'admin-gate', 'J9p7holx', 'admin-gate', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('11', 'ace-config', 'DPVimEpy', 'ace-config', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('12', 'ace-monitor', 'oLMMIajd', 'ace-monitor', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('13', 'sleuth2-service', 'wlF1XH56', 'sleuth2-service', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('14', 'template-service', '8M7zbmpw', 'template-service', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('15', 'eureka-service', '5ZYdbo6o', 'eureka-service', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('16', 'config-service', 'ilwvlDw2', 'config-service', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('17', 'zipkin-service', '4GIFLF80', 'zipkin-service', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('18', 'gate-auth', '4WZ01kAf', 'gate-auth', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('19', 'gate-admin', 'COfhjNSO', 'gate-admin', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('20', 'back-admin', 'nIAMxWN7', 'back-admin', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('21', 'gate-server', '5BDNe6P0', 'gate-server', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('22', 'template1-service', 'cmttXuXt', 'template1-service', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('23', 'spring-cloud-eureka', 'mv1mJ2iG', 'spring-cloud-eureka', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('24', 'gate-service', 'a9rizC0O', 'gate-service', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('25', 'service-consumer', 'aTvN9QVk', 'service-consumer', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('26', 'spring-cloud-eureka3', 'mHBq7WrJ', 'spring-cloud-eureka3', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('27', 'auth-service', 'Qou9tjXx', 'auth-service', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('28', 'hystrix-dashboard-turbine', 'vn3UXiLk', 'hystrix-dashboard-turbine', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('29', 'monitor', '6M2DfbzL', 'monitor', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('30', 'server-config', 'GSUt5uGM', 'server-config', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('31', 'ops-mq', 'dLqusteI', 'ops-mq', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('32', 'ops-gate', 'uME2kiEB', 'ops-gate', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('33', 'ops-service', 'hT8pqBxu', 'ops-service', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('34', 'ops-admin', 'm7gnqI7V', 'ops-admin', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('35', 'ops-auth', 'waQ2ujlc', 'ops-auth', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('36', 'ops-wechat', 'OwAlAwQt', 'ops-wechat', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('37', 'dmc-service-task', 'C5hjbVq8', 'dmc-service-task', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client` VALUES ('38', 'ops-task', 'CMZebWOG', 'ops-task', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for gate_client_service
-- ----------------------------
DROP TABLE IF EXISTS `gate_client_service`;
CREATE TABLE `gate_client_service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `service_id` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `crt_host` varchar(255) DEFAULT NULL,
  `attr1` varchar(255) DEFAULT NULL,
  `attr2` varchar(255) DEFAULT NULL,
  `attr3` varchar(255) DEFAULT NULL,
  `attr4` varchar(255) DEFAULT NULL,
  `attr5` varchar(255) DEFAULT NULL,
  `attr6` varchar(255) DEFAULT NULL,
  `attr7` varchar(255) DEFAULT NULL,
  `attr8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of gate_client_service
-- ----------------------------
INSERT INTO `gate_client_service` VALUES ('20', '44', '1', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client_service` VALUES ('21', '4', '5', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client_service` VALUES ('22', '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client_service` VALUES ('23', '3', '6', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client_service` VALUES ('24', '6', '1', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `gate_client_service` VALUES ('25', '6', '1', null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for gate_filter
-- ----------------------------
DROP TABLE IF EXISTS `gate_filter`;
CREATE TABLE `gate_filter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `orders` varchar(10) DEFAULT NULL,
  `exec_setting` varchar(20) DEFAULT NULL,
  `canary_setting` varchar(20) DEFAULT NULL,
  `version` varchar(20) DEFAULT NULL,
  `script` text,
  `update_date` datetime DEFAULT NULL,
  `service_name` varchar(50) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of gate_filter
-- ----------------------------
INSERT INTO `gate_filter` VALUES ('11', 'bitcoin', 'pre', '1', 'false', 'false', '1', 'package com.yonyou.microservice.gate.server.filter;\n\nimport java.util.Enumeration;\n\nimport javax.servlet.http.HttpServletRequest;\n\nimport org.apache.log4j.Logger;\nimport org.springframework.stereotype.Component;\n\nimport com.alibaba.fastjson.JSON;\nimport com.netflix.zuul.ZuulFilter;\nimport com.netflix.zuul.context.RequestContext;\nimport com.yonyou.microservice.gate.common.msg.TokenErrorResponse;\n\nimport lombok.extern.slf4j.Slf4j;\n\n@Component\npublic class BitcoinFilter extends ZuulFilter {\n	private static Logger logger=Logger.getLogger(BitcoinFilter.class);\n    \n    \n    public BitcoinFilter(){\n    	logger.info(\"--bitcoin对象创建\");\n    }\n\n    @Override\n    public String filterType() {\n        return \"pre\";\n    }\n\n    @Override\n    public int filterOrder() {\n        return 1;\n    }\n\n    @Override\n    public boolean shouldFilter() {\n        return true;\n    }\n\n    @Override\n    public Object run() {\n    	logger.info(\"--bitcoin.run(),进入网关\");\n        RequestContext ctx = RequestContext.getCurrentContext();\n        HttpServletRequest request = ctx.getRequest();\n        Enumeration<String> names=request.getParameterNames();\n        while(names.hasMoreElements()){\n        	String name=names.nextElement();\n        	String[] values=request.getParameterValues(name);\n        	for(int i=0;i<values.length;i++){\n        		String value=values[i];\n        		if(value.contains(\"bitcoin\")){\n                    setFailedRequest(JSON.toJSONString(new TokenErrorResponse(\"i like bitcoin\")),200);\n\n    	logger.info(\"--hit me\");\n                    return null;\n        		}\n        	}\n        }\n\n    	logger.info(\"--bitcoin out\");\n        return null;\n    }\n    private void setFailedRequest(String body, int code) {\n        RequestContext ctx = RequestContext.getCurrentContext();\n        ctx.setResponseStatusCode(code);\n        if (ctx.getResponseBody() == null) {\n            ctx.setResponseBody(body);\n            ctx.setSendZuulResponse(false);\n        }\n    }\n}', '2018-01-05 17:53:50', 'demo', '1', '2018-01-05 17:39:23', '1');

-- ----------------------------
-- Table structure for gate_ignore_uri
-- ----------------------------
DROP TABLE IF EXISTS `gate_ignore_uri`;
CREATE TABLE `gate_ignore_uri` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uri` varchar(100) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of gate_ignore_uri
-- ----------------------------
INSERT INTO `gate_ignore_uri` VALUES ('1', '/static', null, null, null, null);
INSERT INTO `gate_ignore_uri` VALUES ('2', '/auth/jwt', null, null, null, null);
INSERT INTO `gate_ignore_uri` VALUES ('6', '/appservice/template', null, null, null, null);
INSERT INTO `gate_ignore_uri` VALUES ('9', '/update/token/token2jwt', null, null, null, null);
INSERT INTO `gate_ignore_uri` VALUES ('10', '/proxy', null, null, null, null);
INSERT INTO `gate_ignore_uri` VALUES ('11', '/appservice/api/action/AppUserInfoAction/queryUserInfoByToken', null, null, null, null);
INSERT INTO `gate_ignore_uri` VALUES ('12', '/appservice/api/action/AppUserInfoAction/appUserLogin.json', null, null, null, null);

-- ----------------------------
-- Table structure for gate_log
-- ----------------------------
DROP TABLE IF EXISTS `gate_log`;
CREATE TABLE `gate_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `menu` varchar(255) DEFAULT NULL COMMENT '菜单',
  `opt` varchar(255) DEFAULT NULL COMMENT '操作',
  `uri` varchar(255) DEFAULT NULL COMMENT '资源路径',
  `crt_time` datetime DEFAULT NULL COMMENT '操作时间',
  `crt_user` varchar(255) DEFAULT NULL COMMENT '操作人ID',
  `crt_name` varchar(255) DEFAULT NULL COMMENT '操作人',
  `crt_host` varchar(255) DEFAULT NULL COMMENT '操作主机',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7660 DEFAULT CHARSET=utf8mb4;


-- ----------------------------
-- Table structure for gate_routes
-- ----------------------------
DROP TABLE IF EXISTS `gate_routes`;
CREATE TABLE `gate_routes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(100) DEFAULT NULL,
  `service_id` varchar(100) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `strip_prefix` varchar(10) DEFAULT NULL,
  `retryable` varchar(10) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of gate_routes
-- ----------------------------
INSERT INTO `gate_routes` VALUES ('11', '/thirdAuth/**', 'ops-auth', '', 'false', 'false', null, null, null, null);
INSERT INTO `gate_routes` VALUES ('18', '/update/token/**', 'ops-auth', null, 'false', 'false', null, null, null, null);
INSERT INTO `gate_routes` VALUES ('19', '/appservice/**', null, 'http://10.180.4.46:8080', 'false', 'false', null, null, null, null);
INSERT INTO `gate_routes` VALUES ('20', '/appservice/api/action/AppUserInfoAction/queryUserInfoByToken.json', 'ops-auth', null, 'false', 'false', null, null, null, null);
INSERT INTO `gate_routes` VALUES ('23', '/appservice/api/action/AppUserInfoAction/appUserLogin.json', 'ops-auth', null, 'false', 'false', null, null, null, null);

-- ----------------------------
-- Table structure for wechat_menu_url
-- ----------------------------
DROP TABLE IF EXISTS `wechat_menu_url`;
CREATE TABLE `wechat_menu_url` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) DEFAULT NULL COMMENT '菜单proxy/{serviceNo}/{id}配置中的{id}值',
  `url` varchar(200) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of wechat_menu_url
-- ----------------------------
INSERT INTO `wechat_menu_url` VALUES ('1', 'person', 'http://www.baidu.com', null, null, null, null);
INSERT INTO `wechat_menu_url` VALUES ('2', 'openid2user', 'http://ops-admin/api/user/username/test', null, null, null, null);

-- ----------------------------
-- Table structure for wechat_office_account
-- ----------------------------
DROP TABLE IF EXISTS `wechat_office_account`;
CREATE TABLE `wechat_office_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `service_no` varchar(50) DEFAULT NULL COMMENT '服务号编码,/proxy/{serviceNo}/{id}中的serviceNo',
  `appid` varchar(100) DEFAULT NULL,
  `appsecret` varchar(100) DEFAULT NULL,
  `token` varchar(100) DEFAULT NULL,
  `menu_str` varchar(100) DEFAULT NULL,
  `aeskey` varchar(150) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='微信服务号信息表';

-- ----------------------------
-- Records of wechat_office_account
-- ----------------------------
INSERT INTO `wechat_office_account` VALUES ('1', 'gmmc', '65yu7hg63kl89cj47ls903', 'hh7yks', '22', '22', '2444', null, null, null, null);
