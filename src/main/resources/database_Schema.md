# Manual to set up Database
## Create Database
```sql
CREATE DATABASE marketplace_database
```
## Create Tables
### Player Table
```sql
CREATE TABLE player (player_name varchar(30) NOT NULL,
                     inventory_space INT NOT NULL,
                     day INT NOT NULL,
                     money decimal(10, 2) NOT NULL); 
```
### HighScore Table
```sql
CREATE TABLE high_score (player_name VARCHAR(30) NOT NULL,
                         score INT NOT NULL);
```
### Inventory Table
```sql
CREATE TABLE inventory (item_id INT NOT NULL PRIMARY KEY ,
                    item_name TEXT NOT NULL,
                    amount INT NOT NULL);
```
### Items Table
```sql
CREATE TABLE items (item_id INT NOT NULL PRIMARY KEY, 
                    item_name TEXT NOT NULL, 
                    image BLOB NOT NULL, 
                    price decimal(10, 2) NOT NULL);
```
## Insert Items into Table
```sql
INSERT INTO items (item_id, item_name, image, price)
VALUES
    (1, 'Bread', 'Placeholder', 2.0),
    (2, 'Steak', 'Placeholder', 4.0),
    (3, 'Cheese', 'Placeholder', 5.0),
    (4, 'Mead', 'Placeholder', 12.0),
    (5, 'Apple', 'Placeholder', 3.0),
    (6, 'Pickaxe', 'Placeholder', 10.0),
    (7, 'Journal', 'Placeholder', 8.0),
    (8, 'Fire Starter', 'Placeholder', 3.0),
    (9, 'Bucket', 'Placeholder', 4.0),
    (10, 'Rope', 'Placeholder', 6.0),
    (11, 'Sword', 'Placeholder', 50.0),
    (12, 'Bow', 'Placeholder', 30.0),
    (13, 'Arrows', 'Placeholder', 15.0),
    (14, 'Chest Plate', 'Placeholder', 25.0),
    (15, 'Iron Helmet', 'Placeholder', 20.0),
    (16, 'Healing Potion', 'Placeholder', 18.0),
    (17, 'Elixir', 'Placeholder', 25.0),
    (18, 'Herbs', 'Placeholder', 7.0),
    (19, 'Antidote Potion', 'Placeholder', 16.0),
    (20, 'Tea', 'Placeholder', 5.0),
    (21, 'Cloak', 'Placeholder', 22.0),
    (22, 'Boots', 'Placeholder', 12.0),
    (23, 'Silver Ring', 'Placeholder', 20.0),
    (24, 'Tunic', 'Placeholder', 8.0),
    (25, 'Hat', 'Placeholder', 10.0),
    (26, 'Crystal Orb', 'Placeholder', 60.0),
    (27, 'Spell Scroll', 'Placeholder', 45.0),
    (28, 'Amulet', 'Placeholder', 80.0),
    (29, 'Mysterious Dust', 'Placeholder', 40.0),
    (30, 'Dragon Scale', 'Placeholder', 100.0);
```