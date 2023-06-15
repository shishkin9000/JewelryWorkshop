CREATE SEQUENCE IF NOT EXISTS clients_orders_sequence START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS clients_sequence START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS employees_positions_sequence START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS employees_sequence START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS finished_items_sequence START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS metal_types_sequence START WITH 1 INCREMENT BY 1;

ALTER TABLE clients
    ADD CONSTRAINT pk_clients PRIMARY KEY (id);

ALTER TABLE clients_orders
    ADD CONSTRAINT pk_clients_orders PRIMARY KEY (id);

ALTER TABLE employees
    ADD CONSTRAINT pk_employees PRIMARY KEY (id);

ALTER TABLE employees_positions
    ADD CONSTRAINT pk_employees_positions PRIMARY KEY (id);

ALTER TABLE finished_items
    ADD CONSTRAINT pk_finished_items PRIMARY KEY (id);

ALTER TABLE gems_types
    ADD CONSTRAINT pk_gems_types PRIMARY KEY (id);

ALTER TABLE metals_types
    ADD CONSTRAINT pk_metals_types PRIMARY KEY (id);

ALTER TABLE finished_items_gems_types
    DROP CONSTRAINT finished_items_gems_types_pkey;

ALTER TABLE finished_items_metals_types
    DROP CONSTRAINT finished_items_metals_types_pkey;

ALTER TABLE employees
    ALTER COLUMN address TYPE VARCHAR(255) USING (address::VARCHAR(255));

ALTER TABLE employees
    ALTER COLUMN address DROP NOT NULL;

ALTER TABLE employees
    ALTER COLUMN birth_date DROP NOT NULL;

ALTER TABLE clients
    ALTER COLUMN created_by TYPE VARCHAR(255) USING (created_by::VARCHAR(255));

ALTER TABLE clients_orders
    ALTER COLUMN created_by TYPE VARCHAR(255) USING (created_by::VARCHAR(255));

ALTER TABLE employees
    ALTER COLUMN created_by TYPE VARCHAR(255) USING (created_by::VARCHAR(255));

ALTER TABLE employees_positions
    ALTER COLUMN created_by TYPE VARCHAR(255) USING (created_by::VARCHAR(255));

ALTER TABLE finished_items
    ALTER COLUMN created_by TYPE VARCHAR(255) USING (created_by::VARCHAR(255));

ALTER TABLE gems_types
    ALTER COLUMN created_by TYPE VARCHAR(255) USING (created_by::VARCHAR(255));

ALTER TABLE metals_types
    ALTER COLUMN created_by TYPE VARCHAR(255) USING (created_by::VARCHAR(255));

ALTER TABLE clients
    ALTER COLUMN deleted_by TYPE VARCHAR(255) USING (deleted_by::VARCHAR(255));

ALTER TABLE clients_orders
    ALTER COLUMN deleted_by TYPE VARCHAR(255) USING (deleted_by::VARCHAR(255));

ALTER TABLE employees
    ALTER COLUMN deleted_by TYPE VARCHAR(255) USING (deleted_by::VARCHAR(255));

ALTER TABLE employees_positions
    ALTER COLUMN deleted_by TYPE VARCHAR(255) USING (deleted_by::VARCHAR(255));

ALTER TABLE finished_items
    ALTER COLUMN deleted_by TYPE VARCHAR(255) USING (deleted_by::VARCHAR(255));

ALTER TABLE gems_types
    ALTER COLUMN deleted_by TYPE VARCHAR(255) USING (deleted_by::VARCHAR(255));

ALTER TABLE metals_types
    ALTER COLUMN deleted_by TYPE VARCHAR(255) USING (deleted_by::VARCHAR(255));

ALTER TABLE clients_orders
    ALTER COLUMN description TYPE VARCHAR(255) USING (description::VARCHAR(255));

ALTER TABLE employees_positions
    ALTER COLUMN description TYPE VARCHAR(255) USING (description::VARCHAR(255));

ALTER TABLE finished_items
    ALTER COLUMN description TYPE VARCHAR(255) USING (description::VARCHAR(255));

ALTER TABLE finished_items
    ALTER COLUMN employee_id SET NOT NULL;

ALTER TABLE employees
    ALTER COLUMN first_name TYPE VARCHAR(255) USING (first_name::VARCHAR(255));

ALTER TABLE employees
    ALTER COLUMN first_name DROP NOT NULL;

ALTER TABLE clients
    ALTER COLUMN full_name TYPE VARCHAR(255) USING (full_name::VARCHAR(255));

ALTER TABLE finished_items
    ALTER COLUMN gems_info TYPE VARCHAR(255) USING (gems_info::VARCHAR(255));

ALTER TABLE employees
    ALTER COLUMN last_name TYPE VARCHAR(255) USING (last_name::VARCHAR(255));

ALTER TABLE employees
    ALTER COLUMN last_name DROP NOT NULL;

ALTER TABLE employees
    ALTER COLUMN login TYPE VARCHAR(255) USING (login::VARCHAR(255));

ALTER TABLE employees
    ALTER COLUMN login DROP NOT NULL;

ALTER TABLE finished_items
    ALTER COLUMN metals_info TYPE VARCHAR(255) USING (metals_info::VARCHAR(255));

ALTER TABLE employees
    ALTER COLUMN middle_name TYPE VARCHAR(255) USING (middle_name::VARCHAR(255));

ALTER TABLE employees
    ALTER COLUMN middle_name DROP NOT NULL;

ALTER TABLE employees
    ALTER COLUMN password TYPE VARCHAR(255) USING (password::VARCHAR(255));

ALTER TABLE employees
    ALTER COLUMN password DROP NOT NULL;

ALTER TABLE clients
    ALTER COLUMN phone TYPE VARCHAR(255) USING (phone::VARCHAR(255));

ALTER TABLE employees
    ALTER COLUMN phone TYPE VARCHAR(255) USING (phone::VARCHAR(255));

ALTER TABLE employees
    ALTER COLUMN phone DROP NOT NULL;

ALTER TABLE finished_items
    ALTER COLUMN photo_path TYPE VARCHAR(255) USING (photo_path::VARCHAR(255));

ALTER TABLE employees
    ALTER COLUMN position_id DROP NOT NULL;

ALTER TABLE employees_positions
    ALTER COLUMN role TYPE VARCHAR(255) USING (role::VARCHAR(255));

ALTER TABLE employees_positions
    ALTER COLUMN title TYPE VARCHAR(255) USING (title::VARCHAR(255));

ALTER TABLE employees_positions
    ALTER COLUMN title DROP NOT NULL;

ALTER TABLE gems_types
    ALTER COLUMN title TYPE VARCHAR(255) USING (title::VARCHAR(255));

ALTER TABLE metals_types
    ALTER COLUMN title TYPE VARCHAR(255) USING (title::VARCHAR(255));
CREATE SEQUENCE IF NOT EXISTS clients_orders_sequence START WITH 1 INCREMENT BY 1;

ALTER TABLE employees
    ALTER COLUMN first_name TYPE VARCHAR(255) USING (first_name::VARCHAR(255));

ALTER TABLE employees
    ALTER COLUMN first_name DROP NOT NULL;

ALTER TABLE clients
    ALTER COLUMN full_name TYPE VARCHAR(255) USING (full_name::VARCHAR(255));

ALTER TABLE finished_items
    ALTER COLUMN gems_info TYPE VARCHAR(255) USING (gems_info::VARCHAR(255));

ALTER TABLE employees
    ALTER COLUMN last_name TYPE VARCHAR(255) USING (last_name::VARCHAR(255));

ALTER TABLE employees
    ALTER COLUMN last_name DROP NOT NULL;

ALTER TABLE employees
    ALTER COLUMN login TYPE VARCHAR(255) USING (login::VARCHAR(255));

ALTER TABLE employees
    ALTER COLUMN login DROP NOT NULL;

ALTER TABLE finished_items
    ALTER COLUMN metals_info TYPE VARCHAR(255) USING (metals_info::VARCHAR(255));

ALTER TABLE employees
    ALTER COLUMN middle_name TYPE VARCHAR(255) USING (middle_name::VARCHAR(255));

ALTER TABLE employees
    ALTER COLUMN middle_name DROP NOT NULL;

ALTER TABLE employees
    ALTER COLUMN password TYPE VARCHAR(255) USING (password::VARCHAR(255));

ALTER TABLE employees
    ALTER COLUMN password DROP NOT NULL;

ALTER TABLE clients
    ALTER COLUMN phone TYPE VARCHAR(255) USING (phone::VARCHAR(255));

ALTER TABLE employees
    ALTER COLUMN phone TYPE VARCHAR(255) USING (phone::VARCHAR(255));

ALTER TABLE employees
    ALTER COLUMN phone DROP NOT NULL;

ALTER TABLE finished_items
    ALTER COLUMN photo_path TYPE VARCHAR(255) USING (photo_path::VARCHAR(255));

ALTER TABLE employees
    ALTER COLUMN position_id DROP NOT NULL;

ALTER TABLE employees_positions
    ALTER COLUMN role TYPE VARCHAR(255) USING (role::VARCHAR(255));

ALTER TABLE employees_positions
    ALTER COLUMN title TYPE VARCHAR(255) USING (title::VARCHAR(255));

ALTER TABLE employees_positions
    ALTER COLUMN title DROP NOT NULL;

ALTER TABLE gems_types
    ALTER COLUMN title TYPE VARCHAR(255) USING (title::VARCHAR(255));

ALTER TABLE metals_types
    ALTER COLUMN title TYPE VARCHAR(255) USING (title::VARCHAR(255));