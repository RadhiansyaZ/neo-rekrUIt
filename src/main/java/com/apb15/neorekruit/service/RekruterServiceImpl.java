package com.apb15.neorekruit.service;

import com.apb15.neorekruit.model.Rekruter;
import com.apb15.neorekruit.repository.RekruterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RekruterServiceImpl implements RekruterService {
    private final RekruterRepository rekruterRepository;
    @Override
    public Rekruter createRekruter(Rekruter rekruter) {
        return rekruterRepository.save(rekruter);
    }
}
