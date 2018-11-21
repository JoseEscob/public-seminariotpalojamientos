package controladoresDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import extra.Conexion;
import modelo.TipoEstadoSolicitud;

public class TiposEstadosSolicitudes implements Connectable<TipoEstadoSolicitud> {

	private static HashMap<String,String> queries = new HashMap<String, String>(){{
		put("all", "select * from tiposestadossolicitudes");
		put("insert", "insert into tiposestadossolicitudes values(null,?,default)");
		put("count", "select count(*) as cantidad from tiposestadossolicitudes");
		put("update","update tiposestadossolicitudes set descripcion=?, habilitado=? where idEstadoSolicitud=?");
		put("get","select * from tiposestadossolicitudes where idEstadoSolicitud=?");
		put("like", "");
			
	}};
		
	private Conexion cn;
	private ArrayList<TipoEstadoSolicitud> m;
	
	@Override
	public ArrayList<TipoEstadoSolicitud> getAll() {
		cn = new Conexion();
		m = new ArrayList<TipoEstadoSolicitud>();
		
		 try
		 {
			 cn.Open();
			 ResultSet rs= cn.query(queries.get("all"));
			 while(rs.next())
			 {					
				 TipoEstadoSolicitud o = new TipoEstadoSolicitud();
				 o.setIdEstadoSolicitud(rs.getInt(1));
				 o.setDescripcion(rs.getString(2));
				 o.setHabilitado(rs.getBoolean(3));
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
	public ArrayList<TipoEstadoSolicitud> getLike(String like) {
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
	public TipoEstadoSolicitud get(TipoEstadoSolicitud obj) {
		cn = new Conexion();
		TipoEstadoSolicitud o = null;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("get"));
			ps.setInt(1, obj.getIdEstadoSolicitud());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				o = new TipoEstadoSolicitud();
				 o.setIdEstadoSolicitud(rs.getInt(1));
				 o.setDescripcion(rs.getString(2));
				 o.setHabilitado(rs.getBoolean(3));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			cn.close();
		}
		return o;
	}

	@Override
	public boolean insert(TipoEstadoSolicitud obj) {
		if(obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;;
		try {
		
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("insert"));
			ps.setString(1, obj.getDescripcion());
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
	public boolean update(TipoEstadoSolicitud obj) {
		if(obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;
		try {
			
		
			
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("update"));	
			ps.setString(1, obj.getDescripcion());
			ps.setBoolean(2, obj.isHabilitado());
			ps.setInt(3,obj.getIdEstadoSolicitud());
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
	public boolean remove(TipoEstadoSolicitud obj) {
		TipoEstadoSolicitud u = new TipoEstadoSolicitud();
		u.setIdEstadoSolicitud(obj.getIdEstadoSolicitud());
		u = this.get(u);
		u.setHabilitado(false);
		return this.update(u);
	}


}
