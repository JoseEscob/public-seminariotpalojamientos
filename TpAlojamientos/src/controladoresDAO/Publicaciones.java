package controladoresDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import extra.Conexion;
import modelo.Publicacion;
import modelo.TipoAlojamiento;

public class Publicaciones implements Connectable<Publicacion> {

	@Override
	public ArrayList<Publicacion> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Publicacion> getLike(String like) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Publicacion get(Publicacion obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Publicacion obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Publicacion obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Publicacion obj) {
		// TODO Auto-generated method stub
		return false;
	}
	/*private int idPublicacion;
	private int idUsuario;
	private int idTipoAlojamiento;
	private String nombre;
	private String descripcion;
	private int idLocalidad;
	private int codPostal;
	private String coordenadas;
	private float precioNoche;

	private int metrosCuadrados;
	private int cantPersonas;
	private int cantAmbientes;
	private int cantBanios;
	private int cantHabitaciones;

	private boolean bitJardin;
	private boolean bitCochera;
	private boolean bitMascotas;
	private boolean bitFumadores;
	private boolean bitAmoblada;
	private boolean bitDesayuno;

	private Date fechaAlta;
	private float puntaje;
	private boolean habilitado;
	
	private static HashMap<String,String> queries = new HashMap<String, String>(){{
		put("all", "select * from tiposalojamientos");
		put("insert", "insert into tiposalojamientos values(null,?,default)");
		put("count", "select count(*) as cantidad from tiposalojamientos");
		put("update","update tiposalojamientos set descripcion=?, habilitado=? where idTipoAlojamiento=?");
		put("get","select * from tiposalojamientos where idTipoAlojamiento=?");
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
				 o.setIdTipoAlojamiento(rs.getInt(1));
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
		TipoAlojamiento o = null;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("get"));
			ps.setInt(1, obj.getIdTipoAlojamiento());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				o = new Publicacion();
				o.setIdTipoAlojamiento(rs.getInt(1));
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
	public boolean insert(Publicacion obj) {
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
	public boolean update(Publicacion obj) {
		if(obj == null) {
			return false;
		}
		cn = new Conexion();
		boolean correcto = false;
		try {
			PreparedStatement ps = cn.Open().prepareStatement(queries.get("update"));
			ps.setString(1, obj.getDescripcion());
			ps.setBoolean(2, obj.isHabilitado());
			ps.setInt(3,obj.getIdTipoAlojamiento());
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
		u.setIdTipoAlojamiento(obj.getIdTipoAlojamiento());
		u = this.get(u);
		u.setHabilitado(false);
		return this.update(u);
	}
*/
}
