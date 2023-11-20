CREATE TABLE IF NOT EXISTS students
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS subscriptions
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id     BIGINT NOT NULL,
    school_page_id BIGINT NOT NULL,
    UNIQUE (school_page_id, student_id)
);
