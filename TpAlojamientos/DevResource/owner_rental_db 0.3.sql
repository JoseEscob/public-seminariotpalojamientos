-- owner rental database version 0.3 by David Martinez
create database owner_rental_db character set utf8mb4 collate utf8mb4_bin;
use owner_rental_db;


create table USUARIOS(
	id_usuario int not null auto_increment,
	nombre varchar(50) not null,
	apellido varchar(50) not null,
	dni varchar(15) not null,
	fechaNac date not null,
	mail varchar(50) not null,
	nroUsuario varchar(50) not null,
	puntaje float not null, -- Es para obtener el promedio total
	clave varchar(50) not null,
	habilitado tinyint(1) not null default 1,
	constraint pk_usuarios primary key(id_usuario)
);

create table tipos_alojamientos(
	id_tipo_alojamiento int not null auto_increment,
	descripcion varchar(50) not null,
	constraint pk_tipos_alojamientos primary key(id_tipo_alojamiento)
);

create table partidos(
	id_partido int not null auto_increment,
	nombre varchar(100) not null,
	habilitado tinyint(1) not null default 1,
	constraint pk_partidos primary key(id_partido)
);

create table localidades(
	id_localidad int not null auto_increment,
	id_partido int not null,
	nombre varchar(100) not null,
	habilitado tinyint(1) not null default 1,
	constraint pk_localidades primary key(id_localidad),
	constraint fk_localidades_partidos foreign key(id_partido) references partidos(id_partido)
);

create table publicaciones(
	id_publicacion int not null auto_increment,
	id_usuario int not null, -- una publicacion por usuario... poque no usar el id_usuario como pk de publicaciones?
	id_tipo_alojamiento int not null,
	nombre varchar(50) not null,
	descripcion varchar(50) not null,
	domicilio varchar(100) not null,
	id_localidad int not null,
	codigo_postal int not null,
	coordenadas varchar(50) not null,
	precio_noche float not null,
	metros_cuadrados int not null,
	cantPersonas int not null,
	cantAmbientes int not null,
	cantBanios int not null,
	cantHabitaciones int not null,
	bit_jardin tinyint(1) not null,
	bit_cochera tinyint(1) not null,
	bit_mascotas tinyint(1) not null,
	bit_fumadores tinyint(1) not null,
	bit_amoblada tinyint(1) not null,
	bit_desayuno tinyint(1) not null,
	fecha_alta datetime not null default current_timestamp,
	puntaje float not null,
	habilitado tinyint(1) not null default 1,
	constraint pk_publicaciones primary key(id_publicacion),
	constraint fk_publicaciones_usuarios foreign key(id_usuario) references usuarios(id_usuario),
	constraint fk_publicaciones_tipos_alojamientos foreign key(id_tipo_alojamiento) references tipos_alojamientos(id_tipo_alojamiento),
	constraint fk_publicaciones_localidades foreign key(id_localidad) references localidades(id_localidad)
);

create table tipos_estados_solicitudes(
	id_estadoSolicitud int not null auto_increment,
	descripcion varchar(50) not null,
	constraint pk_tipos_estados_solicitudes primary key(id_estadoSolicitud)
);

create table solicitudes(
	id_solicitud int not null auto_increment,
	id_usuario int not null,
	id_publicacion int not null,
	fecha_alta datetime not null default current_timestamp,
	fecha_confirmacion datetime null,
	fecha_baja datetime null,
	dias int not null,
	esDeReserva tinyint(1) not null, -- si no es reserva(huesped) es alojamiento(anfitrion)
	id_estadoSolicitud int not null,
	constraint pk_solicitudes primary key(id_solicitud),
	constraint fk_solicitudes_usuarios foreign key(id_usuario) references usuarios(id_usuario),
	constraint fk_solicitudes_publicaciones foreign key(id_publicacion) references publicaciones(id_publicacion),
	constraint fk_solicitudes_tipos_estados_solicitudes foreign key(id_estadoSolicitud) references tipos_estados_solicitudes(id_estadoSolicitud)
);

create table comprobantes(
	id_comprobante int not null auto_increment,
	id_solicitud int not null,
	nombre_anfitrion varchar(50) not null,
	apellido_anfitrion varchar(50) not null,
	nombre_huesped varchar(50) not null,
	apellido_huesped varchar(50) not null,
	descripcion_publicacion varchar(300) not null, -- todos los campos que se pueden mostrar de publicaciones pasadas a un string(tipo_alojamiento,jardin,cochera,mascotas,fumadores,amoblada,desayuno,domicilio,precio_noche,codigo_postal,metros_cuadrados,personas,ambientes,banios,habitaciones)
	precio_final float not null,
	constraint pk_comprobantes primary key(id_comprobante),
	constraint fk_comprobantes_solicitures foreign key(id_solicitud) references solicitudes(id_solicitud)
);

create table comentarios(
	id_usuario int not null,
	id_publicacion int not null,
	descripcion varchar(300) not null,
	puntaje int not null,
	habilitado tinyint(1) not null default 1,
	constraint pk_comentarios primary key(id_usuario, id_publicacion),
	constraint fk_comentarios_usuarios foreign key(id_usuario) references usuarios(id_usuario),
	constraint fk_comentarios_publicaciones foreign key(id_publicacion) references publicaciones(id_publicacion)
);

create table FAVORITOS(
	id_favorita int not null,
	id_usuario int not null,
	id_publicacion int not null,
	habilitado tinyint(1) not null default 1,
	constraint pk_favoritos primary key(id_usuario, id_publicacion),
	constraint fk_favoritos_usuarios foreign key(id_usuario) references usuarios(id_usuario),
	constraint fk_favoritos_publicaciones foreign key(id_publicacion) references publicaciones(id_publicacion)
);

create table imagenes(
	id_imagen int not null,
	id_publicacion int not null,
	ruta varchar(50) not null,
	constraint pk_imagenes primary key(id_imagen, id_publicacion),
	constraint fk_imagenes_publicaciones foreign key(id_publicacion) references publicaciones(id_publicacion)
);

create table telefonos(
	id_telefono int not null auto_increment,
	id_publicacion int not null,
	numero varchar(20) not null,
	habilitado tinyint(1) not null default 1,
	constraint pk_telefonos primary key(id_telefono),
	constraint fk_telefonos_publicaciones foreign key(id_publicacion) references publicaciones(id_publicacion)
);

create table bajas(
	id_baja int not null auto_increment,
	id_usuario int not null,
	id_publicacion int not null,
	fecha_baja datetime not null default current_timestamp,
	fecha_solucion datetime null,
	solucionado tinyint(1) not null default 0,
	razon_baja varchar(300) not null,
	constraint pk_bajas primary key(id_baja),
	constraint fk_bajas_usuarios foreign key(id_usuario) references usuarios(id_usuario),
	constraint fk_bajas_publicaciones foreign key(id_publicacion) references publicaciones(id_publicacion)
);