CREATE TABLE application_user
(
    user_id     SERIAL        NOT NULL,
    user_name   VARCHAR(32)   NOT NULL,
    password    VARCHAR(128)  NOT NULL,
    active      BOOLEAN       NOT NULL,
    PRIMARY KEY (user_id),
    UNIQUE (user_name)
);

CREATE TABLE application_role
(
    role_id SERIAL      NOT NULL,
    role    VARCHAR(20) NOT NULL,
    PRIMARY KEY (role_id)
);

CREATE TABLE application_user_role
(
    user_id   INT      NOT NULL,
    role_id   INT      NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_application_user_role_application_user
        FOREIGN KEY (user_id)
            REFERENCES application_user (user_id),
    CONSTRAINT fk_application_user_role_application_role
        FOREIGN KEY (role_id)
            REFERENCES application_role (role_id)
);
