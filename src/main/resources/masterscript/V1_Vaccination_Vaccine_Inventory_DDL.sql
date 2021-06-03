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
    vacInvId       int4        NOT NULL DEFAULT nextval('covid.employee_vacc_sch_info_seq'::regclass),
    vacType    int2        NOT NULL,
    dateofavailability     DATE   NOT NULL,
    noOfDoses   int2        NOT NULL,
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
    noOfDoses   int2         NULL,
    location    int2         NULL,
    rev               int4 NULL,
    revtype           int2,
    createdon  timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    createdby  varchar(255) NULL,
    modifiedon timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    modifiedby varchar(255) NULL,
    isactive bool  NULL DEFAULT true,
    CONSTRAINT vacInvId_aud_pk PRIMARY KEY (vacInvId)
);
grant all ON table covid.vaccine_inventory_aud TO vacadmin;