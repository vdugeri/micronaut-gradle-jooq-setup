BEGIN;

CREATE TABLE IF NOT EXISTS students (
    id uuid PRIMARY KEY,
    first_name character varying (255) NOT NULL,
    last_name character varying (255) NOT NULL,
    other_names character varying (255),
    reg_number character varying (255),
    user_id UUID NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE,
    updated_at TIMESTAMP WITH TIME ZONE,

    CONSTRAINT reg_number_unique UNIQUE (reg_number)
);


CREATE TABLE IF NOT EXISTS student_profile (
    id uuid NOT NULL PRIMARY KEY,
    student_id UUID NOT NULL,
    admission_date DATE NOT NULL,
    dob DATE NOT NULL,
    blood_group character varying (255),
    gender character varying (255) NOT NULL,
    religion character varying (255),
    nationality character varying (255),
    state_of_origin character varying (255),
    lga_of_origin character varying (255),
    health_info character varying (2550),
    batch_id UUID NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE,
    updated_at TIMESTAMP WITH TIME ZONE,

    CONSTRAINT fk_student_id FOREIGN KEY (student_id) REFERENCES students (id)
);


COMMIT;