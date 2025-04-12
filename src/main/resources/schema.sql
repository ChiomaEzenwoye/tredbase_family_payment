-- Create Parent table
CREATE TABLE IF NOT EXISTS parent (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(255) NOT NULL,
    balance DECIMAL(10, 2) NOT NULL
    );

-- Create Student table
CREATE TABLE IF NOT EXISTS student (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL,
    balance DECIMAL(10, 2) NOT NULL
    );

-- Create Parent-Student join table
CREATE TABLE IF NOT EXISTS parent_student (
                                              parent_id BIGINT NOT NULL,
                                              student_id BIGINT NOT NULL,
                                              FOREIGN KEY (parent_id) REFERENCES parent(id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE,
    PRIMARY KEY (parent_id, student_id)
    );
