-- Insert initial data
INSERT INTO parent (name, balance) VALUES ('Parent A', 1000.0);
INSERT INTO parent (name, balance) VALUES ('Parent B', 1000.0);

INSERT INTO student (name, balance) VALUES ('Student Shared', 0.0);
INSERT INTO student (name, balance) VALUES ('Student A Only', 0.0);
INSERT INTO student (name, balance) VALUES ('Student B Only', 0.0);

-- Create relationships
INSERT INTO parent_student (parent_id, student_id) VALUES (1, 1);
INSERT INTO parent_student (parent_id, student_id) VALUES (2, 1);
INSERT INTO parent_student (parent_id, student_id) VALUES (1, 2);
INSERT INTO parent_student (parent_id, student_id) VALUES (2, 3);

