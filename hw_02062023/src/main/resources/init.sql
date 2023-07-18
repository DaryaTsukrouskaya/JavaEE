DROP SCHEMA IF EXISTS shop;
CREATE SCHEMA IF NOT EXISTS shop;
DROP TABLE IF EXISTS shop.users;
CREATE TABLE IF NOT EXISTS shop.users(
   id INT NOT NULL AUTO_INCREMENT,
   name VARCHAR(20) NOT NULL,
   surname VARCHAR(20) NOT NULL,
   birthDate TIMESTAMP NOT NULL,
   email VARCHAR(40) NOT NULL,
   password VARCHAR(10) NOT NULL,
   PRIMARY KEY (id));

DROP TABLE IF EXISTS shop.categories;
CREATE TABLE IF NOT EXISTS shop.categories(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    PRIMARY KEY (id));

DROP TABLE IF EXISTS shop.products;
CREATE TABLE IF NOT EXISTS shop.products(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    description VARCHAR(270) NOT NULL,
    category VARCHAR(40) NOT NULL,
    price DECIMAL NOT NULL,
    imageName VARCHAR(40) NOT NULL,
    PRIMARY KEY (id));


INSERT INTO shop.categories(name) VALUES("Бюстгалтеры");
INSERT INTO shop.categories(name) VALUES("Трусики");
INSERT INTO shop.categories(name) VALUES("Трикотаж");
INSERT INTO shop.categories(name) VALUES("Пижамы");
INSERT INTO shop.categories(name) VALUES("Колготки и чулки");
INSERT INTO shop.categories(name) VALUES("Носки");

INSERT INTO shop.products(name,description,category,price,imageName) VALUES("Балконет","Бюстгалтер Балконет из Ультратонкой Микрофибры ","Бюстгалтеры",2999,"balkonet.png");
INSERT INTO shop.products(name,description,category,price,imageName) VALUES("Треугольник","Бюстгальтер Треугольной Формы Emma Feeling Beautiful","Бюстгалтеры",2999,"triangle.png");
INSERT INTO shop.products(name,description,category,price,imageName) VALUES("Пуш-ап","Бюстгалтер Супер Пуш-ап из Микрофибры Ultralight","Бюстгалтеры",2999,"pushApp.png");
INSERT INTO shop.products(name,description,category,price,imageName)VALUES("Бандо/Без бретелек","Бюстгалтер Балконет Трансформер","Бюстгалтеры",2999,"bando.png");

INSERT INTO shop.products(name,description,category,price,imageName) VALUES("Бразильяно","Бразильяно Pretty Flowers","Трусики",1119,"braziliano.png");
INSERT INTO shop.products(name,description,category,price,imageName) VALUES("Шортики","Моделирующие Шортики из Микрофибры Без Швов","Трусики",4629,"shorts.png");
INSERT INTO shop.products(name,description,category,price,imageName) VALUES("Стринги","Стринги из шелка и кружева","Трусики",1119,"stringes.png");
INSERT INTO shop.products(name,description,category,price,imageName) VALUES("С высокой талией","Высокие Кружевные Кюлоты","Трусики",1119,"highWaist.png");

INSERT INTO shop.products(name,description,category,price,imageName) VALUES("Боди","Кружевное Боди","Трикотаж",3499,"body.png");
INSERT INTO shop.products(name,description,category,price,imageName) VALUES("Футболка","Льняной Топ с Короткими Рукавами","Трикотаж",2599,"t-short.png");
INSERT INTO shop.products(name,description,category,price,imageName) VALUES("Рубашка","Шелковая Рубашка с Длинными Рукавами","Трикотаж",3499,"silkShirt.png");
INSERT INTO shop.products(name,description,category,price,imageName) VALUES("Легинсы","Легинсы из Ультратонкого Модала","Трикотаж",3499,"leggings.png");

INSERT INTO shop.products(name,description,category,price,imageName) VALUES("Длинная пижама","Пижама из Атласа и Шелка","Пижамы",6667,"longPajamas.png");
INSERT INTO shop.products(name,description,category,price,imageName) VALUES("Короткая пижама","Пижама из Атласа и Шелка","Пижамы",5449,"shirtPajamas.png");
INSERT INTO shop.products(name,description,category,price,imageName) VALUES("Ночная сорочка","Ночная Рубашка из Хлопка","Пижамы",3499,"nightDress.png");

INSERT INTO shop.products(name,description,category,price,imageName) VALUES("Классические колготки","Полупрозрачные Колготки","Колготки и чулки",1119,"tights.png");
INSERT INTO shop.products(name,description,category,price,imageName) VALUES("Плотные колготки","Колготки из Модала с Добавлением Кашемира","Колготки и чулки",1119,"warmTights.png");
INSERT INTO shop.products(name,description,category,price,imageName) VALUES("Чулки","Прозрачные Матовые чулки","Колготки и чулки",1419,"stockings.png");
INSERT INTO shop.products(name,description,category,price,imageName) VALUES("Теплые чулки","Шерстяные Гольфины","Колготки и чулки",1119,"warmStockings.png");

INSERT INTO shop.products(name,description,category,price,imageName) VALUES("Классические носки","Носки с Блесткми","Носки",199,"socks.png");
INSERT INTO shop.products(name,description,category,price,imageName) VALUES("Короткие носки","Короткие Носки изо Льна и Вискозы","Носки",199,"shortSocks.png");
INSERT INTO shop.products(name,description,category,price,imageName) VALUES("Прозрачные носки","Ультратонкие Носки","Носки",199,"transparentSocks.png");
INSERT INTO shop.products(name,description,category,price,imageName) VALUES("Следки","Следки изо Льна и Вискозы","Носки",199,"footprints.png");

