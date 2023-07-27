CREATE TABLE address
(
    address_id  SERIAL      NOT NULL,
    country     VARCHAR(32) NOT NULL,
    city        VARCHAR(32) NOT NULL,
    street      VARCHAR(32) NOT NULL,
    number      VARCHAR(8)  NOT NULL,
    zip_code    VARCHAR(8)  NOT NULL,
    PRIMARY KEY (address_id)
);

CREATE TABLE food_app_user
(
    food_app_user_id    SERIAL      NOT NULL,
    name                VARCHAR(32) NOT NULL,
    surname             VARCHAR(32) NOT NULL,
    email               VARCHAR(32) NOT NULL,
    phone               VARCHAR(32) NOT NULL,
    address_id          INT         NOT NULL,
    PRIMARY KEY (food_app_user_id),
    UNIQUE      (email),
    UNIQUE      (phone),
    UNIQUE      (address_id),
    CONSTRAINT fk_food_app_user_address
        FOREIGN KEY (address_id)
            REFERENCES address (address_id)
);

CREATE TABLE delivery_address
(
    delivery_address_id SERIAL      NOT NULL,
    country             VARCHAR(32) NOT NULL,
    city                VARCHAR(32) NOT NULL,
    street              VARCHAR(32) NOT NULL,
    PRIMARY KEY (delivery_address_id)
);

CREATE TABLE restaurant
(
    restaurant_id       SERIAL        NOT NULL,
    name                VARCHAR(32)   NOT NULL,
    food_app_user_id    INT           NOT NULL,
    address_id          INT           NOT NULL,
    PRIMARY KEY (restaurant_id),
    UNIQUE      (address_id),
    CONSTRAINT fk_restaurant_address
        FOREIGN KEY (address_id)
            REFERENCES address (address_id),
    CONSTRAINT fk_restaurant_food_app_user
        FOREIGN KEY (food_app_user_id)
            REFERENCES food_app_user (food_app_user_id)
);

CREATE TABLE menu
(
    menu_id         SERIAL      NOT NULL,
    name            VARCHAR(32) NOT NULL,
    category        VARCHAR(16) NOT NULL,
    restaurant_id   INT         NOT NULL,
    PRIMARY KEY (menu_id),
    CONSTRAINT fk_menu_restaurant
        FOREIGN KEY (restaurant_id)
            REFERENCES restaurant (restaurant_id)
);

CREATE TABLE food
(
    food_id         SERIAL          NOT NULL,
    name            VARCHAR(32)     NOT NULL,
    price           NUMERIC(6, 2)   NOT NULL,
    description     TEXT,
    menu_id         INT             NOT NULL,
    PRIMARY KEY (food_id),
    CONSTRAINT fk_food_menu
        FOREIGN KEY (menu_id)
            REFERENCES menu (menu_id)
);

CREATE TABLE app_order
(
    app_order_id            SERIAL                      NOT NULL,
    number                  VARCHAR(32)                 NOT NULL,
    total_cost              NUMERIC(7, 2)               NOT NULL,
    state                   VARCHAR(16)                 NOT NULL,
    ordered_at              TIMESTAMP WITH TIME ZONE    NOT NULL,
    planned_delivery_time   TIMESTAMP WITH TIME ZONE    NOT NULL,
    additional_information  TEXT,
    restaurant_id           INT                         NOT NULL,
    food_app_user_id        INT                         NOT NULL,
    PRIMARY KEY (app_order_id),
    UNIQUE      (number),
    CONSTRAINT fk_app_order_restaurant
        FOREIGN KEY (restaurant_id)
            REFERENCES restaurant (restaurant_id),
    CONSTRAINT fk_app_order_food_app_user
        FOREIGN KEY (food_app_user_id)
            REFERENCES food_app_user (food_app_user_id)
);

CREATE TABLE order_details
(
    order_details_id    SERIAL  NOT NULL,
    quantity            INT     NOT NULL,
    food_id             INT     NOT NULL,
    app_order_id        INT     NOT NULL,
    PRIMARY KEY (order_details_id),
    CONSTRAINT fk_order_details_food
        FOREIGN KEY (food_id)
            REFERENCES food (food_id),
    CONSTRAINT fk_order_details_app_order
        FOREIGN KEY (app_order_id)
            REFERENCES app_order (app_order_id)
);

CREATE TABLE restaurant_delivery_address
(
    restaurant_delivery_address_id  SERIAL                      NOT NULL,
    delivery_time                   TIMESTAMP WITH TIME ZONE    NOT NULL,
    delivery_address_id             INT                         NOT NULL,
    restaurant_id                   INT                         NOT NULL,
    PRIMARY KEY (restaurant_delivery_address_id),
    CONSTRAINT fk_restaurant_delivery_address_delivery_address
        FOREIGN KEY (delivery_address_id)
            REFERENCES delivery_address (delivery_address_id),
    CONSTRAINT fk_restaurant_delivery_address_restaurant
        FOREIGN KEY (restaurant_id)
            REFERENCES restaurant (restaurant_id)
);