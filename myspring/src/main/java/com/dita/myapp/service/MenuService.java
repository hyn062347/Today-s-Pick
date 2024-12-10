package com.dita.myapp.service;

import com.dita.myapp.domain.Menu;
import com.dita.myapp.repository.MenuRepository;
import org.springframework.stereotype.Service;

@Service
public class MenuService {
    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu getRandomMenu(String category) {
        // '전체'일 경우 category를 null로 전달
        return menuRepository.findRandomMenuByCategory("전체".equals(category) ? null : category);
    }

}