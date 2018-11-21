package controladoresDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import extra.Conexion;
import modelo.Localidad;
import modelo.Publicacion;
import modelo.TipoAlojamiento;
import modelo.Usuario;


public class Publicaciones implements Connectable<Publicacion> {
	

	
	private static HashMap<String,String> queries = new HashMap<String, String>(){{
		put("all", "select * from publicaciones");
		put("insert", "insert into publicaciones values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,default)");
		put("count", "select count(*) as cantidad from publicaciones");
		put("update","update publicaciones set idUsuario=?, idTipoAlojamiento=?, nombre=?, descripcion=?, "
				+ "domicilio=?, idLocalidad=?, codPostal=?, coordenadas=?, precioNoche=?, "
				+ "metrosCuadrados=?, cantPersonas=?, cantAmbientes=?, cantBanios=?, cantHabitaciones=?,"
				+ " bitJardin=?, bitCochera=?, bitMascotas=?, bitFumadores=?, bitAmoblada=?, bitDesayuno=?,"
				+ "fechaAlta=?, puntaje=?, habilitado=? where idPublicacion=?");
		put("get","select * from publicaciones where idPublicacion=?");
		put("like", "");
			
	}};
	
	private Conexion cn;
	private ArrayList<Publicacion> m;
	
	@Override
	public ArrayList<Publicacion> getAll() {
		cn = new Conexion();
		m = new ArrayList<Publicacion>();
		
		 try
		 {
			 cn.Open();
			 ResultSet rs= cn.query(queries.get("all"));
			 while(rs.next())
			 {					
				 Publicacion o = new Publicacion();
				 o.setIdPublicacion(rs.getInt(1));
				 o.setIdUsuario(rs.getInt(2));
				 o.setIdTipoAlojamiento(rs.getInt(3));
				 o.setNombre(rs.getString(4));
				 o.setDescripcion(rs.getString(5));
				 o.setDomicilio(rs.getString(6));
				 o.setIdLocalidad(rs.getInt(7));
				 o.setCodPostal(rs.getInt(8));
				 o.setCoordenadas(rs.getString(9));
				 o.setPrecioNoche(rs.getFloat(10));
				 o.setMetrosCuadrados(rs.getInt(11));
				 o.setCantPersonas(rs.getInt(12));
				 o.setCantAmbientes(rs.getInt(13));
				 o.setCantBanios(rs.getInt(14));
				 o.setCantHabitaciones(rs.getInt(15));
				 o.setBitJardin(rs.getBoolean(16));
				 o.setBitCochera(rs.getBoolean(17));
				 o.setBitMascotas(rs.getBoolean(18));
				 o.setBitFumadores(rs.getBoolean(19));
				 
				 o.setBitAmoblada(rs.getBoolean(20));
				 o.setBitDesayuno(rs.getBoolean(21));
				 o.setFechaAlta(rs.getDate(22));
				 o.setPuntaje(rs.getFloat(23));
				 o.setHabilitado(rs.getBoolean(24));				 
				 m.add(o);
			 }
			 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 cn.close();
		 }
		 return m;
	}

	@Override
	public ArrayList<Publicacion> getLike(String like) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount() {
		cn = new Conexion();
		int cantidad = 0;
		try {
			cn.Open();
			ResultSet rs = cn.query(queries.get("count"));
			
			if(rs.next()) {
				cantidad = rs.getInt("cantidad");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			cn.close();
		}
		return cantidad;
	}

	@Override
	public Publicacion get(Publicacion obj) {
		cn = new Conexion();
		Publicacion o = null;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("get"));
			ps.setInt(1, obj.getIdTipoAlojamiento());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				o = new Publicacion();
				o.setIdPublicacion(rs.getInt(1));
				o.setIdUsuario(rs.getInt(2));
				o.setIdTipoAlojamiento(rs.getInt(3));
				o.setNombre(rs.getString(4));
				o.setDescripcion(rs.getString(5));
				o.setDomicilio(rs.getString(6));
				o.setIdLocalidad(rs.getInt(7));
				o.setCodPostal(rs.getInt(8));
				o.setCoordenadas(rs.getString(9));
				o.setPrecioNoche(rs.getFloat(10));
				o.setMetrosCuadrados(rs.getInt(11));
				o.setCantPersonas(rs.getInt(12));
				o.setCantAmbientes(rs.getInt(13));
				o.setCantBanios(rs.getInt(14));
				o.setCantHabitaciones(rs.getInt(15));
				o.setBitJardin(rs.getBoolean(16));
				o.setBitCochera(rs.getBoolean(17));
				o.setBitMascotas(rs.getBoolean(18));
				o.setBitFumadores(rs.getBoolean(19));
				o.setBitAmoblada(rs.getBoolean(20));
				o.setBitDesayuno(rs.getBoolean(21));
				o.setFechaAlta(rs.getDate(22));
				o.setPuntaje(rs.getFloat(23));
				o.setHabilitado(rs.getBoolean(24));				 
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			cn.close();
		}
		return o;
	}

	@Override
	public boolean insert(Publicacion obj) {
		if(obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;;
		try {
			
			
			Usuarios usuarios = new Usuarios();
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(obj.getIdUsuario());
			if(usuarios.get(usuario) == null)
				return false;
			
			TiposAlojamientos alojamientos = new TiposAlojamientos();
			TipoAlojamiento alojamiento = new TipoAlojamiento();
			alojamiento.setIdTipoAlojamiento(obj.getIdTipoAlojamiento());
			if(alojamientos.get(alojamiento) == null)
				return false;
			
			Localidades localidades = new Localidades();
			Localidad localidad = new Localidad();
			localidad.setIdLocalidad(obj.getIdLocalidad());
			if(localidades.get(localidad) == null)
				return false;
			
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("insert"));
			ps.setInt(1, obj.getIdUsuario());
			ps.setInt(2, obj.getIdTipoAlojamiento());
			ps.setString(3, obj.getNombre());
			ps.setString(4, obj.getDescripcion());
			ps.setString(5, obj.getDomicilio());
			ps.setInt(6, obj.getIdLocalidad());
			ps.setInt(7, obj.getCodPostal());
			ps.setString(8, obj.getCoordenadas());
			ps.setFloat(9, obj.getPrecioNoche());
			ps.setInt(10, obj.getMetrosCuadrados());
			ps.setInt(11, obj.getCantPersonas());
			ps.setInt(12, obj.getCantAmbientes());
			ps.setInt(13, obj.getCantBanios());
			ps.setInt(14, obj.getCantHabitaciones());
			ps.setBoolean(15, obj.isBitJardin());
			ps.setBoolean(16, obj.isBitCochera());
			ps.setBoolean(17, obj.isBitMascotas());
			ps.setBoolean(18, obj.isBitFumadores());
			ps.setBoolean(19, obj.isBitAmoblada());
			ps.setBoolean(20, obj.isBitDesayuno());
			ps.setDate(21, obj.getFechaAlta());
			ps.setFloat(22, obj.getPuntaje());
			ps.executeUpdate();
			correcto = true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			cn.close();
		}
		return correcto;
	}

	@Override
	public boolean update(Publicacion obj) {
		if(obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;
		try {
			
			
			Usuarios usuarios = new Usuarios();
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(obj.getIdUsuario());
			if(usuarios.get(usuario) == null)
				return false;
			
			TiposAlojamientos alojamientos = new TiposAlojamientos();
			TipoAlojamiento alojamiento = new TipoAlojamiento();
			alojamiento.setIdTipoAlojamiento(obj.getIdTipoAlojamiento());
			if(alojamientos.get(alojamiento) == null)
				return false;
			
			Localidades localidades = new Localidades();
			Localidad localidad = new Localidad();
			localidad.setIdLocalidad(obj.getIdLocalidad());
			if(localidades.get(localidad) == null)
				return false;
			
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("update"));
			ps.setInt(1, obj.getIdUsuario());
			ps.setInt(2, obj.getIdTipoAlojamiento());
			ps.setString(3, obj.getNombre());
			ps.setString(4, obj.getDescripcion());
			ps.setString(5, obj.getDomicilio());
			ps.setInt(6, obj.getIdLocalidad());
			ps.setInt(7, obj.getCodPostal());
			ps.setString(8, obj.getCoordenadas());
			ps.setFloat(9, obj.getPrecioNoche());
			ps.setInt(10, obj.getMetrosCuadrados());
			ps.setInt(11, obj.getCantPersonas());
			ps.setInt(12, obj.getCantAmbientes());
			ps.setInt(13, obj.getCantBanios());
			ps.setInt(14, obj.getCantHabitaciones());
			ps.setBoolean(15, obj.isBitJardin());
			ps.setBoolean(16, obj.isBitCochera());
			ps.setBoolean(17, obj.isBitMascotas());
			ps.setBoolean(18, obj.isBitFumadores());
			ps.setBoolean(19, obj.isBitAmoblada());
			ps.setBoolean(20, obj.isBitDesayuno());
			ps.setDate(21, obj.getFechaAlta());
			ps.setFloat(22, obj.getPuntaje());
			if(ps.executeUpdate() != 0)
				correcto = true;
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			cn.close();
		}
		return correcto;
	}

	@Override
	public boolean remove(Publicacion obj) {
		Publicacion u = new Publicacion();
		u.setIdPublicacion(obj.getIdPublicacion());
		u = this.get(u);
		u.setHabilitado(false);
		return this.update(u);
	}

}
