CREATE TABLE IF NOT EXISTS administrators (
    id BIGINT AUTO_INCREMENT PRIMARY KEY ,
    name VARCHAR(100) NOT NULL,
    UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS school_pages (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              administrator_id BIGINT NOT NULL,
                              location VARCHAR(100) NOT NULL,
                              name VARCHAR(100) NOT NULL,
                              UNIQUE (location, name)
);
