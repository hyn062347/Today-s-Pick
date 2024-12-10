package com.dita.myapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dita.myapp.domain.Preference;
import com.dita.myapp.dto.AccountsDto;
import com.dita.myapp.dto.FavoriteDto;
import com.dita.myapp.dto.PreferenceDto;
import com.dita.myapp.service.PreferenceService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/preference")
@Log
public class PreferenceController {
    private final PreferenceService preferenceService;

    @PostMapping("/all")
    public ResponseEntity<?> getPreferenceAll(@RequestBody AccountsDto accdto) {
        ArrayList<PreferenceDto> list=new ArrayList<>();
        try{
            list=preferenceService.getPreference(accdto.getUid());
        }catch(Exception e){
            return ResponseEntity.ok().body(false);
        }
        
        ArrayList<String> li=new ArrayList<>();
        for (PreferenceDto predto : list) {
            li.add(predto.getCtg());
        }

        return ResponseEntity.ok().body(li);
    }

    @PostMapping("/set")
    public ResponseEntity<?> setPreference(@RequestBody AccountsDto accdto) {

        try{
            preferenceService.setPreference(accdto.getUid(),accdto.getPreference());
        }catch(Exception e){
            return ResponseEntity.ok().body(false);
        }
        
        return ResponseEntity.ok().body(true);
    }
}
