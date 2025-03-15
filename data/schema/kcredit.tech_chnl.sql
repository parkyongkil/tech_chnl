/* 사용자 생성 */
CREATE ROLE kcredit WITH
    SUPERUSER
    CREATEDB
    CREATEROLE
    INHERIT
    LOGIN
    NOREPLICATION
    NOBYPASSRLS
    CONNECTION LIMIT -1;

COMMENT ON ROLE kcredit IS 'kcredit';
ALTER ROLE kcredit PASSWORD 'kcredit';

/* database 생성 */
CREATE DATABASE kcredit
    WITH
    OWNER = kcredit
    ENCODING = 'UTF8'
    LOCALE_PROVIDER = 'libc'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

/* database 확인 */
SELECT current_database();
ALTER ROLE kcredit IN DATABASE kcredit SET search_path="$user";



/* database kcredit 으로 접속 */

/* schema 생성 */
CREATE SCHEMA tech_chnl AUTHORIZATION kcredit;
SELECT current_schema();
SET search_path TO kcredit;
SHOW search_path;

/* 시퀀스 생성 */
CREATE SEQUENCE tech_chnl.seq_user_no
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

/* 테이블 생성 */
CREATE TABLE tech_chnl."user" (
                                  "no" numeric DEFAULT nextval('tech_chnl.seq_user_no'::regclass) NOT NULL,
                                  "name" varchar NULL,
                                  pwd varchar NULL,
                                  grade varchar NOT NULL,
                                  reg_date timestamp NULL,
                                  upd_date timestamp NULL,
                                  CONSTRAINT users_pk PRIMARY KEY (no)
);

select * from tech_chnl.user;
