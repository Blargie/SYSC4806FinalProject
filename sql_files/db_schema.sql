-- Instructions:
    -- 1. Ensure that you have PostgreSQL and pgAdmin4 installed on your machine, and that
    --    they are included in the PATH environment variables of your system
    -- 2. In pgAdmin4, create a new database called sysc4806-project with password 7553
    --    (if you want to change dbname/password, ensure changes are made to postgrest.conf)
    -- 3. Using the query tool, run the contents of this file to create the database
    --    schema and the web_anon role
    -- 4. In order to run postgrest, you must run the following console command, from the
    --    directory where postgrest.conf is located:
    --    postgrest postgrest.conf

    -- Considerations: Include a db_dump.sql file that creates a ton of entries into the
    -- database to test the functionality of the application

-- Survey Table Creation
CREATE TABLE IF NOT EXISTS Survey (
    "surveyId" SERIAL PRIMARY KEY,
--     userId INT NOT NULL,
    "surveyName" VARCHAR(255) NOT NULL,
    "surveyDescription" VARCHAR(255),
    "isOpen" BOOLEAN NOT NULL,
    "isAnonymous" BOOLEAN,
    "expirationDate" TIMESTAMP,
    "createdAt" TIMESTAMP

--     FOREIGN KEY ("userId") REFERENCES User("userId")
);
-- Question Tables Creation
CREATE TABLE IF NOT EXISTS Question (
    "questionId" SERIAL PRIMARY KEY,
    "questionText" VARCHAR(255) NOT NULL,
    "surveyId" INT NOT NULL,
    "required" BOOLEAN NOT NULL,

    FOREIGN KEY ("surveyId") REFERENCES Survey("surveyId")
);

CREATE TABLE IF NOT EXISTS MultipleChoiceQuestion (
    "mcqId" SERIAL PRIMARY KEY,
    "questionId" INT NOT NULL,
    "numAnswers" INT NOT NULL,

    FOREIGN KEY ("questionId") REFERENCES Question("questionId")
);

CREATE TABLE IF NOT EXISTS MultipleChoiceOption (
    "optionId" SERIAL PRIMARY KEY,
    "mcqId" INT NOT NULL,
    "optionText" VARCHAR(255) NOT NULL,

    FOREIGN KEY ("mcqId") REFERENCES MultipleChoiceQuestion("mcqId")
);

CREATE TABLE IF NOT EXISTS NumericRangeQuestion (
    "nrqId" SERIAL PRIMARY KEY,
    "questionId" INT NOT NULL,
    "upperBound" INT NOT NULL,
    "lowerBound" INT NOT NULL,

    FOREIGN KEY ("questionId") REFERENCES Question("questionId")
);

CREATE TABLE IF NOT EXISTS TextQuestion (
    "tqId" SERIAL PRIMARY KEY,
    "questionId" INT NOT NULL,
    "charLimit" INT,

    FOREIGN KEY ("questionId") REFERENCES Question("questionId")
);

-- Answer Table Creation
CREATE TABLE IF NOT EXISTS Answer (
    "answerId" SERIAL PRIMARY KEY,
--     userId INT NOT NULL,
    "surveyId" INT NOT NULL,
    "questionId" INT NOT NULL,

--     FOREIGN KEY (userId) REFERENCES User(userId),
    FOREIGN KEY ("surveyId") REFERENCES Survey("surveyId"),
    FOREIGN KEY ("questionId") REFERENCES Question("questionId")
);

CREATE TABLE IF NOT EXISTS MultipleChoiceAnswer (
    "mcaId" SERIAL PRIMARY KEY,
    "answerId" INT NOT NULL,
    "selectedOption" INT NOT NULL,
    "selectedOptionText" VARCHAR(255),

    FOREIGN KEY ("answerId") REFERENCES Answer("answerId"),
    FOREIGN KEY ("selectedOption") REFERENCES MultipleChoiceOption("optionId")
);

CREATE TABLE IF NOT EXISTS NumericRangeAnswer (
    "nraId" SERIAL PRIMARY KEY,
    "answerId" INT NOT NULL,
    "choice" INT NOT NULL,

    FOREIGN KEY ("answerId") REFERENCES Answer("answerId")
);

CREATE TABLE IF NOT EXISTS TextAnswer (
    "taId" SERIAL PRIMARY KEY,
    "answerId" INT NOT NULL,
    "text" VARCHAR(255),

    FOREIGN KEY ("answerId") REFERENCES Answer("answerId")
);

-- Users Table Creation
-- CREATE TABLE IF NOT EXISTS User (
--     "userId" SERIAL PRIMARY KEY,
--     "username" VARCHAR(255) NOT NULL,
--     "password" VARCHAR(255) NOT NULL,
--     "email" VARCHAR(255) NOT NULL,
--     "isAdmin" BOOLEAN NOT NULL
-- );

-- Create web_anon role
-- CREATE ROLE web_anon NOLOGIN;
-- Note: If web_anon exists, there is no need to create the role again

-- Grant Permissions to web_anon
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO web_anon;
-- Possibly need to execute after table creation

-- Test
CREATE TABLE IF NOT EXISTS Test (
    "testId" SERIAL PRIMARY KEY,
    "testText" VARCHAR(255) NOT NULL
);

-- Insert Test Data
INSERT INTO Test ("testText") VALUES ('Hello World');
INSERT INTO Test ("testText") VALUES ('Goodbye World');
INSERT INTO Test ("testText") VALUES ('Meow :3');
INSERT INTO Test ("testText") VALUES ('Woof :{');
INSERT INTO Test ("testText") VALUES ('Moo :D');

-- Potential Fixes for some web_anon issues
-- SELECT pg_get_serial_sequence('survey', 'surveyId');
-- CREATE SEQUENCE survey_surveyId_seq START 1;
-- ALTER TABLE survey ALTER COLUMN surveyId SET DEFAULT nextval('survey_surveyId_seq');
-- GRANT USAGE, SELECT ON SEQUENCE survey_surveyId_seq TO web_anon;


