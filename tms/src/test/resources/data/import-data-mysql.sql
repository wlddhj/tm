
insert into t_sys_user (id, login_name, name, password, salt, register_date) values(1,'admin','Admin','691b14d79bf0fa2215f155235df5e670b64394cc','7efbd59d9741d34f','2012-06-04 01:00:00');
insert into t_sys_user (id, login_name, name, password, salt, register_date) values(2,'user','Calvin','2488aa0c31c624687bd9928e0a5d29e7d1ed520b','6d65d24122c30500','2012-06-04 02:00:00');

insert into t_task (id, title, description, user_id) values(1, 'Study PlayFramework 2.0','http://www.playframework.org/', 2);
insert into t_task (id, title, description, user_id) values(2, 'Study Grails 2.0','http://www.grails.org/', 2);
insert into t_task (id, title, description, user_id) values(3, 'Try SpringFuse','http://www.springfuse.com/', 2);
insert into t_task (id, title, description, user_id) values(4, 'Try Spring Roo','http://www.springsource.org/spring-roo', 2);
insert into t_task (id, title, description, user_id) values(5, 'Release SpringSide 4.0','As soon as posibble.', 2);


insert into t_sys_menu (id,enable_flg,menu_name,remark,url,parent_id) values(1,1,'系统管理','系统管理备注','',null);
insert into t_sys_menu (id,enable_flg,menu_name,remark,url,parent_id) values(2,1,'资源管理','资源管理备注','/admin/resource',1);
insert into t_sys_menu (id,enable_flg,menu_name,remark,url,parent_id) values(3,1,'菜单管理','菜单管理备注','/admin/menu',1);
insert into t_sys_menu (id,enable_flg,menu_name,remark,url,parent_id) values(4,1,'组织管理','组织管理备注','',null);
insert into t_sys_menu (id,enable_flg,menu_name,remark,url,parent_id) values(5,1,'人员管理','机构管理备注','/admin/user',4);

INSERT INTO t_sys_role (id, role_cd, role_name) VALUES (1, 'admin', '管理员');
INSERT INTO t_sys_role (id, role_cd, role_name) VALUES (2, 'user', '普通用户');
INSERT INTO t_sys_role (id, role_cd, role_name) VALUES (3, 'superadmin', '超级管理员');

INSERT INTO t_sys_permission (id, perm_cd, perm_name, remark) VALUES (1, 'user:create', '新增用户', '3333');
INSERT INTO t_sys_permission (id, perm_cd, perm_name, remark) VALUES (2, 'user:delete', '删除用户', '');
INSERT INTO t_sys_permission (id, perm_cd, perm_name, remark) VALUES (3, 'user:update', '修改用户', '');

insert into t_sys_role_user_rela(user_id,role_id)value(1,1);
insert into t_sys_role_user_rela(user_id,role_id)value(2,2);
