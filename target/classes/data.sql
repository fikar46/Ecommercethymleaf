--USERS--
INSERT INTO USERS (ID,USERNAME,WALLET,PASSWORD) VALUES
(1,'fikar',5000000,'fikar123'),
(2,'rosy',5500000,'rosy123'),
(3,'jamal',500000,'jamal123'),
(4,'aziz',500000,'aziz123'),
(5,'rizal',500000,'rizal123'),
(6,'muklis',500000,'muklis123');

--Produk--

INSERT INTO HARDWARE (ID,HARGA,MERK,NAMABARANG,STOCK) VALUES
(1,350000,'VGEN','SSD 150 GB',20),
(2,550000,'KINGSTON','HARDISK 500 GB',20),
(3,350000,'CORSAIR','KEYBOARD RGB',25),
(4,160000,'DIGITAL ALLIANCE','KEYBOARD RGB',10);

--transaksi--
INSERT INTO TRANSACTION (ID,HARGA,ID_HARDWARE,ID_USER,PPN,QTY,TOTAL) VALUES
(1,10000,1,1,10000,1,110000),
(2,10000,2,2,10000,1,110000),


