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

INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),2,1,1,'Dose-1',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),2,2,2,'Dose-2',true);

INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),3,1,1,'Covaxin',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),3,2,2,'Covishield',true);

INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),4,1,1,'10:00-10:15',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),4,2,2,'10:15-10:30',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),4,3,3,'10:30-10:45',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),4,4,4,'10:45-11:00',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),4,5,5,'11:00-11:15',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),4,6,6,'11:15-11:30',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),4,7,7,'11:30-11:45',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),4,8,8,'11:45-12:00',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),4,9,9,'12:00-12:15',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),4,10,10,'12:15-12:30',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),4,11,11,'12:30-12:45',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),4,12,12,'12:45-1:00',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),4,13,13,'1:30-1:45',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),4,14,14,'1:45-2:00',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),4,15,15,'2:00-2:15',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),4,16,16,'2:15-2:30',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),4,17,17,'2:30-2:45',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),4,18,18,'2:45-3:00',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),4,19,19,'3:00-3:15',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),4,20,20,'3:15-3:30',true);

INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),5,1,1,'HR',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),5,2,2,'Engineer',true);

INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),6,1,1,'BOOKED',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),6,2,2,'CANCELED',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),6,3,3,'RESCHEDULED',true);
INSERT INTO covid.lov(lovid, lovtypeid, lovsequence, valueid, value, isactive)	VALUES (nextval('covid.lov_seq'),6,4,4,'COMPLETED',true);
