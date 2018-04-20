package org.ram.testwebservice.messengers.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ram.testwebservice.messengers.database.DataBase;
import org.ram.testwebservice.messengers.model.Profile;

public class ProfileServices {

	private Map <String, Profile> profiles = DataBase.getProfiles();
	
	public ProfileServices(){
		profiles.put("Ram", new Profile(1l, "Ramnath", "Shinde", "Ram"));
	}
	
	public List<Profile> getAllProfile(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	
	public Profile getProfile(String profileName){
		return profiles.get(profileName);
	} 
	public Profile addProfile(Profile profile){
		profile.setId(profiles.size() + 1 );
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile){
		if(profile.getProfileName().isEmpty()){
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	
	public Profile removeProfile(String profileName){
		return profiles.remove(profileName);
	}
}
