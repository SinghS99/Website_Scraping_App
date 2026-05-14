-- Suppose product.itemId = 4 is "Bed"
INSERT INTO platform_listing (platform, url, price, unit, product_id)
VALUES ('Rentomojo', 'https://www.rentomojo.com/items/queen-bed', 799, 'per month', 4),
       ('Cityfurnish', 'https://cityfurnish.com/rent/queen-bed', 749, 'per month', 4);

-- Suppose product.itemId = 2 is "Refrigerator"
INSERT INTO platform_listing (platform, url, price, unit, product_id)
VALUES ('Rentomojo', 'https://www.rentomojo.com/items/refrigerator', 999, 'per month', 2),
       ('Cityfurnish', 'https://cityfurnish.com/rent/refrigerator', 899, 'per month', 2);
