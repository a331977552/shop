
create table attr_key
(
    id int auto_increment
        primary key,
    name varchar(64) null,
    select_type enum('single', 'multiple') null,
    entry_method enum('custom', 'selection') null,
    category_id int not null,
    sort int default 0 null,
    searchable tinyint(1) null,
    searchType enum('normal', 'color') null,
    created_time timestamp default CURRENT_TIMESTAMP null,
    updated_time timestamp default CURRENT_TIMESTAMP null
);

create table attr_value
(
    id int auto_increment
        primary key,
    attr_key int null,
    value varchar(64) null,
    created_time timestamp default CURRENT_TIMESTAMP null,
    updated_time timestamp default CURRENT_TIMESTAMP null
);

create table brand
(
    name varchar(64) null,
    registration_num varchar(64) null,
    created_time timestamp default CURRENT_TIMESTAMP null,
    updated_time timestamp default CURRENT_TIMESTAMP null,
    capital_letter char null,
    priority int null,
    is_manufacturer tinyint(1) null,
    visible tinyint(1) null,
    info varchar(128) null,
    description varchar(128) null,
    id int auto_increment
        primary key,
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
    updated_time timestamp default CURRENT_TIMESTAMP null,
    level int null,
    isLeaf tinyint(1) null,
    visible tinyint(1) null,
    priority int null,
    suffix varchar(16) null,
    keyword varchar(32) null,
    nav_visible tinyint(1) default 1 null,
    description varchar(32) null
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
    customer_id char(32) not null,
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

create table product
(
    id char(32) not null
        primary key,
    name varchar(64) not null,
    category int not null,
    brand int null,
    weight int null,
    sales int null,
    status enum('OUT_OF_ORDER', 'ON_SALE') null,
    priority int null comment 'order in prodct list',
    thumbnail_img varchar(32) null comment '缩略图 id',
    standard_img varchar(32) null comment '标准大小图片 id',
    created_time timestamp default CURRENT_TIMESTAMP null,
    updated_time timestamp default CURRENT_TIMESTAMP null,
    subtitle varchar(64) not null,
    description varchar(128) null,
    item_No varchar(64) null,
    price decimal(20,3) null,
    market_price decimal null,
    review_status enum('PASSED', 'FAILED', 'REVIEWING') null,
    suffix varchar(12) null comment '计量单位',
    detail text null,
    specs varchar(256) null
);

create table product_spec
(
    id int auto_increment
        primary key,
    name varchar(64) null,
    select_type enum('single', 'multiple') null,
    entry_method enum('custom', 'selection') null,
    category_id int not null,
    sort int null,
    searchable tinyint(1) null,
    created_time timestamp default CURRENT_TIMESTAMP null,
    updated_time timestamp default CURRENT_TIMESTAMP null,
    value varchar(512) null
);

create table s_order
(
    id char(32) not null
        primary key,
    customer_id char(32) not null,
    total_price decimal null,
    status enum('UNPAID', 'PAID', 'SHIPPED', 'FINISHED', 'REFUND', 'CLOSED') not null,
    created_time timestamp default CURRENT_TIMESTAMP null,
    updated_time timestamp default CURRENT_TIMESTAMP null,
    pay_method enum('alipay', 'wechat') null,
    order_source enum('android_app', 'ios_app', 'mini_program', 'web') null,
    order_num char(20) not null,
    buyer_comment varchar(256) null,
    seller_comment varchar(256) null,
    auto_confirm_days int null comment '自动确认收货时间',
    constraint order_num
        unique (order_num)
);

create table s_order_item
(
    id char(32) not null
        primary key,
    customer_id char(32) not null,
    order_id char(32) not null,
    quantity int not null,
    product_id char(32) not null,
    snapshot_product_id char(32) not null,
    unit_price decimal not null,
    subTotal decimal not null,
    thumb_img varchar(32) not null,
    created_time timestamp default CURRENT_TIMESTAMP null,
    updated_time timestamp default CURRENT_TIMESTAMP null,
    product_sku varchar(256) null
);

create table shipping_address
(
    id char(32) not null
        primary key,
    order_id char(32) null,
    customer_name varchar(32) not null,
    post_code varchar(16) null,
    home_address varchar(256) not null,
    phone_number varchar(20) not null,
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
    attribute varchar(256) null,
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
comment 'store purchased items';

