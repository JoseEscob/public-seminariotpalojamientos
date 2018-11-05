-- owner rental database version 0.3 by David Martinez
create database owner_rental_db character set utf8mb4 collate utf8mb4_bin;
use owner_rental_db;


create table USUARIOS(
	id int not null auto_increment,
	nombre varchar(50) not null,
	apellido varchar(50) not null,
	dni varchar(15) not null,
	fechaNac date not null,
	mail varchar(50) not null,
	nroUsuario varchar(50) not null,
	puntaje float not null, -- TODO @David128.96 . Entiendo que es para obtener el promedio total
	clave varchar(50) not null,
	constraint pk_usuarios primary key(id)
);

create table tipos_alojamientos(
	id int not null auto_increment,
	descripcion varchar(50) not null,
	constraint pk_tipos_alojamientos primary key(id)
);

create table partidos(
	id int not null auto_increment,
	nombre varchar(100) not null,
	habilitado tinyint(1) not null default 1,
	constraint pk_partidos primary key(id)
);

create table localidades(
	id int not null auto_increment,
	id_partido int not null,
	nombre varchar(100) not null,
	habilitado tinyint(1) not null default 1,
	constraint pk_localidades primary key(id),
	constraint fk_localidades_partidos foreign key(id_partido) references partidos(id)
);

create table publicaciones(
	id_usuario int not null, -- una publicacion por usuario... poque no usar el id_usuario como pk de publicaciones?
	nombre varchar(50) not null,
	tipo_alojamiento int not null,
	jardin tinyint(1) not null,
	cochera tinyint(1) not null,
	mascotas tinyint(1) not null,
	fumadores tinyint(1) not null,
	amoblada tinyint(1) not null,
	desayuno tinyint(1) not null,
	coordenadas varchar(50) not null,
	domicilio varchar(100) not null,
	localidad int not null,
	precio_noche float not null,
	codigo_postal int not null,
	metros_cuadrados int not null,
	personas int not null,
	ambientes int not null,
	banios int not null,
	habitaciones int not null,
	fecha_alta datetime not null default current_timestamp,
	puntaje float not null,
	habilitado tinyint(1) not null default 1,
	constraint pk_publicaciones primary key(id_usuario),
	constraint fk_publicaciones_usuarios foreign key(id_usuario) references usuarios(id),
	constraint fk_publicaciones_tipos_alojamientos foreign key(tipo_alojamiento) references tipos_alojamientos(id),
	constraint fk_publicaciones_localidades foreign key(localidad) references localidades(id)
);

create table tipos_estados_solicitudes(
	id int not null auto_increment,
	descripcion varchar(50) not null,
	constraint pk_tipos_estados_solicitudes primary key(id)
);

create table solicitudes(
	id int not null auto_increment,
	id_usuario int not null,
	id_publicacion int not null,
	fecha_alta datetime not null default current_timestamp,
	fecha_confirmacion datetime null,
	fecha_baja datetime null,
	dias int not null,
	esDeReserva tinyint(1) not null, -- si no es reserva(huesped) es alojamiento(anfitrion)
	id_tipo_estado int not null,
	constraint pk_solicitudes primary key(id),
	constraint fk_solicitudes_usuarios foreign key(id_usuario) references usuarios(id),
	constraint fk_solicitudes_publicaciones foreign key(id_publicacion) references publicaciones(id_usuario),
	constraint fk_solicitudes_tipos_estados_solicitudes foreign key(tipo_estado) references tipos_estados_solicitudes(id)
);

create table comprobantes(
	id int not null auto_increment,
	id_solicitud int not null,
	nombre_anfitrion varchar(50) not null,
	apellido_anfitrion varchar(50) not null,
	nombre_huesped varchar(50) not null,
	apellido_huesped varchar(50) not null,
	descripcion_publicacion varchar(300) not null, -- todos los campos que se pueden mostrar de publicaciones pasadas a un string(tipo_alojamiento,jardin,cochera,mascotas,fumadores,amoblada,desayuno,domicilio,precio_noche,codigo_postal,metros_cuadrados,personas,ambientes,banios,habitaciones)
	precio_final float not null,
	constraint pk_comprobantes primary key(id),
	constraint fk_comprobantes_solicitures foreign key(id_solicitud) references solicitudes(id)
);

create table comentarios(
	id_usuario int not null,
	id_publicacion int not null,
	descripcion varchar(300) not null,
	puntaje int not null,
	habilitado tinyint(1) not null default 1,
	constraint pk_comentarios primary key(id_usuario, id_publicacion),
	constraint fk_comentarios_usuarios foreign key(id_usuario) references usuarios(id),
	constraint fk_comentarios_publicaciones foreign key(id_publicacion) references publicaciones(id_usuario)
);

create table FAVORITOS(
	id_favorita int not null,
	id_usuario int not null,
	id_publicacion int not null,
	habilitado tinyint(1) not null default 1,
	constraint pk_favoritos primary key(id_usuario, id_publicacion),
	constraint fk_favoritos_usuarios foreign key(id_usuario) references usuarios(id),
	constraint fk_favoritos_publicaciones foreign key(id_publicacion) references publicaciones(id_usuario)
);

create table imagenes(
	id int not null,
	id_publicacion int not null,
	ruta varchar(50) not null,
	constraint pk_imagenes primary key(id, id_publicacion),
	constraint fk_imagenes_publicaciones foreign key(id_publicacion) references publicaciones(id_usuario)
);

create table telefonos(-- TODO @jescob09 Me parece q se debería obtener el teléfono del anfitrión 
	id int not null auto_increment,
	id_publicacion int not null,
	numero varchar(20) not null,
	habilitado tinyint(1) not null default 1,
	constraint pk_telefonos primary key(id),
	constraint fk_telefonos_publicaciones foreign key(id_publicacion) references publicaciones(id_usuario)
);

create table bajas(
	id int not null auto_increment,
	id_usuario int not null,
	id_publicacion int not null,
	fecha_baja datetime not null default current_timestamp,
	fecha_solucion datetime null,
	solucionado tinyint(1) not null default 0,
	razon_baja varchar(300) not null,
	constraint pk_bajas primary key(id),
	constraint fk_bajas_usuarios foreign key(id_usuario) references usuarios(id),
	constraint fk_bajas_publicaciones foreign key(id_publicacion) references publicaciones(id_usuario)
);