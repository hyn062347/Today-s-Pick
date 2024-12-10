package com.dita.myapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dita.myapp.domain.Preference;
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
}
