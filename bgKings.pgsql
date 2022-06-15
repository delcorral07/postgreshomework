--
-- PostgreSQL database dump
--

-- Dumped from database version 14.3
-- Dumped by pg_dump version 14.3

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
-- Name: sc_kings; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA sc_kings;


ALTER SCHEMA sc_kings OWNER TO postgres;

--
-- Name: reign; Type: TYPE; Schema: sc_kings; Owner: postgres
--

CREATE TYPE sc_kings.reign AS (
	start_year smallint,
	end_year smallint
);


ALTER TYPE sc_kings.reign OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: t_country; Type: TABLE; Schema: sc_kings; Owner: postgres
--

CREATE TABLE sc_kings.t_country (
    id character varying NOT NULL,
    name character varying
);


ALTER TABLE sc_kings.t_country OWNER TO postgres;

--
-- Name: t_houses; Type: TABLE; Schema: sc_kings; Owner: postgres
--

CREATE TABLE sc_kings.t_houses (
    id character varying NOT NULL,
    name character varying,
    country character varying
);


ALTER TABLE sc_kings.t_houses OWNER TO postgres;

--
-- Name: t_kings; Type: TABLE; Schema: sc_kings; Owner: postgres
--

CREATE TABLE sc_kings.t_kings (
    id character varying NOT NULL,
    name character varying,
    reign character varying,
    country character varying,
    house character varying
);


ALTER TABLE sc_kings.t_kings OWNER TO postgres;

--
-- Data for Name: t_country; Type: TABLE DATA; Schema: sc_kings; Owner: postgres
--

COPY sc_kings.t_country (id, name) FROM stdin;
e0fc0f12-ebea-11ec-8ea0-0242ac120002	United Kingdom
e0fc11e2-ebea-11ec-8ea0-0242ac120002	Ireland
e0fc1318-ebea-11ec-8ea0-0242ac120002	Belgium
e0fc1598-ebea-11ec-8ea0-0242ac120002	Germany
e0fc18d6-ebea-11ec-8ea0-0242ac120002	Poland
\.


--
-- Data for Name: t_houses; Type: TABLE DATA; Schema: sc_kings; Owner: postgres
--

COPY sc_kings.t_houses (id, name, country) FROM stdin;
e0fc1c00-ebea-11ec-8ea0-0242ac120002	House of Garlic	Ireland
e0fc1d0e-ebea-11ec-8ea0-0242ac120002	Home of the Whooper	Ireland
e0fc1e1c-ebea-11ec-8ea0-0242ac120002	Mansion of the Beer	Ireland
e0fc2182-ebea-11ec-8ea0-0242ac120002	Universal house	Belgium
e0fc2290-ebea-11ec-8ea0-0242ac120002	Ottos House	Belgium
e0fc239e-ebea-11ec-8ea0-0242ac120002	Home of Pizza	Belgium
e0fc24a2-ebea-11ec-8ea0-0242ac120002	House of Weesex	Germany
e0fc25b0-ebea-11ec-8ea0-0242ac120002	House of Prantim	Germany
e0fc26aa-ebea-11ec-8ea0-0242ac120002	Home of Eliodoro	United Kingdom
e0fc292a-ebea-11ec-8ea0-0242ac120002	Mansion of the British	United Kingdom
e0fc2a38-ebea-11ec-8ea0-0242ac120002	One euro pint	United Kingdom
e0fc2b28-ebea-11ec-8ea0-0242ac120002	House of Polaroid	Poland
\.


--
-- Data for Name: t_kings; Type: TABLE DATA; Schema: sc_kings; Owner: postgres
--

COPY sc_kings.t_kings (id, name, reign, country, house) FROM stdin;
e9d79e84-ebeb-11ec-8ea0-0242ac120002	The barbarian Polish	(746,394)	Poland	House of Polaroid
d0658082-ebec-11ec-8ea0-0242ac120002	The british Slanger	(581,672)	United Kingdom	One euro pint
0907aa96-ebed-11ec-8ea0-0242ac120002	German Titan	(612,713)	Germany	House of Prantim
0907ad48-ebed-11ec-8ea0-0242ac120002	The Belgium fear	(412,782)	Belgium	Ottos House
0907ae38-ebed-11ec-8ea0-0242ac120002	The king of Garlic	(520,912)	Ireland	House of Garlic
\.


--
-- Name: t_country t_country_pkey; Type: CONSTRAINT; Schema: sc_kings; Owner: postgres
--

ALTER TABLE ONLY sc_kings.t_country
    ADD CONSTRAINT t_country_pkey PRIMARY KEY (id);


--
-- Name: t_houses t_houses_pkey; Type: CONSTRAINT; Schema: sc_kings; Owner: postgres
--

ALTER TABLE ONLY sc_kings.t_houses
    ADD CONSTRAINT t_houses_pkey PRIMARY KEY (id);


--
-- Name: t_kings t_kings_pkey; Type: CONSTRAINT; Schema: sc_kings; Owner: postgres
--

ALTER TABLE ONLY sc_kings.t_kings
    ADD CONSTRAINT t_kings_pkey PRIMARY KEY (id);


--
-- Name: t_houses unique_name; Type: CONSTRAINT; Schema: sc_kings; Owner: postgres
--

ALTER TABLE ONLY sc_kings.t_houses
    ADD CONSTRAINT unique_name UNIQUE (name) INCLUDE (name);


--
-- Name: t_country unique_name_country; Type: CONSTRAINT; Schema: sc_kings; Owner: postgres
--

ALTER TABLE ONLY sc_kings.t_country
    ADD CONSTRAINT unique_name_country UNIQUE (name) INCLUDE (name);


--
-- Name: t_houses fk_house_country; Type: FK CONSTRAINT; Schema: sc_kings; Owner: postgres
--

ALTER TABLE ONLY sc_kings.t_houses
    ADD CONSTRAINT fk_house_country FOREIGN KEY (country) REFERENCES sc_kings.t_country(name) NOT VALID;


--
-- Name: t_kings fk_kings_country ; Type: FK CONSTRAINT; Schema: sc_kings; Owner: postgres
--

ALTER TABLE ONLY sc_kings.t_kings
    ADD CONSTRAINT "fk_kings_country " FOREIGN KEY (country) REFERENCES sc_kings.t_country(name) NOT VALID;


--
-- Name: t_kings fk_kings_house; Type: FK CONSTRAINT; Schema: sc_kings; Owner: postgres
--

ALTER TABLE ONLY sc_kings.t_kings
    ADD CONSTRAINT fk_kings_house FOREIGN KEY (house) REFERENCES sc_kings.t_houses(name) NOT VALID;


--
-- PostgreSQL database dump complete
--

