INSERT INTO `owner_rental_db`.`usuarios` 	SET `idUsuario`= '1' , `nombre` = 'UTN', `apellido` = 'FRGP', `dni` = '11111', `mail` = 'utn@frgp.com', `fechaNac` = STR_TO_DATE('01/01/1990','%d/%m/%Y'), `usuario` = 'utnfrgp', `clave` = 'utn123', `sexo` = '1', `rutaFotoPerfil` = 'imagenes/usuarios/Usuario_1/fotoUsuario_1.jpg', `admin` = '1', `puntaje` = '0', `habilitado` = '1', `fechaAlta` = STR_TO_DATE('01/01/2019','%d/%m/%Y') ,`fechaUltConexion` = STR_TO_DATE('01/01/1900','%d/%m/%Y'),`fechaUltModificado` = STR_TO_DATE('01/01/1900','%d/%m/%Y'), `verificado` = '0', `nroTelefono` = '111110';
INSERT INTO `owner_rental_db`.`usuarios` 	SET `idUsuario`= '2' , `nombre` = 'David', `apellido` = 'Martinez', `dni` = '22222', `mail` = 'dmartinez@gmail.com', `fechaNac` = STR_TO_DATE('21/11/1991','%d/%m/%Y'), `usuario` = 'dm123', `clave` = 'dm123', `sexo` = '1', `rutaFotoPerfil` = '', `admin` = '1', `puntaje` = '5', `habilitado` = '1', `fechaAlta` = STR_TO_DATE('01/01/2019','%d/%m/%Y') ,`fechaUltConexion` = STR_TO_DATE('01/01/1900','%d/%m/%Y'),`fechaUltModificado` = STR_TO_DATE('01/01/1900','%d/%m/%Y'), `verificado` = '1', `nroTelefono` = '222220';
INSERT INTO `owner_rental_db`.`usuarios` 	SET `idUsuario`= '3' , `nombre` = 'José', `apellido` = 'Escobar', `dni` = '33333', `mail` = 'jescobar@gmail.com', `fechaNac` = STR_TO_DATE('31/12/2000','%d/%m/%Y'), `usuario` = 'je123', `clave` = 'je123', `sexo` = '1', `rutaFotoPerfil` = 'imagenes/usuarios/Usuario_3/fotoUsuario_3.jpg', `admin` = '1', `puntaje` = '5', `habilitado` = '1', `fechaAlta` = STR_TO_DATE('01/01/2019','%d/%m/%Y') ,`fechaUltConexion` = STR_TO_DATE('01/01/1900','%d/%m/%Y'),`fechaUltModificado` = STR_TO_DATE('01/01/1900','%d/%m/%Y'), `verificado` = '1', `nroTelefono` = '333330';
INSERT INTO `owner_rental_db`.`usuarios` 	SET `idUsuario`= '4' , `nombre` = 'Gabriela', `apellido` = 'Quevedo', `dni` = '44444', `mail` = 'gquevedo@gmail.com', `fechaNac` = STR_TO_DATE('21/11/1993','%d/%m/%Y'), `usuario` = 'gq123', `clave` = 'gq123', `sexo` = '0', `rutaFotoPerfil` = 'imagenes/usuarios/Usuario_4/fotoUsuario_4.jpg', `admin` = '0', `puntaje` = '4', `habilitado` = '1', `fechaAlta` = STR_TO_DATE('01/03/2019','%d/%m/%Y') ,`fechaUltConexion` = STR_TO_DATE('01/01/1900','%d/%m/%Y'),`fechaUltModificado` = STR_TO_DATE('01/01/1900','%d/%m/%Y'), `verificado` = '1', `nroTelefono` = '444440';
INSERT INTO `owner_rental_db`.`usuarios` 	SET `idUsuario`= '5' , `nombre` = 'Santiago', `apellido` = 'López', `dni` = '55555', `mail` = 'slopez@gmail.com', `fechaNac` = STR_TO_DATE('21/11/1994','%d/%m/%Y'), `usuario` = 'sl123', `clave` = 'sl123', `sexo` = '1', `rutaFotoPerfil` = 'imagenes/usuarios/Usuario_5/fotoUsuario_5.jpg', `admin` = '0', `puntaje` = '3', `habilitado` = '1', `fechaAlta` = STR_TO_DATE('01/02/2019','%d/%m/%Y') ,`fechaUltConexion` = STR_TO_DATE('01/01/1900','%d/%m/%Y'),`fechaUltModificado` = STR_TO_DATE('01/01/1900','%d/%m/%Y'), `verificado` = '0', `nroTelefono` = '555550';
INSERT INTO `owner_rental_db`.`usuarios` 	SET `idUsuario`= '6' , `nombre` = 'Lola', `apellido` = 'B', `dni` = '66666', `mail` = 'lolaB@gmail.com', `fechaNac` = STR_TO_DATE('11/03/2013','%d/%m/%Y'), `usuario` = 'lb123', `clave` = 'lb123', `sexo` = '0', `rutaFotoPerfil` = 'imagenes/usuarios/Usuario_5/fotoUsuario_6.jpg', `admin` = '0', `puntaje` = '5', `habilitado` = '1', `fechaAlta` = STR_TO_DATE('01/02/2020','%d/%m/%Y') ,`fechaUltConexion` = STR_TO_DATE('01/01/2000','%d/%m/%Y'),`fechaUltModificado` = STR_TO_DATE('01/01/2000','%d/%m/%Y'), `verificado` = '1', `nroTelefono` = '666660';
INSERT INTO `owner_rental_db`.`usuarios` 	SET `idUsuario`= '7' , `nombre` = 'Tomi', `apellido` = 'B', `dni` = '77777', `mail` = 'tomiB@gmail.com', `fechaNac` = STR_TO_DATE('12/10/2010','%d/%m/%Y'), `usuario` = 'tb123', `clave` = 'tb123', `sexo` = '1', `rutaFotoPerfil` = 'imagenes/usuarios/Usuario_5/fotoUsuario_7.jpg', `admin` = '0', `puntaje` = '5', `habilitado` = '1', `fechaAlta` = STR_TO_DATE('01/02/2021','%d/%m/%Y') ,`fechaUltConexion` = STR_TO_DATE('01/01/2000','%d/%m/%Y'),`fechaUltModificado` = STR_TO_DATE('01/01/2000','%d/%m/%Y'), `verificado` = '1', `nroTelefono` = '777770';

INSERT INTO `owner_rental_db`.`tiposalojamientos` (`idTipoAlojamiento`, `descripcion`, `habilitado`) VALUES	('1', 'Casa', '1');
INSERT INTO `owner_rental_db`.`tiposalojamientos` (`idTipoAlojamiento`, `descripcion`, `habilitado`) VALUES	('2', 'Cabaña', '1');
INSERT INTO `owner_rental_db`.`tiposalojamientos` (`idTipoAlojamiento`, `descripcion`, `habilitado`) VALUES	('3', 'Departamento', '1');
INSERT INTO `owner_rental_db`.`tiposalojamientos` (`idTipoAlojamiento`, `descripcion`, `habilitado`) VALUES	('4', 'Loft', '1');
INSERT INTO `owner_rental_db`.`tiposalojamientos` (`idTipoAlojamiento`, `descripcion`, `habilitado`) VALUES	('5', 'Suite', '1');
INSERT INTO `owner_rental_db`.`tiposalojamientos` (`idTipoAlojamiento`, `descripcion`, `habilitado`) VALUES	('6', 'Hostel', '1');

INSERT INTO `owner_rental_db`.`publicaciones` 	SET `idPublicacion`= '1', `idUsuario` ='1', `idTipoAlojamiento` ='1', `descripcion` ='Casa - Test', `idLocalidad` ='2544', `codPostal` ='1648', `coordenadas` ='', `calle` ='Av. alguna parte', `altura` ='3000', `piso` ='0', `dpto` ='0', `supCubierta` ='700', `supDescubierta` ='50', `precioExpensas` ='0', `precioNoche` ='700', `cantPersonas` ='4', `cantAmbientes` ='4', `cantBanios` ='2', `cantHabitaciones` ='2', `aniosAntiguedad` ='5', `fechaAlta` =STR_TO_DATE('30/01/2019','%d/%m/%Y'), `puntaje` ='3.5', `habilitado` ='1', `verificado` ='0';
INSERT INTO `owner_rental_db`.`publicaciones` 	SET `idPublicacion`= '2', `idUsuario` ='1', `idTipoAlojamiento` ='1', `descripcion` ='Casa - Tigre', `idLocalidad` ='2545', `codPostal` ='0', `coordenadas` ='', `calle` ='Los pasos', `altura` ='2500', `piso` ='0', `dpto` ='0', `supCubierta` ='450', `supDescubierta` ='40', `precioExpensas` ='1500', `precioNoche` ='400', `cantPersonas` ='3', `cantAmbientes` ='4', `cantBanios` ='1', `cantHabitaciones` ='3', `aniosAntiguedad` ='2', `fechaAlta` =STR_TO_DATE('30/12/2018','%d/%m/%Y'), `puntaje` ='2.5', `habilitado` ='1', `verificado` ='0';
INSERT INTO `owner_rental_db`.`publicaciones` 	SET `idPublicacion`= '3', `idUsuario` ='1', `idTipoAlojamiento` ='4', `descripcion` ='Loft - vteLopez- Disfruta de tus vacaciones en este departamento remodelado y pensado en cada detalle para que tu estancia sea muy cómoda e inolvidable. El departamento es muy luminoso y cálido. La cocina y la sala de estar están bien equipadas.', `idLocalidad` ='2896', `codPostal` ='0', `coordenadas` ='', `calle` ='Av. Libertador', `altura` ='2000', `piso` ='0', `dpto` ='0', `supCubierta` ='500', `supDescubierta` ='50', `precioExpensas` ='2000', `precioNoche` ='750', `cantPersonas` ='4', `cantAmbientes` ='6', `cantBanios` ='2', `cantHabitaciones` ='4', `aniosAntiguedad` ='3', `fechaAlta` =STR_TO_DATE('01/01/2019','%d/%m/%Y'), `puntaje` ='4', `habilitado` ='1', `verificado` ='1';
INSERT INTO `owner_rental_db`.`publicaciones` 	SET `idPublicacion`= '4', `idUsuario` ='2', `idTipoAlojamiento` ='3', `descripcion` ='Depto - San isi', `idLocalidad` ='2278', `codPostal` ='1646', `coordenadas` ='', `calle` ='Reconquista', `altura` ='1500', `piso` ='4', `dpto` ='b', `supCubierta` ='500', `supDescubierta` ='50', `precioExpensas` ='0', `precioNoche` ='800', `cantPersonas` ='5', `cantAmbientes` ='6', `cantBanios` ='2', `cantHabitaciones` ='4', `aniosAntiguedad` ='1', `fechaAlta` =STR_TO_DATE('01/01/2019','%d/%m/%Y'), `puntaje` ='5', `habilitado` ='1', `verificado` ='1';
INSERT INTO `owner_rental_db`.`publicaciones` 	SET `idPublicacion`= '5', `idUsuario` ='2', `idTipoAlojamiento` ='2', `descripcion` ='Cabaña en sanFer', `idLocalidad` ='2278', `codPostal` ='1646', `coordenadas` ='', `calle` ='Pinos', `altura` ='1000', `piso` ='0', `dpto` ='0', `supCubierta` ='150', `supDescubierta` ='60', `precioExpensas` ='0', `precioNoche` ='450', `cantPersonas` ='4', `cantAmbientes` ='3', `cantBanios` ='1', `cantHabitaciones` ='2', `aniosAntiguedad` ='5', `fechaAlta` =STR_TO_DATE('01/01/2019','%d/%m/%Y'), `puntaje` ='4.5', `habilitado` ='1', `verificado` ='1';
INSERT INTO `owner_rental_db`.`publicaciones` 	SET `idPublicacion`= '6', `idUsuario` ='4', `idTipoAlojamiento` ='1', `descripcion` ='Casa Hostel - Bed and Breakfast(B&B)', `idLocalidad` ='2278', `codPostal` ='0', `coordenadas` ='', `calle` ='san justo', `altura` ='500', `piso` ='1', `dpto` ='c', `supCubierta` ='80', `supDescubierta` ='0', `precioExpensas` ='0', `precioNoche` ='200', `cantPersonas` ='2', `cantAmbientes` ='2', `cantBanios` ='1', `cantHabitaciones` ='1', `aniosAntiguedad` ='6', `fechaAlta` =STR_TO_DATE('01/01/2019','%d/%m/%Y'), `puntaje` ='3.5', `habilitado` ='1', `verificado` ='0';
INSERT INTO `owner_rental_db`.`publicaciones` 	SET `idPublicacion`= '7', `idUsuario` ='4', `idTipoAlojamiento` ='4', `descripcion` ='Loft en Tigre. Disfrutá su estilo moderno', `idLocalidad` ='2897', `codPostal` ='0', `coordenadas` ='', `calle` ='Uruguay', `altura` ='250', `piso` ='0', `dpto` ='0', `supCubierta` ='650', `supDescubierta` ='50', `precioExpensas` ='245', `precioNoche` ='960', `cantPersonas` ='5', `cantAmbientes` ='2', `cantBanios` ='3', `cantHabitaciones` ='4', `aniosAntiguedad` ='1', `fechaAlta` =STR_TO_DATE('01/01/2019','%d/%m/%Y'), `puntaje` ='5', `habilitado` ='1', `verificado` ='0';


-- ----------
INSERT INTO `owner_rental_db`.`tiposServicios` 	SET `idServicio`= '1', `idTipoServicio`='1', `descripcion`='Balcon';
INSERT INTO `owner_rental_db`.`tiposServicios` 	SET `idServicio`= '2', `idTipoServicio`='1', `descripcion`='Baño';
INSERT INTO `owner_rental_db`.`tiposServicios` 	SET `idServicio`= '3', `idTipoServicio`='1', `descripcion`='Baulera';
INSERT INTO `owner_rental_db`.`tiposServicios` 	SET `idServicio`= '4', `idTipoServicio`='1', `descripcion`='Cochera';
INSERT INTO `owner_rental_db`.`tiposServicios` 	SET `idServicio`= '5', `idTipoServicio`='1', `descripcion`='Cocina';
INSERT INTO `owner_rental_db`.`tiposServicios` 	SET `idServicio`= '6', `idTipoServicio`='1', `descripcion`='Comedor';
INSERT INTO `owner_rental_db`.`tiposServicios` 	SET `idServicio`= '7', `idTipoServicio`='1', `descripcion`='Escritorio';
INSERT INTO `owner_rental_db`.`tiposServicios` 	SET `idServicio`= '8', `idTipoServicio`='1', `descripcion`='Dormitorio';
INSERT INTO `owner_rental_db`.`tiposServicios` 	SET `idServicio`= '9', `idTipoServicio`='1', `descripcion`='Jardin';
INSERT INTO `owner_rental_db`.`tiposServicios` 	SET `idServicio`= '10', `idTipoServicio`='1', `descripcion`='Living comedor';
INSERT INTO `owner_rental_db`.`tiposServicios` 	SET `idServicio`= '11', `idTipoServicio`='1', `descripcion`='Suite';
INSERT INTO `owner_rental_db`.`tiposServicios` 	SET `idServicio`= '12', `idTipoServicio`='1', `descripcion`='Vestidor';
INSERT INTO `owner_rental_db`.`tiposServicios` 	SET `idServicio`= '13', `idTipoServicio`='2', `descripcion`='Gimnasio';
INSERT INTO `owner_rental_db`.`tiposServicios` 	SET `idServicio`= '14', `idTipoServicio`='2', `descripcion`='Pileta';
INSERT INTO `owner_rental_db`.`tiposServicios` 	SET `idServicio`= '15', `idTipoServicio`='2', `descripcion`='Sauna';
INSERT INTO `owner_rental_db`.`tiposServicios` 	SET `idServicio`= '16', `idTipoServicio`='2', `descripcion`='Parrilla';
INSERT INTO `owner_rental_db`.`tiposServicios` 	SET `idServicio`= '17', `idTipoServicio`='3', `descripcion`='Agua corriente';
INSERT INTO `owner_rental_db`.`tiposServicios` 	SET `idServicio`= '18', `idTipoServicio`='3', `descripcion`='Cocina';
INSERT INTO `owner_rental_db`.`tiposServicios` 	SET `idServicio`= '19', `idTipoServicio`='3', `descripcion`='Electricidad';
INSERT INTO `owner_rental_db`.`tiposServicios` 	SET `idServicio`= '20', `idTipoServicio`='3', `descripcion`='Gas natural';
INSERT INTO `owner_rental_db`.`tiposServicios` 	SET `idServicio`= '21', `idTipoServicio`='3', `descripcion`='Seguridad';
INSERT INTO `owner_rental_db`.`tiposServicios` 	SET `idServicio`= '22', `idTipoServicio`='3', `descripcion`='Telefono';
INSERT INTO `owner_rental_db`.`tiposServicios` 	SET `idServicio`= '23', `idTipoServicio`='3', `descripcion`='Mascotas';
INSERT INTO `owner_rental_db`.`tiposServicios` 	SET `idServicio`= '24', `idTipoServicio`='3', `descripcion`='Fumadores';
INSERT INTO `owner_rental_db`.`tiposServicios` 	SET `idServicio`= '25', `idTipoServicio`='3', `descripcion`='Amoblada';
INSERT INTO `owner_rental_db`.`tiposServicios` 	SET `idServicio`= '26', `idTipoServicio`='3', `descripcion`='Desayuno';


INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='1',`idServicio`= '1';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='1',`idServicio`= '2';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='1',`idServicio`= '3';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='1',`idServicio`= '4';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='1',`idServicio`= '5';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='1',`idServicio`= '14';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='1',`idServicio`= '15';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='1',`idServicio`= '16';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='1',`idServicio`= '19';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='1',`idServicio`= '20';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='1',`idServicio`= '21';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='1',`idServicio`= '22';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='1',`idServicio`= '23';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='2',`idServicio`= '19';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='2',`idServicio`= '20';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='2',`idServicio`= '21';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='2',`idServicio`= '22';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='2',`idServicio`= '24';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='2',`idServicio`= '25';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='2',`idServicio`= '26';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='3',`idServicio`= '8';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='3',`idServicio`= '9';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='3',`idServicio`= '10';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='4',`idServicio`= '14';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='4',`idServicio`= '15';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='4',`idServicio`= '18';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='4',`idServicio`= '22';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='4',`idServicio`= '23';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='4',`idServicio`= '24';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='4',`idServicio`= '25';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='4',`idServicio`= '3';
INSERT INTO `owner_rental_db`.`serviciospublicaciones` 	SET `idPublicacion`='4',`idServicio`= '2';
/* +++++++++++ */

-- INSERT INTO `owner_rental_db`.`imagenes` (`idImagen`, `idPublicacion`, `rutaImgPublicacion`, `habilitado`) VALUES	('1', '1', 'imágenes/publicaciones/Publicacion_1', '1');


/* ********************** */

INSERT INTO `owner_rental_db`.`comentarios` (`idUsuario`, `idPublicacion`, `descripcion`, `fechaComentario`, `puntaje`, `habilitado`) VALUES	('4', '1', 'comentario - test 1', STR_TO_DATE('30/11/2018','%d/%m/%Y'), '5', '1');
INSERT INTO `owner_rental_db`.`comentarios` (`idUsuario`, `idPublicacion`, `descripcion`, `fechaComentario`, `puntaje`, `habilitado`) VALUES	('2', '1', 'Una casa buena, fácil para llegar y con todo lo necesario', STR_TO_DATE('30/11/2018','%d/%m/%Y'), '5', '1');
INSERT INTO `owner_rental_db`.`comentarios` (`idUsuario`, `idPublicacion`, `descripcion`, `fechaComentario`, `puntaje`, `habilitado`) VALUES	('3', '2', 'comentario - test 3', STR_TO_DATE('30/12/2018','%d/%m/%Y'), '3', '1');
INSERT INTO `owner_rental_db`.`comentarios` (`idUsuario`, `idPublicacion`, `descripcion`, `fechaComentario`, `puntaje`, `habilitado`) VALUES	('4', '2', 'Lugar Buenísimo, cerca de todo, muy privado, Cómodo, Los Anfitriones siempre atentos a los posibles requerimientos. Hay Cocina, refrigerador, y baño privado. Excelente!!! Volveré!!!', STR_TO_DATE('13/01/2019','%d/%m/%Y'), '5', '1');
INSERT INTO `owner_rental_db`.`comentarios` (`idUsuario`, `idPublicacion`, `descripcion`, `fechaComentario`, `puntaje`, `habilitado`) VALUES	('5', '2', 'Hermosa casa y personas, muy amables.. Gracias x todo!', STR_TO_DATE('08/01/2019','%d/%m/%Y'), '4', '1');
INSERT INTO `owner_rental_db`.`comentarios` (`idUsuario`, `idPublicacion`, `descripcion`, `fechaComentario`, `puntaje`, `habilitado`) VALUES	('1', '4', 'Excelente ubicación, comodidad, privacidad.', STR_TO_DATE('04/01/2019','%d/%m/%Y'), '4', '1');
INSERT INTO `owner_rental_db`.`comentarios` (`idUsuario`, `idPublicacion`, `descripcion`, `fechaComentario`, `puntaje`, `habilitado`) VALUES	('2', '4', 'El apartamento esta muy bien ubicado funcionado todo muy limpio y tranquillo de noche para nada ruidoso', STR_TO_DATE('04/01/2019','%d/%m/%Y'), '4', '1');
INSERT INTO `owner_rental_db`.`comentarios` (`idUsuario`, `idPublicacion`, `descripcion`, `fechaComentario`, `puntaje`, `habilitado`) VALUES	('3', '4', 'Hermoso lugar! Impecable en todo los detalles. La ubicación es ideal. Se nota la dedicación y generosidad', STR_TO_DATE('04/01/2019','%d/%m/%Y'), '5', '1');
INSERT INTO `owner_rental_db`.`comentarios` (`idUsuario`, `idPublicacion`, `descripcion`, `fechaComentario`, `puntaje`, `habilitado`) VALUES	('4', '4', 'Un espacio acogedor, limpio, práctico con todo lo necesario para una excelente estadía. el depto es exactamente igual qie en las fotos, incluso mas lindo. ', STR_TO_DATE('02/01/2019','%d/%m/%Y'), '5', '1');
INSERT INTO `owner_rental_db`.`comentarios` (`idUsuario`, `idPublicacion`, `descripcion`, `fechaComentario`, `puntaje`, `habilitado`) VALUES	('5', '4', 'Todo perfecto. Comunicación rápida y departamento impecable', STR_TO_DATE('04/01/2019','%d/%m/%Y'), '5', '1');
INSERT INTO `owner_rental_db`.`comentarios` (`idUsuario`, `idPublicacion`, `descripcion`, `fechaComentario`, `puntaje`, `habilitado`) VALUES	('1', '5', 'La atención es muy buena, el lugar limpio y mas que agradable, un lugar ideal para descansar y desenchufarse !', STR_TO_DATE('04/01/2019','%d/%m/%Y'), '5', '1');
INSERT INTO `owner_rental_db`.`comentarios` (`idUsuario`, `idPublicacion`, `descripcion`, `fechaComentario`, `puntaje`, `habilitado`) VALUES	('4', '5', 'Mejor imposible, lo recomiendo 100%..quede fascinada... espero volver ', STR_TO_DATE('12/01/2019','%d/%m/%Y'), '5', '1');
INSERT INTO `owner_rental_db`.`comentarios` (`idUsuario`, `idPublicacion`, `descripcion`, `fechaComentario`, `puntaje`, `habilitado`) VALUES	('5', '5', 'Ideal para desconectar, las cabañas tienen intimidad a pesar de estar en el mismo predio, y la pileta y la posibilidad de salir en kayaks a pasear libremente por los arroyos suma muchísimo a la experiencia. Recomiendo llevar lo que precisen para cocina', STR_TO_DATE('03/01/2019','%d/%m/%Y'), '5', '1');


INSERT INTO `owner_rental_db`.`favoritos` (`idFavorita`, `idUsuario`, `idPublicacion`, `habilitado`) VALUES	('1', '3', '4', '1');
INSERT INTO `owner_rental_db`.`favoritos` (`idFavorita`, `idUsuario`, `idPublicacion`, `habilitado`) VALUES	('2', '3', '5', '1');
INSERT INTO `owner_rental_db`.`favoritos` (`idFavorita`, `idUsuario`, `idPublicacion`, `habilitado`) VALUES	('3', '3', '6', '1');
INSERT INTO `owner_rental_db`.`favoritos` (`idFavorita`, `idUsuario`, `idPublicacion`, `habilitado`) VALUES	('4', '3', '7', '1');
INSERT INTO `owner_rental_db`.`favoritos` (`idFavorita`, `idUsuario`, `idPublicacion`, `habilitado`) VALUES	('5', '2', '4', '1');



/* ********************** */

INSERT INTO `owner_rental_db`.`tiposEstadosSolicitudes` 	SET `idEstadoSolicitud`= '1', `descripcion`='Iniciada - Pendiente confirmación', `habilitado` = '1';
INSERT INTO `owner_rental_db`.`tiposEstadosSolicitudes` 	SET `idEstadoSolicitud`= '2', `descripcion`='Rechazada', `habilitado` = '1';
INSERT INTO `owner_rental_db`.`tiposEstadosSolicitudes` 	SET `idEstadoSolicitud`= '3', `descripcion`='Aceptada/ Confirmada', `habilitado` = '1';
INSERT INTO `owner_rental_db`.`tiposEstadosSolicitudes` 	SET `idEstadoSolicitud`= '4', `descripcion`='Vencida', `habilitado` = '1';



INSERT INTO `owner_rental_db`.`solicitudesDeReservas` 	SET `idSolicitud`= '1', `idUsuarioHuesped` ='3', `idPublicacion` ='3', `fechaReservaInicio` =STR_TO_DATE('04/01/2019','%d/%m/%Y'), `fechaReservaFin` =STR_TO_DATE('30/01/2019','%d/%m/%Y'), `cantPersonas` ='2', `precioFinal` ='1000', `fechaAltaSolicitud` =STR_TO_DATE('01/01/2019','%d/%m/%Y'), `idUsuarioPropietario` ='1', `fechaDecisionPropietario` =STR_TO_DATE('05/01/2019','%d/%m/%Y'), `motivoDecisionPropietario` ='Aceptada', `idEstadoSolicitud` ='3', `habilitado` = '1';
INSERT INTO `owner_rental_db`.`solicitudesDeReservas` 	SET `idSolicitud`= '2', `idUsuarioHuesped` ='2', `idPublicacion` ='1', `fechaReservaInicio` =STR_TO_DATE('24/01/2019','%d/%m/%Y'), `fechaReservaFin` =STR_TO_DATE('28/01/2019','%d/%m/%Y'), `cantPersonas` ='2', `precioFinal` ='1000', `fechaAltaSolicitud` =STR_TO_DATE('15/01/2019','%d/%m/%Y'), `idUsuarioPropietario` ='1', `fechaDecisionPropietario` =STR_TO_DATE('17/01/2019','%d/%m/%Y'), `motivoDecisionPropietario` ='Aceptada', `idEstadoSolicitud` ='3', `habilitado` = '1';

INSERT INTO `owner_rental_db`.`solicitudesDeReservas` 	SET `idSolicitud`= '3', `idUsuarioHuesped` ='4', `idPublicacion` ='1', `fechaReservaInicio` =STR_TO_DATE('24/01/2019','%d/%m/%Y'), `fechaReservaFin` =STR_TO_DATE('29/01/2019','%d/%m/%Y'), `cantPersonas` ='2', `precioFinal` ='1000', `fechaAltaSolicitud` =STR_TO_DATE('15/01/2019','%d/%m/%Y'), `idUsuarioPropietario` ='1', `fechaDecisionPropietario` =STR_TO_DATE('','%d/%m/%Y'), `motivoDecisionPropietario` ='', `idEstadoSolicitud` ='1', `habilitado` = '1';
INSERT INTO `owner_rental_db`.`solicitudesDeReservas` 	SET `idSolicitud`= '4', `idUsuarioHuesped` ='5', `idPublicacion` ='1', `fechaReservaInicio` =STR_TO_DATE('25/01/2019','%d/%m/%Y'), `fechaReservaFin` =STR_TO_DATE('30/01/2019','%d/%m/%Y'), `cantPersonas` ='2', `precioFinal` ='1000', `fechaAltaSolicitud` =STR_TO_DATE('15/01/2019','%d/%m/%Y'), `idUsuarioPropietario` ='1', `fechaDecisionPropietario` =STR_TO_DATE('','%d/%m/%Y'), `motivoDecisionPropietario` ='', `idEstadoSolicitud` ='1', `habilitado` = '1';
INSERT INTO `owner_rental_db`.`solicitudesDeReservas` 	SET `idSolicitud`= '5', `idUsuarioHuesped` ='3', `idPublicacion` ='1', `fechaReservaInicio` =STR_TO_DATE('20/01/2019','%d/%m/%Y'), `fechaReservaFin` =STR_TO_DATE('23/01/2019','%d/%m/%Y'), `cantPersonas` ='3', `precioFinal` ='1000', `fechaAltaSolicitud` =STR_TO_DATE('15/01/2019','%d/%m/%Y'), `idUsuarioPropietario` ='1', `fechaDecisionPropietario` =STR_TO_DATE('','%d/%m/%Y'), `motivoDecisionPropietario` ='', `idEstadoSolicitud` ='1', `habilitado` = '1';
INSERT INTO `owner_rental_db`.`solicitudesDeReservas` 	SET `idSolicitud`= '6', `idUsuarioHuesped` ='3', `idPublicacion` ='2', `fechaReservaInicio` =STR_TO_DATE('04/01/2019','%d/%m/%Y'), `fechaReservaFin` =STR_TO_DATE('04/02/2019','%d/%m/%Y'), `cantPersonas` ='4', `precioFinal` ='1000', `fechaAltaSolicitud` =STR_TO_DATE('16/01/2019','%d/%m/%Y'), `idUsuarioPropietario` ='1', `fechaDecisionPropietario` =STR_TO_DATE('','%d/%m/%Y'), `motivoDecisionPropietario` ='', `idEstadoSolicitud` ='1', `habilitado` = '1';
INSERT INTO `owner_rental_db`.`solicitudesDeReservas` 	SET `idSolicitud`= '7', `idUsuarioHuesped` ='6', `idPublicacion` ='2', `fechaReservaInicio` =STR_TO_DATE('24/01/2019','%d/%m/%Y'), `fechaReservaFin` =STR_TO_DATE('27/01/2019','%d/%m/%Y'), `cantPersonas` ='3', `precioFinal` ='1000', `fechaAltaSolicitud` =STR_TO_DATE('17/01/2019','%d/%m/%Y'), `idUsuarioPropietario` ='1', `fechaDecisionPropietario` =STR_TO_DATE('','%d/%m/%Y'), `motivoDecisionPropietario` ='', `idEstadoSolicitud` ='1', `habilitado` = '1';
INSERT INTO `owner_rental_db`.`solicitudesDeReservas` 	SET `idSolicitud`= '8', `idUsuarioHuesped` ='6', `idPublicacion` ='3', `fechaReservaInicio` =STR_TO_DATE('24/01/2019','%d/%m/%Y'), `fechaReservaFin` =STR_TO_DATE('27/01/2019','%d/%m/%Y'), `cantPersonas` ='2', `precioFinal` ='1000', `fechaAltaSolicitud` =STR_TO_DATE('18/01/2019','%d/%m/%Y'), `idUsuarioPropietario` ='1', `fechaDecisionPropietario` =STR_TO_DATE('','%d/%m/%Y'), `motivoDecisionPropietario` ='', `idEstadoSolicitud` ='1', `habilitado` = '1';
INSERT INTO `owner_rental_db`.`solicitudesDeReservas` 	SET `idSolicitud`= '9', `idUsuarioHuesped` ='6', `idPublicacion` ='4', `fechaReservaInicio` =STR_TO_DATE('02/02/2019','%d/%m/%Y'), `fechaReservaFin` =STR_TO_DATE('27/02/2019','%d/%m/%Y'), `cantPersonas` ='8', `precioFinal` ='1000', `fechaAltaSolicitud` =STR_TO_DATE('19/01/2019','%d/%m/%Y'), `idUsuarioPropietario` ='2', `fechaDecisionPropietario` =STR_TO_DATE('','%d/%m/%Y'), `motivoDecisionPropietario` ='', `idEstadoSolicitud` ='1', `habilitado` = '1';
INSERT INTO `owner_rental_db`.`solicitudesDeReservas` 	SET `idSolicitud`= '10', `idUsuarioHuesped` ='7', `idPublicacion` ='4', `fechaReservaInicio` =STR_TO_DATE('02/02/2019','%d/%m/%Y'), `fechaReservaFin` =STR_TO_DATE('27/02/2019','%d/%m/%Y'), `cantPersonas` ='6', `precioFinal` ='1000', `fechaAltaSolicitud` =STR_TO_DATE('19/01/2019','%d/%m/%Y'), `idUsuarioPropietario` ='2', `fechaDecisionPropietario` =STR_TO_DATE('','%d/%m/%Y'), `motivoDecisionPropietario` ='', `idEstadoSolicitud` ='1', `habilitado` = '1';



INSERT INTO `owner_rental_db`.`comprobantes`	SET `idComprobante`= '1', `idSolicitud` ='1', `idUsuarioHuesped` ='3', `idPublicacion` ='3', `fechaReservaInicio` =STR_TO_DATE('04/01/2019','%d/%m/%Y'), `fechaReservaFin` =STR_TO_DATE('30/01/2019','%d/%m/%Y'), `cantPersonas` ='2', `precioFinal` ='1000', `fechaAlta` =STR_TO_DATE('05/01/2019','%d/%m/%Y'), `idUsuarioPropietario` ='1', `habilitado` = '1';
INSERT INTO `owner_rental_db`.`comprobantes`	SET `idComprobante`= '2', `idSolicitud` ='2', `idUsuarioHuesped` ='2', `idPublicacion` ='1', `fechaReservaInicio` =STR_TO_DATE('24/01/2019','%d/%m/%Y'), `fechaReservaFin` =STR_TO_DATE('29/01/2019','%d/%m/%Y'), `cantPersonas` ='2', `precioFinal` ='1000', `fechaAlta` =STR_TO_DATE('17/01/2019','%d/%m/%Y'), `idUsuarioPropietario` ='1', `habilitado` = '1';
