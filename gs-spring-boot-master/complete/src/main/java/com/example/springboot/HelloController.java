package com.example.springboot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/LCS")
public class HelloController {

	@RequestMapping("/")
	public String index() {
		return "Greetings from Charu!";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST,
			consumes = "application/json")
	@ResponseBody
	public ResponseEntity<Object> getLCS(@RequestBody ArrayList<String> s1)
	{
		System.out.println(s1);
        System.out.println(hasDups(s1));
        //check if strings have duplicates or no string are sent
		if(s1.size()==0)
		{
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("The set of Strings is empty");
		}
       else if(hasDups(s1))
		{
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("The set of Strings is not unique");
		}
		String s2[]=s1.toArray(new String[s1.size()]);
		//System.out.println(printLCSubStr(s1.get(0),s1.get(1)));
		System.out.println(findLCS(s2));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/")
				.buildAndExpand()
				.toUri();

		//return ResponseEntity.created(location).build();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(findLCS(s2));
	}



	public static String findLCS(String arr[])
	{

		int n = arr.length;
		String s = arr[0];
		int len = s.length();

		String res = "";

		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j <= len; j++) {
				String stem = s.substring(i, j);
				int k = 1;
				for (k = 1; k < n; k++)
					if (!arr[k].contains(stem))
						break;
				if (k == n && res.length() < stem.length())
					res = stem;
			}
		}

		return res;
	}

	public boolean hasDups(ArrayList<String> s1)
	{
		Set<String> hs= new HashSet<>();
		for(String s2:s1)
		{
			if(!hs.add(s2))
				return true;
		}
		return false;
	}


}
