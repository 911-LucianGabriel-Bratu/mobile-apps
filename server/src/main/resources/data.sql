DELETE FROM musical_instruments;
DELETE FROM orders;
DELETE FROM instrument_brands;
DELETE FROM instrument_categories;
DELETE FROM users;
--https://i.kym-cdn.com/entries/icons/mobile/000/047/683/cheeseburger_wah.jpg
--https://assets.stickpng.com/images/585aad184f6ae202fedf2913.png
INSERT INTO instrument_brands (instrument_brandid, instrument_brand_name, png_url)
VALUES (1, 'Fender', 'https://assets.stickpng.com/images/585aad184f6ae202fedf2913.png'),
       (2, 'Gibson', 'https://upload.wikimedia.org/wikipedia/commons/thumb/5/51/Gibson_Guitar_logo.svg/1280px-Gibson_Guitar_logo.svg.png'),
       (3, 'Ibanez', 'https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Ibanez_logo.svg/2560px-Ibanez_logo.svg.png');

INSERT INTO instrument_categories (instrument_categoryid, instrument_category_name, png_url)
VALUES (1, 'Electric Guitars', 'https://thenounproject.com/api/private/icons/77928/edit/?backgroundShape=SQUARE&backgroundShapeColor=%23000000&backgroundShapeOpacity=0&exportSize=752&flipX=false&flipY=false&foregroundColor=%23000000&foregroundOpacity=1&imageFormat=png&rotation=0'),
       (2, 'Bass Guitars', 'https://thenounproject.com/api/private/icons/367516/edit/?backgroundShape=SQUARE&backgroundShapeColor=%23000000&backgroundShapeOpacity=0&exportSize=752&flipX=false&flipY=false&foregroundColor=%23000000&foregroundOpacity=1&imageFormat=png&rotation=0'),
       (3, 'Keyboards', 'https://cdn-icons-png.flaticon.com/512/1002/1002081.png');

INSERT INTO musical_instruments (musical_instrumentid, instrument_categoryid, instrument_brandid, musical_instrument_name, description, png_url, price, quantity, on_sale)
VALUES (1, 1, 1, 'Fender Vintera II 60s Strat RW 3TS', 'Body: Alder, Neck: Maple, Rosewood fretboard', 'https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/571354.webp', 5444, 23, false),
(2, 1, 1, 'Fender Player Series Strat MN PWT', 'Body: Alder, Neck: Maple, Rosewood fretboard', 'https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/439056.webp', 3799, 10, true),
(3, 1, 1, 'Fender AM Ultra Tele MN Cobra Blue', 'Body: Alder, Neck: Maple, Maple fretboard', 'https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/477101.webp', 11390, 5, false),
(4, 1, 2, 'Gibson SG ´61 Standard VC', 'Body: Mahogany, Neck: Mahogany, Rosewood fretboard', 'https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/462531.webp', 9299, 7, false),
(5, 1, 2, 'Gibson ES-335 Figured Iced Tea', 'Semi-hollow body shape, Neck: Mahogany, Rosewood fretboard', 'https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/482463.webp', 19590, 2, false),
(6, 1, 2, 'Gibson Les Paul Standard 50s P90', 'Body: Mahogany, Neck: Mahogany, Rosewood fretboard', 'https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/462508.webp', 13690, 5, true),
(7, 1, 3, 'Ibanez PIA3761C', 'Steve Vai Signature Model, Body: Alder', 'https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/555556.webp', 15890, 1, false),
(8, 1, 3, 'Ibanez KIKO100-TRR', 'Kiko Loureiro Signature Model, Body: Alder', 'https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/546524.webp', 11890, 4, true),
(9, 1, 3, 'Ibanez RG9PB-TGF', 'Body: Nyatoh, Top: Burl Polar', 'https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/557157.webp', 6198, 5, false),
(10, 2, 1, 'Fender Jaco PastoriusTribute Jazz 3SB', 'Body: Alder, Neck: Maple, Fretboard: Rosewood', 'https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/179948.webp', 20890, 3, false),
(11, 2, 1, 'Fender Am Pro II Jazz Bass RW MERC', 'Body: Alder, Neck: Maple, Fretboard: Rosewood', 'https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/500294.webp', 10090, 6, false),
(12, 2, 1, 'Fender Am Pro II Jazz Bass MN DK NIT', 'Body: Alder, Neck: Maple, Fretboard: Rosewood', 'https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/500412.webp', 9899, 10, true),
(13, 2, 3, 'Ibanez TMB30-MGR Talman Short Scale', 'Body: Poplar, Neck: Maple, Fretboard: Purpleheart', 'https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/457381.webp', 1090, 30, true),
(14, 2, 3, 'Ibanez TMB30-IV', 'Body: Poplar, Neck: Maple, Fretboard: Purpleheart', 'https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/387082.webp', 1090, 20, false),
(15, 2, 3, 'Ibanez GSR180-BK', 'Body: Poplar, Neck: Maple, Fretboard: Purpleheart', 'https://thumbs.static-thomann.de/thumb/thumb220x220/pics/prod/177146.webp', 948, 10, true);

INSERT INTO Users
VALUES (1, 'username', 'password');