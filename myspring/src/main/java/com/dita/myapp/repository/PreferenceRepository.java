package com.dita.myapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dita.myapp.domain.Preference;
import com.dita.myapp.domain.PreferenceId;

public interface PreferenceRepository extends CrudRepository<Preference, PreferenceId>{

    @Query(value="select * from preference where uid=?1",nativeQuery = true)
    public List<Preference> getPreference(String uid);
    
}
