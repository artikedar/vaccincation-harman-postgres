/*employee vaccine schedule info*/
DROP TABLE IF EXISTS covid.employee_vacc_sch_info;
DROP SEQUENCE IF EXISTS covid.employee_vacc_sch_info_seq;
CREATE SEQUENCE covid.employee_vacc_sch_info_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 999999
    START 1
	CACHE 1
	NO CYCLE;

CREATE TABLE covid.employee_vacc_sch_info
(
    empVaccSchId       int4        NOT NULL DEFAULT nextval('covid.employee_vacc_sch_info_seq'::regclass),
    empmasterid int4 NOT NULL,
    dateOfVaccination     timestamp   NOT NULL,
    slotAllocated    int2        NOT NULL,
    bookingStatus             varchar(25) NOT NULL,
    vaccineStatus             varchar(25) NOT NULL,
    isBookingActive         bool        NOT NULL DEFAULT false,
    doseLevel    int2        NOT NULL,
    location    int2        NOT NULL,
    vacType    int2        NOT NULL,
    createdon  timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    createdby  varchar(255) NULL,
    modifiedon timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    modifiedby varchar(255) NULL,
    isactive bool NOT NULL DEFAULT true,
    CONSTRAINT empVaccSchId_pk PRIMARY KEY (empVaccSchId),
    CONSTRAINT fk_empmaster_employee_vacc_sch_info_empmasterid FOREIGN KEY (empmasterid) REFERENCES covid.employee_master(empmasterid)
);
grant all ON SEQUENCE covid.employee_vacc_sch_info_seq TO vacadmin;
GRANT all ON table covid.employee_vacc_sch_info TO vacadmin;


/*Audit */

DROP TABLE IF EXISTS covid.employee_vacc_sch_info_aud;

CREATE TABLE covid.employee_vacc_sch_info_aud
(

    empVaccSchId       int4  NULL,
    empmasterid int4  NULL,
    dateOfVaccination     timestamp    NULL,
    slotAllocated    int2         NULL,
    bookingStatus varchar(25)  NULL,
    vaccineStatus varchar(25)  NULL,
    isBookingActive bool NULL,
    doseLevel    int2  NULL,
    location    int2 NULL,
    vacType    int2  NULL,
    rev               int4 NULL,
    revtype           int2,
    createdon         timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    createdby         varchar(255) NULL,
    modifiedon        timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    modifiedby        varchar(255) NULL,
    isactive          bool NULL DEFAULT true,
    CONSTRAINT empVaccSchId_aud_pk PRIMARY KEY (empVaccSchId)
);
grant all ON table covid.employee_vacc_sch_info_aud TO vacadmin;




