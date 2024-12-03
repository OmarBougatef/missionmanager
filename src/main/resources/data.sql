INSERT INTO user_info (cin, email, first_name, last_name, passport_number, passport_issue_date, passport_expiry_date, phone_number, address, birth_date, nationality, gender, role, manager_id)
SELECT * FROM (
  VALUES 
    (123456789012345, 'admin@example.com', 'Admin', 'User', 'P123456', '2020-01-01', '2030-01-01', '1234567890', '123 Admin St', '1980-01-01', 'Country', 'Male', 'ADMIN', null),
    (234567890123456, 'user1@example.com', 'User1', 'One', 'P654321', '2021-02-01', '2031-02-01', '0987654321', '456 User St', '1990-02-01', 'Country', 'Female', 'COLLABORATEUR', 123456789012345),
    (345678901234567, 'user2@example.com', 'User2', 'Two', 'P789012', '2022-03-01', '2032-03-01', '1122334455', '789 User St', '2000-03-01', 'Country', 'Male', 'COLLABORATEUR', 123456789012345)
);

INSERT INTO mission (id, title, description, destination, start_date, end_date, budget, status, user_info_cin, user_cin)
VALUES 
    (1, 'Business Meeting Paris', 'Annual stakeholder meeting', 'Paris', '2024-02-01', '2024-02-07', 2500.00, 'EN_ATTENTE', 123456789012345, 123456789012345),
    (2, 'Tech Conference Berlin', 'Web Development Summit', 'Berlin', '2024-03-15', '2024-03-20', 3000.00, 'TERMINEE', 123456789012345, 123456789012345),
    (3, 'Training Madrid', 'Advanced Java Training', 'Madrid', '2024-04-10', '2024-04-15', 2000.00, 'EN_ATTENTE', 234567890123456, 234567890123456),
    (4, 'Client Meeting Rome', 'Project kickoff meeting', 'Rome', '2024-05-01', '2024-05-03', 1500.00, 'ANNULEE', 345678901234567, 345678901234567);