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
    dateOfDoseII    timestamp NULL,
    vacType    int2        NOT NULL,
    emailId       varchar(255) NOT NULL,
    country       varchar(255) NOT NULL,
    dateOfBirth    timestamp NULL,
    grade             varchar(25) NOT NULL,
    location int2         NULL,

    empmasterid          int4        NOT NULL,
    appointmentid		int4		 NULL,
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
    dateOfDoseII    timestamp NULL,
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
    CONSTRAINT personId_aud_pk PRIMARY KEY (personId)
);
grant all ON table covid.person_aud TO vacadmin;