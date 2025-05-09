-- Создание таблицы курьеров
CREATE TABLE t_courier (
                           id BIGSERIAL PRIMARY KEY,
                           full_name VARCHAR(255) NOT NULL,
                           transport VARCHAR(255),
                           work_zone VARCHAR(255)
);

-- Создание таблицы посылок
CREATE TABLE t_parcel (
                          id BIGSERIAL PRIMARY KEY,
                          weight DOUBLE PRECISION NOT NULL,
                          dimensions VARCHAR(255),
                          destination_address VARCHAR(255)
);

-- Создание таблицы доставок
CREATE TABLE t_delivery (
                            id BIGSERIAL PRIMARY KEY,
                            parcel_id BIGINT NOT NULL REFERENCES t_parcel(id) ON DELETE CASCADE,
                            courier_id BIGINT NOT NULL REFERENCES t_courier(id) ON DELETE CASCADE,
                            delivery_date DATE NOT NULL,
                            status VARCHAR(50) NOT NULL CHECK (status IN ('PENDING', 'IN_TRANSIT', 'DELIVERED', 'CANCELLED'))
);
