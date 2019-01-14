-- owner rental database version 0.6 by David Martinez
create database owner_rental_db character set utf8mb4 collate utf8mb4_bin;
use owner_rental_db;


create table usuarios(
	idUsuario int not null auto_increment,
	nombre varchar(50) not null,
	apellido varchar(50) not null,
	dni varchar(15) not null,
	mail varchar(50) not null,
    fechaNac date not null,
	usuario varchar(50) not null,
    clave varchar(20) not null,
    sexo tinyint(1) not null default 1,
    admin tinyint(1) not null default 0,
	puntaje float not null, -- Es para obtener el promedio total
	habilitado tinyint(1) not null default 1,

	constraint pk_usuarios primary key(idUsuario)
);

create table tiposAlojamientos(
	idTipoAlojamiento int not null auto_increment,
	descripcion varchar(50) not null,
	habilitado tinyint(1) not null default 1,
	constraint pk_tipos_alojamientos primary key(idTipoAlojamiento)
);

create table partidos(
	idPartido int not null auto_increment,
	nombre varchar(100) not null,
	habilitado tinyint(1) not null default 1,
	constraint pk_partidos primary key(idPartido)
);

create table localidades(
	idLocalidad int not null auto_increment,
	idPartido int not null,
	nombre varchar(100) not null,
	codPostal int not null,
	habilitado tinyint(1) not null default 1,
	constraint pk_localidades primary key(idLocalidad),
	constraint fk_localidades_partidos foreign key(idPartido) references partidos(idPartido)
);

create table publicaciones(
	idPublicacion int not null auto_increment,
	idUsuario int not null, -- una publicacion por usuario... poque no usar el id_usuario como pk de publicaciones?
	idTipoAlojamiento int not null,
	nombre varchar(50) not null,
	descripcion varchar(50) not null,
	domicilio varchar(100) not null,
	idLocalidad int not null,
	codPostal int not null,
	coordenadas varchar(50) not null,
	precioNoche float not null,
	metrosCuadrados int not null,
	cantPersonas int not null,
	cantAmbientes int not null,
	cantBanios int not null,
	cantHabitaciones int not null,
	bitJardin tinyint(1) not null,
	bitCochera tinyint(1) not null,
	bitMascotas tinyint(1) not null,
	bitFumadores tinyint(1) not null,
	bitAmoblada tinyint(1) not null,
	bitDesayuno tinyint(1) not null,
	fechaAlta datetime not null default current_timestamp,
	puntaje float not null,
	habilitado tinyint(1) not null default 1,
	constraint pk_publicaciones primary key(idPublicacion),
	constraint fk_publicaciones_usuarios foreign key(idUsuario) references usuarios(idUsuario),
	constraint fk_publicaciones_tipos_alojamientos foreign key(idTipoAlojamiento) references tiposAlojamientos(idTipoAlojamiento),
	constraint fk_publicaciones_localidades foreign key(idLocalidad) references localidades(idLocalidad)
);

create table tiposEstadosSolicitudes(
	idEstadoSolicitud int not null auto_increment,
	descripcion varchar(50) not null,
	habilitado tinyint(1) not null default 1, 
	constraint pk_tipos_estados_solicitudes primary key(idEstadoSolicitud)
);

create table solicitudes(
	idSolicitud int not null auto_increment,
	idUsuario int not null,
	idPublicacion int not null,
	fechaAlta datetime not null default current_timestamp,
	fechaConfirmacion datetime null,
	fechaBaja datetime null,
	cantDias int not null,
	esDeReserva tinyint(1) not null, -- si no es reserva(huesped) es alojamiento(anfitrion)
	idEstadoSolicitud int not null,
	habilitado tinyint(1) not null default 1,

	constraint pk_solicitudes primary key(idSolicitud),
	constraint fk_solicitudes_usuarios foreign key(idUsuario) references usuarios(idUsuario),
	constraint fk_solicitudes_publicaciones foreign key(idPublicacion) references publicaciones(idPublicacion),
	constraint fk_solicitudes_tipos_estados_solicitudes foreign key(idEstadoSolicitud) references tiposEstadosSolicitudes(idEstadoSolicitud)
);

create table comprobantes(
	idComprobante int not null auto_increment,
	idSolicitud int not null,
	nombreAnfitrion varchar(50) not null,
	apellidoAnfitrion varchar(50) not null,
	nombreHuesped varchar(50) not null,
	apellidoHuesped varchar(50) not null,
	descripcionPublicacion varchar(300) not null, -- todos los campos que se pueden mostrar de publicaciones pasadas a un string(tipo_alojamiento,jardin,cochera,mascotas,fumadores,amoblada,desayuno,domicilio,precio_noche,codigo_postal,metros_cuadrados,personas,ambientes,banios,habitaciones)
	precioFinal float not null,
	habilitado tinyint(1) not null default 1,
	constraint pk_comprobantes primary key(idComprobante),
	constraint fk_comprobantes_solicitures foreign key(idSolicitud) references solicitudes(idSolicitud)
);

create table comentarios(
	idUsuario int not null,
	idPublicacion int not null,
	descripcion varchar(300) not null,
	puntaje int not null,
	habilitado tinyint(1) not null default 1,
	constraint pk_comentarios primary key(idUsuario, idPublicacion),
	constraint fk_comentarios_usuarios foreign key(idUsuario) references usuarios(idUsuario),
	constraint fk_comentarios_publicaciones foreign key(idPublicacion) references publicaciones(idPublicacion)
);

create table favoritos(
	idFavorita int not null,
	idUsuario int not null,
	idPublicacion int not null,
	habilitado tinyint(1) not null default 1,
	constraint pk_favoritos primary key(idUsuario, idPublicacion),
	constraint fk_favoritos_usuarios foreign key(idUsuario) references usuarios(idUsuario),
	constraint fk_favoritos_publicaciones foreign key(idPublicacion) references publicaciones(idPublicacion)
);

create table imagenes(
	idImagen int not null,
	idPublicacion int not null,
	ruta varchar(50) not null,
	habilitado tinyint(1) not null default 1,
	constraint pk_imagenes primary key(idImagen, idPublicacion),
	constraint fk_imagenes_publicaciones foreign key(idPublicacion) references publicaciones(idPublicacion)
);

create table telefonos(
	idTelefono int not null auto_increment,
	idPublicacion int not null,
	numero varchar(20) not null,
	habilitado tinyint(1) not null default 1,
	constraint pk_telefonos primary key(idTelefono),
	constraint fk_telefonos_publicaciones foreign key(idPublicacion) references publicaciones(idPublicacion)
);

create table bajas(
	idBaja int not null auto_increment,
	idUsuario int not null,
	idPublicacion int not null,
	fechaBaja datetime not null default current_timestamp,
	fechaSolucion datetime null,
	solucionado tinyint(1) not null default 0,
	razonBaja varchar(300) not null,
	habilitado tinyint(1) not null default 1,

	constraint pk_bajas primary key(idBaja),
	constraint fk_bajas_usuarios foreign key(idUsuario) references usuarios(idUsuario),
	constraint fk_bajas_publicaciones foreign key(idPublicacion) references publicaciones(idPublicacion)
);