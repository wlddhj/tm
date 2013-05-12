
    alter table t_app_dict_data 
        drop 
        foreign key FKD7E2756A5D86820A;

    alter table t_sys_dept 
        drop 
        foreign key FK2B4CA3E2D6B950AC;

    alter table t_sys_menu 
        drop 
        foreign key FK2B50BAFCD6BD67C6;

    alter table t_sys_menu_perm_rela 
        drop 
        foreign key FKD14F215493FC22F1;

    alter table t_sys_menu_perm_rela 
        drop 
        foreign key FKD14F2154BFC13FB1;

    alter table t_sys_resource_perm_rela 
        drop 
        foreign key FK19B08AC3BFC13FB1;

    alter table t_sys_resource_perm_rela 
        drop 
        foreign key FK19B08AC32BFC4D11;

    alter table t_sys_role_perm_rela 
        drop 
        foreign key FK2E13DBABAD6AB791;

    alter table t_sys_role_perm_rela 
        drop 
        foreign key FK2E13DBABBFC13FB1;

    alter table t_sys_role_user_rela 
        drop 
        foreign key FK1A91B3B0AD6AB791;

    alter table t_sys_role_user_rela 
        drop 
        foreign key FK1A91B3B052957B71;

    alter table t_sys_user 
        drop 
        foreign key FK2B549168B7FBB131;

    alter table t_task 
        drop 
        foreign key FKCB63167052957B71;

    drop table if exists t_app_dict_data;

    drop table if exists t_app_dict_type;

    drop table if exists t_sys_dept;

    drop table if exists t_sys_menu;

    drop table if exists t_sys_menu_perm_rela;

    drop table if exists t_sys_permission;

    drop table if exists t_sys_resource;

    drop table if exists t_sys_resource_perm_rela;

    drop table if exists t_sys_role;

    drop table if exists t_sys_role_perm_rela;

    drop table if exists t_sys_role_user_rela;

    drop table if exists t_sys_user;

    drop table if exists t_task;

    create table t_app_dict_data (
        id bigint not null auto_increment,
        created_date datetime,
        updated_date datetime,
        dict_cd varchar(50),
        dict_name varchar(255),
        disp_order_no decimal(12,0),
        i18n varchar(255),
        remark varchar(255),
        dict_type_id bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    create table t_app_dict_type (
        id bigint not null auto_increment,
        created_date datetime,
        updated_date datetime,
        dict_type_cd varchar(255),
        dict_type_name varchar(255),
        disp_order_no decimal(12,0),
        remark varchar(255),
        primary key (id)
    ) ENGINE=InnoDB;

    create table t_sys_dept (
        id bigint not null auto_increment,
        created_date datetime,
        updated_date datetime,
        dept_name varchar(255),
        disp_order_no decimal(12,0),
        enable_flg bit,
        remark varchar(255),
        parent_id bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    create table t_sys_menu (
        id bigint not null auto_increment,
        created_date datetime,
        updated_date datetime,
        enable_flg bit,
        menu_name varchar(255),
        remark varchar(255),
        url varchar(255),
        parent_id bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    create table t_sys_menu_perm_rela (
        menu_id bigint not null,
        permission_id bigint not null
    ) ENGINE=InnoDB;

    create table t_sys_permission (
        id bigint not null auto_increment,
        created_date datetime,
        updated_date datetime,
        perm_cd varchar(255),
        perm_name varchar(255),
        remark varchar(255),
        primary key (id)
    ) ENGINE=InnoDB;

    create table t_sys_resource (
        id bigint not null auto_increment,
        created_date datetime,
        updated_date datetime,
        enable_flg bit,
        remark varchar(255),
        url varchar(255),
        primary key (id)
    ) ENGINE=InnoDB;

    create table t_sys_resource_perm_rela (
        resource_id bigint not null,
        permission_id bigint not null
    ) ENGINE=InnoDB;

    create table t_sys_role (
        id bigint not null auto_increment,
        created_date datetime,
        updated_date datetime,
        remark varchar(255),
        role_cd varchar(255),
        role_name varchar(255),
        primary key (id)
    ) ENGINE=InnoDB;

    create table t_sys_role_perm_rela (
        role_id bigint not null,
        permission_id bigint not null
    ) ENGINE=InnoDB;

    create table t_sys_role_user_rela (
        user_id bigint not null,
        role_id bigint not null
    ) ENGINE=InnoDB;

    create table t_sys_user (
        id bigint not null auto_increment,
        created_date datetime,
        updated_date datetime,
        disp_order_no decimal(12,0),
        enable_flg bit,
        login_name varchar(255),
        name varchar(255),
        password varchar(255),
        remark varchar(255),
        salt varchar(255),
        dept_id bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    create table t_task (
        id bigint not null auto_increment,
        created_date datetime,
        updated_date datetime,
        description varchar(255),
        title varchar(255),
        user_id bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    alter table t_app_dict_data 
        add index FKD7E2756A5D86820A (dict_type_id), 
        add constraint FKD7E2756A5D86820A 
        foreign key (dict_type_id) 
        references t_app_dict_type (id);

    alter table t_sys_dept 
        add index FK2B4CA3E2D6B950AC (parent_id), 
        add constraint FK2B4CA3E2D6B950AC 
        foreign key (parent_id) 
        references t_sys_dept (id);

    alter table t_sys_menu 
        add index FK2B50BAFCD6BD67C6 (parent_id), 
        add constraint FK2B50BAFCD6BD67C6 
        foreign key (parent_id) 
        references t_sys_menu (id);

    alter table t_sys_menu_perm_rela 
        add index FKD14F215493FC22F1 (menu_id), 
        add constraint FKD14F215493FC22F1 
        foreign key (menu_id) 
        references t_sys_menu (id);

    alter table t_sys_menu_perm_rela 
        add index FKD14F2154BFC13FB1 (permission_id), 
        add constraint FKD14F2154BFC13FB1 
        foreign key (permission_id) 
        references t_sys_permission (id);

    alter table t_sys_resource_perm_rela 
        add index FK19B08AC3BFC13FB1 (permission_id), 
        add constraint FK19B08AC3BFC13FB1 
        foreign key (permission_id) 
        references t_sys_permission (id);

    alter table t_sys_resource_perm_rela 
        add index FK19B08AC32BFC4D11 (resource_id), 
        add constraint FK19B08AC32BFC4D11 
        foreign key (resource_id) 
        references t_sys_resource (id);

    alter table t_sys_role_perm_rela 
        add index FK2E13DBABAD6AB791 (role_id), 
        add constraint FK2E13DBABAD6AB791 
        foreign key (role_id) 
        references t_sys_role (id);

    alter table t_sys_role_perm_rela 
        add index FK2E13DBABBFC13FB1 (permission_id), 
        add constraint FK2E13DBABBFC13FB1 
        foreign key (permission_id) 
        references t_sys_permission (id);

    alter table t_sys_role_user_rela 
        add index FK1A91B3B0AD6AB791 (role_id), 
        add constraint FK1A91B3B0AD6AB791 
        foreign key (role_id) 
        references t_sys_role (id);

    alter table t_sys_role_user_rela 
        add index FK1A91B3B052957B71 (user_id), 
        add constraint FK1A91B3B052957B71 
        foreign key (user_id) 
        references t_sys_user (id);

    alter table t_sys_user 
        add index FK2B549168B7FBB131 (dept_id), 
        add constraint FK2B549168B7FBB131 
        foreign key (dept_id) 
        references t_sys_dept (id);

    alter table t_task 
        add index FKCB63167052957B71 (user_id), 
        add constraint FKCB63167052957B71 
        foreign key (user_id) 
        references t_sys_user (id);
