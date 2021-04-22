package ru.nazarov.practice.country.service;

import ru.nazarov.practice.country.view.CountryView;

import java.util.List;

public interface CountryService {

    List<CountryView> getList();
}
