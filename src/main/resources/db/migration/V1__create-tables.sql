CREATE TABLE courses (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE authors (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE topics (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  message TEXT NOT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  status BOOLEAN NOT NULL DEFAULT true,

  course_id BIGINT NOT NULL,

  author_id BIGINT NOT NULL,

  FOREIGN KEY (course_id)
  REFERENCES courses(id)
  ON DELETE CASCADE,

  FOREIGN KEY (author_id)
  REFERENCES authors(id)
  ON DELETE RESTRICT
);

CREATE INDEX idx_topics_course ON topics(course_id);
CREATE INDEX idx_topics_author ON topics(author_id);
CREATE INDEX idx_topics_status ON topics(status);
