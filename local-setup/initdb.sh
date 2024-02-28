# This script is used for local development only.
# It's run by Docker when initializing postgres via `docker-compose up -d postgres`
# The process is kicked off via `gradlew startdb`.
psql -v ON_ERROR_STOP=1 -U "$POSTGRES_USER" -d "$POSTGRES_DB" <<-EOSQL
    CREATE SCHEMA IF NOT EXISTS "$POSTGRES_SCHEMA";

    -- prevent service user from creating tables, allows to properly test permissions locally
    REVOKE ALL ON schema "$POSTGRES_SCHEMA" FROM public;

    DO \$\$
          BEGIN
            IF EXISTS (
               SELECT FROM pg_catalog.pg_roles
               WHERE  rolname = '$POSTGRES_SERVICE_USER') THEN
               RAISE NOTICE 'Role "$POSTGRES_SERVICE_USER" already exists. Skipping.';
            ELSE
               BEGIN   -- nested block
                  CREATE ROLE "$POSTGRES_SERVICE_USER" LOGIN ENCRYPTED PASSWORD '$POSTGRES_SERVICE_PASSWORD' VALID UNTIL 'infinity';
                  CREATE ROLE "order-sub-replication" LOGIN ENCRYPTED PASSWORD '$POSTGRES_SERVICE_PASSWORD' VALID UNTIL 'infinity';
               EXCEPTION
                  WHEN duplicate_object THEN
                     RAISE NOTICE 'Role "$POSTGRES_SERVICE_USER" was just created by a concurrent transaction. Skipping.';
               END;
            END IF;
          END
        \$\$;

    ALTER ROLE "$POSTGRES_SERVICE_USER" SET search_path="$POSTGRES_SCHEMA", public;

    GRANT USAGE ON SCHEMA "$POSTGRES_SCHEMA" TO "$POSTGRES_SERVICE_USER";

    ALTER DEFAULT PRIVILEGES IN SCHEMA "$POSTGRES_SCHEMA"
      GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO "$POSTGRES_SERVICE_USER";
EOSQL