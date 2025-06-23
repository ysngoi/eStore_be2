package com.example.demo.service;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.demo.model.WeatherBean;

public class WeatherService implements DsDao {
   private DataSource dataSource;
   private JdbcTemplate jdbcTemplate;
   
   public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
   }
   
   public int createWeather(WeatherBean e) 
   {
	    int istatus = 0;
	    try
	    {
	    	istatus= jdbcTemplate.update("insert into weather(srckey,srcvalue,jasonresult,description,createddate) values(?,?,?,?,?)",new Object[] {e.getKey(), e.getValue(), e.getJasonresult(),e.getDescription(),e.getCreatedDate()});
	    }
	    catch(Exception ex)
	    {
	    	ex.printStackTrace();
	    }
		return istatus;
   } 
   
   public List<WeatherBean> getTotalHitPerHourRange(String key,String value,String shourFrom,String shourTo) 
   {
		String sql = "SELECT * FROM weather where (createddate >= '"+shourFrom+":00' and createddate < '"+shourTo+":59') and (srckey='"+key+"' and srcvalue='"+value+"');";
	   	
		List<WeatherBean> liststaff = jdbcTemplate.query(
				sql,(rs, rowNum) -> new WeatherBean() ); 
		
        return liststaff;
   }
}
