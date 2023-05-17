# PROGETTO PER EDOK

Per far funzionare l'applicazione è necessario utilizzare <a href="https://www.postgresql.org/"> postgresql</a> e seguire le istruzioni in questo file.


## CREAZIONE DEL DATABASE

Inserisci le tables nel database

CREATE TABLE IF NOT EXISTS fornitore
(
    id_fornitore integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    nome character varying(50) NOT NULL,
    CONSTRAINT fornitore_pkey PRIMARY KEY (id_fornitore)
);


CREATE TABLE IF NOT EXISTS prodotto
(
    id_prodotto integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    nome character varying(50) NOT NULL,
    prezzo integer NOT NULL,
    id_fornitore integer,
    CONSTRAINT prodotto_pkey PRIMARY KEY (id_prodotto),
    CONSTRAINT prodotto_id_fornitore_fkey FOREIGN KEY (id_fornitore)
        REFERENCES fornitore (id_fornitore) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

## DRIVER JDBC

Per far funzionare l'applicazione è necessario installare e aggiungere la <a href ="https://jdbc.postgresql.org/download/postgresql-42.6.0.jar">libreria esterna</a>
