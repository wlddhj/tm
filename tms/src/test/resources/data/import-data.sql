insert into t_task (id, title, description, user_id) values(1, 'Study PlayFramework 2.0','http://www.playframework.org/', 2);
insert into t_task (id, title, description, user_id) values(2, 'Study Grails 2.0','http://www.grails.org/', 2);
insert into t_task (id, title, description, user_id) values(3, 'Try SpringFuse','http://www.springfuse.com/', 2);
insert into t_task (id, title, description, user_id) values(4, 'Try Spring Roo','http://www.springsource.org/spring-roo', 2);
insert into t_task (id, title, description, user_id) values(5, 'Release SpringSide 4.0','As soon as posibble.', 2);

insert into t_user (id, login_name, name, password, salt, roles, register_date) values(1,'admin','Admin','691b14d79bf0fa2215f155235df5e670b64394cc','7efbd59d9741d34f','admin','2012-06-04 01:00:00');
insert into t_user (id, login_name, name, password, salt, roles, register_date) values(2,'user','Calvin','2488aa0c31c624687bd9928e0a5d29e7d1ed520b','6d65d24122c30500','user','2012-06-04 02:00:00');


insert into t_sys_menu (id,enable_flg,menu_name,remark,url,parent_id) values(1,true,'系统管理','系统管理备注','',0);
insert into t_sys_menu (id,enable_flg,menu_name,remark,url,parent_id) values(2,true,'资源管理','资源管理备注','/admin/resource',1);
insert into t_sys_menu (id,enable_flg,menu_name,remark,url,parent_id) values(3,true,'菜单管理','菜单管理备注','/admin/menu',1);
insert into t_sys_menu (id,enable_flg,menu_name,remark,url,parent_id) values(4,true,'组织管理','组织管理备注','',0);
insert into t_sys_menu (id,enable_flg,menu_name,remark,url,parent_id) values(5,true,'人员管理','机构管理备注','/admin/user',4);


