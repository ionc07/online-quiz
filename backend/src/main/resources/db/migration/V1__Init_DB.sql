
CREATE TABLE ROLE (id serial PRIMARY KEY,
                   name varchar(50));


CREATE TABLE users (id BIGSERIAL PRIMARY KEY,
                    email varchar(150),
                    first_name varchar(100),
                    last_name varchar(100),
                    password varchar(255),
                    role_id integer CONSTRAINT user_role_fk REFERENCES ROLE,
                    created_at timestamp, reset_code varchar(255));


CREATE TABLE test_settings (id BIGSERIAL PRIMARY KEY,
                            available_from timestamp, available_to timestamp, chat_enabled boolean DEFAULT FALSE,
                            time_limit integer, max_attempts integer DEFAULT 1);


CREATE TABLE test (id BIGSERIAL PRIMARY KEY,
                   title varchar(255) NOT NULL,
                   description varchar(1024),
                   user_id bigint CONSTRAINT user_test_fk REFERENCES users,
                   created_at timestamp, settings_id bigint NOT NULL CONSTRAINT test_settings_fk REFERENCES test_settings,
                   available boolean DEFAULT TRUE NOT NULL);


CREATE TABLE user_test (id BIGSERIAL PRIMARY KEY,
                        user_id bigint NOT NULL CONSTRAINT user_test_user_fk REFERENCES users,
                        test_id bigint NOT NULL CONSTRAINT user_test_test_fk REFERENCES test,
                        start_date timestamp, submit_date timestamp);


CREATE TABLE answer_type (id serial PRIMARY KEY,
                          name varchar(100) NOT NULL);


CREATE TABLE question (id BIGSERIAL PRIMARY KEY,
                       test_id bigint CONSTRAINT question_test_fk REFERENCES test,
                       value text NOT NULL,
                       SEQUENCE integer NOT NULL,
                       time_limit integer, wait_for_time_limit boolean DEFAULT FALSE,
                       score integer, answer_type_id integer NOT NULL CONSTRAINT "question_answerType_fk" REFERENCES answer_type);


CREATE TABLE answer (id BIGSERIAL PRIMARY KEY,
                     question_id bigint NOT NULL CONSTRAINT question_fk REFERENCES question,
                     value text NOT NULL,
                     correct boolean, score integer DEFAULT 0 NOT NULL);


CREATE TABLE user_response (id BIGSERIAL PRIMARY KEY,
                            user_id bigint NOT NULL CONSTRAINT user_response_user_fk REFERENCES users,
                            answer_id bigint NOT NULL CONSTRAINT user_response_answer_fk REFERENCES answer,
                            question_id bigint NOT NULL CONSTRAINT user_response_question_fk REFERENCES question,
                            test_id bigint NOT NULL CONSTRAINT user_response_test_fk REFERENCES test,
                            CONSTRAINT user_response_user_answer_question_unique_k UNIQUE (answer_id,
                                                                                           question_id,
                                                                                           user_id));


CREATE TABLE test_group (id BIGSERIAL CONSTRAINT test_group_pk PRIMARY KEY,
                         name varchar NOT NULL,
                         user_id bigint NOT NULL CONSTRAINT test_group_users__fk REFERENCES users);


CREATE TABLE test_group_test (test_group_id bigint NOT NULL CONSTRAINT test_group_test__test_group_id_fk REFERENCES test_group,
                              test_id bigint NOT NULL CONSTRAINT test_group_test_test_id_fk REFERENCES test);


CREATE TABLE user_group (id serial CONSTRAINT user_group_pk PRIMARY KEY,
                         name varchar NOT NULL,
                         user_id bigint NOT NULL CONSTRAINT user_group__users_fk REFERENCES users);


CREATE TABLE user_group_user (user_group_id bigint NOT NULL CONSTRAINT fk35c0kokvxaajs05y2dkeoj9x1 REFERENCES user_group,
                              user_id bigint NOT NULL CONSTRAINT fkm1rsjhbi9cbr12pp3b34rsiwl REFERENCES users,
                              UNIQUE (user_group_id,
                                      user_id));


CREATE TABLE user_shared_test (test_id bigint NOT NULL REFERENCES test,
                               user_id bigint NOT NULL REFERENCES users);


CREATE TABLE test_result
(id serial PRIMARY KEY,
 user_id integer NOT NULL CONSTRAINT fk_user REFERENCES users ON DELETE CASCADE,
 test_id integer NOT NULL CONSTRAINT fk_test REFERENCES test ON DELETE CASCADE,
 score integer NOT NULL,
 taken_at timestamp DEFAULT CURRENT_TIMESTAMP,
 correct_questions integer DEFAULT 0 NOT NULL,
 total_score integer DEFAULT 0 NOT NULL);


INSERT INTO role(name)
VALUES ('ROLE_admin'),
       ('ROLE_user');


INSERT INTO users(created_at, email, first_name, last_name, password, role_id)
VALUES (CURRENT_TIMESTAMP, 'ciolacu.ion07@gmail.com', 'Ciolacu', 'Ion', '$2a$10$YjuCkqpugtkzUD.Lfh0J3.qYwBxqgI1XAT3WzKoYn7fn/IzA4GWT6', 1);


INSERT INTO answer_type (id, name)
VALUES (1, 'radio');


INSERT INTO answer_type (id, name)
VALUES (2, 'checkbox');


INSERT INTO answer_type (id, name)
VALUES (3, 'dropdown');