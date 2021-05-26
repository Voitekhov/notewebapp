DROP TABLE IF EXISTS notes;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    first_name       VARCHAR(45)                  NOT NULL,
    email      VARCHAR(100)                       NOT NULL,
    password   VARCHAR(100)                       NOT NULL,
    role       VARCHAR(5)          DEFAULT 'USER' NOT NULL,
    registered TIMESTAMP           DEFAULT now()  NOT NULL,
    enabled    BOOL                DEFAULT TRUE   NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);


CREATE TABLE categories
(
    id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id INTEGER NOT NULL,
    name    VARCHAR NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE TABLE notes
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    category_id INTEGER                           NOT NULL,
    title       VARCHAR                           NOT NULL,
    text        VARCHAR                           NOT NULL,
    created     TIMESTAMP           DEFAULT now() NOT NULL,
    link        VARCHAR,
    FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE
)