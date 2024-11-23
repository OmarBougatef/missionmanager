INSERT INTO user_info (cin, email, first_name, last_name, passport_number, passport_issue_date, passport_expiry_date, phone_number, address, birth_date, nationality, gender, role)
SELECT * FROM (
  VALUES 
    (123456789012345, 'admin@example.com', 'Admin', 'User', 'P123456', '2020-01-01', '2030-01-01', '1234567890', '123 Admin St', '1980-01-01', 'Country', 'Male', 'ADMIN'),
    (234567890123456, 'user1@example.com', 'User1', 'One', 'P654321', '2021-02-01', '2031-02-01', '0987654321', '456 User St', '1990-02-01', 'Country', 'Female', 'COLLABORATEUR'),
    (345678901234567, 'user2@example.com', 'User2', 'Two', 'P789012', '2022-03-01', '2032-03-01', '1122334455', '789 User St', '2000-03-01', 'Country', 'Male', 'COLLABORATEUR')
) AS temp
WHERE NOT EXISTS (
    SELECT 1 FROM user_info WHERE cin IN (123456789012345, 234567890123456, 345678901234567)
);