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
                     money INT NOT NULL); 
```
### HighScore Table
```sql
CREATE TABLE high_score (player_name VARCHAR(30) NOT NULL,
                         score INT NOT NULL);
```
### Inventory Table
```sql
CREATE TABLE inventory (item_name TEXT NOT NULL,
                    amount INT NOT NULL,
                    avg_buying_price INT NOT NULL);
```
### Items Table
```sql
CREATE TABLE items (item_name TEXT NOT NULL, 
                    image_url TEXT NOT NULL, 
                    price INT NOT NULL);
```
## Insert Items into Table
```sql
INSERT INTO items (item_name, image_url, price)
VALUES
    ('Bread', '/assets/items/bread.png', 200),
    ('Steak', '/assets/items/steak.png', 400),
    ('Cheese', '/assets/items/cheese.png', 500),
    ('Mead', '/assets/items/mead.png', 1200),
    ('Apple', '/assets/items/apple.png', 300),
    ('Pickaxe', '/assets/items/pickaxe.jpg', 1000),
    ('Journal', '/assets/items/journal.png', 800),
    ('Fire Starter', '/assets/items/fire_starter.png', 300),
    ('Bucket', '/assets/items/bucket.png', 400),
    ('Rope', '/assets/items/rope.png', 600),
    ('Sword', '/assets/items/sword.jpg', 5000),
    ('Bow', '/assets/items/bow.jpg', 3000),
    ('Arrows', '/assets/items/arrows.jpg', 1500),
    ('Chest Plate', '/assets/items/chest_plate.png', 2500),
    ('Iron Helmet', '/assets/items/iron_helmet.png', 2000),
    ('Healing Potion', '/assets/items/healing_potion.png', 1800),
    ('Elixir', '/assets/items/elixir.png', 2500),
    ('Herbs', '/assets/items/herbs.png', 700),
    ('Antidote Potion', '/assets/items/antidote_potion.png', 1600),
    ('Tea', '/assets/items/tea.png', 500),
    ('Cloak', '/assets/items/cloak.png', 2200),
    ('Boots', '/assets/items/boots.png', 1200),
    ('Silver Ring', '/assets/items/silver_ring.png', 2000),
    ('Tunic', '/assets/items/tunic.png', 800),
    ('Hat', '/assets/items/hat.png', 1000),
    ('Crystal Orb', '/assets/items/crystal_orb.png', 6000),
    ('Spell Scroll', '/assets/items/spell_scroll.png', 4500),
    ('Amulet', '/assets/items/amulet.png', 8000),
    ('Mysterious Dust', '/assets/items/mysterious_dust.png', 4000),
    ('Dragon Scale', '/assets/items/dragon_scale.png', 10000);
```