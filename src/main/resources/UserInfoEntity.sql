use PUSH_NOTIFICATION_SERVICE;
CREATE TABLE PUSH_NOTIFICATION_SERVICE.user_info (
    user_id INT PRIMARY KEY,
    user_name VARCHAR(255),
    email_address VARCHAR(255),
    mobile_number VARCHAR(20),
    country_code VARCHAR(5),
    created_at TIMESTAMP
);
CREATE TABLE PUSH_NOTIFICATION_SERVICE.device_info (
    id INT PRIMARY KEY,
    device_token VARCHAR(255),
    user_id INT,
    last_logged_in_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user_info(user_id)
);
INSERT INTO PUSH_NOTIFICATION_SERVICE.user_info (user_id, user_name, email_address, mobile_number, country_code, created_at) VALUES (123, 'John Doe', 'john.doe@example.com', '1234567890', '+1', '2024-03-10T00:00:00');
INSERT INTO PUSH_NOTIFICATION_SERVICE.user_info (user_id, user_name, email_address, mobile_number, country_code, created_at) VALUES (2, 'Jane Smith', 'jane.smith@example.com', '9876543210', '+44', '2024-03-10T00:00:00');
INSERT INTO PUSH_NOTIFICATION_SERVICE.device_info (id, device_token, user_id, last_logged_in_at) VALUES (1, 'device_token_1', 123, '2024-03-10T00:00:00');
INSERT INTO PUSH_NOTIFICATION_SERVICE.device_info (id, device_token, user_id, last_logged_in_at) VALUES (2, 'device_token_2', 123, '2024-03-10T00:00:00');

