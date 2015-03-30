
--This creates the queries to delete the tables..
select 'drop table '||table_name||' cascade constraints;' from user_tables;

CREATE TABLE ROLE(
 role_id integer PRIMARY KEY,
 role_name varchar(50)
);

CREATE TABLE CASES(
 case_id integer PRIMARY KEY,
 case_type varchar(50) NOT NULL,
 status varchar(20) NOT NULL,
 open_date date NOT NULL,
 closed_date date,
 contact_name varchar(50),
 description varchar(100),
 case_owner varchar(50) NOT NULL,
 case_priority varchar(20)
);



CREATE TABLE IMGMT_USER(
    user_id integer PRIMARY KEY,
    first_name varchar(50) NOT NULL,
    middle_name varchar(50),
    last_name varchar(50) NOT NULL,
    phone_no integer,
    passwd varchar(50) NOT NULL,
    role_id integer NOT NULL, 
    street varchar(100),
    city varchar(50),
    state varchar(50),
    zip_code integer,
    email varchar(20) NOT NULL,
    FOREIGN KEY(role_id) REFERENCES ROLE(role_id)
  );

CREATE TABLE BRAND_MANAGER
  (
  user_id INTEGER PRIMARY KEY,
  FOREIGN KEY (user_id) REFERENCES IMGMT_USER(user_id)
  );

CREATE TABLE STORE_MANAGER
  (
  user_id INTEGER PRIMARY KEY,
  FOREIGN KEY (user_id) REFERENCES IMGMT_USER(user_id)
  );
CREATE TABLE INVENTORY_MANAGER(
  user_id INTEGER PRIMARY KEY,
  FOREIGN KEY (user_id) REFERENCES IMGMT_USER(user_id)
  );

CREATE TABLE ADMIN(
  user_id INTEGER PRIMARY KEY,
  FOREIGN KEY (user_id) REFERENCES IMGMT_USER(user_id)
  );

  CREATE TABLE PAYMENT(
    bill_id integer PRIMARY KEY,
    bill_date date NOT NULL,
    total NUMBER(30,2) NOT NULL,
    bill_status varchar(20) NOT NULL,
    quantity integer not null,
    brand_manager_id integer not null,
    inventory_manager_id integer not null,
    FOREIGN KEY(brand_manager_id) REFERENCES BRAND_MANAGER(user_id),
    FOREIGN KEY(inventory_manager_id) REFERENCES       INVENTORY_MANAGER(user_id)
    );
  
 


 CREATE TABLE DISCOUNT(
  discount_id integer PRIMARY KEY,
  start_date date,
  end_date date,
  active varchar(20),
  dis_percent NUMBER(4,2),
  store_manager_id integer not null,
  inventory_manager_id integer not null,
  FOREIGN KEY(store_manager_id) REFERENCES STORE_MANAGER(user_id),
  FOREIGN KEY(inventory_manager_id) REFERENCES INVENTORY_MANAGER(user_id)
  );



   CREATE TABLE ITEM_STORE(
    store_id integer PRIMARY KEY,
    user_id integer,
    store_type CHAR(1) NOT NULL,
    street varchar(50),
    city varchar(20),
    state varchar(20),
    zip_code integer NOT NULL,
    FOREIGN KEY (user_id) REFERENCES STORE_MANAGER(user_id)
  );
  
    CREATE TABLE ITEM_ORDER(
    order_id integer PRIMARY KEY,
    description varchar(100),
    order_date date,
    user_id integer not null,
    store_id integer not null,
    FOREIGN KEY(store_id) REFERENCES ITEM_STORE(store_id),
    FOREIGN KEY(user_id) REFERENCES IMGMT_USER(user_id)
  );

   CREATE TABLE ITEM(
  item_id integer PRIMARY KEY,
  active char(1) NOT NULL,
  selling_price NUMBER(10,2),
  item_name varchar(50) NOT NULL,
  description varchar(100),
  discount_id integer,
  order_id integer,
  FOREIGN KEY(discount_id) REFERENCES DISCOUNT(discount_id),
  FOREIGN KEY(order_id) REFERENCES ITEM_ORDER(order_id)
  );
  
    
 CREATE TABLE ITEM_DETAIL(
    item_id integer PRIMARY KEY,
    cost_price NUMBER(10,2) NOT NULL,
    exp_date date,
    item_category varchar(20) NOT NULL,
    item_size varchar(20) NOT NULL,
    mfg_date date NOT NULL,
    weight NUMBER(4,2) NOT NULL,
    FOREIGN KEY(item_id) REFERENCES ITEM(item_id)
  );
  

  CREATE TABLE BRAND(
    brand_id integer PRIMARY KEY,
    brand_name varchar(50) NOT NULL UNIQUE,
    user_id integer NOT NULL,
    FOREIGN KEY(user_id) REFERENCES BRAND_MANAGER(user_id)
);

CREATE TABLE "CATEGORY"(
   cat_id integer PRIMARY KEY,
   cat_name varchar(20) NOT NULL,
   sub_category varchar(20)
  );

  CREATE TABLE BELONGS_TO(
    brand_id integer,
    category_id integer,
    PRIMARY KEY(brand_id, category_id),
    FOREIGN KEY(brand_id) REFERENCES BRAND(brand_id),
    FOREIGN KEY(category_id) REFERENCES CATEGORY(cat_id)
 );
  
 create table review(
 review_id integer primary KEY not null,
 sku integer,
 rating integer,
 title varchar(100),
 comments varchar(100)
);