CREATE DATABASE IF NOT EXISTS inventory;
CREATE DATABASE IF NOT EXISTS customer;
GRANT ALL ON inventory.* TO 'product'@'*';
GRANT ALL ON custormer.* TO 'customer'@'*';