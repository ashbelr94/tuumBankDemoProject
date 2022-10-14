CREATE USER DbTest with PASSWORD 'test' CREATEDB;

SELECT 'CREATE DATABASE "account-serviceDb"' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'account-serviceDb')\gexec

SELECT 'CREATE DATABASE "transaction-serviceDb"' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'transaction-serviceDb')\gexec

SELECT datname  FROM pg_database;