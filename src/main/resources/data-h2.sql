INSERT INTO MENU(menu_id, menu_price, menu_remain, menu_status)
VALUES (1, 1000, 10, 'OK'),
       (2, 2000, 10, 'OK'),
       (3, 3000, 10, 'OK'),
       (4, 4000, 10, 'OK'),
       (5, 5000, 10, 'OK'),
       (6, 6000, 10, 'OK'),
       (7, 7000, 10, 'OK');

INSERT INTO ORDERS(order_id, order_total_price, order_timestamp, order_status, order_table_number)
VALUES (1, 3000, null, 'ORDER_STATUS_BEFORE_PAYMENT', 1),
       (2, 4000, null, 'ORDER_STATUS_PREPARING', 1),
       (3, 5000, null, 'ORDER_STATUS_PREPARING', 1),
       (4, 6000, null, 'ORDER_STATUS_COMPLETE', 1),
       (5, 7000, null, 'ORDER_STATUS_COMPLETE', 1);