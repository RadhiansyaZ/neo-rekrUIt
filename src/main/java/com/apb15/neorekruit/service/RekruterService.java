package com.apb15.neorekruit.service;

import com.apb15.neorekruit.model.Rekruter;

import java.util.Optional;

public interface RekruterService {
    Rekruter createRekruter(Rekruter rekruter);
    Rekruter findByEmail(String email);
}
