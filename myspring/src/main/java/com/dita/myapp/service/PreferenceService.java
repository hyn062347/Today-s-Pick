package com.dita.myapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dita.myapp.domain.Preference;
import com.dita.myapp.domain.PreferenceId;
import com.dita.myapp.dto.PreferenceDto;
import com.dita.myapp.repository.PreferenceRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PreferenceService {
    private final PreferenceRepository preferenceRepository;

    public ArrayList<PreferenceDto> getPreference(String uid){
        ArrayList<PreferenceDto> arrlist=new ArrayList<>();

        try{
            List<Preference> list=preferenceRepository.getPreference(uid);
            for (Preference preference : list) {
                PreferenceDto pdto=new PreferenceDto();
                pdto.setCtg(preference.getCtg());
                pdto.setUid(preference.getUid());
                arrlist.add(pdto);
            }
        }
        catch(Exception e){
            
        }

        return arrlist;
    }

    public boolean setPreference(String uid,String[] preferences){

        try{
            List<Preference> list=preferenceRepository.getPreference(uid);
            ArrayList<PreferenceId> ids=new ArrayList<>();
            for (Preference preference : list) {
                PreferenceId id=new PreferenceId();
                id.setUid(preference.getUid());
                id.setCtg(preference.getCtg());
                ids.add(id);
            }
            for (PreferenceId preferenceId : ids) {
                preferenceRepository.deleteById(preferenceId);
            }


            for(String p : preferences){
                Preference preference=new Preference();
                preference.setUid(uid);
                preference.setCtg(p);
                preferenceRepository.save(preference);
            }
        }
        catch(Exception e){
            return false;
        }

        return true;
    }
}
