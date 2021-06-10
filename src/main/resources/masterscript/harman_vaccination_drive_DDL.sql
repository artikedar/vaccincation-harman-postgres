DROP SCHEMA IF EXISTS covid cascade;
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
GRANT all ON table covid.revinfo TO vacadmin;

/*employee master*/
DROP TABLE IF EXISTS covid.employee_master;
DROP SEQUENCE IF EXISTS covid.employee_master_seq;
CREATE SEQUENCE covid.employee_master_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 999999
    START 1
	CACHE 1
	NO CYCLE;

CREATE TABLE covid.employee_master
(
    empmasterid       int4        NOT NULL DEFAULT nextval('covid.employee_master_seq'::regclass),
    empid             int4        NOT NULL,
    fullname          varchar(255) NOT NULL,
    dateOfJoining     timestamp   NOT NULL,
    grade             varchar(25) NOT NULL,
    workplaceLocation varchar(25) NOT NULL,
    employmentType    int2        NOT NULL,
    createdon         timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    createdby         varchar(255) NULL,
    modifiedon        timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    modifiedby        varchar(255) NULL,
    isactive          bool        NOT NULL DEFAULT true,
    CONSTRAINT employee_master_pk PRIMARY KEY (empmasterid)
);
grant all ON SEQUENCE covid.employee_master_seq TO vacadmin;
GRANT all ON table covid.employee_master TO vacadmin;


/*Audit */

DROP TABLE IF EXISTS covid.employee_master_aud;

CREATE TABLE covid.employee_master_aud
(
    empmasterid       int4 NOT NULL,
    empid             int4 NULL,
    fullname          varchar(255)  NULL,
    dateOfJoining     timestamp NULL,
    grade             varchar(25) NULL,
    workplaceLocation varchar(25) NULL,
    employmentType    int2 NULL,
    rev               int4 NULL,
    revtype           int2,
    createdon         timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    createdby         varchar(255) NULL,
    modifiedon        timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    modifiedby        varchar(255) NULL,
    isactive          bool NULL DEFAULT true,
    CONSTRAINT employee_master_aud_pk PRIMARY KEY (empmasterid,rev)
);
grant all ON table covid.employee_master_aud TO vacadmin;

/*person*/
DROP TABLE IF EXISTS covid.person;
DROP SEQUENCE IF EXISTS covid.person_seq;
CREATE SEQUENCE covid.person_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 999999
    START 1
	CACHE 1
	NO CYCLE;

CREATE TABLE covid.person
(
    personId       int4        NOT NULL DEFAULT nextval('covid.person_seq'::regclass),
    fullName       varchar(255) NOT NULL,
    personAge int2 NOT NULL,
    gender    int2        NOT NULL,
    isDoseI    bool        NOT NULL DEFAULT false,
    dateOfDoseI    timestamp NULL,
    isDoseII    bool        NOT NULL DEFAULT false,
    dateOfDoseI    timestamp NULL,
    vacType    int2        NOT NULL,
    emailId       varchar(255) NOT NULL,
    country       varchar(255) NOT NULL,
    dateOfBirth    timestamp NULL,
    grade             varchar(25) NOT NULL,
    location int2         NULL,
    empmasterid          int4        NOT NULL,
    appointmentid		   int4		 NULL,
    createdon         timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    createdby         varchar(255) NULL,
    modifiedon        timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    modifiedby        varchar(255) NULL,
    isactive          bool        NOT NULL DEFAULT true,
    CONSTRAINT personId_pk PRIMARY KEY (personId),
    CONSTRAINT fk_empmaster_person_empMasterId FOREIGN KEY (empmasterid) REFERENCES covid.employee_master(empmasterid)
);
grant all ON SEQUENCE covid.person_seq TO vacadmin;
GRANT all ON table covid.person TO vacadmin;


/*Audit */

DROP TABLE IF EXISTS covid.person_aud;

CREATE TABLE covid.person_aud
(
    personId       int4     NULL,
    fullName       varchar(255)  NULL,
    personAge int2  NULL,
    gender    int2         NULL,
    isDoseI    bool         NULL DEFAULT false,
    dateOfDoseI    timestamp NULL,
    isDoseII    bool         NULL DEFAULT false,
    dateOfDoseI    timestamp NULL,
    vacType    int2         NULL,
    emailId       varchar(255)  NULL,
    country       varchar(255)  NULL,
    dateOfBirth    timestamp NULL,
    grade             varchar(25)  NULL,
    location int2         NULL,
    empmasterid          int4         NULL,
    appointmentid		int4		 NULL,
    createdon         timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    createdby         varchar(255) NULL,
    modifiedon        timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    modifiedby        varchar(255) NULL,
    isactive          bool         NULL DEFAULT true,
    rev               int4 NULL,
    revtype           int2,
    CONSTRAINT personId_aud_pk PRIMARY KEY (personId,rev)
);
grant all ON table covid.person_aud TO vacadmin;

/*employee vaccine schedule info*/
DROP TABLE IF EXISTS covid.employee_vacc_appointment_info;
DROP SEQUENCE IF EXISTS covid.employee_vacc_appointment_info_seq;
CREATE SEQUENCE covid.employee_vacc_appointment_info_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 999999
    START 1
	CACHE 1
	NO CYCLE;

CREATE TABLE covid.employee_vacc_appointment_info
(
    empVaccAppId       int4        NOT NULL DEFAULT nextval('covid.employee_vacc_appointment_info_seq'::regclass),
    personId int4        NOT null,
    dateOfVaccination date NOT NULL,
    status             int2 NOT NULL,
    isBookingActive         bool        NOT NULL DEFAULT false,
    doseLevel    int2        NOT NULL,
    location    int2        NOT NULL,
    vacType    int2        NOT NULL,
    slotNo    int2        NOT NULL,
    createdon  timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    createdby  varchar(255) NULL,
    modifiedon timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    modifiedby varchar(255) NULL,
    isactive bool NOT NULL DEFAULT true,
    CONSTRAINT employee_vacc_appointment_info_empvaccappid_pk PRIMARY KEY (empVaccAppId),
    CONSTRAINT fk_person_employee_vacc_appointment_info_personId FOREIGN KEY (personId) REFERENCES covid.person(personId)
);
grant all ON SEQUENCE covid.employee_vacc_appointment_info_seq TO vacadmin;
GRANT all ON table covid.employee_vacc_appointment_info TO vacadmin;


/*Audit */

DROP TABLE IF EXISTS covid.employee_vacc_appointment_info_aud;

CREATE TABLE covid.employee_vacc_appointment_info_aud
(
    empVaccAppId       int4         NULL ,
    personId int4         null,
    dateOfVaccination date  NULL,
    status             int2  NULL,
    isBookingActive         bool         NULL DEFAULT false,
    doseLevel    int2         NULL,
    location    int2         NULL,
    vacType    int2         NULL,
    slotNo    int2         NULL,
    rev               int4 NULL,
    revtype           int2,
    createdon         timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    createdby         varchar(255) NULL,
    modifiedon        timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    modifiedby        varchar(255) NULL,
    isactive          bool NULL DEFAULT true,
    CONSTRAINT employee_vacc_appointment_info_empvaccappid_aud_pk PRIMARY KEY (empVaccAppId,rev)
);
grant all ON table covid.employee_vacc_appointment_info_aud TO vacadmin;


DROP TABLE  IF EXISTS  covid.lov_type cascade;
CREATE TABLE covid.lov_type (
	lovtypeid int2 NOT NULL,
	"name" varchar(255) NULL,
	displayname varchar(255) NULL,
	isactive bool NULL DEFAULT true,
	CONSTRAINT lov_type_pkey PRIMARY KEY (lovtypeid)
);
DROP TABLE  IF EXISTS  covid.lov;
DROP SEQUENCE  IF EXISTS covid.lov_seq CASCADE;

CREATE SEQUENCE covid.lov_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 999999
	START 1
	CACHE 1
	NO CYCLE;

CREATE TABLE covid.lov (
	lovid int4 NOT NULL DEFAULT nextval('covid.LOV_SEQ'::regclass),
	lovtypeid int2 NULL,
	lovsequence varchar(255) NULL,
	valueid int4 NULL,
	value varchar(255) NULL,
	isactive bool NULL DEFAULT true,
	CONSTRAINT lov_pkey PRIMARY KEY (lovid),
	CONSTRAINT fk_lov_lov_1 FOREIGN KEY (lovtypeid) REFERENCES covid.lov_type(lovtypeid)
);
GRANT all ON SEQUENCE covid.lov_seq TO vacadmin;
GRANT all ON table covid.lov_type TO vacadmin;
GRANT all ON table covid.lov TO vacadmin;

/******************************************LOV TYPE*********************************************************/
INSERT INTO covid.lov_type(lovtypeid, name, displayname)VALUES (1, 'Location', 'Location');
INSERT INTO covid.lov_type(lovtypeid, name, displayname)VALUES (2, 'DoseNum', 'Dose Number');
INSERT INTO covid.lov_type(lovtypeid, name, displayname)VALUES (3, 'VacType', 'Vaccination Type');
INSERT INTO covid.lov_type(lovtypeid, name, displayname)VALUES (4, 'VacSlots', 'Vaccination Slots');
INSERT INTO covid.lov_type(lovtypeid, name, displayname)VALUES (5, 'EmpType', 'Employment Type');
INSERT INTO covid.lov_type(lovtypeid, name, displayname)VALUES (6, 'AppointmentStatus', 'Appointment Status');

/******************************************LOV VALUES*********************************************************/
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),1,1,1,'PUNE',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),1,2,2,'BANGLORE',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),1,3,3,'GURGAON',true);

INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),2,1,1,'Dose-1',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),2,2,2,'Dose-2',true);

INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),3,1,1,'Covaxin',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),3,2,2,'Covishield',true);

INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),4,1,1,'9:30-10:30',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),4,2,2,'10:30-11:30',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),4,3,3,'11:30-12:30',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),4,4,4,'2:30-3:30',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),4,5,5,'3:30-4:30',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),4,6,6,'4:30-5:30',true);

INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),5,1,1,'HR',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),5,2,2,'Engineer',true);

INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),6,1,1,'BOOKED',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),6,2,2,'CANCELED',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),6,3,3,'RESCHEDULED',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),6,4,4,'COMPLETED',true);


/* vaccine inventory */
DROP SEQUENCE IF EXISTS covid.vaccine_inventory_seq;
DROP TABLE IF EXISTS covid.vaccine_inventory;
CREATE SEQUENCE covid.vaccine_inventory_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 999999
    START 1
	CACHE 1
	NO CYCLE;

CREATE TABLE covid.vaccine_inventory
(
    vacInvId       int4        NOT NULL DEFAULT nextval('covid.vaccine_inventory_seq'::regclass),
    vacType    int2        NOT NULL,
    dateofavailability     DATE   NOT NULL,
    totalNoOfDoses   int2     NOT    NULL,
    noOfBookedDoses   int2     NOT    NULL,
    noOfAvailableDoses   int2     NOT    NULL,
    location    int2        NOT NULL,
    createdon  timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    createdby  varchar(255) NULL,
    modifiedon timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    modifiedby varchar(255) NULL,
    isactive bool NOT NULL DEFAULT true,
    CONSTRAINT vacInvId_pk PRIMARY KEY (vacInvId)

);
grant all ON SEQUENCE covid.vaccine_inventory_seq TO vacadmin;
GRANT all ON table covid.vaccine_inventory TO vacadmin;


/*Audit */

DROP TABLE IF EXISTS covid.vaccine_inventory_aud;

CREATE TABLE covid.vaccine_inventory_aud
(
    vacInvId       int4         NULL DEFAULT nextval('covid.vaccine_inventory_seq'::regclass),
    vacType    int2         NULL,
    dateofavailability     date    NULL,
    totalNoOfDoses   int2         NULL,
    noOfBookedDoses   int2         NULL ,
    noOfAvailableDoses   int2         NULL,
    location    int2         NULL,
    rev               int4 NULL,
    revtype           int2,
    createdon  timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    createdby  varchar(255) NULL,
    modifiedon timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    modifiedby varchar(255) NULL,
    isactive bool  NULL DEFAULT true,
    CONSTRAINT vacInvId_aud_pk PRIMARY KEY (vacInvId,rev)
);
grant all ON table covid.vaccine_inventory_aud TO vacadmin;

/* slot info */

DROP TABLE IF EXISTS covid.slot_info;
DROP SEQUENCE IF EXISTS covid.slot_info_seq;
CREATE SEQUENCE covid.slot_info_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 999999
    START 1
	CACHE 1
	NO CYCLE;

CREATE TABLE covid.slot_info
(
    slotInfoId       int4        NOT NULL DEFAULT nextval('covid.slot_info_seq'::regclass),
    location    int2        NOT NULL,
    slotNo    int2        NOT NULL,
    totalNoOfDoses   int2        NOT NULL,
    noOfBookedDoses   int2        NOT NULL,
    noOfAvailableDoses   int2        NOT NULL,
    vacInvId       int4        NOT null,
    createdon  timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    createdby  varchar(255) NULL,
    modifiedon timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    modifiedby varchar(255) NULL,
    isactive bool NOT NULL DEFAULT true,
    CONSTRAINT slotInfoId_pk PRIMARY KEY (slotInfoId),
    CONSTRAINT fk_vacInv_slotInfo_vacInvId FOREIGN KEY (vacInvId) REFERENCES covid.vaccine_inventory(vacInvId)
);
grant all ON SEQUENCE covid.slot_info_seq TO vacadmin;
GRANT all ON table covid.slot_info TO vacadmin;


/*Audit */

DROP TABLE IF EXISTS covid.slot_info_aud;

CREATE TABLE covid.slot_info_aud
(
    slotInfoId       int4         NULL DEFAULT nextval('covid.slot_info_seq'::regclass),
    location    int2         NULL,
    slotNo    int2         NULL,
    rev               int4 NULL,
    revtype           int2,
    totalNoOfDoses   int2         NULL,
    noOfBookedDoses   int2         NULL,
    noOfAvailableDoses   int2         NULL,
    vacInvId       int4         null,
    createdon  timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    createdby  varchar(255) NULL,
    modifiedon timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    modifiedby varchar(255) NULL,
    isactive bool  NULL DEFAULT true,
    CONSTRAINT slotInfoId_aud_pk PRIMARY KEY (slotInfoId, rev)
);
grant all ON table covid.slot_info_aud TO vacadmin;

