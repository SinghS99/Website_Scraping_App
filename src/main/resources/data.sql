-- Product 1: Sofa
INSERT INTO product (item_id, item_profile, item_desc, item_quantity, price)
VALUES (1, 'Sofa', 'Comfortable sofa set', 5, 15000);
INSERT INTO product_item_type (product_item_id, item_type)
VALUES (1, 'Single'), (1, 'Double'), (1, 'Triple');

-- Product 2: Refrigerator
INSERT INTO product (item_id, item_profile, item_desc, item_quantity, price)
VALUES (2, 'Refrigerator', 'Energy efficient fridge', 3, 25000);
INSERT INTO product_item_type (product_item_id, item_type)
VALUES (2, 'Single'), (2, 'Double');

-- Product 3: Microwave Oven
INSERT INTO product (item_id, item_profile, item_desc, item_quantity, price)
VALUES (3, 'Microwave Oven', 'Compact and fast cooking oven', 4, 12000);
INSERT INTO product_item_type (product_item_id, item_type)
VALUES (3, 'Solo'), (3, 'Grill'), (3, 'Convection');

-- Product 4: Bed
INSERT INTO product (item_id, item_profile, item_desc, item_quantity, price)
VALUES (4, 'Bed', 'Wooden king-size bed', 2, 30000);
INSERT INTO product_item_type (product_item_id, item_type)
VALUES (4, 'Single'), (4, 'Double');

-- Product 5: Dining Table
INSERT INTO product (item_id, item_profile, item_desc, item_quantity, price)
VALUES (5, 'Dining Table', '6-seater dining table set', 6, 20000);
INSERT INTO product_item_type (product_item_id, item_type)
VALUES (5, 'Wood'), (5, 'Glass'), (5, 'Marble');

-- Product 6: Washing Machine
INSERT INTO product (item_id, item_profile, item_desc, item_quantity, price)
VALUES (6, 'Washing Machine', 'Front-load washing machine with inverter motor', 4, 18000);
INSERT INTO product_item_type (product_item_id, item_type)
VALUES (6, 'Front Load'), (6, 'Top Load');

-- Product 7: Laptop
INSERT INTO product (item_id, item_profile, item_desc, item_quantity, price)
VALUES (7, 'Laptop', 'Lightweight laptop with 16GB RAM', 10, 55000);
INSERT INTO product_item_type (product_item_id, item_type)
VALUES (7, 'Gaming'), (7, 'Business'), (7, 'Student');

-- Product 8: Television
INSERT INTO product (item_id, item_profile, item_desc, item_quantity, price)
VALUES (8, 'Television', '55-inch 4K Smart LED TV', 7, 40000);
INSERT INTO product_item_type (product_item_id, item_type)
VALUES (8, 'LED'), (8, 'OLED'), (8, 'QLED');

-- Product 9: Air Conditioner
INSERT INTO product (item_id, item_profile, item_desc, item_quantity, price)
VALUES (9, 'Air Conditioner', 'Split AC with inverter technology', 5, 35000);
INSERT INTO product_item_type (product_item_id, item_type)
VALUES (9, 'Split'), (9, 'Window');

-- Product 10: Wardrobe
INSERT INTO product (item_id, item_profile, item_desc, item_quantity, price)
VALUES (10, 'Wardrobe', '3-door wooden wardrobe with mirror', 3, 22000);
INSERT INTO product_item_type (product_item_id, item_type)
VALUES (10, 'Wood'), (10, 'Laminate');

-- Products already exist in your PRODUCT table, so just insert listings
-- Suppose product.itemId = 4 is "Bed"
INSERT INTO platform_listing (platform, url, price, unit, product_id)
VALUES ('Rentomojo', 'https://www.rentomojo.com/items/queen-bed', 799, 'per month', 4),
       ('Cityfurnish', 'https://cityfurnish.com/rent/queen-bed', 749, 'per month', 4);

-- Suppose product.itemId = 2 is "Refrigerator"
INSERT INTO platform_listing (platform, url, price, unit, product_id)
VALUES ('Rentomojo', 'https://www.rentomojo.com/items/refrigerator', 999, 'per month', 2),
       ('Cityfurnish', 'https://cityfurnish.com/rent/refrigerator', 899, 'per month', 2);
