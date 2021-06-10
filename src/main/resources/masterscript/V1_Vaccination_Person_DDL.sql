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
    manipalId       varchar(255)  NULL,
    cowinid       varchar(255)  NULL,
    isRegistered    bool        NOT NULL DEFAULT false,
    dateOfDoseI   date  NULL,
    vacType    int2        NOT NULL,
    empmasterid          int4        NOT NULL,
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
    fullName       varchar(255) NOT NULL,
    manipalId       varchar(255)  NULL,
    cowinid       varchar(255)  NULL,
    isRegistered    bool        NOT NULL DEFAULT false,
    dateOfDoseI   date  NULL,
    vacType    int2        NOT NULL,
    empmasterid          int4        NOT NULL,
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