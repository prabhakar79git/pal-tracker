package io.pivotal.pal.tracker;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvController {
	
   private String port ;
   private String memoryLimit;
   private String cfInstanceIndex;
   private String address;
	
  
	public EnvController(@Value("${ports}")String port,
			@Value("${memory.limit}")String memoryLimit,
			@Value("${cf.instance.index:NOT SET}") String cfInstanceIndex,
			@Value("${cf.instance.addr}")String address){
		
		this.port=port;
		this.memoryLimit=memoryLimit;
		this.cfInstanceIndex=cfInstanceIndex;
		this.address=address;
		
			
			}

    @GetMapping("/env")
    public Map<String, String> getEnv() {
    	
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("PORTS", port);
    	map.put("MEMORY_LIMIT", memoryLimit);
    	map.put("CF_INSTANCE_INDEX", cfInstanceIndex);
    	map.put("CF_INSTANCE_ADDR", address);
    	
        return map;
    }
}