DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS comments;


CREATE TABLE posts
(
    id SERIAL PRIMARY KEY,
    name            varchar(100)  NOT NULL,
    text            varchar(1000) NOT NULL,
    img   bytea,
    created_date BIGINT,
    visible boolean default true,
    deleted boolean default false
);

CREATE TABLE likes
(
    resource_id BIGINT NOT NULL,
    like_count BIGINT NOT NULL DEFAULT 0
);

CREATE TABLE comments
(
    id   SERIAL PRIMARY KEY,
    text    varchar(1000) NOT NULL,
    post_id BIGINT           NOT NULL

);

CREATE TABLE tags
(
    id  SERIAL PRIMARY KEY,
    tag varchar(50) NOT NULL UNIQUE
);

CREATE TABLE posts_tags
(
    post_id BIGINT NOT NULL,
    tag_id  BIGINT NOT NULL
);


