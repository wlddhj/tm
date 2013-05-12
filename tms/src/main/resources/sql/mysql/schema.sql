
    drop table if exists t_sys_menu;

    drop table if exists t_sys_menu_perm_rela;

    drop table if exists t_sys_permission;

    drop table if exists t_sys_resource;

    drop table if exists t_sys_resource_perm_rela;

    drop table if exists t_sys_role;

    drop table if exists t_sys_role_perm_rela;

    drop table if exists t_task;

    drop table if exists t_user;

    create table t_sys_menu (
        id bigint not null auto_increment,
        enable_flg bit not null,
        menu_name varchar(255),
        remark varchar(255),
        url varchar(255),
        parent_id bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    create table t_sys_menu_perm_rela (
        id bigint not null auto_increment,
        menu_id bigint,
        permission_id bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    create table t_sys_permission (
        id bigint not null auto_increment,
        perm_cd varchar(255),
        perm_name varchar(255),
        remark varchar(255),
        primary key (id)
    ) ENGINE=InnoDB;

    create table t_sys_resource (
        id bigint not null auto_increment,
        enable_flg bit not null,
        remark varchar(255),
        url varchar(255),
        primary key (id)
    ) ENGINE=InnoDB;

    create table t_sys_resource_perm_rela (
        id bigint not null auto_increment,
        permission_id bigint,
        resource_id bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    create table t_sys_role (
        id bigint not null auto_increment,
        remark varchar(255),
        role_cd varchar(255),
        role_name varchar(255),
        primary key (id)
    ) ENGINE=InnoDB;

    create table t_sys_role_perm_rela (
        id bigint not null auto_increment,
        permission_id bigint,
        role_id bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    create table t_task (
        id bigint not null auto_increment,
        description varchar(255),
        title varchar(255),
        user_id bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    create table t_user (
        id bigint not null auto_increment,
        login_name varchar(255),
        name varchar(255),
        password varchar(255),
        register_date datetime,
        roles varchar(255),
        salt varchar(255),
        primary key (id)
    ) ENGINE=InnoDB;

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

    alter table t_task 
        add index FKCB631670D86B3F0 (user_id), 
        add constraint FKCB631670D86B3F0 
        foreign key (user_id) 
        references t_user (id);
