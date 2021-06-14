--
-- PostgreSQL database dump
--

-- Dumped from database version 13.3
-- Dumped by pg_dump version 13.3

-- Started on 2021-06-12 09:27:40

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 17034)
-- Name: covid; Type: SCHEMA; Schema: -; Owner: vacadmin
--
--
--CREATE USER vacadmin WITH PASSWORD 'HarmanV@#12';
--CREATE DATABASE  harmanvac with owner = vacadmin;
DROP SCHEMA IF EXISTS covid cascade;

CREATE SCHEMA covid AUTHORIZATION vacadmin;

ALTER SCHEMA covid OWNER TO vacadmin;

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
GRANT all ON table covid.revinfo TO vacadmin;


DROP SEQUENCE IF EXISTS covid.employee_master_seq;
CREATE SEQUENCE covid.employee_master_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 999999
	START 1
	CACHE 1
	NO CYCLE;

DROP TABLE IF EXISTS covid.employee_master;
CREATE TABLE covid.employee_master (
	empmasterid int4 NOT NULL DEFAULT nextval('covid.employee_master_seq'::regclass),
	empid varchar(255) NOT NULL,
	fullname varchar(255) NOT NULL,
	dob date NOT NULL,
	employmenttype int2 NOT NULL,
	createdon timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	createdby varchar(255) NULL,
	modifiedon timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	modifiedby varchar(255) NULL,
	isactive bool NOT NULL DEFAULT true,
	email varchar(255) NULL,
	CONSTRAINT employee_master_empid_un UNIQUE (empid),
	CONSTRAINT employee_master_pk PRIMARY KEY (empmasterid)
);

DROP TABLE IF EXISTS covid.employee_master_aud;
CREATE TABLE covid.employee_master_aud (
	empmasterid int4 NOT NULL,
	empid varchar(255) NOT NULL,
	fullname varchar(255) NOT NULL,
	email varchar(255) NULL,
	dob date NOT NULL,
	employmenttype int2 NOT NULL,
	rev int4 NOT NULL,
	revtype int2 NULL,
	createdon timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	createdby varchar(255) NULL,
	modifiedon timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	modifiedby varchar(255) NULL,
	isactive bool NULL DEFAULT true,
	CONSTRAINT employee_master_aud_pk PRIMARY KEY (empmasterid, rev)
);

DROP SEQUENCE IF EXISTS covid.employee_vacc_appointment_info_seq;
CREATE SEQUENCE covid.employee_vacc_appointment_info_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 999999
	START 1
	CACHE 1
	NO CYCLE;

DROP TABLE IF EXISTS covid.employee_vacc_appointment_info;

CREATE TABLE covid.employee_vacc_appointment_info (
	empvaccappid int4 NOT NULL DEFAULT nextval('covid.employee_vacc_appointment_info_seq'::regclass),
	personid int4 NOT NULL,
	dateofvaccination date NOT NULL,
	status int2 NOT NULL,
	isbookingactive bool NOT NULL DEFAULT false,
	doselevel int2 NOT NULL,
	"location" int2 NOT NULL,
	vactype int2 NOT NULL,
	slotno int2 NOT NULL,
	createdon timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	createdby varchar(255) NULL,
	modifiedon timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	modifiedby varchar(255) NULL,
	isactive bool NOT NULL DEFAULT true,
	CONSTRAINT employee_vacc_appointment_info_empvaccappid_pk PRIMARY KEY (empvaccappid)
);

DROP TABLE IF EXISTS covid.employee_vacc_appointment_info_aud;

CREATE TABLE covid.employee_vacc_appointment_info_aud (
	empvaccappid int4 NOT NULL,
	personid int4 NULL,
	dateofvaccination date NULL,
	status int2 NULL,
	isbookingactive bool NULL DEFAULT false,
	doselevel int2 NULL,
	"location" int2 NULL,
	vactype int2 NULL,
	slotno int2 NULL,
	rev int4 NOT NULL,
	revtype int2 NULL,
	createdon timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	createdby varchar(255) NULL,
	modifiedon timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	modifiedby varchar(255) NULL,
	isactive bool NULL DEFAULT true,
	CONSTRAINT employee_vacc_appointment_info_empvaccappid_aud_pk PRIMARY KEY (empvaccappid, rev)
);

DROP TABLE IF EXISTS covid.lov_type;

CREATE TABLE covid.lov_type (
	lovtypeid int2 NOT NULL,
	"name" varchar(255) NULL,
	displayname varchar(255) NULL,
	isactive bool NULL DEFAULT true,
	CONSTRAINT lov_type_pkey PRIMARY KEY (lovtypeid)
);


DROP SEQUENCE IF EXISTS covid.lov_seq;

CREATE SEQUENCE covid.lov_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 999999
	START 1
	CACHE 1
	NO CYCLE;

DROP TABLE IF EXISTS covid.lov;
CREATE TABLE covid.lov (
	lovid int4 NOT NULL DEFAULT nextval('covid.lov_seq'::regclass),
	lovtypeid int2 NULL,
	lovsequence varchar(255) NULL,
	valueid int4 NULL,
	value varchar(255) NULL,
	isactive bool NULL DEFAULT true,
	CONSTRAINT lov_pkey PRIMARY KEY (lovid)
);
ALTER TABLE covid.lov ADD CONSTRAINT fk_lov_lov_1 FOREIGN KEY (lovtypeid) REFERENCES covid.lov_type(lovtypeid);

DROP SEQUENCE IF EXISTS covid.person_seq;

CREATE SEQUENCE covid.person_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 999999
	START 1
	CACHE 1
	NO CYCLE;

-- covid.person definition
 DROP TABLE IF EXISTS covid.person;

CREATE TABLE covid.person (
	personid int4 NOT NULL DEFAULT nextval('covid.person_seq'::regclass),
	fullname varchar(255) NOT NULL,
	manipalid varchar(255) NULL,
	cowinid varchar(255) NULL,
	isregistered bool NOT NULL DEFAULT false,
	dateofdosei date NULL,
	vactype int2 NOT NULL DEFAULT 2,
	empmasterid int4 NOT NULL,
	createdon timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	createdby varchar(255) NULL,
	modifiedon timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	modifiedby varchar(255) NULL,
	isactive bool NOT NULL DEFAULT true,
	CONSTRAINT personid_pk PRIMARY KEY (personid)
);
ALTER TABLE covid.person ADD CONSTRAINT fk_empmaster_person_empmasterid FOREIGN KEY (empmasterid) REFERENCES covid.employee_master(empmasterid);

DROP TABLE IF EXISTS covid.person_aud;

CREATE TABLE covid.person_aud (
	personid int4 NOT NULL,
	fullname varchar(255) NOT NULL,
	manipalid varchar(255) NULL,
	cowinid varchar(255) NULL,
	isregistered bool NOT NULL DEFAULT false,
	dateofdosei date NULL,
	vactype int2 NOT NULL,
	empmasterid int4 NOT NULL,
	createdon timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	createdby varchar(255) NULL,
	modifiedon timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	modifiedby varchar(255) NULL,
	isactive bool NULL DEFAULT true,
	rev int4 NOT NULL,
	revtype int2 NULL,
	CONSTRAINT personid_aud_pk PRIMARY KEY (personid, rev)
);

DROP SEQUENCE IF EXISTS covid.vaccine_inventory_seq;

CREATE SEQUENCE covid.vaccine_inventory_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 999999
	START 1
	CACHE 1
	NO CYCLE;

-- covid.vaccine_inventory definition

DROP TABLE IF EXISTS covid.vaccine_inventory;

CREATE TABLE covid.vaccine_inventory (
	vacinvid int4 NOT NULL DEFAULT nextval('covid.vaccine_inventory_seq'::regclass),
	vactype int2 NOT NULL,
	dateofavailability date NOT NULL,
	totalnoofdoses int2 NOT NULL,
	noofbookeddoses int2 NOT NULL,
	noofavailabledoses int2 NOT NULL,
	"location" int2 NOT NULL,
	createdon timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	createdby varchar(255) NULL,
	modifiedon timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	modifiedby varchar(255) NULL,
	isactive bool NOT NULL DEFAULT true,
	CONSTRAINT vacinvid_pk PRIMARY KEY (vacinvid)
);

DROP TABLE IF EXISTS covid.vaccine_inventory_aud;

CREATE TABLE covid.vaccine_inventory_aud (
	vacinvid int4 NOT NULL DEFAULT nextval('covid.vaccine_inventory_seq'::regclass),
	vactype int2 NULL,
	dateofavailability date NULL,
	totalnoofdoses int2 NULL,
	noofbookeddoses int2 NULL,
	noofavailabledoses int2 NULL,
	"location" int2 NULL,
	rev int4 NOT NULL,
	revtype int2 NULL,
	createdon timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	createdby varchar(255) NULL,
	modifiedon timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	modifiedby varchar(255) NULL,
	isactive bool NULL DEFAULT true,
	CONSTRAINT vacinvid_aud_pk PRIMARY KEY (vacinvid, rev)
);

DROP SEQUENCE IF EXISTS covid.slot_info_seq;

CREATE SEQUENCE covid.slot_info_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 999999
	START 1
	CACHE 1
	NO CYCLE;

DROP TABLE IF EXISTS covid.slot_info;

CREATE TABLE covid.slot_info (
	slotinfoid int4 NOT NULL DEFAULT nextval('covid.slot_info_seq'::regclass),
	"location" int2 NOT NULL,
	slotno int2 NOT NULL,
	totalnoofdoses int2 NOT NULL,
	noofbookeddoses int2 NOT NULL,
	noofavailabledoses int2 NOT NULL,
	vacinvid int4 NOT NULL,
	createdon timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	createdby varchar(255) NULL,
	modifiedon timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	modifiedby varchar(255) NULL,
	isactive bool NOT NULL DEFAULT true,
	CONSTRAINT slotinfoid_pk PRIMARY KEY (slotinfoid)
);


-- covid.slot_info foreign keys

ALTER TABLE covid.slot_info ADD CONSTRAINT fk_vacinv_slotinfo_vacinvid FOREIGN KEY (vacinvid) REFERENCES covid.vaccine_inventory(vacinvid);

DROP TABLE IF EXISTS covid.slot_info_aud;

CREATE TABLE covid.slot_info_aud (
	slotinfoid int4 NOT NULL DEFAULT nextval('covid.slot_info_seq'::regclass),
	"location" int2 NULL,
	slotno int2 NULL,
	rev int4 NOT NULL,
	revtype int2 NULL,
	totalnoofdoses int2 NULL,
	noofbookeddoses int2 NULL,
	noofavailabledoses int2 NULL,
	vacinvid int4 NULL,
	createdon timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	createdby varchar(255) NULL,
	modifiedon timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	modifiedby varchar(255) NULL,
	isactive bool NULL DEFAULT true,
	CONSTRAINT slotinfoid_aud_pk PRIMARY KEY (slotinfoid, rev)
);

-- covid.spring_session definition
DROP TABLE IF EXISTS covid.spring_session;

CREATE TABLE covid.spring_session (
	primary_id bpchar(36) NOT NULL,
	session_id bpchar(36) NOT NULL,
	creation_time int8 NOT NULL,
	last_access_time int8 NOT NULL,
	max_inactive_interval int4 NOT NULL,
	expiry_time int8 NOT NULL,
	principal_name varchar(100) NULL,
	CONSTRAINT spring_session_pk PRIMARY KEY (primary_id)
);
CREATE UNIQUE INDEX spring_session_ix1 ON covid.spring_session USING btree (session_id);
CREATE INDEX spring_session_ix2 ON covid.spring_session USING btree (expiry_time);
CREATE INDEX spring_session_ix3 ON covid.spring_session USING btree (principal_name);

-- covid.spring_session_attributes definition
DROP TABLE IF EXISTS covid.spring_session_attributes;

CREATE TABLE covid.spring_session_attributes (
	session_primary_id bpchar(36) NOT NULL,
	attribute_name varchar(200) NOT NULL,
	attribute_bytes bytea NOT NULL,
	CONSTRAINT spring_session_attributes_pk PRIMARY KEY (session_primary_id, attribute_name)
);


-- covid.spring_session_attributes foreign keys

ALTER TABLE covid.spring_session_attributes ADD CONSTRAINT spring_session_attributes_fk FOREIGN KEY (session_primary_id) REFERENCES covid.spring_session(primary_id) ON DELETE CASCADE;

INSERT INTO covid.lov_type VALUES (1, 'Location', 'Location', true);
INSERT INTO covid.lov_type VALUES (2, 'DoseNum', 'Dose Number', true);
INSERT INTO covid.lov_type VALUES (3, 'VacType', 'Vaccination Type', true);
INSERT INTO covid.lov_type VALUES (4, 'VacSlots', 'Vaccination Slots', true);
INSERT INTO covid.lov_type VALUES (5, 'EmpType', 'Employment Type', true);
INSERT INTO covid.lov_type VALUES (6, 'AppointmentStatus', 'Appointment Status', true);


INSERT INTO covid.lov VALUES (1, 1, '1', 1, 'PUNE', true);
INSERT INTO covid.lov VALUES (2, 1, '2', 2, 'BANGLORE', true);
INSERT INTO covid.lov VALUES (4, 2, '1', 1, 'Dose-1', true);
INSERT INTO covid.lov VALUES (5, 2, '2', 2, 'Dose-2', true);
INSERT INTO covid.lov VALUES (6, 3, '1', 1, 'Covaxin', true);
INSERT INTO covid.lov VALUES (7, 3, '2', 2, 'Covishield', true);
INSERT INTO covid.lov VALUES (10, 4, '1', 1, '10:00-10:15', true);
INSERT INTO covid.lov VALUES (11, 4, '2', 2, '10:15-10:30', true);
INSERT INTO covid.lov VALUES (12, 4, '3', 3, '10:30-10:45', true);
INSERT INTO covid.lov VALUES (13, 4, '4', 4, '10:45-11:00', true);
INSERT INTO covid.lov VALUES (14, 4, '5', 5, '11:00-11:15', true);
INSERT INTO covid.lov VALUES (15, 4, '6', 6, '11:15-11:30', true);
INSERT INTO covid.lov VALUES (16, 4, '7', 7, '11:30-11:45', true);
INSERT INTO covid.lov VALUES (17, 4, '8', 8, '11:45-12:00', true);
INSERT INTO covid.lov VALUES (18, 4, '9', 9, '12:00-12:15', true);
INSERT INTO covid.lov VALUES (19, 4, '10', 10, '12:15-12:30', true);
INSERT INTO covid.lov VALUES (20, 4, '11', 11, '12:30-12:45', true);
INSERT INTO covid.lov VALUES (21, 4, '12', 12, '12:45-1:00', true);
INSERT INTO covid.lov VALUES (21, 4, '13', 13, '01:30-1:45', true);
INSERT INTO covid.lov VALUES (21, 4, '14', 14, '01:45-2:00', true);
INSERT INTO covid.lov VALUES (22, 4, '15', 15, '2:00-2:15', true);
INSERT INTO covid.lov VALUES (23, 4, '16', 16, '2:15-2:30', true);
INSERT INTO covid.lov VALUES (24, 4, '17', 17, '2:30-2:45', true);
INSERT INTO covid.lov VALUES (25, 4, '18', 18, '2:45-3:00', true);
INSERT INTO covid.lov VALUES (26, 4, '19', 19, '3:00-3:15', true);
INSERT INTO covid.lov VALUES (27, 4, '20', 20, '3:15-3:30', true);
INSERT INTO covid.lov VALUES (28, 4, '21', 21, '3:30-3:45', true);
INSERT INTO covid.lov VALUES (29, 4, '22', 22, '3:45-4:00', true);
INSERT INTO covid.lov VALUES (30, 4, '23', 23, '4:00-4:15', true);
INSERT INTO covid.lov VALUES (31, 4, '24', 24, '4:15-4:30', true);
INSERT INTO covid.lov VALUES (32, 4, '25', 25, '4:30-4:45', true);
INSERT INTO covid.lov VALUES (33, 5, '1', 1, 'HR', true);
INSERT INTO covid.lov VALUES (34, 5, '2', 2, 'Engineer', true);
INSERT INTO covid.lov VALUES (35, 6, '1', 1, 'BOOKED', true);
INSERT INTO covid.lov VALUES (36, 6, '2', 2, 'CANCELED', true);
INSERT INTO covid.lov VALUES (37, 6, '3', 3, 'RESCHEDULED', true);
INSERT INTO covid.lov VALUES (38, 6, '4', 4, 'COMPLETED', true);

