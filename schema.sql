create table attr_key
(
    id int auto_increment
        primary key,
    name varchar(64) null,
    categoryID int not null,
    created_time timestamp default CURRENT_TIMESTAMP null,
    updated_time timestamp default CURRENT_TIMESTAMP null
);

create table attr_value
(
    id int auto_increment
        primary key,
    attr_key int null,
    value varchar(64) null,
    img char(32) null,
    sort int null,
    created_time timestamp default CURRENT_TIMESTAMP null,
    updated_time timestamp default CURRENT_TIMESTAMP null
);

create table brand
(
    id char(32) null,
    name varchar(64) null,
    registration_num varchar(64) null,
    created_time timestamp default CURRENT_TIMESTAMP null,
    updated_time timestamp default CURRENT_TIMESTAMP null,
    constraint registration_num
        unique (registration_num)
);

create table cart_item
(
    id char(32) not null
        primary key,
    customer_id char(32) not null,
    product_id char(32) not null,
    cart_id char(32) not null,
    quantity int null,
    total_price decimal null,
    created_time timestamp default CURRENT_TIMESTAMP null,
    updated_time timestamp default CURRENT_TIMESTAMP null
);

create table category
(
    id int auto_increment
        primary key,
    name varchar(64) null,
    parent int null,
    created_time timestamp default CURRENT_TIMESTAMP null,
    updated_time timestamp default CURRENT_TIMESTAMP null
);

create table customer
(
    id char(32) not null
        primary key,
    username varchar(64) not null,
    password varchar(128) not null,
    email varchar(128) null comment 'email',
    phone varchar(20) not null,
    alias varchar(64) null comment 'nike name',
    avatar char(32) null comment 'avatar image id',
    date_of_birth timestamp null comment 'birthday',
    role varchar(20) null comment 'role of customer, including regular customer, manager, superuser etc.    CUSTOMER,MANAGER,SUPERUSER',
    created_time timestamp default CURRENT_TIMESTAMP null,
    updated_time timestamp default CURRENT_TIMESTAMP null,
    constraint email
        unique (email),
    constraint phone
        unique (phone)
);

create table customer_address
(
    id char(32) not null
        primary key,
    customer_id int not null,
    post_code varchar(16) null,
    home_address varchar(256) null,
    phone_number varchar(20) null,
    created_time timestamp default CURRENT_TIMESTAMP null,
    updated_time timestamp default CURRENT_TIMESTAMP null
);

create table image
(
    id char(32) not null
        primary key,
    path varchar(256) null,
    description varchar(32) null,
    created_time timestamp default CURRENT_TIMESTAMP null,
    updated_time timestamp default CURRENT_TIMESTAMP null
);

create table image_product
(
    id char(32) not null
        primary key,
    product_id char(32) null,
    img_id char(32) null
);

create table order_item
(
    id char(32) not null
        primary key,
    customer_id char(32) not null,
    order_id char(32) not null,
    quantity int not null,
    product_id char(32) not null,
    snapshot_product_id char(32) not null,
    price decimal not null,
    created_time timestamp default CURRENT_TIMESTAMP null,
    updated_time timestamp default CURRENT_TIMESTAMP null
);

create table product
(
    id char(32) not null
        primary key,
    name varchar(64) not null,
    category int not null,
    weight int null,
    sales int null,
    status enum('OUT_OF_ORDER', 'ON_SALE') null,
    priority int null comment 'order in prodct list',
    thumbnail_img char(32) null,
    standard_img char(32) null,
    created_time timestamp default CURRENT_TIMESTAMP null,
    updated_time timestamp default CURRENT_TIMESTAMP null,
    brand char(32) null,
    description text null
);

create table shop_order
(
    id char(32) not null
        primary key,
    customer_id int not null,
    total_price decimal null,
    created_time timestamp default CURRENT_TIMESTAMP null,
    updated_time timestamp default CURRENT_TIMESTAMP null
);

create table shopping_cart
(
    id char(32) not null
        primary key,
    customer_id int null,
    total_price decimal null
);

create table sku
(
    id int auto_increment
        primary key,
    product_id char(32) not null,
    attribute json not null,
    stock int null,
    price decimal not null,
    sales int null comment '销量',
    created_time timestamp default CURRENT_TIMESTAMP null,
    updated_time timestamp default CURRENT_TIMESTAMP null,
    img char(32) null
);

create table snapshot_product
(
    id char(32) not null
        primary key,
    product_id char(32) null,
    name varchar(64) not null,
    price decimal not null,
    category int not null,
    thumbnail_img int null comment '缩略图 id',
    standard_img int null comment '标准大小图片 id',
    description text null,
    created_time timestamp default CURRENT_TIMESTAMP null,
    updated_time timestamp default CURRENT_TIMESTAMP null
)
    comment 'store ordered items';

