INSERT into category(cat_name) values 
('Fashion'),
('Electronics'),
('Books'),
('Groceries'),
('Medicines');

INSERT into role(role) values 
('CONSUMER'),
('SELLER'); 

INSERT into custom_user ( password , username) values 
('pass_word','jack'), 
('pass_word','bob'), 
('pass_word','apple'), 
('pass_word','glaxo');

INSERT into cart (cart_owner , t_amount) values
(1,20),
(2,0);

INSERT into user_role (roles_role_id , user_user_id) values
(1,1),
(1,2),
(2,3),
(2,4);

insert into product (price,p_name,category_id ,  seller_id ) values 
(29190,'Apple ipad 10.2',2,3),
(39190,'Apple ipad 11.3',2,3),
(10,'Crocin pain',5,4);

INSERT into  cart_products (cart_id, product_id, quantity) values
(1,2,2);

