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

INSERT INTO user_app (email, full_name, phone, username, password, is_delete)
VALUES ('admin@mail.com' ,'Admin Personel', '09876543210', 'admin', '$2a$12$T8erOAVmB9UvJV0UV04GYugRJWty56BICIWaRplVdjoO2Gzy9Fpu6', false);

INSERT INTO user_app (email, full_name, phone, username, password, is_delete)
VALUES ('interviewer@mail.com' ,'Interviewer Personel', '09876543210', 'interviewer', '$2a$12$ZTQL9SdqUczzy3c.5UA6subT.7v6mwCX9rJssMi4loKgun11ILiD.', false);

INSERT INTO user_app (email, full_name, phone, username, password, is_delete)
VALUES ('hr@mail.com' ,'HR Personel', '09876543210', 'hr', '$2a$12$x.CjAasl6O5fQ28EqgnwBOvEFRHUndpUGtwp2v6uDwICTkz6LgEsK', false);

INSERT INTO user_app (email, full_name, phone, username, password, is_delete)
VALUES ('superadmin@mail.com' ,'SuperAdmin Personel', '09876543210', 'superadmin', '$2a$12$uLd7YX7itJWpS1QacI4hA.To3VH0s4SWhOsSM6RBXpfTWTrD8bx6y', false);

-- USER_ROLE --
INSERT INTO user_role (user_id, role_id) 
VALUES (1, 1);

INSERT INTO user_role (user_id, role_id) 
VALUES (2, 2);

INSERT INTO user_role (user_id, role_id) 
VALUES (3, 3);

INSERT INTO user_role (user_id, role_id)
VALUES (4, 1);

INSERT INTO user_role (user_id, role_id)
VALUES (5, 2);

INSERT INTO user_role (user_id, role_id)
VALUES (6, 3);

INSERT INTO user_role (user_id, role_id)
VALUES (7, 1);

INSERT INTO user_role (user_id, role_id)
VALUES (7, 2);

INSERT INTO user_role (user_id, role_id)
VALUES (7, 3);

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
VALUES ('Cong nghe thong tin', 'Mot nghanh nghe nang dong, gi??p ph??t trien dat nuoc');

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
VALUES (1, 8);

INSERT INTO job_skill (job_id, skill_id) 
VALUES (2, 2);

INSERT INTO job_skill (job_id, skill_id) 
VALUES (3, 3);

-- RECRUITMENT --
INSERT INTO recruitment (max_salary, min_salary, number, start_recruitment, end_recruitment, career_id, job_id, is_delete)
VALUES (1000, 500, 5, CURDATE(), CURDATE() + 2, 1, 1, false);

INSERT INTO recruitment (max_salary, min_salary, number, start_recruitment, end_recruitment, career_id, job_id, is_delete)
VALUES (1000, 500, 10, CURDATE(), CURDATE() + 2, 2, 2, false);

INSERT INTO recruitment (max_salary, min_salary, number, start_recruitment, end_recruitment, career_id, job_id, is_delete)
VALUES (1000, 500, 15, CURDATE(), CURDATE() + 2, 3, 3, false);

INSERT INTO recruitment (max_salary, min_salary, number, start_recruitment, end_recruitment, career_id, job_id, is_delete)
VALUES (1000, 500, 20, CURDATE(), CURDATE() + 2, 1, 1, false);

INSERT INTO recruitment (max_salary, min_salary, number, start_recruitment, end_recruitment, career_id, job_id, is_delete)
VALUES (1000, 500, 25, CURDATE(), CURDATE() + 2, 2, 2, false);

INSERT INTO recruitment (max_salary, min_salary, number, start_recruitment, end_recruitment, career_id, job_id, is_delete)
VALUES (1000, 500, 30, CURDATE(), CURDATE() + 2, 3, 3, false);

INSERT INTO recruitment (max_salary, min_salary, number, start_recruitment, end_recruitment, career_id, job_id, is_delete)
VALUES (1000, 500, 35, CURDATE(), CURDATE() + 2, 1, 1, false);

INSERT INTO recruitment (max_salary, min_salary, number, start_recruitment, end_recruitment, career_id, job_id, is_delete)
VALUES (1000, 500, 40, CURDATE(), CURDATE() + 2, 2, 2, false);

INSERT INTO recruitment (max_salary, min_salary, number, start_recruitment, end_recruitment, career_id, job_id, is_delete)
VALUES (1000, 500, 45, CURDATE(), CURDATE() + 2, 3, 3, false);

INSERT INTO recruitment (max_salary, min_salary, number, start_recruitment, end_recruitment, career_id, job_id, is_delete)
VALUES (1000, 500, 50, CURDATE(), CURDATE() + 2, 1, 1, false);

-- RECRUITMENT_SKILL --
INSERT INTO recruitment_skill (recruitment_id, skill_id) 
VALUES (1, 1);

INSERT INTO recruitment_skill (recruitment_id, skill_id)
VALUES (2, 2);

INSERT INTO recruitment_skill (recruitment_id, skill_id) 
VALUES (3, 3);

INSERT INTO recruitment_skill (recruitment_id, skill_id) 
VALUES (4, 4);

INSERT INTO recruitment_skill (recruitment_id, skill_id)
VALUES (5, 5);

INSERT INTO recruitment_skill (recruitment_id, skill_id)
VALUES (6, 6);

INSERT INTO recruitment_skill (recruitment_id, skill_id)
VALUES (7, 1);

INSERT INTO recruitment_skill (recruitment_id, skill_id)
VALUES (8, 2);

INSERT INTO recruitment_skill (recruitment_id, skill_id)
VALUES (9, 3);

INSERT INTO recruitment_skill (recruitment_id, skill_id)
VALUES (10, 4);

-- CANDIDATE --
INSERT INTO candidate (full_name, is_delete, phone, email, sex, birth_day, status, cmnd, activity, experience_year, skill)
VALUES ('Vo Van A', 0 ,'0123456789','avo@gmail.com', 'Male', '1990/3/10', 'Open', '215460589', 'c', 1, 'Java');

INSERT INTO candidate (full_name, is_delete, phone, email, sex, birth_day, status, cmnd, activity, experience_year, skill)
VALUES ('Vo Van A', 0 ,'0123456789', 'bvo@gmail.com', 'Male', '1991/3/10', 'Close', '215460589', 'c', 1, 'Java');

INSERT INTO candidate (full_name, is_delete, phone, email, sex, birth_day, status, cmnd, activity, experience_year, skill)
VALUES ('Vo Van A', 0 ,'0123456789', 'cvo@gmail.com', 'Male', '1992/3/10', 'INPROCESS', '215460589', 'c', 1, 'Java');

INSERT INTO candidate (full_name, is_delete, phone, email, sex, birth_day, status, cmnd, activity, experience_year, skill)
VALUES ('Vo Van A', 0 ,'0123456789', 'dvo@gmail.com', 'Male', '1993/3/10', 'INPROCESS', '215460589', 'c', 1, 'Java');

INSERT INTO candidate (full_name, is_delete, phone, email, sex, birth_day, status, cmnd, activity, experience_year, skill)
VALUES ('Vo Van A', 0 ,'0123456789', 'evo@gmail.com', 'Male', '1994/3/10', 'Open', '215460589', 'c', 1, 'Java');

INSERT INTO candidate (full_name, is_delete, phone, email, sex, birth_day, status, cmnd, activity, experience_year, skill)
VALUES ('Vo Van A', 0 ,'0123456789', 'fvo@gmail.com', 'Male', '1995/3/10', 'Open', '215460589', 'c', 1, 'Java');

INSERT INTO candidate (full_name, is_delete, phone, email, sex, birth_day, status, cmnd, activity, experience_year, skill)
VALUES ('Vo Van B', 0 ,'0123456789', 'gvo@gmail.com', 'Male', '1996/3/10', 'INPROCESS', '215460589', 'c', 1, 'Java');

INSERT INTO candidate (full_name, is_delete, phone, email, sex, birth_day, status, cmnd, activity, experience_year, skill)
VALUES ('Vo Van B', 0 ,'0123456789', 'hvo@gmail.com', 'Male', '1997/3/10', 'INPROCESS', '215460589', 'c', 1, 'Java');

INSERT INTO candidate (full_name, is_delete, phone, email, sex, birth_day, status, cmnd, activity, experience_year, skill)
VALUES ('Vo Van B', 0 ,'0123456789', 'yvo@gmail.com', 'Male', '1998/3/10', 'INPROCESS', '215460589', 'c', 1, 'Java');

INSERT INTO candidate (full_name, is_delete, phone, email, sex, birth_day, status, cmnd, activity, experience_year, skill) 
VALUES ('Vo Van K', 0 ,'0123456789', 'kvo@gmail.com', 'Male', '1999/3/10', 'Close', '215460589', 'c', 1, 'Java');

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

-- ENTRY-TEST --
INSERT INTO entry_test (point, candidate_id)
VALUES (10, 1);

INSERT INTO entry_test (point, candidate_id)
VALUES (20, 2);

INSERT INTO entry_test (point, candidate_id)
VALUES (30, 3);

INSERT INTO entry_test (point, candidate_id)
VALUES (40, 4);

INSERT INTO entry_test (point, candidate_id)
VALUES (50, 5);

INSERT INTO entry_test (point, candidate_id)
VALUES (60, 6);

INSERT INTO entry_test (point, candidate_id)
VALUES (70, 7);

INSERT INTO entry_test (point, candidate_id)
VALUES (80, 8);

INSERT INTO entry_test (point, candidate_id)
VALUES (90, 9);

INSERT INTO entry_test (point, candidate_id)
VALUES (100, 10);

-- INTERVIEW --
INSERT INTO interview (date, time_interview, location, evaluation, note, result, name_interviewer, candidate_id, round)
VALUES ('2022-4-15', '15:00:00', 'FT_1', 'GOOD', 'GOOD', 'PASS', 'HAI', 5, 1);

INSERT INTO interview (date, time_interview, location, evaluation, note, result, name_interviewer, candidate_id, round)
VALUES ('2022-4-15', '15:00:00', 'FT_1', 'GOOD', 'GOOD', 'PASS', 'HAI', 5, 2);

INSERT INTO interview (date, time_interview, location, evaluation, note, result, name_interviewer, candidate_id, round)
VALUES ('2022-4-15', '15:00:00', 'FT_1', 'GOOD', 'GOOD', 'PASS', 'HAI', 6, 1);

INSERT INTO interview (date, time_interview, location, evaluation, note, result, name_interviewer, candidate_id, round)
VALUES ('2022-4-15', '15:00:00', 'FT_1', 'GOOD', 'GOOD', 'PASS', 'HAI', 6, 2);

INSERT INTO interview (date, time_interview, location, evaluation, note, result, name_interviewer, candidate_id, round)
VALUES ('2022-4-15', '15:00:00', 'FT_1', 'GOOD', 'GOOD', 'PASS', 'HAI', 7, 1);

INSERT INTO interview (date, time_interview, location, evaluation, note, result, name_interviewer, candidate_id, round)
VALUES ('2022-4-15', '15:00:00', 'FT_1', 'GOOD', 'GOOD', 'PASS', 'HAI', 7, 2);

INSERT INTO interview (date, time_interview, location, evaluation, note, result, name_interviewer, candidate_id, round)
VALUES ('2022-4-15', '15:00:00', 'FT_1', 'GOOD', 'GOOD', 'PASS', 'HAI', 8, 1);

INSERT INTO interview (date, time_interview, location, evaluation, note, result, name_interviewer, candidate_id, round)
VALUES ('2022-4-15', '15:00:00', 'FT_1', 'GOOD', 'GOOD', 'PASS', 'HAI', 8, 2);

INSERT INTO interview (date, time_interview, location, evaluation, note, result, name_interviewer, candidate_id, round)
VALUES ('2022-4-15', '15:00:00', 'FT_1', 'GOOD', 'GOOD', 'PASS', 'HAI', 9, 1);

INSERT INTO interview (date, time_interview, location, evaluation, note, result, name_interviewer, candidate_id, round)
VALUES ('2022-4-15', '15:00:00', 'FT_1', 'GOOD', 'GOOD', 'PASS', 'HAI', 9, 2);

INSERT INTO interview (date, time_interview, location, evaluation, note, result, name_interviewer, candidate_id, round)
VALUES ('2022-4-15', '15:00:00', 'FT_1', 'GOOD', 'GOOD', 'PASS', 'HAI', 10, 1);

INSERT INTO interview (date, time_interview, location, evaluation, note, result, name_interviewer, candidate_id, round)
VALUES ('2022-4-15', '15:00:00', 'FT_1', 'GOOD', 'GOOD', 'PASS', 'HAI', 10, 2);

-- QUESTION ENTRY TEST --
INSERT INTO question_entry_test (content, option1, option2, option3, option4, answer1, answer2, answer3, answer4, skill_id)
VALUES ('????u kh??ng ph???i l?? m???t ki???u d??? li???u nguy??n thu??? trong Java', 'double', 'long', 'int', 'long double', 'null','null','null','on', 1);

INSERT INTO question_entry_test (content, option1, option2, option3, option4, answer1, answer2, answer3, answer4, skill_id)
VALUES ('Ph????ng th???c next() c???a l???p Scanner d??ng ????? l??m g??', 'Nh???p m???t s??? nguy??n', 'Nh???p m???t k?? t???', 'Nh???p m???t chu???i', 'Kh??ng c?? ph????ng th???c n??y', 'null','null','on','null', 1);

INSERT INTO question_entry_test (content, option1, option2, option3, option4, answer1, answer2, answer3, answer4, skill_id)
VALUES ('C?? m???y c??ch ????? truy???n tham s??? v??o cho m???t ph????ng th???c', '1', '2', '3', '4', 'null','on','null','null', 1);

INSERT INTO question_entry_test (content, option1, option2, option3, option4, answer1, answer2, answer3, answer4, skill_id)
VALUES ('OOP c?? m???y t??nh ch???t', '1', '2', '3', '4', 'null','null','null','on', 1);

INSERT INTO question_entry_test (content, option1, option2, option3, option4, answer1, answer2, answer3, answer4, skill_id)
VALUES ('T??nh n??ng n??o kh??ng ph???i l?? t??nh n??ng c???a Java', 'Dynamic (?????ng)', 'Architecture Neutral (?????c l???p v???i c???u tr??c)', 'Use of pointers (S??? d???ng c??c ??i???m tr???)', 'Object-oriented (H?????ng ?????i t?????ng) ', 'null','null','on','null', 1);

INSERT INTO question_entry_test (content, option1, option2, option3, option4, answer1, answer2, answer3, answer4, skill_id)
VALUES ('File ch???a m?? ngu???n java sau khi ???????c bi??n d???ch c?? ??u??i l?? g??', ' .java', ' .class', ' .jar', ' .exe', 'null','on','null','null', 1);

INSERT INTO question_entry_test (content, option1, option2, option3, option4, answer1, answer2, answer3, answer4, skill_id)
VALUES ('Java ch???y tr??n h??? ??i???u h??nh n??o sau ????y:', 'Microsoft Windows', 'Linux', 'Sun Solaris OS', 'T???t c??? c??c ????p ??n ?????u ????ng', 'null','null','null','on', 1);

INSERT INTO question_entry_test (content, option1, option2, option3, option4, answer1, answer2, answer3, answer4, skill_id)
VALUES ('C??u l???nh khai b??o chu???n cho c??ch main nh?? th??? n??o', 'public static void main(String[] a) {}', 'public static int main(String args) {}', 'public static main(String[] args) {}', 'public static final void main(String[] args) {}', 'on','null','null','null', 1);

INSERT INTO question_entry_test (content, option1, option2, option3, option4, answer1, answer2, answer3, answer4, skill_id)
VALUES ('M???t l???p trong Java c?? th??? c?? bao nhi??u l???p cha', '1', '2', '3', '4', 'on','null','null','null', 1);

INSERT INTO question_entry_test (content, option1, option2, option3, option4, answer1, answer2, answer3, answer4, skill_id)
VALUES ('M???t l???p trong Java c?? bao nhi??u l???p con', '1', '2', '3', 'V?? S???', 'null','null','null','on', 1);
