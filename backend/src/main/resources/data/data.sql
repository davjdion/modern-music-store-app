INSERT INTO "USER" (first_name, last_name, email, password, age, address)
VALUES
    ('John', 'Doe', 'john.doe@example.com', 'password123', 30, '123 Main St, Springfield, USA'),
    ('Jane', 'Smith', 'jane.smith@example.com', 'securepass', 25, '456 Elm St, Springfield, USA');

INSERT INTO "PRODUCT" (name, description, img)
VALUES
    ('Fender Telecaster', 'A Fender Telecaster guitar', 'img/telecaster.jpg'),
    ('DigiCo Console', 'A DigiCo sound console', 'img/digico_console.jpg'),
    ('Yamaha Mixer', 'A Yamaha mixing console', 'img/yamaha_mixer.jpg'),
    ('Shure Microphone', 'A Shure microphone', 'img/shure_microphone.jpg'),
    ('Roland Keyboard', 'A Roland keyboard', 'img/roland_keyboard.jpg');

INSERT INTO "STOCK" (product_id, cnt, unit_price)
VALUES
    (1, 10, 2550.95),
    (2, 5, 29999.90),
    (3, 8, 9999.95),
    (4, 15, 1799.95),
    (5, 20, 1699.95);

INSERT INTO "CART_ITEM" (user_id, product_id, cnt)
VALUES
    (1, 1, 2),
    (1, 2, 1);

