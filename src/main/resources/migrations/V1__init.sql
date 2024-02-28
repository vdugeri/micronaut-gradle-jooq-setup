BEGIN;

CREATE TABLE IF NOT EXISTS users (
    id uuid PRIMARY KEY,
    first_name character varying (255) NOT NULL,
    last_name character varying (255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE,
    updated_at TIMESTAMP WITH TIME ZONE
);

COMMIT;