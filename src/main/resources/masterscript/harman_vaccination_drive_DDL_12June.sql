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

CREATE USER vacadmin WITH PASSWORD 'HarmanV@#12';
CREATE DATABASE  harmanvac with owner = vacadmin;

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

--
-- TOC entry 214 (class 1259 OID 17450)
-- Name: employee_master_seq; Type: SEQUENCE; Schema: covid; Owner: vacadmin
--

CREATE SEQUENCE covid.employee_master_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 999999
    CACHE 1;


ALTER TABLE covid.employee_master_seq OWNER TO vacadmin;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 17452)
-- Name: employee_master; Type: TABLE; Schema: covid; Owner: vacadmin
--

CREATE TABLE covid.employee_master (
    empmasterid integer DEFAULT nextval('covid.employee_master_seq'::regclass) NOT NULL,
    empid character varying(255) NOT NULL,
    fullname character varying(255) NOT NULL,
    dob date NOT NULL,
    employmenttype smallint NOT NULL,
    createdon timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    createdby character varying(255),
    modifiedon timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    modifiedby character varying(255),
    isactive boolean DEFAULT true NOT NULL,
    email character varying(255)
);


ALTER TABLE covid.employee_master OWNER TO vacadmin;

--
-- TOC entry 222 (class 1259 OID 17602)
-- Name: employee_master_aud; Type: TABLE; Schema: covid; Owner: vacadmin
--

CREATE TABLE covid.employee_master_aud (
    empmasterid integer NOT NULL,
    empid character varying(255) NOT NULL,
    fullname character varying(255) NOT NULL,
    email character varying(255),
    dob date NOT NULL,
    employmenttype smallint NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    createdon timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    createdby character varying(255),
    modifiedon timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    modifiedby character varying(255),
    isactive boolean DEFAULT true
);


ALTER TABLE covid.employee_master_aud OWNER TO vacadmin;

--
-- TOC entry 203 (class 1259 OID 17101)
-- Name: employee_vacc_appointment_info_seq; Type: SEQUENCE; Schema: covid; Owner: vacadmin
--

CREATE SEQUENCE covid.employee_vacc_appointment_info_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 999999
    CACHE 1;


ALTER TABLE covid.employee_vacc_appointment_info_seq OWNER TO vacadmin;

--
-- TOC entry 204 (class 1259 OID 17103)
-- Name: employee_vacc_appointment_info; Type: TABLE; Schema: covid; Owner: vacadmin
--

CREATE TABLE covid.employee_vacc_appointment_info (
    empvaccappid integer DEFAULT nextval('covid.employee_vacc_appointment_info_seq'::regclass) NOT NULL,
    personid integer NOT NULL,
    dateofvaccination date NOT NULL,
    status smallint NOT NULL,
    isbookingactive boolean DEFAULT false NOT NULL,
    doselevel smallint NOT NULL,
    location smallint NOT NULL,
    vactype smallint NOT NULL,
    slotno smallint NOT NULL,
    createdon timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    createdby character varying(255),
    modifiedon timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    modifiedby character varying(255),
    isactive boolean DEFAULT true NOT NULL
);


ALTER TABLE covid.employee_vacc_appointment_info OWNER TO vacadmin;

--
-- TOC entry 205 (class 1259 OID 17121)
-- Name: employee_vacc_appointment_info_aud; Type: TABLE; Schema: covid; Owner: vacadmin
--

CREATE TABLE covid.employee_vacc_appointment_info_aud (
    empvaccappid integer NOT NULL,
    personid integer,
    dateofvaccination date,
    status smallint,
    isbookingactive boolean DEFAULT false,
    doselevel smallint,
    location smallint,
    vactype smallint,
    slotno smallint,
    rev integer NOT NULL,
    revtype smallint,
    createdon timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    createdby character varying(255),
    modifiedon timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    modifiedby character varying(255),
    isactive boolean DEFAULT true
);


ALTER TABLE covid.employee_vacc_appointment_info_aud OWNER TO vacadmin;

--
-- TOC entry 201 (class 1259 OID 17035)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: covid; Owner: vacadmin
--

CREATE SEQUENCE covid.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE covid.hibernate_sequence OWNER TO vacadmin;

--
-- TOC entry 220 (class 1259 OID 17569)
-- Name: lov_seq; Type: SEQUENCE; Schema: covid; Owner: vacadmin
--

CREATE SEQUENCE covid.lov_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 999999
    CACHE 1;


ALTER TABLE covid.lov_seq OWNER TO vacadmin;

--
-- TOC entry 221 (class 1259 OID 17571)
-- Name: lov; Type: TABLE; Schema: covid; Owner: vacadmin
--

CREATE TABLE covid.lov (
    lovid integer DEFAULT nextval('covid.lov_seq'::regclass) NOT NULL,
    lovtypeid smallint,
    lovsequence character varying(255),
    valueid integer,
    value character varying(255),
    isactive boolean DEFAULT true
);


ALTER TABLE covid.lov OWNER TO vacadmin;

--
-- TOC entry 219 (class 1259 OID 17560)
-- Name: lov_type; Type: TABLE; Schema: covid; Owner: vacadmin
--

CREATE TABLE covid.lov_type (
    lovtypeid smallint NOT NULL,
    name character varying(255),
    displayname character varying(255),
    isactive boolean DEFAULT true
);


ALTER TABLE covid.lov_type OWNER TO vacadmin;

--
-- TOC entry 216 (class 1259 OID 17475)
-- Name: person_seq; Type: SEQUENCE; Schema: covid; Owner: vacadmin
--

CREATE SEQUENCE covid.person_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 999999
    CACHE 1;


ALTER TABLE covid.person_seq OWNER TO vacadmin;

--
-- TOC entry 217 (class 1259 OID 17477)
-- Name: person; Type: TABLE; Schema: covid; Owner: vacadmin
--

CREATE TABLE covid.person (
    personid integer DEFAULT nextval('covid.person_seq'::regclass) NOT NULL,
    fullname character varying(255) NOT NULL,
    manipalid character varying(255),
    cowinid character varying(255),
    isregistered boolean DEFAULT false NOT NULL,
    dateofdosei date,
    vactype smallint DEFAULT 2 NOT NULL,
    empmasterid integer NOT NULL,
    createdon timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    createdby character varying(255),
    modifiedon timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    modifiedby character varying(255),
    isactive boolean DEFAULT true NOT NULL
);


ALTER TABLE covid.person OWNER TO vacadmin;

--
-- TOC entry 218 (class 1259 OID 17495)
-- Name: person_aud; Type: TABLE; Schema: covid; Owner: vacadmin
--

CREATE TABLE covid.person_aud (
    personid integer NOT NULL,
    fullname character varying(255) NOT NULL,
    manipalid character varying(255),
    cowinid character varying(255),
    isregistered boolean DEFAULT false NOT NULL,
    dateofdosei date,
    vactype smallint NOT NULL,
    empmasterid integer NOT NULL,
    createdon timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    createdby character varying(255),
    modifiedon timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    modifiedby character varying(255),
    isactive boolean DEFAULT true,
    rev integer NOT NULL,
    revtype smallint
);


ALTER TABLE covid.person_aud OWNER TO vacadmin;

--
-- TOC entry 202 (class 1259 OID 17037)
-- Name: revinfo; Type: TABLE; Schema: covid; Owner: vacadmin
--

CREATE TABLE covid.revinfo (
    rev integer NOT NULL,
    revtstmp bigint
);


ALTER TABLE covid.revinfo OWNER TO vacadmin;

--
-- TOC entry 209 (class 1259 OID 17185)
-- Name: slot_info_seq; Type: SEQUENCE; Schema: covid; Owner: vacadmin
--

CREATE SEQUENCE covid.slot_info_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 999999
    CACHE 1;


ALTER TABLE covid.slot_info_seq OWNER TO vacadmin;

--
-- TOC entry 210 (class 1259 OID 17187)
-- Name: slot_info; Type: TABLE; Schema: covid; Owner: vacadmin
--

CREATE TABLE covid.slot_info (
    slotinfoid integer DEFAULT nextval('covid.slot_info_seq'::regclass) NOT NULL,
    location smallint NOT NULL,
    slotno smallint NOT NULL,
    totalnoofdoses smallint NOT NULL,
    noofbookeddoses smallint NOT NULL,
    noofavailabledoses smallint NOT NULL,
    vacinvid integer NOT NULL,
    createdon timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    createdby character varying(255),
    modifiedon timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    modifiedby character varying(255),
    isactive boolean DEFAULT true NOT NULL
);


ALTER TABLE covid.slot_info OWNER TO vacadmin;

--
-- TOC entry 211 (class 1259 OID 17204)
-- Name: slot_info_aud; Type: TABLE; Schema: covid; Owner: vacadmin
--

CREATE TABLE covid.slot_info_aud (
    slotinfoid integer DEFAULT nextval('covid.slot_info_seq'::regclass) NOT NULL,
    location smallint,
    slotno smallint,
    rev integer NOT NULL,
    revtype smallint,
    totalnoofdoses smallint,
    noofbookeddoses smallint,
    noofavailabledoses smallint,
    vacinvid integer,
    createdon timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    createdby character varying(255),
    modifiedon timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    modifiedby character varying(255),
    isactive boolean DEFAULT true
);


ALTER TABLE covid.slot_info_aud OWNER TO vacadmin;

--
-- TOC entry 212 (class 1259 OID 17221)
-- Name: spring_session; Type: TABLE; Schema: covid; Owner: vacadmin
--

CREATE TABLE covid.spring_session (
    primary_id character(36) NOT NULL,
    session_id character(36) NOT NULL,
    creation_time bigint NOT NULL,
    last_access_time bigint NOT NULL,
    max_inactive_interval integer NOT NULL,
    expiry_time bigint NOT NULL,
    principal_name character varying(100)
);


ALTER TABLE covid.spring_session OWNER TO vacadmin;

--
-- TOC entry 213 (class 1259 OID 17229)
-- Name: spring_session_attributes; Type: TABLE; Schema: covid; Owner: vacadmin
--

CREATE TABLE covid.spring_session_attributes (
    session_primary_id character(36) NOT NULL,
    attribute_name character varying(200) NOT NULL,
    attribute_bytes bytea NOT NULL
);


ALTER TABLE covid.spring_session_attributes OWNER TO vacadmin;

--
-- TOC entry 206 (class 1259 OID 17159)
-- Name: vaccine_inventory_seq; Type: SEQUENCE; Schema: covid; Owner: vacadmin
--

CREATE SEQUENCE covid.vaccine_inventory_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 999999
    CACHE 1;


ALTER TABLE covid.vaccine_inventory_seq OWNER TO vacadmin;

--
-- TOC entry 207 (class 1259 OID 17161)
-- Name: vaccine_inventory; Type: TABLE; Schema: covid; Owner: vacadmin
--

CREATE TABLE covid.vaccine_inventory (
    vacinvid integer DEFAULT nextval('covid.vaccine_inventory_seq'::regclass) NOT NULL,
    vactype smallint NOT NULL,
    dateofavailability date NOT NULL,
    totalnoofdoses smallint NOT NULL,
    noofbookeddoses smallint NOT NULL,
    noofavailabledoses smallint NOT NULL,
    location smallint NOT NULL,
    createdon timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    createdby character varying(255),
    modifiedon timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    modifiedby character varying(255),
    isactive boolean DEFAULT true NOT NULL
);


ALTER TABLE covid.vaccine_inventory OWNER TO vacadmin;

--
-- TOC entry 208 (class 1259 OID 17173)
-- Name: vaccine_inventory_aud; Type: TABLE; Schema: covid; Owner: vacadmin
--

CREATE TABLE covid.vaccine_inventory_aud (
    vacinvid integer DEFAULT nextval('covid.vaccine_inventory_seq'::regclass) NOT NULL,
    vactype smallint,
    dateofavailability date,
    totalnoofdoses smallint,
    noofbookeddoses smallint,
    noofavailabledoses smallint,
    location smallint,
    rev integer NOT NULL,
    revtype smallint,
    createdon timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    createdby character varying(255),
    modifiedon timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    modifiedby character varying(255),
    isactive boolean DEFAULT true
);


ALTER TABLE covid.vaccine_inventory_aud OWNER TO vacadmin;


INSERT INTO covid.lov VALUES (1, 1, '1', 1, 'PUNE', true);
INSERT INTO covid.lov VALUES (2, 1, '2', 2, 'BANGLORE', true);
INSERT INTO covid.lov VALUES (3, 1, '3', 3, 'GURGAON', true);
INSERT INTO covid.lov VALUES (4, 2, '1', 1, 'Dose-1', true);
INSERT INTO covid.lov VALUES (5, 2, '2', 2, 'Dose-2', true);
INSERT INTO covid.lov VALUES (6, 3, '1', 1, 'Covaxin', true);
INSERT INTO covid.lov VALUES (7, 3, '2', 2, 'Covishield', true);
INSERT INTO covid.lov VALUES (8, 4, '1', 1, '9:30-9:45', true);
INSERT INTO covid.lov VALUES (9, 4, '2', 2, '9:45-10:00', true);
INSERT INTO covid.lov VALUES (10, 4, '3', 3, '10:00-10:15', true);
INSERT INTO covid.lov VALUES (11, 4, '4', 4, '10:15-10:30', true);
INSERT INTO covid.lov VALUES (12, 4, '5', 5, '10:30-10:45', true);
INSERT INTO covid.lov VALUES (13, 4, '6', 6, '10:45-11:00', true);
INSERT INTO covid.lov VALUES (14, 4, '7', 7, '11:00-11:15', true);
INSERT INTO covid.lov VALUES (15, 4, '8', 8, '11:15-11:30', true);
INSERT INTO covid.lov VALUES (16, 4, '9', 9, '11:30-11:45', true);
INSERT INTO covid.lov VALUES (17, 4, '10', 10, '11:45-12:00', true);
INSERT INTO covid.lov VALUES (18, 4, '11', 11, '12:00-12:15', true);
INSERT INTO covid.lov VALUES (19, 4, '12', 12, '12:15-12:30', true);
INSERT INTO covid.lov VALUES (20, 4, '13', 13, '12:30-12:45', true);
INSERT INTO covid.lov VALUES (21, 4, '14', 14, '12:45-1:00', true);
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
INSERT INTO covid.lov VALUES (32, 5, '1', 1, 'HR', true);
INSERT INTO covid.lov VALUES (33, 5, '2', 2, 'Engineer', true);
INSERT INTO covid.lov VALUES (34, 6, '1', 1, 'BOOKED', true);
INSERT INTO covid.lov VALUES (35, 6, '2', 2, 'CANCELED', true);
INSERT INTO covid.lov VALUES (36, 6, '3', 3, 'RESCHEDULED', true);
INSERT INTO covid.lov VALUES (37, 6, '4', 4, 'COMPLETED', true);


--
-- TOC entry 3164 (class 0 OID 17560)
-- Dependencies: 219
-- Data for Name: lov_type; Type: TABLE DATA; Schema: covid; Owner: vacadmin
--

INSERT INTO covid.lov_type VALUES (1, 'Location', 'Location', true);
INSERT INTO covid.lov_type VALUES (2, 'DoseNum', 'Dose Number', true);
INSERT INTO covid.lov_type VALUES (3, 'VacType', 'Vaccination Type', true);
INSERT INTO covid.lov_type VALUES (4, 'VacSlots', 'Vaccination Slots', true);
INSERT INTO covid.lov_type VALUES (5, 'EmpType', 'Employment Type', true);
INSERT INTO covid.lov_type VALUES (6, 'AppointmentStatus', 'Appointment Status', true);



ALTER TABLE ONLY covid.employee_master_aud
    ADD CONSTRAINT employee_master_aud_pk PRIMARY KEY (empmasterid, rev);


--
-- TOC entry 2999 (class 2606 OID 17601)
-- Name: employee_master employee_master_empid_un; Type: CONSTRAINT; Schema: covid; Owner: vacadmin
--

ALTER TABLE ONLY covid.employee_master
    ADD CONSTRAINT employee_master_empid_un UNIQUE (empid);


--
-- TOC entry 3001 (class 2606 OID 17463)
-- Name: employee_master employee_master_pk; Type: CONSTRAINT; Schema: covid; Owner: vacadmin
--

ALTER TABLE ONLY covid.employee_master
    ADD CONSTRAINT employee_master_pk PRIMARY KEY (empmasterid);


--
-- TOC entry 2982 (class 2606 OID 17132)
-- Name: employee_vacc_appointment_info_aud employee_vacc_appointment_info_empvaccappid_aud_pk; Type: CONSTRAINT; Schema: covid; Owner: vacadmin
--

ALTER TABLE ONLY covid.employee_vacc_appointment_info_aud
    ADD CONSTRAINT employee_vacc_appointment_info_empvaccappid_aud_pk PRIMARY KEY (empvaccappid, rev);


--
-- TOC entry 2980 (class 2606 OID 17115)
-- Name: employee_vacc_appointment_info employee_vacc_appointment_info_empvaccappid_pk; Type: CONSTRAINT; Schema: covid; Owner: vacadmin
--

ALTER TABLE ONLY covid.employee_vacc_appointment_info
    ADD CONSTRAINT employee_vacc_appointment_info_empvaccappid_pk PRIMARY KEY (empvaccappid);


--
-- TOC entry 3009 (class 2606 OID 17580)
-- Name: lov lov_pkey; Type: CONSTRAINT; Schema: covid; Owner: vacadmin
--

ALTER TABLE ONLY covid.lov
    ADD CONSTRAINT lov_pkey PRIMARY KEY (lovid);


--
-- TOC entry 3007 (class 2606 OID 17568)
-- Name: lov_type lov_type_pkey; Type: CONSTRAINT; Schema: covid; Owner: vacadmin
--

ALTER TABLE ONLY covid.lov_type
    ADD CONSTRAINT lov_type_pkey PRIMARY KEY (lovtypeid);


--
-- TOC entry 3005 (class 2606 OID 17506)
-- Name: person_aud personid_aud_pk; Type: CONSTRAINT; Schema: covid; Owner: vacadmin
--

ALTER TABLE ONLY covid.person_aud
    ADD CONSTRAINT personid_aud_pk PRIMARY KEY (personid, rev);


--
-- TOC entry 3003 (class 2606 OID 17489)
-- Name: person personid_pk; Type: CONSTRAINT; Schema: covid; Owner: vacadmin
--

ALTER TABLE ONLY covid.person
    ADD CONSTRAINT personid_pk PRIMARY KEY (personid);


--
-- TOC entry 2978 (class 2606 OID 17041)
-- Name: revinfo revinfo_pkey; Type: CONSTRAINT; Schema: covid; Owner: vacadmin
--

ALTER TABLE ONLY covid.revinfo
    ADD CONSTRAINT revinfo_pkey PRIMARY KEY (rev);


--
-- TOC entry 2990 (class 2606 OID 17215)
-- Name: slot_info_aud slotinfoid_aud_pk; Type: CONSTRAINT; Schema: covid; Owner: vacadmin
--

ALTER TABLE ONLY covid.slot_info_aud
    ADD CONSTRAINT slotinfoid_aud_pk PRIMARY KEY (slotinfoid, rev);


--
-- TOC entry 2988 (class 2606 OID 17198)
-- Name: slot_info slotinfoid_pk; Type: CONSTRAINT; Schema: covid; Owner: vacadmin
--

ALTER TABLE ONLY covid.slot_info
    ADD CONSTRAINT slotinfoid_pk PRIMARY KEY (slotinfoid);


--
-- TOC entry 2997 (class 2606 OID 17236)
-- Name: spring_session_attributes spring_session_attributes_pk; Type: CONSTRAINT; Schema: covid; Owner: vacadmin
--

ALTER TABLE ONLY covid.spring_session_attributes
    ADD CONSTRAINT spring_session_attributes_pk PRIMARY KEY (session_primary_id, attribute_name);


--
-- TOC entry 2995 (class 2606 OID 17225)
-- Name: spring_session spring_session_pk; Type: CONSTRAINT; Schema: covid; Owner: vacadmin
--

ALTER TABLE ONLY covid.spring_session
    ADD CONSTRAINT spring_session_pk PRIMARY KEY (primary_id);


--
-- TOC entry 2986 (class 2606 OID 17184)
-- Name: vaccine_inventory_aud vacinvid_aud_pk; Type: CONSTRAINT; Schema: covid; Owner: vacadmin
--

ALTER TABLE ONLY covid.vaccine_inventory_aud
    ADD CONSTRAINT vacinvid_aud_pk PRIMARY KEY (vacinvid, rev);


--
-- TOC entry 2984 (class 2606 OID 17172)
-- Name: vaccine_inventory vacinvid_pk; Type: CONSTRAINT; Schema: covid; Owner: vacadmin
--

ALTER TABLE ONLY covid.vaccine_inventory
    ADD CONSTRAINT vacinvid_pk PRIMARY KEY (vacinvid);


--
-- TOC entry 2991 (class 1259 OID 17226)
-- Name: spring_session_ix1; Type: INDEX; Schema: covid; Owner: vacadmin
--

CREATE UNIQUE INDEX spring_session_ix1 ON covid.spring_session USING btree (session_id);


--
-- TOC entry 2992 (class 1259 OID 17227)
-- Name: spring_session_ix2; Type: INDEX; Schema: covid; Owner: vacadmin
--

CREATE INDEX spring_session_ix2 ON covid.spring_session USING btree (expiry_time);


--
-- TOC entry 2993 (class 1259 OID 17228)
-- Name: spring_session_ix3; Type: INDEX; Schema: covid; Owner: vacadmin
--

CREATE INDEX spring_session_ix3 ON covid.spring_session USING btree (principal_name);


--
-- TOC entry 3014 (class 2606 OID 17490)
-- Name: person fk_empmaster_person_empmasterid; Type: FK CONSTRAINT; Schema: covid; Owner: vacadmin
--

ALTER TABLE ONLY covid.person
    ADD CONSTRAINT fk_empmaster_person_empmasterid FOREIGN KEY (empmasterid) REFERENCES covid.employee_master(empmasterid);


--
-- TOC entry 3015 (class 2606 OID 17581)
-- Name: lov fk_lov_lov_1; Type: FK CONSTRAINT; Schema: covid; Owner: vacadmin
--

ALTER TABLE ONLY covid.lov
    ADD CONSTRAINT fk_lov_lov_1 FOREIGN KEY (lovtypeid) REFERENCES covid.lov_type(lovtypeid);


--
-- TOC entry 3012 (class 2606 OID 17199)
-- Name: slot_info fk_vacinv_slotinfo_vacinvid; Type: FK CONSTRAINT; Schema: covid; Owner: vacadmin
--

ALTER TABLE ONLY covid.slot_info
    ADD CONSTRAINT fk_vacinv_slotinfo_vacinvid FOREIGN KEY (vacinvid) REFERENCES covid.vaccine_inventory(vacinvid);


--
-- TOC entry 3013 (class 2606 OID 17237)
-- Name: spring_session_attributes spring_session_attributes_fk; Type: FK CONSTRAINT; Schema: covid; Owner: vacadmin
--

ALTER TABLE ONLY covid.spring_session_attributes
    ADD CONSTRAINT spring_session_attributes_fk FOREIGN KEY (session_primary_id) REFERENCES covid.spring_session(primary_id) ON DELETE CASCADE;


-- Completed on 2021-06-12 09:27:45

--
-- PostgreSQL database dump complete
--

