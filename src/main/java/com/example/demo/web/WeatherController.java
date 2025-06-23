package com.example.demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.example.demo.model.WeatherBean;
import com.example.demo.service.WeatherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.stream.Collectors;
import java.util.Calendar;

@RestController
public class WeatherController {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	WeatherService weatherService = (WeatherService)context.getBean("WeatherDB");
	
	@GetMapping("/testing3")
	public String testing3(@RequestParam String key,@RequestParam String value,@RequestParam String appid) 
	{
		String inline = "";
		String sMessage = "";
	    ObjectMapper objectMapper = new ObjectMapper(); 
        String sWeatherDescription = "";
        WeatherBean w = new WeatherBean();
        Calendar cal = Calendar.getInstance();
        Date currentDate = cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH");
        String formattedDateTime = dateFormat.format(currentDate);
        String sHourFrom = dateFormat2.format(currentDate);
        String sHourTo = dateFormat2.format(currentDate);
        
        if( (key!=null && key.trim().length()>0) &&
        	(value!=null && value.trim().length()>0) &&
        	(appid!=null && appid.trim().length()>0) 
        )
        {}
        else
        {
        	 return "Wrong key value is passed in. No result return";
        }	
          
		try
		{	
			URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q="+value+","+key+"&appid="+appid);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.connect();
	
	        int responsecode = conn.getResponseCode();
	        
	        if (responsecode != 200) 
	        {
	            return "Wrong key value is passed in. No result return";
            } 
	        else 
            {
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) 
                {
                    inline += scanner.nextLine();
                }

                scanner.close();

                JsonNode jsonNode = objectMapper.readTree(inline);
             
                List<String> lstWeatherDescription = jsonNode.findValues("description")
                	    .stream()
                	    .map(JsonNode::asText)
                	    .collect(Collectors.toList());
               
                for(int i=0;i<lstWeatherDescription.size();i++)
                {
                	sWeatherDescription = lstWeatherDescription.get(i);
                } 	
                
                int iSuccess = 0;
                int iTotalHit = weatherService.getTotalHitPerHourRange(key,value,sHourFrom,sHourTo).size();
                 
                if(iTotalHit<5)
                {
                	w.setkey(key);
                    w.setValue(value);
                    w.setJasonresult(inline);
                    w.setDescription(sWeatherDescription);
                    w.setCreatedDate(formattedDateTime);
                    
                	iSuccess = weatherService.createWeather(w);
                	sMessage = "Weather description is "+sWeatherDescription;
                }	
                else
                {
                	return "5 weather report per hour exceeded the limit for this key search";
                }
            }
		
		} catch (Exception e) {
	        e.printStackTrace();
	    }
		
		return sMessage;
	}
}
