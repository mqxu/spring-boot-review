drop table if exists message;
create table message
(
    msg_id      int         not null auto_increment,
    msg_text    varchar(32) not null,
    msg_summary varchar(32) default null,
    primary key (msg_id)
) engine = InnoDB
  auto_increment = 1
  default charset = utf8