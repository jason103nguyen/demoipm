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

/*-- JOB_SKILL --
INSERT INTO job_skill (job_id, skill_id) 
VALUES (1, 1);

INSERT INTO job_skill (job_id, skill_id)
VALUES (1, 8);

INSERT INTO job_skill (job_id, skill_id) 
VALUES (2, 2);

INSERT INTO job_skill (job_id, skill_id) 
VALUES (3, 3);*/

-- RECRUITMENT --
INSERT INTO recruitment (max_salary, min_salary, number, start_recruitment, end_recruitment, career_id, job_id, is_delete)
VALUES (1000, 500, 5, CURDATE(), CURDATE() + 5, 1, 1, false);

INSERT INTO recruitment (max_salary, min_salary, number, start_recruitment, end_recruitment, career_id, job_id, is_delete)
VALUES (1000, 500, 10, CURDATE(), CURDATE() + 5, 2, 2, false);

INSERT INTO recruitment (max_salary, min_salary, number, start_recruitment, end_recruitment, career_id, job_id, is_delete)
VALUES (1000, 500, 15, CURDATE(), CURDATE() + 5, 3, 3, false);

INSERT INTO recruitment (max_salary, min_salary, number, start_recruitment, end_recruitment, career_id, job_id, is_delete)
VALUES (1000, 500, 20, CURDATE(), CURDATE() + 5, 1, 1, false);

INSERT INTO recruitment (max_salary, min_salary, number, start_recruitment, end_recruitment, career_id, job_id, is_delete)
VALUES (1000, 500, 25, CURDATE(), CURDATE() + 5, 2, 2, false);

INSERT INTO recruitment (max_salary, min_salary, number, start_recruitment, end_recruitment, career_id, job_id, is_delete)
VALUES (1000, 500, 30, CURDATE(), CURDATE() + 5, 3, 3, false);

INSERT INTO recruitment (max_salary, min_salary, number, start_recruitment, end_recruitment, career_id, job_id, is_delete)
VALUES (1000, 500, 35, CURDATE(), CURDATE() + 5, 1, 1, false);

INSERT INTO recruitment (max_salary, min_salary, number, start_recruitment, end_recruitment, career_id, job_id, is_delete)
VALUES (1000, 500, 40, CURDATE(), CURDATE() + 5, 2, 2, false);

INSERT INTO recruitment (max_salary, min_salary, number, start_recruitment, end_recruitment, career_id, job_id, is_delete)
VALUES (1000, 500, 45, CURDATE(), CURDATE() + 5, 3, 3, false);

INSERT INTO recruitment (max_salary, min_salary, number, start_recruitment, end_recruitment, career_id, job_id, is_delete)
VALUES (1000, 500, 50, CURDATE(), CURDATE() + 5, 1, 1, false);

/*-- RECRUITMENT_SKILL --
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
VALUES (10, 4);*/

-- CANDIDATE --
INSERT INTO candidate (full_name, phone, email, sex, birth_day)
VALUES ('Vo Van A', '0123456789', 'avo@gmail.com', 'nam', '1996/3/10');

INSERT INTO candidate (full_name, phone, email, sex, birth_day) 
VALUES ('Vo Van B', '0123456789', 'bvo@gmail.com', 'nam', '1996/3/10');

INSERT INTO candidate (full_name, phone, email, sex, birth_day) 
VALUES ('Vo Van C', '0123456789', 'cvo@gmail.com', 'nam', '1996/3/10');

-- QUESTION ENTRY TEST --
INSERT INTO question_entry_test (content, option1, option2, option3, option4, answer1, answer2, answer3, answer4, skill_id)
VALUES ('Đâu không phải là một kiểu dữ liệu nguyên thuỷ trong Java', 'double', 'long', 'int', 'long double', 'null','null','null','on', 1);

INSERT INTO question_entry_test (content, option1, option2, option3, option4, answer1, answer2, answer3, answer4, skill_id)
VALUES ('Phương thức next() của lớp Scanner dùng để làm gì', 'Nhập một số nguyên', 'Nhập một ký tự', 'Nhập một chuỗi', 'Không có phương thức này', 'null','null','on','null', 1);

INSERT INTO question_entry_test (content, option1, option2, option3, option4, answer1, answer2, answer3, answer4, skill_id)
VALUES ('Có mấy cách để truyền tham số vào cho một phương thức', '1', '2', '3', '4', 'null','on','null','null', 1);

INSERT INTO question_entry_test (content, option1, option2, option3, option4, answer1, answer2, answer3, answer4, skill_id)
VALUES ('OOP có mấy tính chất', '1', '2', '3', '4', 'null','null','null','on', 1);

INSERT INTO question_entry_test (content, option1, option2, option3, option4, answer1, answer2, answer3, answer4, skill_id)
VALUES ('Tính năng nào không phải là tính năng của Java', 'Dynamic (Động)', 'Architecture Neutral (Độc lập với cấu trúc)', 'Use of pointers (Sử dụng các điểm trỏ)', 'Object-oriented (Hướng đối tượng) ', 'null','null','on','null', 1);

INSERT INTO question_entry_test (content, option1, option2, option3, option4, answer1, answer2, answer3, answer4, skill_id)
VALUES ('File chứa mã nguồn java sau khi được biên dịch có đuôi là gì', ' .java', ' .class', ' .jar', ' .exe', 'null','on','null','null', 1);

INSERT INTO question_entry_test (content, option1, option2, option3, option4, answer1, answer2, answer3, answer4, skill_id)
VALUES ('Java chạy trên hệ điều hành nào sau đây:', 'Microsoft Windows', 'Linux', 'Sun Solaris OS', 'Tất cả các đáp án đều đúng', 'null','null','null','on', 1);

INSERT INTO question_entry_test (content, option1, option2, option3, option4, answer1, answer2, answer3, answer4, skill_id)
VALUES ('Câu lệnh khai báo chuẩn cho cách main như thế nào', 'public static void main(String[] a) {}', 'public static int main(String args) {}', 'public static main(String[] args) {}', 'public static final void main(String[] args) {}', 'on','null','null','null', 1);

INSERT INTO question_entry_test (content, option1, option2, option3, option4, answer1, answer2, answer3, answer4, skill_id)
VALUES ('Một lớp trong Java có thể có bao nhiêu lớp cha', '1', '2', '3', '4', 'on','null','null','null', 1);

INSERT INTO question_entry_test (content, option1, option2, option3, option4, answer1, answer2, answer3, answer4, skill_id)
VALUES ('Một lớp trong Java có bao nhiêu lớp con', '1', '2', '3', 'Vô Số', 'null','null','null','on', 1);

/*-- INTERVIEW --
INSERT INTO interview (time_interview, local, evaluation, note, result, name_interviewer, candidate_id)
VALUES ('2021/12/20', 'FT_1', 'GOOD', 'GOOD', 'PASS', 'HAI', 1);

INSERT INTO interview (time_interview, local, evaluation, note, result, name_interviewer, candidate_id)
VALUES ('2021/12/20', 'FT_1', 'GOOD', 'GOOD', 'PASS', 'HAI', 2);

INSERT INTO interview (time_interview, local, evaluation, note, result, name_interviewer, candidate_id)
VALUES ('2021/12/20', 'FT_1', 'GOOD', 'GOOD', 'PASS', 'HAI', 3);*/