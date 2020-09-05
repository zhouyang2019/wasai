drop table if exists demo_enum;
create table demo_enum
(
    id       bigint auto_increment primary key,
    name     varchar(32) not null comment '名字',
    int_type tinyint     not null default 1 comment 'int类型枚举值',
    str_type varchar(16) not null default 'ACTIVE' comment 'string类型枚举值'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '枚举demo';