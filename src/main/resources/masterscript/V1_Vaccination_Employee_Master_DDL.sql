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
    /*CONSTRAINT fk_person_empmaster_personId FOREIGN KEY (personId) REFERENCES covid.person(personId)*/
);
grant all ON SEQUENCE covid.employee_master_seq TO vacadmin;
GRANT all ON table covid.employee_master TO vacadmin;


/*Audit */

DROP TABLE IF EXISTS covid.employee_master_aud;

CREATE TABLE covid.employee_master_aud
(

    empmasterid       int4 NOT NULL,
    empid             int4 NULL,
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
    CONSTRAINT employee_master_aud_pk PRIMARY KEY (empmasterid)
);
grant all ON table covid.employee_master_aud TO vacadmin;




