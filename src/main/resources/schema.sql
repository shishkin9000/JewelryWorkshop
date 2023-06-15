create table clients
(
    id           bigserial unique,
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
    id           bigserial unique,
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
    id           bigserial unique,
    created_by   varchar(50),
    created_when timestamp,
    is_deleted   boolean default false,
    deleted_by   varchar(50),
    deleted_when timestamp,

    login        varchar(50) not null unique,
    password     varchar(50) not null,

    first_name   VARCHAR(50) not null,
    middle_name  VARCHAR(50) not null,
    last_name    VARCHAR(50) not null,
    phone        VARCHAR(50) not null,
    address      VARCHAR(50) not null,
    birth_date   DATE        not null,
    position_id  bigint      not null,

    constraint fk_employee_position_id foreign key (position_id) references employees_positions (id)
);

create table clients_orders
(
    id             bigserial unique,
    created_by     varchar(50),
    created_when   timestamp,
    is_deleted     boolean default false,
    deleted_by     varchar(50),
    deleted_when   timestamp,

    client_id      bigint not null,
    description    text,
    deadline       date,
    employee_id    bigint,

    is_completed   boolean default false,
    completed_when timestamp,

    constraint fk_order_client_id foreign key (client_id) references clients (id),
    constraint fk_order_employee_id foreign key (employee_id) references employees (id)
);

create table gems_types
(
    id           bigserial unique,
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
    id           bigserial unique,
    created_by   varchar(50),
    created_when timestamp,
    is_deleted   boolean default false,
    deleted_by   varchar(50),
    deleted_when timestamp,

    title        varchar(50)
);

create table finished_items_metals
(
    finished_item_id bigint not null ,
    metal_type_id bigint not null,
    primary key (finished_item_id, metal_type_id),
    constraint fk_finished_item foreign key (finished_item_id) references finished_items(id),
    constraint fk_metal_type foreign key (metal_type_id) references metals_types(id)
);

create table finished_items_gems
(
    finished_item_id bigint not null ,
    gem_type_id bigint not null,
    primary key (finished_item_id, gem_type_id),
    constraint fk_finished_item foreign key (finished_item_id) references finished_items(id),
    constraint fk_gem_type foreign key (gem_type_id) references gems_types(id)
);

create table finished_items
(
    id           bigserial unique,
    created_by   varchar(50),
    created_when timestamp,
    is_deleted   boolean default false,
    deleted_by   varchar(50),
    deleted_when timestamp,

    item_code integer,

net_cost double precision,

description varchar(100),

photo_path varchar(100)
);




