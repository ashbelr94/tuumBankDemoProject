CREATE USER DbTest with PASSWORD 'test' CREATEDB;

-- SELECT 'CREATE DATABASE "account-serviceDb" WITH OWNER = DbTest
-- ENCODING = ''UTF8''
-- LC_COLLATE = ''en_US.utf8''
-- LC_CTYPE = ''en_US.utf8''
-- TABLESPACE = pg_default
-- CONNECTION LIMIT = -1;' WHERE NOT EXISTS(SELECT FROM pg_database WHERE datname = 'account-serviceDb')\gexec;
--
-- SELECT 'CREATE DATABASE "transaction-serviceDb" WITH OWNER = DbTest
-- ENCODING = ''UTF8''
-- LC_COLLATE = ''en_US.utf8''
-- LC_CTYPE = ''en_US.utf8''
-- TABLESPACE = pg_default
-- CONNECTION LIMIT = -1;' WHERE NOT EXISTS(SELECT FROM pg_database WHERE datname = 'transaction-serviceDb')\gexec;

CREATE DATABASE "account-serviceDb" WITH OWNER = DbTest
ENCODING = 'UTF8'
LC_COLLATE = 'en_US.utf8'
LC_CTYPE = 'en_US.utf8'
TABLESPACE = pg_default
CONNECTION LIMIT = -1;

CREATE DATABASE "transaction-serviceDb" WITH OWNER = DbTest
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;