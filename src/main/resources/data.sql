INSERT INTO user_info (cin, email, first_name, last_name, passport_number, passport_issue_date, passport_expiry_date, phone_number, address, birth_date, nationality, gender, role, manager_id)
SELECT * FROM (
  VALUES 
    (123456789012345, 'admin@example.com', 'Admin', 'User', 'P123456', '2020-01-01', '2030-01-01', '1234567890', '123 Admin St', '1980-01-01', 'Country', 'Male', 'ADMIN', null),
    (456789012345678, 'manager@example.com', 'Manager', 'User', 'P987654', '2019-01-01', '2029-01-01', '2233445566', '321 Manager St', '1985-01-01', 'Country', 'Female', 'MANAGER', null),
    (234567890123456, 'user1@example.com', 'User1', 'One', 'P654321', '2021-02-01', '2031-02-01', '0987654321', '456 User St', '1990-02-01', 'Country', 'Female', 'COLLABORATEUR', 456789012345678),
    (345678901234567, 'user2@example.com', 'User2', 'Two', 'P789012', '2022-03-01', '2032-03-01', '1122334455', '789 User St', '2000-03-01', 'Country', 'Male', 'COLLABORATEUR', 456789012345678)
);

-- Add 40 missions (10 per user)
INSERT INTO mission (id, title, description, destination, start_date, end_date, budget, status, user_info_cin, user_cin)
VALUES 
    -- Admin missions (123456789012345)
    (1, 'Business Meeting Paris', 'Annual stakeholder meeting', 'Paris', '2024-02-01', '2024-02-07', 2500.00, 'EN_ATTENTE', 123456789012345, 123456789012345),
    (2, 'Tech Conference Berlin', 'Web Development Summit', 'Berlin', '2024-03-15', '2024-03-20', 3000.00, 'TERMINEE', 123456789012345, 123456789012345),
    (3, 'Project Review London', 'Quarterly review', 'London', '2024-04-10', '2024-04-15', 2800.00, 'EN_ATTENTE', 123456789012345, 123456789012345),
    (4, 'Innovation Summit NYC', 'Tech innovation conference', 'New York', '2024-05-01', '2024-05-07', 3500.00, 'TERMINEE', 123456789012345, 123456789012345),
    (5, 'Strategy Meeting Tokyo', 'Annual planning', 'Tokyo', '2024-06-15', '2024-06-20', 4000.00, 'EN_ATTENTE', 123456789012345, 123456789012345),
    (6, 'Leadership Conference', 'Global leadership summit', 'Singapore', '2024-07-01', '2024-07-07', 3200.00, 'ANNULEE', 123456789012345, 123456789012345),
    (7, 'Board Meeting Dubai', 'Q3 board meeting', 'Dubai', '2024-08-15', '2024-08-20', 3800.00, 'EN_ATTENTE', 123456789012345, 123456789012345),
    (8, 'Tech Summit SF', 'Technology summit', 'San Francisco', '2024-09-01', '2024-09-07', 3600.00, 'TERMINEE', 123456789012345, 123456789012345),
    (9, 'Global Conference', 'International conference', 'Sydney', '2024-10-15', '2024-10-20', 4200.00, 'EN_ATTENTE', 123456789012345, 123456789012345),
    (10, 'Year-End Meeting', 'Annual closure meeting', 'Geneva', '2024-11-01', '2024-11-07', 3000.00, 'EN_ATTENTE', 123456789012345, 123456789012345),
    
    -- Manager missions (456789012345678)
    (11, 'Team Building Paris', 'Department team building', 'Paris', '2024-02-10', '2024-02-15', 2200.00, 'TERMINEE', 456789012345678, 456789012345678),
    (12, 'Management Summit', 'Leadership training', 'Madrid', '2024-03-20', '2024-03-25', 2500.00, 'EN_ATTENTE', 456789012345678, 456789012345678),
    (13, 'Department Review', 'Quarterly planning', 'Rome', '2024-04-15', '2024-04-20', 2300.00, 'TERMINEE', 456789012345678, 456789012345678),
    (14, 'Strategy Workshop', 'Team strategy session', 'Amsterdam', '2024-05-10', '2024-05-15', 2400.00, 'EN_ATTENTE', 456789012345678, 456789012345678),
    (15, 'Leadership Training', 'Advanced management course', 'Vienna', '2024-06-20', '2024-06-25', 2600.00, 'TERMINEE', 456789012345678, 456789012345678),
    (16, 'Team Conference', 'Department conference', 'Brussels', '2024-07-15', '2024-07-20', 2700.00, 'EN_ATTENTE', 456789012345678, 456789012345678),
    (17, 'Project Planning', 'Annual project planning', 'Stockholm', '2024-08-10', '2024-08-15', 2800.00, 'ANNULEE', 456789012345678, 456789012345678),
    (18, 'Department Summit', 'Team summit', 'Copenhagen', '2024-09-20', '2024-09-25', 2900.00, 'EN_ATTENTE', 456789012345678, 456789012345678),
    (19, 'Management Review', 'Year-end review', 'Oslo', '2024-10-15', '2024-10-20', 3000.00, 'TERMINEE', 456789012345678, 456789012345678),
    (20, 'Team Workshop', 'Strategic planning', 'Helsinki', '2024-11-10', '2024-11-15', 2800.00, 'EN_ATTENTE', 456789012345678, 456789012345678),

    -- User1 missions (234567890123456)
    (21, 'Training Madrid', 'Technical training', 'Madrid', '2024-02-05', '2024-02-10', 1800.00, 'TERMINEE', 234567890123456, 234567890123456),
    (22, 'Dev Conference', 'Development conference', 'Barcelona', '2024-03-10', '2024-03-15', 2000.00, 'EN_ATTENTE', 234567890123456, 234567890123456),
    (23, 'Tech Workshop', 'Technical workshop', 'Lisbon', '2024-04-20', '2024-04-25', 1900.00, 'TERMINEE', 234567890123456, 234567890123456),
    (24, 'Coding Sprint', 'Team coding sprint', 'Porto', '2024-05-15', '2024-05-20', 1700.00, 'EN_ATTENTE', 234567890123456, 234567890123456),
    (25, 'Agile Training', 'Agile methodology course', 'Milan', '2024-06-10', '2024-06-15', 1800.00, 'TERMINEE', 234567890123456, 234567890123456),
    (26, 'Sprint Planning', 'Q3 sprint planning', 'Venice', '2024-07-20', '2024-07-25', 1900.00, 'EN_ATTENTE', 234567890123456, 234567890123456),
    (27, 'Tech Summit', 'Technology summit', 'Florence', '2024-08-15', '2024-08-20', 2000.00, 'ANNULEE', 234567890123456, 234567890123456),
    (28, 'Code Review', 'Team code review', 'Naples', '2024-09-10', '2024-09-15', 1800.00, 'EN_ATTENTE', 234567890123456, 234567890123456),
    (29, 'Dev Workshop', 'Development workshop', 'Turin', '2024-10-20', '2024-10-25', 1900.00, 'TERMINEE', 234567890123456, 234567890123456),
    (30, 'Sprint Review', 'Year-end sprint review', 'Bologna', '2024-11-15', '2024-11-20', 1700.00, 'EN_ATTENTE', 234567890123456, 234567890123456),

    -- User2 missions (345678901234567)
    (31, 'Client Meeting', 'Client presentation', 'Munich', '2024-02-15', '2024-02-20', 1900.00, 'TERMINEE', 345678901234567, 345678901234567),
    (32, 'Project Kickoff', 'New project launch', 'Frankfurt', '2024-03-25', '2024-03-30', 2100.00, 'EN_ATTENTE', 345678901234567, 345678901234567),
    (33, 'Requirements Gathering', 'Client requirements', 'Hamburg', '2024-04-15', '2024-04-20', 2000.00, 'TERMINEE', 345678901234567, 345678901234567),
    (34, 'Client Workshop', 'Product workshop', 'Stuttgart', '2024-05-20', '2024-05-25', 1800.00, 'EN_ATTENTE', 345678901234567, 345678901234567),
    (35, 'Project Review', 'Mid-year review', 'Cologne', '2024-06-15', '2024-06-20', 1900.00, 'TERMINEE', 345678901234567, 345678901234567),
    (36, 'Sprint Demo', 'Client demonstration', 'Dusseldorf', '2024-07-25', '2024-07-30', 2000.00, 'EN_ATTENTE', 345678901234567, 345678901234567),
    (37, 'Status Update', 'Project status meeting', 'Dresden', '2024-08-20', '2024-08-25', 2100.00, 'ANNULEE', 345678901234567, 345678901234567),
    (38, 'Client Training', 'Product training', 'Leipzig', '2024-09-15', '2024-09-20', 1900.00, 'EN_ATTENTE', 345678901234567, 345678901234567),
    (39, 'Project Planning', 'Next phase planning', 'Hannover', '2024-10-25', '2024-10-30', 2000.00, 'TERMINEE', 345678901234567, 345678901234567),
    (40, 'Year Review', 'Annual project review', 'Bremen', '2024-11-20', '2024-11-25', 1800.00, 'EN_ATTENTE', 345678901234567, 345678901234567);

-- Add corresponding liquidations for completed missions (status = 'TERMINEE')
INSERT INTO liquidation (user_id, mission_id, train_cost, bus_cost, taxi_cost, other_transport_cost, internet_package_cost, sim_card_cost, hotel_cost, total_amount, date)
SELECT 
    user_cin,
    id as mission_id,
    ROUND(RAND() * 200 + 100, 2) as train_cost,
    ROUND(RAND() * 100 + 50, 2) as bus_cost,
    ROUND(RAND() * 100 + 30, 2) as taxi_cost,
    ROUND(RAND() * 100 + 20, 2) as other_transport_cost,
    ROUND(RAND() * 50 + 10, 2) as internet_package_cost,
    ROUND(RAND() * 30 + 5, 2) as sim_card_cost,
    ROUND(RAND() * 500 + 200, 2) as hotel_cost,
    0 as total_amount,
    DATEADD('DAY', 1, end_date) as date
FROM mission
WHERE status = 'TERMINEE';

-- Update total_amount in liquidations
UPDATE liquidation 
SET total_amount = train_cost + bus_cost + taxi_cost + other_transport_cost + internet_package_cost + sim_card_cost + hotel_cost;
