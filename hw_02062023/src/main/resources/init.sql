DROP SCHEMA IF EXISTS eshop;
CREATE SCHEMA IF NOT EXISTS eshop;

DROP TABLE IF EXISTS eshop.categories;
CREATE TABLE IF NOT EXISTS eshop.categories(
                                               id INT NOT NULL AUTO_INCREMENT,
                                               name VARCHAR(60) NOT NULL,
    PRIMARY KEY(id),
    UNIQUE INDEX IDX_categories_id_UNIQUE(id ASC),
    UNIQUE INDEX IDX_categories_name_UNIQUE(name ASC));


DROP TABLE IF EXISTS eshop.users;
CREATE TABLE IF NOT EXISTS eshop.users(
                                          id INT NOT NULL AUTO_INCREMENT,
                                          name VARCHAR(20) NOT NULL,
    surname VARCHAR(20) NOT NULL,
    birthDate DATETIME NOT NULL,
    email VARCHAR(60) NOT NULL,
    password VARCHAR(20) NOT NULL,
    PRIMARY KEY(id),
    UNIQUE INDEX IDX_users_id_UNIQUE(id ASC),
    UNIQUE INDEX IDX_users_email_UNIQUE(email ASC));

DROP TABLE IF EXISTS eshop.orders;
CREATE TABLE IF NOT EXISTS eshop.orders(
                                           id INT NOT NULL AUTO_INCREMENT,
                                           price NUMERIC(20) NOT NULL,
    orderDate DATETIME NOT NUll,
    userId INT NOT NULL,
    address VARCHAR(60) NOT NULL,
    PRIMARY KEY(id),
    UNIQUE INDEX IDX_orders_id_UNIQUE(id ASC),
    CONSTRAINT FK_orders_userId_users_id
    FOREIGN KEY(userId)
    REFERENCES eshop.users (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

DROP TABLE IF EXISTS eshop.products;
CREATE TABLE IF NOT EXISTS eshop.products(
                                             id INT NOT NULL AUTO_INCREMENT,
                                             name VARCHAR(60) NOT NULL,
    description VARCHAR(500) NOT NULL,
    imagePath VARCHAR(20) NOT NULL,
    categoryId INT NOT NULL,
    price DECIMAL(20) NOT NULL,
    PRIMARY KEY(id),
    UNIQUE INDEX IDX_products_id (id ASC),
    CONSTRAINT FK_products_categoryId_categories_id
    FOREIGN KEY (categoryId)
    REFERENCES eshop.categories (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
DROP TABLE IF EXISTS eshop.orders_products;
CREATE TABLE IF NOT EXISTS eshop.orders_products(
                                                    orderId INT NOT NULL ,
                                                    productId INT NOT NULL ,
                                                    quantity INT NOT NULL DEFAULT 0,
                                                    PRIMARY KEY (orderId,productId),
    CONSTRAINT FK_orders_products_orderId_orders_id
    FOREIGN KEY (orderId)
    REFERENCES eshop.orders(id),
    CONSTRAINT FK_orders_products_productId_products_id
    FOREIGN KEY (productId)
    REFERENCES eshop.products(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
INSERT INTO eshop.categories(name) VALUES("Бюстгалтеры");
INSERT INTO eshop.categories(name) VALUES("Трусики");
INSERT INTO eshop.categories(name) VALUES("Трикотаж");
INSERT INTO eshop.categories(name) VALUES("Пижамы");
INSERT INTO eshop.categories(name) VALUES("Колготки и чулки");
INSERT INTO eshop.categories(name) VALUES("Носки");

INSERT INTO eshop.products(name,description,imagePath,categoryId,price) VALUES("Балконет","Бюстгалтер Балконет из Ультратонкой Микрофибры ","balkonet.png",1,2999);
INSERT INTO eshop.products(name,description,imagePath,categoryId,price) VALUES("Треугольник","Бюстгальтер Треугольной Формы Emma Feeling Beautiful","triangle.png",1,2999);
INSERT INTO eshop.products(name,description,imagePath,categoryId,price) VALUES("Пуш-ап","Бюстгалтер Супер Пуш-ап из Микрофибры Ultralight","pushApp.png",1,2999);
INSERT INTO eshop.products(name,description,imagePath,categoryId,price)VALUES("Бандо/Без бретелек","Бюстгалтер Балконет Трансформер","bando.png",1,2999);

INSERT INTO eshop.products(name,description,imagePath,categoryId,price) VALUES("Бразильяно","Бразильяно Pretty Flowers","braziliano.png",2,1119);
INSERT INTO eshop.products(name,description,imagePath,categoryId,price) VALUES("Шортики","Моделирующие Шортики из Микрофибры Без Швов","shorts.png",2,4629);
INSERT INTO eshop.products(name,description,imagePath,categoryId,price) VALUES("Стринги","Стринги из шелка и кружева","stringes.png",2,1119);
INSERT INTO eshop.products(name,description,imagePath,categoryId,price) VALUES("С высокой талией","Высокие Кружевные Кюлоты","highWaist.png",2,1119);

INSERT INTO eshop.products(name,description,imagePath,categoryId,price) VALUES("Боди","Кружевное Боди","body.png",3,3499);
INSERT INTO eshop.products(name,description,imagePath,categoryId,price) VALUES("Футболка","Льняной Топ с Короткими Рукавами","t-short.png",3,2599);
INSERT INTO eshop.products(name,description,imagePath,categoryId,price) VALUES("Рубашка","Шелковая Рубашка с Длинными Рукавами","silkShirt.png",3,3499);
INSERT INTO eshop.products(name,description,imagePath,categoryId,price) VALUES("Легинсы","Легинсы из Ультратонкого Модала","leggings.png",3,3499);

INSERT INTO eshop.products(name,description,imagePath,categoryId,price) VALUES("Длинная пижама","Пижама из Атласа и Шелка","longPajamas.png",4,6667);
INSERT INTO eshop.products(name,description,imagePath,categoryId,price) VALUES("Короткая пижама","Пижама из Атласа и Шелка","shirtPajamas.png",4,5449);
INSERT INTO eshop.products(name,description,imagePath,categoryId,price)VALUES("Ночная сорочка","Ночная Рубашка из Хлопка","nightDress.png",4,3499);






