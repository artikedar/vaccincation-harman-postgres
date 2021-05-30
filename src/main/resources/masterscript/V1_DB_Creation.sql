/*HAL Link
http://localhost:17002/harman/vaccination/v1/explorer/index.html#uri=/harman/vaccination/v1
*/

/* DB , USER , SCHEMA CREATION, PERMISSION*/
CREATE USER vacadmin WITH PASSWORD 'HarmanV@#12';
CREATE DATABASE  harmanvac with
owner = vacadmin;
CREATE SCHEMA covid AUTHORIZATION vacadmin;
GRANT ALL PRIVILEGES ON ALL TABLES IN schema covid TO vacadmin;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA covid TO vacadmin;
GRANT ALL PRIVILEGES ON DATABASE harmanvac TO vacadmin;

CREATE SEQUENCE covid.hibernate_sequence ;
DROP TABLE IF EXISTS covid.revinfo;

CREATE TABLE covid.revinfo(
                              rev integer NOT NULL,
                              revtstmp bigint,
                              CONSTRAINT revinfo_pkey PRIMARY KEY (rev)
);