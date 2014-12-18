DROP DATABASE IF EXISTS `oa`;
CREATE DATABASE `oa` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

use `oa`;


DROP TABLE IF EXISTS MANAGER_ROLE CASCADE;
CREATE TABLE `MANAGER_ROLE` (
  `ID` int(11) NOT NULL auto_increment,
  `EMAIL` varchar(255) NOT NULL COMMENT '邮箱',
  `ROLE_ID` int(11) NOT NULL COMMENT '角色ID',
  `CREATETIME` datetime default '0000-00-00 00:00:00' COMMENT '创建时间',
  `UPDATETIME` datetime default '2000-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY  (`ID`),
  index(EMAIL)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色';

insert into MANAGER_ROLE(`EMAIL`, `ROLE_ID`, `CREATETIME`, `UPDATETIME`)
values('qiu_sen@126.com', 1, '2012-08-01 08:08:08', '2012-08-01 08:08:08')



DROP TABLE IF EXISTS ROLE CASCADE;
CREATE TABLE `ROLE` (
  `ID` int(11) NOT NULL auto_increment,
  `ROLENAME` varchar(20) NOT NULL COMMENT '角色名称',
  `STATUS` int(2) NOT NULL COMMENT '角色状态',
  `CREATETIME` datetime default '0000-00-00 00:00:00' COMMENT '创建时间',
  `RIGHTS` varchar(255) default '' COMMENT '权限',
  PRIMARY KEY  (`ID`),
  index(ROLENAME)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

insert into ROLE(`ROLENAME`,`STATUS`,`CREATETIME`,`RIGHTS`)values('OA系统管理员',1,'2012-08-01 08:08:08',',1,2,3,4,');

DROP TABLE IF EXISTS MENU CASCADE;
CREATE TABLE `MENU` (
  `ID` int(11) NOT NULL auto_increment,
  `MENUNAME` varchar(255) NOT NULL COMMENT '菜单名称',
  `STATUS` int(2) NOT NULL COMMENT '状态',
  `ORDERNUM` int(11) NOT NULL COMMENT '排序',
  `CREATETIME` datetime default '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY  (`id`),
  index(MENUNAME)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单';

insert into MENU(`MENUNAME`,`STATUS`,`ORDERNUM`,`CREATETIME`)values('业务功能',1,1,'2012-08-08 08:08:08');
insert into MENU(`MENUNAME`,`STATUS`,`ORDERNUM`,`CREATETIME`)values('系统菜单',1,10,'2012-08-08 08:08:08');

DROP TABLE IF EXISTS CATALOG CASCADE;
CREATE TABLE `CATALOG` (
  `ID` int(11) NOT NULL auto_increment,
  `CATALOGNAME` varchar(255) NOT NULL COMMENT '目录名称',
  `STATUS` int(2) NOT NULL COMMENT '状态',
  `ORDERNUM` int(11) NOT NULL COMMENT '排序',
  `MENU_ID` int(11) NOT NULL COMMENT '所属菜单ID',
  `CREATETIME` datetime default '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY  (`id`),
  index(CATALOGNAME)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='目录';

insert into CATALOG(`CATALOGNAME`,`STATUS`,`ORDERNUM`,`MENU_ID`,`CREATETIME`)values('业务功能',1,1,1,'2012-08-08 08:08:08');
insert into CATALOG(`CATALOGNAME`,`STATUS`,`ORDERNUM`,`MENU_ID`,`CREATETIME`)values('系统管理',1,1,2,'2012-08-08 08:08:08');
insert into CATALOG(`CATALOGNAME`,`STATUS`,`ORDERNUM`,`MENU_ID`,`CREATETIME`)values('模块管理',1,2,2,'2012-08-08 08:08:08');
insert into CATALOG(`CATALOGNAME`,`STATUS`,`ORDERNUM`,`MENU_ID`,`CREATETIME`)values('用户中心',1,3,2,'2012-08-08 08:08:08');
insert into CATALOG(`CATALOGNAME`,`STATUS`,`ORDERNUM`,`MENU_ID`,`CREATETIME`)values('地域管理',1,4,2,'2012-08-08 08:08:08');
insert into CATALOG(`CATALOGNAME`,`STATUS`,`ORDERNUM`,`MENU_ID`,`CREATETIME`)values('流程管理',1,5,2,'2012-08-08 08:08:08');

DROP TABLE IF EXISTS MODULE CASCADE;
CREATE TABLE `MODULE` (
  `ID` int(11) NOT NULL auto_increment,
  `MODULENAME` varchar(20) NOT NULL COMMENT '模块名称',
  `MODULEURL` varchar(100) NOT NULL COMMENT '模块URL',
  `MODULEACT` varchar(100) NOT NULL COMMENT '模块Action',
  `CATALOG_ID` int(11) NOT NULL COMMENT '所属目录ID',
  `STATUS` int(2) NOT NULL COMMENT '模块状态',
  `CREATETIME` datetime default '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模块';

insert into MODULE(`MODULENAME`,`MODULEURL`,`MODULEACT`,`CATALOG_ID`,`STATUS`,`CREATETIME`)values('用户角色','/admin/managerRole','managerRoleAction',2,1,'2012-08-08 08:08:08');
insert into MODULE(`MODULENAME`,`MODULEURL`,`MODULEACT`,`CATALOG_ID`,`STATUS`,`CREATETIME`)values('角色管理','/admin/role','roleAction',2,1,'2012-08-01 08:08:08');
insert into MODULE(`MODULENAME`,`MODULEURL`,`MODULEACT`,`CATALOG_ID`,`STATUS`,`CREATETIME`)values('菜单管理','/admin/menu','menuAction',3,1,'2012-08-01 08:08:08');
insert into MODULE(`MODULENAME`,`MODULEURL`,`MODULEACT`,`CATALOG_ID`,`STATUS`,`CREATETIME`)values('目录管理','/admin/catalog','catalogAction',3,1,'2012-08-08 08:08:08');
insert into MODULE(`MODULENAME`,`MODULEURL`,`MODULEACT`,`CATALOG_ID`,`STATUS`,`CREATETIME`)values('模块管理','/admin/module','moduleAction',3,1,'2012-08-08 08:08:08');
insert into MODULE(`MODULENAME`,`MODULEURL`,`MODULEACT`,`CATALOG_ID`,`STATUS`,`CREATETIME`)values('日志管理','/admin/logs','logsAction',1,1,'2012-08-01 08:08:08');
insert into MODULE(`MODULENAME`,`MODULEURL`,`MODULEACT`,`CATALOG_ID`,`STATUS`,`CREATETIME`)values('流程部署','/admin/process','processAction',4,1,'2012-08-01 08:08:08');

insert into MODULE(`MODULENAME`,`MODULEURL`,`MODULEACT`,`CATALOG_ID`,`STATUS`,`CREATETIME`)values('字典管理','/admin/dict','dictAction',1,1,'2012-08-01 08:08:08');
insert into MODULE(`MODULENAME`,`MODULEURL`,`MODULEACT`,`CATALOG_ID`,`STATUS`,`CREATETIME`)values('报表','/admin/report','reportAction',1,1,'2012-08-01 08:08:08');

insert into MODULE(`MODULENAME`,`MODULEURL`,`MODULEACT`,`CATALOG_ID`,`STATUS`,`CREATETIME`)values('省','/admin/province','provinceAction',1,1,'2012-08-01 08:08:08');
insert into MODULE(`MODULENAME`,`MODULEURL`,`MODULEACT`,`CATALOG_ID`,`STATUS`,`CREATETIME`)values('市','/admin/city','cityAction',1,1,'2012-08-01 08:08:08');
insert into MODULE(`MODULENAME`,`MODULEURL`,`MODULEACT`,`CATALOG_ID`,`STATUS`,`CREATETIME`)values('区','/admin/area','areaAction',1,1,'2012-08-01 08:08:08');


DROP TABLE IF EXISTS LOGS CASCADE;
CREATE TABLE `LOGS` (
  `ID` int(11) NOT NULL auto_increment,
  `EMAIL` varchar(255) NOT NULL COMMENT '邮箱',
  `NICKNAME` varchar(255) default '' COMMENT '昵称',
  `IP` varchar(100) default '' COMMENT 'IP',
  `ACT` varchar(255) default '' COMMENT '操作标识：login、logoff，……',
  `OPTTIME` datetime default '0000-00-00 00:00:00' COMMENT '操作时间',
  `CONTENT` varchar(2000) default '' COMMENT '操作内容',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志';


DROP TABLE IF EXISTS DICT CASCADE;
CREATE TABLE `DICT` (
  `ID` int(11) NOT NULL auto_increment,
  `CODE` varchar(255) NOT NULL COMMENT '编码',
  `NAME` varchar(255) NOT NULL COMMENT '名称',
  `GROUP` varchar(255) NOT NULL COMMENT '数据组',
  `VALUE` varchar(255) NOT NULL COMMENT '值',
  `CREATEUSER` varchar(255) NOT NULL COMMENT '创建人',
  `CREATETIME` datetime default '2000-01-01 00:00:00' COMMENT '创建时间',
  `UPDATEUSER` varchar(255) default NULL COMMENT '修改人',
  `UPDATETIME` datetime default '2000-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典';

