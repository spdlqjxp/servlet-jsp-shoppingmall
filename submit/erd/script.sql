drop database if exists nhn_academy_16;
create database nhn_academy_16;
use nhn_academy_16;
show tables ;

show create table category_product;
CREATE TABLE `users` (
                         `user_id` varchar(50) NOT NULL COMMENT '아이디',
                         `user_name` varchar(50) NOT NULL COMMENT '이름',
                         `user_password` varchar(200) NOT NULL COMMENT 'mysql password 사용',
                         `user_birth` varchar(8) NOT NULL COMMENT '생년월일 : 19840503',
                         `user_auth` varchar(10) NOT NULL COMMENT '권한: ROLE_ADMIN,ROLE_USER',
                         `user_point` int NOT NULL COMMENT 'default : 1000000',
                         `created_at` datetime NOT NULL COMMENT '가입 일자',
                         `latest_login_at` datetime DEFAULT NULL COMMENT '마지막 로그인 일자'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='회원';

CREATE TABLE `product` (
                           `product_id`	varchar(50)	NOT NULL	COMMENT '상품id',
                           `product_name`	varchar(50)	NOT NULL	COMMENT '상품이름',
                           `product_price`	int	NOT NULL	COMMENT '상품가격',
                           `product_quantity`	int	NULL	COMMENT '재고',
                            `product_description`	varchar(200)	NULL	COMMENT '상품설명',
                            `product_image`	varchar(200)	NULL	COMMENT '상품이미지'
);

CREATE TABLE `category_product` (
                                    `category_id`	varchar(50)	NOT NULL	COMMENT '카테고리Id',
                                    `product_id`	varchar(50)	NOT NULL	COMMENT '상품id'
);

CREATE TABLE `order` (
                         `order_id`	varchar(50)	NOT NULL	COMMENT '주문Id',
                         `user_id`	varchar(50)	NOT NULL	COMMENT '아이디'
);

CREATE TABLE `cart` (
                        `user_id`	varchar(50)	NOT NULL	COMMENT '아이디',
                        `product_id`	varchar(50)	NOT NULL	COMMENT '상품id',
                        `item_quantity`	int	NOT NULL	COMMENT '상품구매예정수량'
);

CREATE TABLE `category` (
                            `category_id`	varchar(50)	NOT NULL	COMMENT '카테고리Id',
                            `category_name`	varchar(50)	NOT NULL	COMMENT '카테고리 이름'
);

CREATE TABLE `order_items` (
                               `order_id`	varchar(50)	NOT NULL	COMMENT '주문Id',
                               `product_id`	varchar(50)	NOT NULL	COMMENT '상품id',
                               `order_quantity`	int	NOT NULL	COMMENT '상품주문수량'
);

ALTER TABLE `product` ADD CONSTRAINT `PK_PRODUCT` PRIMARY KEY (
                                                               `product_id`
    );

ALTER TABLE `category_product` ADD CONSTRAINT `PK_CATEGORY_PRODUCT` PRIMARY KEY (
                                                                                 `category_id`,
                                                                                 `product_id`
    );

ALTER TABLE `order` ADD CONSTRAINT `PK_ORDER` PRIMARY KEY (
                                                           `order_id`
    );

ALTER TABLE `cart` ADD CONSTRAINT `PK_CART` PRIMARY KEY (
                                                         `user_id`,
                                                         `product_id`
    );

ALTER TABLE `category` ADD CONSTRAINT `PK_CATEGORY` PRIMARY KEY (
                                                                 `category_id`
    );

ALTER TABLE `users` ADD CONSTRAINT `PK_USERS` PRIMARY KEY (
                                                           `user_id`
    );

ALTER TABLE `order_items` ADD CONSTRAINT `PK_ORDER_ITEMS` PRIMARY KEY (
                                                                       `order_id`,
                                                                       `product_id`
    );

ALTER TABLE `category_product` ADD CONSTRAINT `FK_category_TO_category_product_1` FOREIGN KEY (
                                                                                               `category_id`
    )
    REFERENCES `category` (
                           `category_id`
        );

ALTER TABLE `category_product` ADD CONSTRAINT `FK_product_TO_category_product_1` FOREIGN KEY (
                                                                                              `product_id`
    )
    REFERENCES `product` (
                          `product_id`
        ) ON DELETE CASCADE;

ALTER TABLE `order` ADD CONSTRAINT `FK_users_TO_order_1` FOREIGN KEY (
                                                                      `user_id`
    )
    REFERENCES `users` (
                        `user_id`
        );

ALTER TABLE `cart` ADD CONSTRAINT `FK_users_TO_cart_1` FOREIGN KEY (
                                                                    `user_id`
    )
    REFERENCES `users` (
                        `user_id`
        );

ALTER TABLE `cart` ADD CONSTRAINT `FK_product_TO_cart_1` FOREIGN KEY (
                                                                      `product_id`
    )
    REFERENCES `product` (
                          `product_id`
        );

ALTER TABLE `order_items` ADD CONSTRAINT `FK_order_TO_order_items_1` FOREIGN KEY (
                                                                                  `order_id`
    )
    REFERENCES `order` (
                        `order_id`
        );

ALTER TABLE `order_items` ADD CONSTRAINT `FK_product_TO_order_items_1` FOREIGN KEY (
                                                                                    `product_id`
    )
    REFERENCES `product` (
                          `product_id`
        );

