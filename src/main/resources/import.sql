
/* Populate tabla productos */
INSERT INTO productos (nombre, precio, fecha_registro) VALUES ('Panasonic Pantalla LCD', 145627, now());
INSERT INTO productos (nombre, precio, fecha_registro) VALUES ('Iphone pro max 14', 11234567, now());
INSERT INTO productos (nombre, precio, fecha_registro) VALUES ('Galaxy Z20 Pro max', 2144567, now());
INSERT INTO productos (nombre, precio, fecha_registro) VALUES ('Computador AZUZ FLZ-56F', 1245467, now());
INSERT INTO productos (nombre, precio, fecha_registro) VALUES ('Apple Mac pro M5', 3234567, now());
INSERT INTO productos (nombre, precio, fecha_registro) VALUES ('Apple Wacht 13 mini', 233567, now());
INSERT INTO productos (nombre, precio, fecha_registro) VALUES ('Lavadora Wirpoul', 345627, now());

/* Populate tabla clientes */

INSERT INTO clientes ( nombre, apellido, email, fecha_registro) VALUES( 'Andrés', 'Guzmán1', 'profe1sor@bolsadeideas.com', '2018-01-01');
INSERT INTO clientes ( nombre, apellido, email, fecha_registro) VALUES( 'Mr. John', 'Doe1', 'john1.doe@gmail.com', '2018-01-02');
INSERT INTO clientes ( nombre, apellido, email, fecha_registro) VALUES( 'Linus', 'Torvald1s', 'lin1us.torvalds@gmail.com', '2018-01-03');
INSERT INTO clientes ( nombre, apellido, email, fecha_registro) VALUES( 'Rasmus', 'Lerdor1f', 'ras1mus.lerdorf@gmail.com', '2018-01-04');
INSERT INTO clientes ( nombre, apellido, email, fecha_registro) VALUES( 'Erich', 'Gam1ma', 'eri1ch.gamma@gmail.com', '2018-02-01');
INSERT INTO clientes ( nombre, apellido, email, fecha_registro) VALUES( 'Richard', 'Hel1m', 'ric1hard.helm@gmail.com', '2018-02-10');
INSERT INTO clientes ( nombre, apellido, email, fecha_registro) VALUES( 'Ralph', 'Johns1on', 'ral1ph.johnson@gmail.com', '2018-02-18');
INSERT INTO clientes ( nombre, apellido, email, fecha_registro) VALUES( 'John', 'Vlissid1es', 'joh1n.vlissides@gmail.com', '2018-02-28');
INSERT INTO clientes ( nombre, apellido, email, fecha_registro) VALUES( 'Dr. James', 'Gos1ling', 'ja1mes.gosling@gmail.com', '2018-03-03');
INSERT INTO clientes ( nombre, apellido, email, fecha_registro) VALUES( 'Magma', 'Le1e', 'magm1a.lee@gmail.com', '2018-03-04');
INSERT INTO clientes ( nombre, apellido, email, fecha_registro) VALUES( 'Tornado', 'Ro1e', 'tornado.r1oe@gmail.com', '2018-03-05');
INSERT INTO clientes ( nombre, apellido, email, fecha_registro) VALUES( 'Jade', 'Doe1', 'jane.do1e@gmail.com', '2018-03-06');


INSERT INTO usuarios (username, nombre, apellido, password, email, locked, disabled, fecha_registro) VALUES ('admin','Victor', 'Galvis','$2y$10$yacDJ.puxkaKOvITGdJ5q.JS04SD6hWdxo/6GpEmXRi6yP1mbTOpy', 'admin_admin@gmail.com', 0, 0, now());
INSERT INTO usuarios (username, nombre, apellido, password, email, locked, disabled, fecha_registro) VALUES ('userOther', 'other', 'gonzales', '$2y$10$2rKPOmbhBSGgsVX0xGN4Gujubx4elwmfPJ.FPiEZG9XFd6kXuM8Ly', 'userOther_123@gmail.com',0, 0, now());

INSERT INTO `roles` (nombre) VALUES ('ADMIN');
INSERT INTO `roles` (nombre) VALUES ('OTHER');

INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (1, 1);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (1, 2);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 1);

/* Creamos algunas facturas */
INSERT INTO facturas (descripcion, observacion, cliente_id, fecha_registro) VALUES ('Factura equipo oficina', null, 1, now());
INSERT INTO facturas_items (cantidad, factura_id ,producto_id) VALUES (1,1,1);
INSERT INTO facturas_items (cantidad, factura_id ,producto_id) VALUES (2,1,4);
INSERT INTO facturas_items (cantidad, factura_id ,producto_id) VALUES (1,1,5);
INSERT INTO facturas_items (cantidad, factura_id ,producto_id) VALUES (1,1,7);

INSERT INTO facturas (descripcion, observacion, cliente_id, fecha_registro) VALUES ('Factura equipo oficina_', null, 2, now());
INSERT INTO facturas_items (cantidad, factura_id ,producto_id) VALUES (1,2,1);
INSERT INTO facturas_items (cantidad, factura_id ,producto_id) VALUES (2,2,4);
INSERT INTO facturas_items (cantidad, factura_id ,producto_id) VALUES (1,2,5);
INSERT INTO facturas_items (cantidad, factura_id ,producto_id) VALUES (1,2,7);

INSERT INTO facturas (descripcion, observacion, cliente_id, fecha_registro) VALUES ('Factura equipo oficina_ramus', null, 4, now());
INSERT INTO facturas_items (cantidad, factura_id ,producto_id) VALUES (1,3,1);
INSERT INTO facturas_items (cantidad, factura_id ,producto_id) VALUES (2,3,4);
INSERT INTO facturas_items (cantidad, factura_id ,producto_id) VALUES (1,3,5);
INSERT INTO facturas_items (cantidad, factura_id ,producto_id) VALUES (1,3,7);

INSERT INTO facturas (descripcion, observacion, cliente_id, fecha_registro) VALUES ('Factura equipos maquinaria pesada ', 'todo tipo de maquinaria pesada que requiere de todo ', 2, now());
INSERT INTO facturas_items (cantidad, factura_id ,producto_id) VALUES (1,4,1);
INSERT INTO facturas_items (cantidad, factura_id ,producto_id) VALUES (2,4,4);
INSERT INTO facturas_items (cantidad, factura_id ,producto_id) VALUES (1,4,5);
INSERT INTO facturas_items (cantidad, factura_id ,producto_id) VALUES (1,4,7);








