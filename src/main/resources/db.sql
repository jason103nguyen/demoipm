-- ROLE_APP --
INSERT INTO role_app (role_name) 
VALUES ('ROLE_ADMIN');

INSERT INTO role_app (role_name) 
VALUES ('ROLE_INTERVIEWER');

INSERT INTO role_app (role_name) 
VALUES ('ROLE_HR');

-- USER_APP --
INSERT INTO user_app (email, full_name, phone, username, password, is_delete)
VALUES ('a103nguyen@gmail.com' ,'Nguyen Van A', '0123456789', 'a103nguyen', '$2a$10$rV3ykBRV.I07I7foZLQ/fuXGyEDedS9qrgxGeViaU/wFSyA3AwOpK', false);

INSERT INTO user_app (email, full_name, phone, username, password, is_delete)
VALUES ('b103nguyen@gmail.com' ,'Nguyen Van B', '0123456789', 'b103nguyen', '$2a$10$rV3ykBRV.I07I7foZLQ/fuXGyEDedS9qrgxGeViaU/wFSyA3AwOpK', false);

INSERT INTO user_app (email, full_name, phone, username, password, is_delete)
VALUES ('c103nguyen@gmail.com' ,'Nguyen Van C', '0123456789', 'c103nguyen', '$2a$10$rV3ykBRV.I07I7foZLQ/fuXGyEDedS9qrgxGeViaU/wFSyA3AwOpK', false);

-- USER_ROLE --
INSERT INTO user_role (user_id, role_id) 
VALUES (1, 1);

INSERT INTO user_role (user_id, role_id) 
VALUES (2, 2);

INSERT INTO user_role (user_id, role_id) 
VALUES (3, 3);

-- SKILL --
INSERT INTO skill (name, description) 
VALUES ('Java', 'Ngon ngu duoc su dung de phat trien web backend');

INSERT INTO skill (name, description) 
VALUES ('C', 'Ngon ngu duoc su dung de phat trien Embedded system');

INSERT INTO skill (name, description) 
VALUES ('Python', 'Ngon ngu duoc su dung de lam Big data');

INSERT INTO skill (name, description) 
VALUES ('JavaScript', 'Day la ngon ngu lap trinh');

INSERT INTO skill (name, description) 
VALUES ('PHP', 'Day la ngon ngu lap trinh');

INSERT INTO skill (name, description) 
VALUES ('Swift', 'Day la ngon ngu lap trinh');

INSERT INTO skill (name, description) 
VALUES ('C#', 'Day la ngon ngu lap trinh');

INSERT INTO skill (name, description) 
VALUES ('JavaScript', 'Day la ngon ngu lap trinh');

INSERT INTO skill (name, description) 
VALUES ('Ruby', 'Day la ngon ngu lap trinh');

INSERT INTO skill (name, description) 
VALUES ('Objective-C', 'Day la ngon ngu lap trinh');

-- CAREER --
INSERT INTO career (name, description) 
VALUES ('Cong nghe thong tin', 'Mot nghanh nghe nang dong, giúp phát trien dat nuoc');

INSERT INTO career (name, description) 
VALUES ('Nhan su', 'Mot nghanh nghe tim kiem cac tai nang, giup phat trien cong ty');

INSERT INTO career (name, description) 
VALUES ('Tai chinh', 'Mot nghanh nghe giup can bang thu chi, giup cong ty co loi nhuan');

-- JOB --
INSERT INTO job (name, career_id) 
VALUES ('Ky su phat trien web', 1);

INSERT INTO job (name, career_id) 
VALUES ('Ky su lap trinh nhung', 1);

INSERT INTO job (name, career_id) 
VALUES ('Ky su data', 1);

-- JOB_SKILL --
INSERT INTO job_skill (job_id, skill_id) 
VALUES (1, 1);

INSERT INTO job_skill (job_id, skill_id) 
VALUES (2, 2);

INSERT INTO job_skill (job_id, skill_id) 
VALUES (3, 3);

-- RECRUITMENT --
INSERT INTO recruitment (max_salary, min_salary, number, start_recruitment, end_recruitment, career_id, job_id, is_delete)
VALUES (1000, 500, 20, '2021/12/16', '2021/12/26', 1, 1, false);

INSERT INTO recruitment (max_salary, min_salary, number, start_recruitment, end_recruitment, career_id, job_id, is_delete)
VALUES (1000, 500, 20, '2021/12/16', '2021/12/26', 2, 2, false);

INSERT INTO recruitment (max_salary, min_salary, number, start_recruitment, end_recruitment, career_id, job_id, is_delete)
VALUES (1000, 500, 20, '2021/12/16', '2021/12/26', 3, 3, false);

-- RECRUITMENT_SKILL --
INSERT INTO recruitment_skill (recruitment_id, skill_id) 
VALUES (1, 1);

INSERT INTO recruitment_skill (recruitment_id, skill_id) 
VALUES (2, 2);

INSERT INTO recruitment_skill (recruitment_id, skill_id) 
VALUES (3, 3);

-- CANDIDATE --
INSERT INTO candidate (full_name, is_delete, phone, email, sex, birth_day, status)
VALUES ('Vo Van A', 0 ,'0123456789','avo@gmail.com', 'nam', '1990/3/10', 'INPROCESS');

INSERT INTO candidate (full_name, is_delete, phone, email, sex, birth_day, status) 
VALUES ('Vo Van B', 0 ,'0123456789', 'bvo@gmail.com', 'nam', '1991/3/10', 'INPROCESS');

INSERT INTO candidate (full_name, is_delete, phone, email, sex, birth_day, status) 
VALUES ('Vo Van C', 0 ,'0123456789', 'cvo@gmail.com', 'nam', '1992/3/10', 'INPROCESS');

INSERT INTO candidate (full_name, is_delete, phone, email, sex, birth_day, status) 
VALUES ('Vo Van D', 0 ,'0123456789', 'dvo@gmail.com', 'nam', '1993/3/10', 'INPROCESS');

INSERT INTO candidate (full_name, is_delete, phone, email, sex, birth_day, status) 
VALUES ('Vo Van E', 0 ,'0123456789', 'evo@gmail.com', 'nam', '1994/3/10', 'INPROCESS');

INSERT INTO candidate (full_name, is_delete, phone, email, sex, birth_day, status) 
VALUES ('Vo Van F', 0 ,'0123456789', 'fvo@gmail.com', 'nam', '1995/3/10', 'INPROCESS');

INSERT INTO candidate (full_name, is_delete, phone, email, sex, birth_day, status) 
VALUES ('Vo Van G', 0 ,'0123456789', 'gvo@gmail.com', 'nam', '1996/3/10', 'INPROCESS');

INSERT INTO candidate (full_name, is_delete, phone, email, sex, birth_day, status) 
VALUES ('Vo Van H', 0 ,'0123456789', 'hvo@gmail.com', 'nam', '1997/3/10', 'INPROCESS');

INSERT INTO candidate (full_name, is_delete, phone, email, sex, birth_day, status) 
VALUES ('Vo Van Y', 0 ,'0123456789', 'yvo@gmail.com', 'nam', '1998/3/10', 'INPROCESS');

INSERT INTO candidate (full_name, is_delete, phone, email, sex, birth_day, status) 
VALUES ('Vo Van K', 0 ,'0123456789', 'kvo@gmail.com', 'nam', '1999/3/10', 'INPROCESS');

-- SKILL_CANDIDATE --
INSERT INTO skill_candidate (candidate_id, skill_id)
VALUES (1, 1);

INSERT INTO skill_candidate (candidate_id, skill_id)
VALUES (1, 2);

INSERT INTO skill_candidate (candidate_id, skill_id)
VALUES (2, 2);

INSERT INTO skill_candidate (candidate_id, skill_id)
VALUES (2, 3);

INSERT INTO skill_candidate (candidate_id, skill_id)
VALUES (3, 3);

INSERT INTO skill_candidate (candidate_id, skill_id)
VALUES (3, 4);

INSERT INTO skill_candidate (candidate_id, skill_id)
VALUES (4, 4);

INSERT INTO skill_candidate (candidate_id, skill_id)
VALUES (4, 5);

INSERT INTO skill_candidate (candidate_id, skill_id)
VALUES (5, 5);

INSERT INTO skill_candidate (candidate_id, skill_id)
VALUES (5, 6);

INSERT INTO skill_candidate (candidate_id, skill_id)
VALUES (6, 6);

INSERT INTO skill_candidate (candidate_id, skill_id)
VALUES (6, 7);

INSERT INTO skill_candidate (candidate_id, skill_id)
VALUES (7, 7);

INSERT INTO skill_candidate (candidate_id, skill_id)
VALUES (7, 8);

INSERT INTO skill_candidate (candidate_id, skill_id)
VALUES (8, 8);

INSERT INTO skill_candidate (candidate_id, skill_id)
VALUES (8, 9);

INSERT INTO skill_candidate (candidate_id, skill_id)
VALUES (9, 9);

INSERT INTO skill_candidate (candidate_id, skill_id)
VALUES (9, 10);

INSERT INTO skill_candidate (candidate_id, skill_id)
VALUES (10, 1);

INSERT INTO skill_candidate (candidate_id, skill_id)
VALUES (10, 2);


-- ENTRYTEST --
INSERT INTO entry_test (time_entry_test, local, result, point, name_test, candidate_id)
VALUES ('2022-1-1 15:00:00', 'FT_1', 'FAILURE', 10, 'Java_Test', 1);

INSERT INTO entry_test (time_entry_test, local, result, point, name_test, candidate_id)
VALUES ('2022-1-1 15:00:00', 'FT_1', 'FAILURE', 20, 'C_Test', 2);

INSERT INTO entry_test (time_entry_test, local, result, point, name_test, candidate_id)
VALUES ('2022-1-1 15:00:00', 'FT_1', 'FAILURE', 30, 'Python_Test', 3);

INSERT INTO entry_test (time_entry_test, local, result, point, name_test, candidate_id)
VALUES ('2022-2-1 15:00:00', 'FT_1', 'FAILURE', 40, 'Python_Test', 4);

INSERT INTO entry_test (time_entry_test, local, result, point, name_test, candidate_id)
VALUES ('2022-3-1 15:00:00', 'FT_1', 'PASS', 50, 'Python_Test', 5);

INSERT INTO entry_test (time_entry_test, local, result, point, name_test, candidate_id)
VALUES ('2022-4-1 15:00:00', 'FT_1', 'PASS', 60, 'Python_Test', 6);

INSERT INTO entry_test (time_entry_test, local, result, point, name_test, candidate_id)
VALUES ('2022-5-1 15:00:00', 'FT_1', 'PASS', 70, 'Python_Test', 7);

INSERT INTO entry_test (time_entry_test, local, result, point, name_test, candidate_id)
VALUES ('2022-6-1 15:00:00', 'FT_1', 'PASS', 80, 'Python_Test', 8);

INSERT INTO entry_test (time_entry_test, local, result, point, name_test, candidate_id)
VALUES ('2022-7-1 15:00:00', 'FT_1', 'PASS', 90, 'Python_Test', 9);

INSERT INTO entry_test (time_entry_test, local, result, point, name_test, candidate_id)
VALUES ('2022-8-1 15:00:00', 'FT_1', 'PASS', 100, 'Python_Test', 10);

-- INTERVIEW --
INSERT INTO interview (time_interview, local, evaluation, note, result, name_interviewer, candidate_id, round)
VALUES ('2022-4-15 15:00:00', 'FT_1', 'GOOD', 'GOOD', 'PASS', 'HAI', 5, 1);

INSERT INTO interview (time_interview, local, evaluation, note, result, name_interviewer, candidate_id, round)
VALUES ('2022-4-16 15:00:00', 'FT_1', 'GOOD', 'GOOD', 'PASS', 'HAI', 5, 2);

INSERT INTO interview (time_interview, local, evaluation, note, result, name_interviewer, candidate_id, round)
VALUES ('2022-4-15 15:00:00', 'FT_1', 'GOOD', 'GOOD', 'PASS', 'HAI', 6, 1);

INSERT INTO interview (time_interview, local, evaluation, note, result, name_interviewer, candidate_id, round)
VALUES ('2022-4-16 15:00:00', 'FT_1', 'GOOD', 'GOOD', 'PASS', 'HAI', 6, 2);

INSERT INTO interview (time_interview, local, evaluation, note, result, name_interviewer, candidate_id, round)
VALUES ('2022-5-15 15:00:00', 'FT_1', 'GOOD', 'GOOD', 'PASS', 'HAI', 7, 1);

INSERT INTO interview (time_interview, local, evaluation, note, result, name_interviewer, candidate_id, round)
VALUES ('2022-5-16 15:00:00', 'FT_1', 'GOOD', 'GOOD', 'PASS', 'HAI', 7, 2);

INSERT INTO interview (time_interview, local, evaluation, note, result, name_interviewer, candidate_id, round)
VALUES ('2022-6-15 15:00:00', 'FT_1', 'GOOD', 'GOOD', 'PASS', 'HAI', 8, 1);

INSERT INTO interview (time_interview, local, evaluation, note, result, name_interviewer, candidate_id, round)
VALUES ('2022-6-16 15:00:00', 'FT_1', 'GOOD', 'GOOD', 'PASS', 'HAI', 8, 2);

INSERT INTO interview (time_interview, local, evaluation, note, result, name_interviewer, candidate_id, round)
VALUES ('2022-7-15 15:00:00', 'FT_1', 'GOOD', 'GOOD', 'PASS', 'HAI', 9, 1);

INSERT INTO interview (time_interview, local, evaluation, note, result, name_interviewer, candidate_id, round)
VALUES ('2022-7-16 15:00:00', 'FT_1', 'GOOD', 'GOOD', 'PASS', 'HAI', 9, 2);

INSERT INTO interview (time_interview, local, evaluation, note, result, name_interviewer, candidate_id, round)
VALUES ('2022-8-15 15:00:00', 'FT_1', 'GOOD', 'GOOD', 'PASS', 'HAI', 10, 1);

INSERT INTO interview (time_interview, local, evaluation, note, result, name_interviewer, candidate_id, round)
VALUES ('2022-8-16 15:00:00', 'FT_1', 'GOOD', 'GOOD', 'PASS', 'HAI', 10, 2);
