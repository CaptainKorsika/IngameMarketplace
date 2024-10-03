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
                     money DECIMAL(10, 2) NOT NULL); 
```
### HighScore Table
```sql
CREATE TABLE high_score (player_name VARCHAR(30) NOT NULL,
                         score INT NOT NULL);
```
### Inventory Table
```sql
CREATE TABLE inventory (item_name TEXT NOT NULL,
                    amount INT NOT NULL);
```
### Items Table
```sql
CREATE TABLE items (item_name TEXT NOT NULL, 
                    image_url TEXT NOT NULL, 
                    price DECIMAL(10, 2) NOT NULL);
```
## Insert Items into Table
```sql
INSERT INTO items (item_name, image_url, price)
VALUES
    ('Bread', '/assets/items/bread.png', 2.0),
    ('Steak', '/assets/items/steak.png', 4.0),
    ('Cheese', '/assets/items/cheese.png', 5.0),
    ('Mead', '/assets/items/mead.png', 12.0),
    ('Apple', '/assets/items/apple.png', 3.0),
    ('Pickaxe', '/assets/items/pickaxe.jpg', 10.0),
    ('Journal', '/assets/items/journal.png', 8.0),
    ('Fire Starter', '/assets/items/fire_starter.png', 3.0),
    ('Bucket', '/assets/items/bucket.png', 4.0),
    ('Rope', '/assets/items/rope.png', 6.0),
    ('Sword', '/assets/items/sword.jpg', 50.0),
    ('Bow', '/assets/items/bow.jpg', 30.0),
    ('Arrows', '/assets/items/arrows.jpg', 15.0),
    ('Chest Plate', '/assets/items/chest_plate.png', 25.0),
    ('Iron Helmet', '/assets/items/iron_helmet.png', 20.0),
    ('Healing Potion', '/assets/items/healing_potion.png', 18.0),
    ('Elixir', '/assets/items/elixir.png', 25.0),
    ('Herbs', '/assets/items/herbs.png', 7.0),
    ('Antidote Potion', '/assets/items/antidote_potion.png', 16.0),
    ('Tea', '/assets/items/tea.png', 5.0),
    ('Cloak', '/assets/items/cloak.png', 22.0),
    ('Boots', '/assets/items/boots.png', 12.0),
    ('Silver Ring', '/assets/items/silver_ring.png', 20.0),
    ('Tunic', '/assets/items/tunic.png', 8.0),
    ('Hat', '/assets/items/hat.png', 10.0),
    ('Crystal Orb', '/assets/items/crystal_orb.png', 60.0),
    ('Spell Scroll', '/assets/items/spell_scroll.png', 45.0),
    ('Amulet', '/assets/items/amulet.png', 80.0),
    ('Mysterious Dust', '/assets/items/mysterious_dust.png', 40.0),
    ('Dragon Scale', '/assets/items/dragon_scale.png', 100.0);
```