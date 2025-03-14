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
ALTER ROLE kcredit IN DATABASE kcredit SET search_path="$user";

COMMENT ON ROLE kcredit IS 'kcredit';

/* schema 생성 */
CREATE SCHEMA tech_chnl AUTHORIZATION kcredit;

/* 테이블 생성 */
CREATE TABLE tech_chnl."user" (
                                  "no" numeric DEFAULT nextval('tech_chnl.seq_user_no'::regclass) NOT NULL,
                                  "name" varchar NULL,
                                  pwd varchar NULL,
                                  reg_date date NULL,
                                  grade varchar NOT NULL,
                                  CONSTRAINT users_pk PRIMARY KEY (no)
);

/* 시퀀스 생성 */
CREATE SEQUENCE tech_chnl.seq_user_no
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;