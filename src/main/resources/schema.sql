create table clients
(
    id           bigserial primary key,
    created_by   varchar(50),
    created_when timestamp,
    is_deleted   boolean default false,
    deleted_by   varchar(50),
    deleted_when timestamp,

    full_name    VARCHAR(50) not null,
    phone        VARCHAR(50) not null
);

create table employees_positions
(
    id           bigserial primary key,
    created_by   varchar(50),
    created_when timestamp,
    is_deleted   boolean default false,
    deleted_by   varchar(50),
    deleted_when timestamp,

    title        varchar(50) not null unique,
    description  varchar(100),
    role         varchar(50) unique
);

create table employees
(
    id           bigserial primary key,
    created_by   varchar(50),
    created_when timestamp,
    is_deleted   boolean default false,
    deleted_by   varchar(50),
    deleted_when timestamp,

    login        varchar(50)  not null unique,
    password     varchar(50)  not null,

    full_name    VARCHAR(50)  not null,
    phone        VARCHAR(50)  not null,
    address      VARCHAR(250) not null,
    birth_date   DATE         not null,
    position_id  bigint       not null,

    constraint fk_employee_position_id foreign key (position_id) references employees_positions (id)
);

create table clients_orders
(
    id             bigserial primary key,
    created_by     varchar(50),
    created_when   timestamp,
    is_deleted     boolean default false,
    deleted_by     varchar(50),
    deleted_when   timestamp,

    client_id      bigint not null,
    description    text,
    deadline       date,
    price          integer,
    employee_id    bigint,

    is_completed   boolean default false,
    completed_when timestamp,

    constraint fk_order_client_id foreign key (client_id) references clients (id),
    constraint fk_order_employee_id foreign key (employee_id) references employees (id)
);

create table gems_types
(
    id           bigserial primary key,
    created_by   varchar(50),
    created_when timestamp,
    is_deleted   boolean default false,
    deleted_by   varchar(50),
    deleted_when timestamp,

    title        varchar(50),
    carat_weight double precision
);

create table metals_types
(
    id           bigserial primary key,
    created_by   varchar(50),
    created_when timestamp,
    is_deleted   boolean default false,
    deleted_by   varchar(50),
    deleted_when timestamp,

    title        varchar(50)
);

create table finished_items
(
    id           bigserial primary key,
    created_by   varchar(50),
    created_when timestamp,
    is_deleted   boolean default false,
    deleted_by   varchar(50),
    deleted_when timestamp,

    item_code    integer,
    metals_info  varchar(100),
    gems_info    varchar(100),
    employee_id  bigint,
    net_cost     double precision,
    description  text,
    photo_path   varchar(100),

    constraint fk_finished_item_employee foreign key (employee_id) references employees (id)
);

create table finished_items_metals_types
(
    finished_item_id bigint not null,
    metal_type_id    bigint not null,
    primary key (finished_item_id, metal_type_id),
    constraint fk_finished_item_id foreign key (finished_item_id) references finished_items (id),
    constraint fk_metal_type_id foreign key (metal_type_id) references metals_types (id)
);

create table finished_items_gems_types
(
    finished_item_id bigint not null,
    gem_type_id      bigint not null,
    primary key (finished_item_id, gem_type_id),
    constraint fk_finished_item_id foreign key (finished_item_id) references finished_items (id),
    constraint fk_gem_type_id foreign key (gem_type_id) references gems_types (id)
);

alter table finished_items add constraint fk_finished_item_employee foreign key (employee_id) references employees (id)




