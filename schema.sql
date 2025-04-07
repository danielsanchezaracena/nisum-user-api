DROP TABLE IF EXISTS phone;
DROP TABLE IF EXISTS myuser;

CREATE TABLE myuser (
                      id UUID PRIMARY KEY,
                      name VARCHAR(255),
                      email VARCHAR(255) UNIQUE,
                      password VARCHAR(255),
                      token VARCHAR(255),
                      isactive BOOLEAN,
                      created TIMESTAMP,
                      modified TIMESTAMP,
                      last_login TIMESTAMP
);

CREATE TABLE phone (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       number VARCHAR(20),
                       citycode VARCHAR(10),
                       countrycode VARCHAR(10),
                       user_id UUID,
                       FOREIGN KEY (user_id) REFERENCES myuser(id)
);
