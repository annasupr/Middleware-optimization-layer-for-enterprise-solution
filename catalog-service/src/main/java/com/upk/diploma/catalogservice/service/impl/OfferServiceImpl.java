package com.upk.diploma.catalogservice.service.impl;

import com.upk.diploma.catalogservice.dto.OfferResponse;
import com.upk.diploma.catalogservice.exception.ConversionException;
import com.upk.diploma.catalogservice.exception.DataNotFoundException;
import com.upk.diploma.catalogservice.mapping.OfferMapper;
import com.upk.diploma.catalogservice.model.entity.Offer;
import com.upk.diploma.catalogservice.repository.OfferRepository;
import com.upk.diploma.catalogservice.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;

    @Override
    public OfferResponse getById(Long id) {
        return offerRepository.findById(id)
                .map(offerMapper::map)
                .orElseThrow(() -> new DataNotFoundException("Offer", id));
    }

    @Override
    public List<OfferResponse> getAll() {
        return offerRepository.findAll()
                .stream()
                .map(offerMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public OfferResponse create(OfferResponse offerResponse) {
        final Offer offer = offerMapper.map(offerResponse);
        final Offer savedOffer = offerRepository.save(offer);
        return offerMapper.map(savedOffer);
    }

    @Override
    public OfferResponse update(Long offerId, OfferResponse offerResponse) {
        final Offer offer = Optional.ofNullable(offerMapper.map(offerResponse))
                .orElseThrow(() -> new ConversionException("Offer"));
        offer.setId(offerId);

        final Offer savedOffer = offerRepository.save(offer);
        return offerMapper.map(savedOffer);
    }

    @Override
    public void delete(Long offerId) {
        try {
            offerRepository.deleteById(offerId);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("Offer", offerId);
        }
    }
}
