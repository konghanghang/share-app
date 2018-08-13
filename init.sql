DROP DATABASE IF EXISTS test;
USE test;

DROP TABLE IF EXISTS t_user;
create table t_user
(
  user_id     int auto_increment
  comment '用户id',
  ref_user_id  varchar(32)            not null
  comment 'refId',
  username   varchar(12) default '' not null
  comment '用户名',
  nickname   varchar(12)            null
  comment '用户昵称',
  salt       varchar(128)           not null
  comment '加密盐',
  password   varchar(128)           null
  comment '用户密码',
  sex        tinyint                null
  comment '性别',
  email      varchar(68)            null
  comment '邮箱',
  head_image  varchar(50)            null,
  create_ip   varchar(15)            null
  comment '创建ip',
  create_date bigint(11) default '0' null
  comment '创建时间',
  constraint t_user_ref_user_id_uindex
  unique (ref_user_id),
  constraint t_user_user_id_uindex
  unique (user_id)
);

alter table t_user
  add primary key (user_id);