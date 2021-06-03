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
    CONSTRAINT employee_vacc_appointment_info_empvaccappid_aud_pk PRIMARY KEY (empVaccAppId)
);
grant all ON table covid.employee_vacc_appointment_info_aud TO vacadmin;