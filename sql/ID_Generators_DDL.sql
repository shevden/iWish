CREATE OR REPLACE TRIGGER category_auto_id
BEFORE INSERT ON category
FOR EACH ROW

BEGIN
  SELECT seq_category.NEXTVAL
  INTO   :new.category_id
  FROM   dual;
END;


CREATE OR REPLACE TRIGGER layout_auto_id
BEFORE INSERT ON layout
FOR EACH ROW

BEGIN
  SELECT seq_layout.NEXTVAL
  INTO   :new.layout_id
  FROM   dual;
END;


CREATE OR REPLACE TRIGGER template_auto_id
BEFORE INSERT ON template
FOR EACH ROW

BEGIN
  SELECT seq_template.NEXTVAL
  INTO   :new.template_id
  FROM   dual;
END;


CREATE OR REPLACE TRIGGER user_auto_id
BEFORE INSERT ON "USER"
FOR EACH ROW

BEGIN
  SELECT seq_user.NEXTVAL
  INTO   :new.user_id
  FROM   dual;
END;


CREATE OR REPLACE TRIGGER wishlist_auto_id
BEFORE INSERT ON wishlist
FOR EACH ROW

BEGIN
  SELECT seq_wishlist.NEXTVAL
  INTO   :new.wishlist_id
  FROM   dual;
END;


CREATE OR REPLACE TRIGGER wku_auto_id
BEFORE INSERT ON wku
FOR EACH ROW

BEGIN
  SELECT seq_wku.NEXTVAL
  INTO   :new.wku_id
  FROM   dual;
END;
