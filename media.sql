--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.3
-- Dumped by pg_dump version 9.5.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: customers; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE customers (
    id integer NOT NULL,
    table_id integer,
    name character varying,
    total double precision,
    hasreceipt boolean
);


ALTER TABLE customers OWNER TO "Guest";

--
-- Name: customers_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE customers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE customers_id_seq OWNER TO "Guest";

--
-- Name: customers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE customers_id_seq OWNED BY customers.id;


--
-- Name: customers_meals; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE customers_meals (
    id integer NOT NULL,
    customer_id integer,
    meal_id integer
);


ALTER TABLE customers_meals OWNER TO "Guest";

--
-- Name: customers_meals_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE customers_meals_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE customers_meals_id_seq OWNER TO "Guest";

--
-- Name: customers_meals_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE customers_meals_id_seq OWNED BY customers_meals.id;


--
-- Name: customers_receipts; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE customers_receipts (
    id integer NOT NULL,
    customer_id integer,
    receipt_id integer
);


ALTER TABLE customers_receipts OWNER TO "Guest";

--
-- Name: customers_receipts_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE customers_receipts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE customers_receipts_id_seq OWNER TO "Guest";

--
-- Name: customers_receipts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE customers_receipts_id_seq OWNED BY customers_receipts.id;


--
-- Name: meals; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE meals (
    id integer NOT NULL,
    name character varying,
    price double precision
);


ALTER TABLE meals OWNER TO "Guest";

--
-- Name: meals_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE meals_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE meals_id_seq OWNER TO "Guest";

--
-- Name: meals_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE meals_id_seq OWNED BY meals.id;


--
-- Name: receipts; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE receipts (
    id integer NOT NULL,
    meal_total double precision,
    tableid integer,
    name character varying
);


ALTER TABLE receipts OWNER TO "Guest";

--
-- Name: receipts_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE receipts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE receipts_id_seq OWNER TO "Guest";

--
-- Name: receipts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE receipts_id_seq OWNED BY receipts.id;


--
-- Name: tables; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE tables (
    id integer NOT NULL,
    name character varying,
    guestcount integer
);


ALTER TABLE tables OWNER TO "Guest";

--
-- Name: tables_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE tables_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tables_id_seq OWNER TO "Guest";

--
-- Name: tables_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE tables_id_seq OWNED BY tables.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY customers ALTER COLUMN id SET DEFAULT nextval('customers_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY customers_meals ALTER COLUMN id SET DEFAULT nextval('customers_meals_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY customers_receipts ALTER COLUMN id SET DEFAULT nextval('customers_receipts_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY meals ALTER COLUMN id SET DEFAULT nextval('meals_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY receipts ALTER COLUMN id SET DEFAULT nextval('receipts_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY tables ALTER COLUMN id SET DEFAULT nextval('tables_id_seq'::regclass);


--
-- Data for Name: customers; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY customers (id, table_id, name, total, hasreceipt) FROM stdin;
1	1	Customer 1	0	f
2	1	Customer 2	0	f
3	1	Customer 3	0	f
\.


--
-- Name: customers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('customers_id_seq', 3, true);


--
-- Data for Name: customers_meals; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY customers_meals (id, customer_id, meal_id) FROM stdin;
\.


--
-- Name: customers_meals_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('customers_meals_id_seq', 1, false);


--
-- Data for Name: customers_receipts; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY customers_receipts (id, customer_id, receipt_id) FROM stdin;
\.


--
-- Name: customers_receipts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('customers_receipts_id_seq', 1, false);


--
-- Data for Name: meals; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY meals (id, name, price) FROM stdin;
1	Hamburger	6.98999977111816406
2	Cheeseburger	7.69000005722045898
3	Bacon Burger	7.98999977111816406
4	Bacon Cheeseburger	8.68999958038330078
5	Hot Dog	4.69000005722045898
6	Cheese Dog	5.3899998664855957
7	Bacon Dog	5.69000005722045898
8	Bacon Cheese Dog	6.3899998664855957
9	Veggie Sandwich	3.69000005722045898
10	Veggie Sandwich with Cheese	4.3899998664855957
11	Grilled Cheese	4.3899998664855957
12	BLT	5.59000015258789062
13	Fries	4.19000005722045898
14	Cajun Fries	4.19000005722045898
15	Coca Cola Products	2.3900001049041748
16	Dasani Bottled Water	2.08999991416931152
\.


--
-- Name: meals_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('meals_id_seq', 16, true);


--
-- Data for Name: receipts; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY receipts (id, meal_total, tableid, name) FROM stdin;
\.


--
-- Name: receipts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('receipts_id_seq', 1, false);


--
-- Data for Name: tables; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY tables (id, name, guestcount) FROM stdin;
1	Lynn's Table	3
\.


--
-- Name: tables_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('tables_id_seq', 1, true);


--
-- Name: customers_meals_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY customers_meals
    ADD CONSTRAINT customers_meals_pkey PRIMARY KEY (id);


--
-- Name: customers_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);


--
-- Name: customers_receipts_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY customers_receipts
    ADD CONSTRAINT customers_receipts_pkey PRIMARY KEY (id);


--
-- Name: meals_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY meals
    ADD CONSTRAINT meals_pkey PRIMARY KEY (id);


--
-- Name: receipts_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY receipts
    ADD CONSTRAINT receipts_pkey PRIMARY KEY (id);


--
-- Name: tables_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY tables
    ADD CONSTRAINT tables_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

