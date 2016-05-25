CREATE SEQUENCE seq_CATEGORY;
CREATE SEQUENCE seq_LAYOUT;
CREATE SEQUENCE seq_TEMPLATE;
CREATE SEQUENCE seq_USER;
CREATE SEQUENCE seq_WISHLIST;
CREATE SEQUENCE seq_WKU;
CREATE TABLE CATEGORY (category_id number(19) NOT NULL, title varchar2(255), priority number(10), background varchar2(10), color varchar2(10), PRIMARY KEY (category_id));
CREATE TABLE CATEGORY_LAYOUT (category_id number(19) NOT NULL, layout_id number(19) NOT NULL, PRIMARY KEY (category_id, layout_id));
CREATE TABLE FRIEND (user_id number(19) NOT NULL, friend_id number(19), PRIMARY KEY (user_id));
CREATE TABLE GIFTLIST (wishlist_id number(19) NOT NULL, friend_id number(19) NOT NULL, PRIMARY KEY (wishlist_id, friend_id));
CREATE TABLE LAYOUT (layout_id number(19) NOT NULL, title number(10), model varchar2(40), height varchar2(40), width varchar2(40), padding varchar2(40), margin varchar2(40), PRIMARY KEY (layout_id));
CREATE TABLE LAYOUT_TEMPLATE (template_id number(19) NOT NULL, layout_id number(19) NOT NULL, PRIMARY KEY (template_id, layout_id));
CREATE TABLE TEMPLATE (template_id number(19) NOT NULL, title varchar2(255), main_color varchar2(10), title_color varchar2(10), text_color varchar2(10), border_color varchar2(10), border_type varchar2(40), border_width varchar2(20), text_style varchar2(40), title_font varchar2(40), "text-style" varchar2(40), text_font varchar2(40), image_position varchar2(40), PRIMARY KEY (template_id));
CREATE TABLE "USER" (user_id number(19) NOT NULL, email varchar2(255), password_hash varchar2(255), first_name varchar2(255), last_name varchar2(255), PRIMARY KEY (user_id));
CREATE TABLE WISHLIST (wishlist_id number(19) NOT NULL, user_id number(19) NOT NULL, title varchar2(255), priority number(10), background varchar2(10), color varchar2(10), PRIMARY KEY (wishlist_id));
CREATE TABLE WISHLIST_LAYOUT (wishlist_id number(19) NOT NULL, layout_id number(19) NOT NULL, PRIMARY KEY (wishlist_id, layout_id));
CREATE TABLE WISHLIST_WKU (wishlist_id number(19) NOT NULL, wku_id number(19) NOT NULL, PRIMARY KEY (wishlist_id, wku_id));
CREATE TABLE WKU (wku_id number(19) NOT NULL, title varchar2(255), priority number(2), large_image_url varchar2(512), small_image_url varchar2(512), description varchar2(2048), PRIMARY KEY (wku_id));
CREATE TABLE WKU_CATEGORY (wku_id number(19) NOT NULL, category_id number(19) NOT NULL, PRIMARY KEY (wku_id, category_id));
CREATE TABLE WKU_REMOTE (wku_id number(19) NOT NULL, remote_url varchar2(512), PRIMARY KEY (wku_id));
CREATE TABLE WKU_TEMPLATE (wku_id number(19) NOT NULL, template_id number(19) NOT NULL, PRIMARY KEY (wku_id, template_id));
ALTER TABLE FRIEND ADD CONSTRAINT FKFRIEND363013 FOREIGN KEY (user_id) REFERENCES "USER" (user_id);
ALTER TABLE WISHLIST ADD CONSTRAINT FKWISHLIST666351 FOREIGN KEY (user_id) REFERENCES "USER" (user_id);
ALTER TABLE GIFTLIST ADD CONSTRAINT FKGIFTLIST581837 FOREIGN KEY (wishlist_id) REFERENCES WISHLIST (wishlist_id);
ALTER TABLE GIFTLIST ADD CONSTRAINT FKGIFTLIST141447 FOREIGN KEY (friend_id) REFERENCES "USER" (user_id);
ALTER TABLE WKU_CATEGORY ADD CONSTRAINT FKWKU_CATEGO457336 FOREIGN KEY (wku_id) REFERENCES WKU (wku_id);
ALTER TABLE WKU_CATEGORY ADD CONSTRAINT FKWKU_CATEGO507356 FOREIGN KEY (category_id) REFERENCES CATEGORY (category_id);
ALTER TABLE WKU_TEMPLATE ADD CONSTRAINT FKWKU_TEMPLA601767 FOREIGN KEY (wku_id) REFERENCES WKU (wku_id);
ALTER TABLE LAYOUT_TEMPLATE ADD CONSTRAINT FKLAYOUT_TEM496473 FOREIGN KEY (template_id) REFERENCES TEMPLATE (template_id);
ALTER TABLE WKU_TEMPLATE ADD CONSTRAINT FKWKU_TEMPLA886347 FOREIGN KEY (template_id) REFERENCES TEMPLATE (template_id);
ALTER TABLE LAYOUT_TEMPLATE ADD CONSTRAINT FKLAYOUT_TEM203513 FOREIGN KEY (layout_id) REFERENCES LAYOUT (layout_id);
ALTER TABLE WISHLIST_LAYOUT ADD CONSTRAINT FKWISHLIST_L506401 FOREIGN KEY (wishlist_id) REFERENCES WISHLIST (wishlist_id);
ALTER TABLE WISHLIST_LAYOUT ADD CONSTRAINT FKWISHLIST_L474665 FOREIGN KEY (layout_id) REFERENCES LAYOUT (layout_id);
ALTER TABLE CATEGORY_LAYOUT ADD CONSTRAINT FKCATEGORY_L557004 FOREIGN KEY (category_id) REFERENCES CATEGORY (category_id);
ALTER TABLE CATEGORY_LAYOUT ADD CONSTRAINT FKCATEGORY_L598644 FOREIGN KEY (layout_id) REFERENCES LAYOUT (layout_id);
ALTER TABLE WKU_REMOTE ADD CONSTRAINT FKWKU_REMOTE41999 FOREIGN KEY (wku_id) REFERENCES WKU (wku_id);
ALTER TABLE WISHLIST_WKU ADD CONSTRAINT FKWISHLIST_W328718 FOREIGN KEY (wishlist_id) REFERENCES WISHLIST (wishlist_id);
ALTER TABLE WISHLIST_WKU ADD CONSTRAINT FKWISHLIST_W631165 FOREIGN KEY (wku_id) REFERENCES WKU (wku_id);
