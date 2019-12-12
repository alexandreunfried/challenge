INSERT INTO manufacturer (id, name) 
VALUES
(1, 'Made in Grill'),
(2, 'Quality farm goods');

INSERT INTO product(id, name, description, barcode, id_manufacturer, unit_price)
VALUES 
(25, 'Orange juice', 'Delicious natural orange juice. No addition of apples to fool consumers.', '8901072002478', 2, 10.00),
(26, 'Apple juice', 'Delicious natural apple juice.', '1201072002421', 2, 15.50),
(29, 'Grape juice', 'Delicious natural grape juice.', '3401072002456', 2, 36.65);

