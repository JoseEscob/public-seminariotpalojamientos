package controladoresDAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import extra.Conexion;
import extra.LOG;
import modelo.Publicacion;
import modelo.Solicitud;
import modelo.TipoEstadoSolicitud;
import modelo.Usuario;

public class Solicitudes implements Connectable<Solicitud> {


	private static HashMap<String,String> queries = new HashMap<String, String>(){{
		put("all", "select * from solicitudes");
		put("insert", "insert into solicitudes values(null,?,?,?,?,?,?,?,?,default)");
		put("count", "select count(*) as cantidad from solicitudes");
		put("update","update solicitudes set idUsuario=?, idPublicacion=?, fechaAlta=?, fechaConfirmacion=?, "
				+ "fechaBaja=?, cantDias=?, esDeReserva=?, idEstadoSolicitud=?, habilitado=? where idSolicitud=?");
		put("get","select * from solicitudes where idSolicitud=?");
		put("getByidUsuario", "select * from solicitudes where idUsuario=?");
		put("limitByIdUsuario", "select * from solicitudes where idUSuario=? limit ?, ?");
		
			
	}};

	
	
	private Conexion cn;
	private ArrayList<Solicitud> m;
	
	@Override
	public ArrayList<Solicitud> getAll() {
		cn = new Conexion();
		m = new ArrayList<Solicitud>();
		
		 try
		 {
			 cn.Open();
			 ResultSet rs= cn.query(queries.get("all"));
			 while(rs.next())
			 {					
				 Solicitud o = new Solicitud();
				 o.setIdSolicitud(rs.getInt(1));
				 o.setIdUsuario(rs.getInt(2));
				 o.setIdPublicacion(rs.getInt(3));
				 o.setFechaAlta(rs.getDate(4));
				 o.setFechaConfirmacion(rs.getDate(5));
				 o.setFechaBaja(rs.getDate(6));
				 o.setCantDias(rs.getInt(7));
				 o.setEsDeReserva(rs.getBoolean(8));
				 o.setIdEstadoSolicitud(rs.getInt(9));
				 o.setHabilitado(rs.getBoolean(10));
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
	public ArrayList<Solicitud> getLike(String like) {
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
	public Solicitud get(Solicitud obj) {
		cn = new Conexion();
		Solicitud o = null;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("get"));
			ps.setInt(1, obj.getIdSolicitud());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				o = new Solicitud();
				o.setIdSolicitud(rs.getInt(1));
				o.setIdUsuario(rs.getInt(2));
				o.setIdPublicacion(rs.getInt(3));
				o.setFechaAlta(rs.getDate(4));
				o.setFechaConfirmacion(rs.getDate(5));
				o.setFechaBaja(rs.getDate(6));
				o.setCantDias(rs.getInt(7));
				o.setEsDeReserva(rs.getBoolean(8));
				o.setIdEstadoSolicitud(rs.getInt(9));
				o.setHabilitado(rs.getBoolean(10));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			cn.close();
		}
		return o;
	}

	@Override
	public boolean insert(Solicitud obj) {
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
			
			Publicaciones publicaciones = new Publicaciones();
			Publicacion publicacion = new Publicacion();
			publicacion.setIdPublicacion(obj.getIdPublicacion());
			if(publicaciones.get(publicacion) == null)
				return false;
			
			TiposEstadosSolicitudes tes = new TiposEstadosSolicitudes();
			TipoEstadoSolicitud tec = new TipoEstadoSolicitud();
			tec.setIdEstadoSolicitud(obj.getIdEstadoSolicitud());
			if(tes.get(tec) == null)
				return false;
			
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("insert"));
			ps.setInt(1, obj.getIdUsuario());
			ps.setInt(2, obj.getIdPublicacion());
			ps.setDate(3, obj.getFechaAlta());
			ps.setDate(4, obj.getFechaConfirmacion());
			ps.setDate(5, obj.getFechaBaja());
			ps.setInt(6, obj.getCantDias());
			ps.setBoolean(7, obj.isEsDeReserva());
			ps.setInt(8, obj.getIdEstadoSolicitud());
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
	public boolean update(Solicitud obj) {
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
			
			Publicaciones publicaciones = new Publicaciones();
			Publicacion publicacion = new Publicacion();
			publicacion.setIdPublicacion(obj.getIdPublicacion());
			if(publicaciones.get(publicacion) == null)
				return false;
			
			TiposEstadosSolicitudes tes = new TiposEstadosSolicitudes();
			TipoEstadoSolicitud tec = new TipoEstadoSolicitud();
			tec.setIdEstadoSolicitud(obj.getIdEstadoSolicitud());
			if(tes.get(tec) == null)
				return false;
			
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("update"));	
			ps.setInt(1, obj.getIdUsuario());
			ps.setInt(2, obj.getIdPublicacion());
			ps.setDate(3, obj.getFechaAlta());
			ps.setDate(4, obj.getFechaConfirmacion());
			ps.setDate(5, obj.getFechaBaja());
			ps.setInt(6, obj.getCantDias());
			ps.setBoolean(7, obj.isEsDeReserva());
			ps.setInt(8, obj.getIdEstadoSolicitud());
			ps.setBoolean(9, obj.isHabilitado());
			ps.setInt(10, obj.getIdSolicitud());
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
	public boolean remove(Solicitud obj) {
		Solicitud u = new Solicitud();
		u.setIdSolicitud(obj.getIdSolicitud());
		u = this.get(u);
		u.setHabilitado(false);
		return this.update(u);
	}
	
	public ArrayList<Solicitud> getByidUsuario(int idUsuario){
		cn = new Conexion();
		m = null;
		try {
			m = new ArrayList<Solicitud>();
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("getByidUsuario"));
			ps.setInt(1, idUsuario);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){					
				Solicitud o = new Solicitud();
				o.setIdSolicitud(rs.getInt(1));
				o.setIdUsuario(rs.getInt(2));
				o.setIdPublicacion(rs.getInt(3));
				o.setFechaAlta(rs.getDate(4));
				o.setFechaConfirmacion(rs.getDate(5));
				o.setFechaBaja(rs.getDate(6));
				o.setCantDias(rs.getInt(7));
				o.setEsDeReserva(rs.getBoolean(8));
				o.setIdEstadoSolicitud(rs.getInt(9));
				o.setHabilitado(rs.getBoolean(10));
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
	public ArrayList<Solicitud> getByidUsuarioReserva(int idUsuario){
		cn = new Conexion();
		m = null;
		try {
			m = new ArrayList<Solicitud>();
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("getByidUsuario"));
			ps.setInt(1, idUsuario);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){					
				Solicitud o = new Solicitud();
				o.setIdSolicitud(rs.getInt(1));
				o.setIdUsuario(rs.getInt(2));
				o.setIdPublicacion(rs.getInt(3));
				o.setFechaAlta(rs.getDate(4));
				o.setFechaConfirmacion(rs.getDate(5));
				o.setFechaBaja(rs.getDate(6));
				o.setCantDias(rs.getInt(7));
				o.setEsDeReserva(rs.getBoolean(8));
				o.setIdEstadoSolicitud(rs.getInt(9));
				o.setHabilitado(rs.getBoolean(10));
				if(o.isEsDeReserva())
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
	public ArrayList<Solicitud> getByidUsuarioAlojamiento(int idUsuario){
		cn = new Conexion();
		m  = null;
		try {
			m = new ArrayList<Solicitud>();
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("getByidUsuario"));
			ps.setInt(1, idUsuario);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){					
				Solicitud o = new Solicitud();
				o.setIdSolicitud(rs.getInt(1));
				o.setIdUsuario(rs.getInt(2));
				o.setIdPublicacion(rs.getInt(3));
				o.setFechaAlta(rs.getDate(4));
				o.setFechaConfirmacion(rs.getDate(5));
				o.setFechaBaja(rs.getDate(6));
				o.setCantDias(rs.getInt(7));
				o.setEsDeReserva(rs.getBoolean(8));
				o.setIdEstadoSolicitud(rs.getInt(9));
				o.setHabilitado(rs.getBoolean(10));
				if(!o.isEsDeReserva())
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
	
	public ArrayList<Solicitud> limitByIdUsuario(int idUsuario, int paginaActual, int cantidadRegistros){
		cn = new Conexion();
		m = null;

		int inicio = paginaActual * cantidadRegistros - cantidadRegistros;

		try {
			cn.Open();
			m = new ArrayList<Solicitud>();
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("limitByIdUSuario"));
			ps.setInt(1, idUsuario);
			ps.setInt(2, inicio);
			ps.setInt(3, cantidadRegistros);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Solicitud o = new Solicitud();
		
				
				o.setIdSolicitud(rs.getInt("idSolicitud"));
				o.setIdUsuario(rs.getInt("idUsuario"));
				o.setIdPublicacion(rs.getInt("idPublicacion"));
				o.setFechaAlta(rs.getDate("fechaAlta"));
				o.setFechaConfirmacion(rs.getDate("fechaConfirmacion"));
				o.setFechaBaja(rs.getDate("fechaBaja"));
				o.setCantDias(rs.getInt("cantDias"));
				o.setEsDeReserva(rs.getBoolean("esDeReserva"));
				o.setIdEstadoSolicitud(rs.getInt("idEstadoSolicitud"));
				o.setHabilitado(rs.getBoolean("habilitado"));
				
				LOG.info(rs.getStatement().toString());
				m.add(o);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		
		return m;
	}
	public ArrayList<Solicitud> limitByIdUsuarioReserva(int idUsuario, int paginaActual, int cantidadRegistros){
		cn = new Conexion();
		m = null;

		int inicio = paginaActual * cantidadRegistros - cantidadRegistros;

		try {
			cn.Open();
			m = new ArrayList<Solicitud>();
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("limitByIdUSuario"));
			ps.setInt(1, idUsuario);
			ps.setInt(2, inicio);
			ps.setInt(3, cantidadRegistros);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Solicitud o = new Solicitud();
		
				
				o.setIdSolicitud(rs.getInt("idSolicitud"));
				o.setIdUsuario(rs.getInt("idUsuario"));
				o.setIdPublicacion(rs.getInt("idPublicacion"));
				o.setFechaAlta(rs.getDate("fechaAlta"));
				o.setFechaConfirmacion(rs.getDate("fechaConfirmacion"));
				o.setFechaBaja(rs.getDate("fechaBaja"));
				o.setCantDias(rs.getInt("cantDias"));
				o.setEsDeReserva(rs.getBoolean("esDeReserva"));
				o.setIdEstadoSolicitud(rs.getInt("idEstadoSolicitud"));
				o.setHabilitado(rs.getBoolean("habilitado"));
				
				LOG.info(rs.getStatement().toString());
				if(o.isEsDeReserva())
					m.add(o);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		
		return m;
	}
	public ArrayList<Solicitud> limitByIdUsuarioAlojamiento(int idUsuario, int paginaActual, int cantidadRegistros){
		cn = new Conexion();
		m = null;

		int inicio = paginaActual * cantidadRegistros - cantidadRegistros;

		try {
			cn.Open();
			m = new ArrayList<Solicitud>();
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("limitByIdUSuario"));
			ps.setInt(1, idUsuario);
			ps.setInt(2, inicio);
			ps.setInt(3, cantidadRegistros);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Solicitud o = new Solicitud();
		
				
				o.setIdSolicitud(rs.getInt("idSolicitud"));
				o.setIdUsuario(rs.getInt("idUsuario"));
				o.setIdPublicacion(rs.getInt("idPublicacion"));
				o.setFechaAlta(rs.getDate("fechaAlta"));
				o.setFechaConfirmacion(rs.getDate("fechaConfirmacion"));
				o.setFechaBaja(rs.getDate("fechaBaja"));
				o.setCantDias(rs.getInt("cantDias"));
				o.setEsDeReserva(rs.getBoolean("esDeReserva"));
				o.setIdEstadoSolicitud(rs.getInt("idEstadoSolicitud"));
				o.setHabilitado(rs.getBoolean("habilitado"));
				
				LOG.info(rs.getStatement().toString());
				if(!o.isEsDeReserva())
					m.add(o);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		
		return m;
	}

}
