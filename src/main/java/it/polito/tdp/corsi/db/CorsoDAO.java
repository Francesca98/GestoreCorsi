package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;

public class CorsoDAO { 

//dao è separato sia dalla logica applicatica sia dall'interfaccia

	/* metodo per determinare tutti i corsi di un periodo didattico
	 *dalla tab corso devo recuperare tutti i corsi : mi serve qualcosa che contenga tutte le informazioni del corso 
	 * deve contenere tutti i  campi della tabella + metodi get e set --> creo classe corso*/

	
	public List<Corso> getCorsiByPeriodo(int pd) //METODO CHE RESTITUISCE LA LISTA DI CORSI 
	{
		String sql = "select * from corso where pd = ?"; // è la query che voglio  svolgere
		List<Corso> result = new LinkedList <>();
		try {
			Connection conn = ConnectDB.getConnection(); //crei la connessione
			PreparedStatement st = conn.prepareStatement(sql);//prepari la query
			st.setInt(1, pd); // definisci il parametro che ti manca
			ResultSet rs = st.executeQuery(); // salvi i risultati
			while (rs.next())
			{
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				result.add(c);
			}
			conn.close(); //IMPORTANTE
			
		} catch (SQLException e)
		{throw new RuntimeException(e);}
		return result;
	}
	
	public Map<Corso, Integer> getIscrittiByPeriodo (int pd)
	{
		String sql = "select c.codins, c.nome, c.crediti,COUNT(*) as tot" +
	"from corso as c , iscrizione as i "+
				"where  c.codins = i.codins and c.pd = ?"+
				"group by c.codins, c.nome, c.crediti,c.pd";
		Map<Corso, Integer> map = new HashMap<>();
		
		try {
			Connection conn = ConnectDB.getConnection(); 
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, pd);
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				int num = rs.getInt("tot");
				map.put(c, num);
			}
			conn.close();
			
		}catch (SQLException e)
		{throw new RuntimeException(e);}

		return map;
		
	}
	
	
	
	

}
