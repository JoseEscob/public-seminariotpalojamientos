package controladoresDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import extra.Conexion;
import modelo.Comprobante;
import modelo.Solicitud;

public class Comprobantes implements Connectable<Comprobante> {


	private static HashMap<String,String> queries = new HashMap<String, String>(){/**
		 * 
		 */
		private static final long serialVersionUID = 8651650972239809763L;

	{
		put("all", "select * from comprobantes");
		put("insert", "insert into comprobantes values(null,?,?,?,?,?,?,?,default)");
		put("count", "select count(*) as cantidad from comprobantes");
		put("update","update comprobantes set idSolicitud=?, nombreAnfitrion=?, apellidoAnfitrion=?, nombreHuesped=?, "
				+ "apellidoHuesped=?, descripcionPublicacion=?, precioFinal=?, habilitado=? where idComprobante=?");
		put("get","select * from comprobantes where idComprobante=?");
		put("like", "");
			
	}};


	
	private Conexion cn;
	private ArrayList<Comprobante> m;
	
	@Override
	public ArrayList<Comprobante> getAll() {
		cn = new Conexion();
		m = new ArrayList<Comprobante>();
		
		 try
		 {
			 cn.Open();
			 ResultSet rs= cn.query(queries.get("all"));
			 while(rs.next())
			 {					
				 Comprobante o = new Comprobante();
				 o.setIdComprobante(rs.getInt(1));
				 o.setIdSolicitud(rs.getInt(2));
				 o.setNombreAnfitrion(rs.getString(3));
				 o.setApellidoAnfitrion(rs.getString(4));
				 o.setNombreHuesped(rs.getString(5));
				 o.setApellidoHuesped(rs.getString(6));
				 o.setDescripcionPublicacion(rs.getString(7));
				 o.setPrecioFinal(rs.getInt(8));
				 o.setHabilitado(rs.getBoolean(9));
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
	public ArrayList<Comprobante> getLike(String like) {
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
	public Comprobante get(Comprobante obj) {
		cn = new Conexion();
		Comprobante o = null;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("get"));
			ps.setInt(1, obj.getIdSolicitud());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				o = new Comprobante();
				o.setIdComprobante(rs.getInt(1));
				o.setIdSolicitud(rs.getInt(2));
				o.setNombreAnfitrion(rs.getString(3));
				o.setApellidoAnfitrion(rs.getString(4));
				o.setNombreHuesped(rs.getString(5));
				o.setApellidoHuesped(rs.getString(6));
				o.setDescripcionPublicacion(rs.getString(7));
				o.setPrecioFinal(rs.getInt(8));
				o.setHabilitado(rs.getBoolean(9));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			cn.close();
		}
		return o;
	}

	@Override
	public boolean insert(Comprobante obj) {
		if(obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;;
		try {
					
			Solicitudes solicitudes = new Solicitudes();
			Solicitud solicitud = new Solicitud();
			solicitud.setIdSolicitud(obj.getIdSolicitud());
			if(solicitudes.get(solicitud) == null)
				return false;
			
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("insert"));
			ps.setInt(1, obj.getIdComprobante());
			ps.setInt(2, obj.getIdSolicitud());
			ps.setString(3, obj.getNombreAnfitrion());
			ps.setString(4, obj.getApellidoAnfitrion());
			ps.setString(5, obj.getNombreHuesped());
			ps.setString(6, obj.getApellidoHuesped());
			ps.setString(7, obj.getDescripcionPublicacion());
			ps.setInt(8, obj.getPrecioFinal());
			ps.setBoolean(9, obj.isHabilitado());
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
	public boolean update(Comprobante obj) {
		if(obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;
		try {
			
			Solicitudes solicitudes = new Solicitudes();
			Solicitud solicitud = new Solicitud();
			solicitud.setIdSolicitud(obj.getIdSolicitud());
			if(solicitudes.get(solicitud) == null)
				return false;
			
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("update"));	
			ps.setInt(1, obj.getIdSolicitud());
			ps.setString(2, obj.getNombreAnfitrion());
			ps.setString(3, obj.getApellidoAnfitrion());
			ps.setString(4, obj.getNombreHuesped());
			ps.setString(5, obj.getApellidoHuesped());
			ps.setString(6, obj.getDescripcionPublicacion());
			ps.setInt(7, obj.getPrecioFinal());
			ps.setBoolean(8, obj.isHabilitado());
			ps.setInt(9, obj.getIdComprobante());
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
	public boolean remove(Comprobante obj) {
		Comprobante u = new Comprobante();
		u.setIdComprobante(obj.getIdComprobante());
		u = this.get(u);
		u.setHabilitado(false);
		return this.update(u);
	}

}
