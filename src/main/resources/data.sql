INSERT INTO user_info (cin, email, first_name, last_name, passport_number, passport_issue_date, passport_expiry_date, phone_number, address, birth_date, nationality, gender, role, manager_id)
SELECT * FROM (
  VALUES 
    (12345678, 'admin@example.com', 'Admin', 'User', 'P123456', '2020-01-01', '2030-01-01', '1234567890', '123 Admin St', '1980-01-01', 'Country', 'Male', 'ADMIN', null),
    (98765432, 'user1@example.com', 'User1', 'One', 'P654321', '2021-02-01', '2031-02-01', '0987654321', '456 User St', '1990-02-01', 'Country', 'Female', 'COLLABORATEUR', 12345678),
    (74185296, 'user2@example.com', 'User2', 'Two', 'P789012', '2022-03-01', '2032-03-01', '1122334455', '789 User St', '2000-03-01', 'Country', 'Male', 'COLLABORATEUR', 12345678)
);

INSERT INTO mission (id, title, description, destination, start_date, end_date, budget, status, user_info_cin, user_cin)
VALUES 
    (1, 'Business Meeting Paris', 'Annual stakeholder meeting', 'Paris', '2024-02-01', '2024-02-07', 2500.00, 'EN_ATTENTE', 12345678, 12345678),
    (2, 'Tech Conference Berlin', 'Web Development Summit', 'Berlin', '2024-03-15', '2024-03-20', 3000.00, 'TERMINEE', 12345678, 12345678),
    (3, 'Training Madrid', 'Advanced Java Training', 'Madrid', '2024-04-10', '2024-04-15', 2000.00, 'EN_ATTENTE', 98765432, 98765432),
    (4, 'Client Meeting Rome', 'Project kickoff meeting', 'Rome', '2024-05-01', '2024-05-03', 1500.00, 'ANNULEE', 74185296, 74185296);

INSERT INTO liquidation (user_id, mission_id, train_cost, bus_cost, taxi_cost, other_transport_cost, internet_package_cost, sim_card_cost, hotel_cost, total_amount, date)
VALUES 
    (98765432, 3, 100.00, 50.00, 30.00, 20.00, 10.00, 5.00, 200.00, 415.00, '2024-04-16'),
    (74185296, 4, 120.00, 60.00, 40.00, 25.00, 15.00, 10.00, 250.00, 520.00, '2024-05-04'),
    (12345678, 1, 150.00, 70.00, 50.00, 30.00, 20.00, 15.00, 300.00, 635.00, '2024-02-08');