package com.upk.diploma.customerservice.service.impl;

import com.upk.diploma.customerservice.dto.UserAccountCreateRequest;
import com.upk.diploma.customerservice.dto.UserAccountResponse;
import com.upk.diploma.customerservice.exception.ConversionException;
import com.upk.diploma.customerservice.exception.DataNotFoundException;
import com.upk.diploma.customerservice.mapping.UserAccountMapper;
import com.upk.diploma.customerservice.model.entity.UserAccount;
import com.upk.diploma.customerservice.repository.UserAccountRepository;
import com.upk.diploma.customerservice.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final UserAccountMapper userAccountMapper;

    @Override
    public UserAccountResponse getById(Long id) {
        return userAccountRepository.findById(id)
                .map(userAccountMapper::map)
                .orElseThrow(() -> new DataNotFoundException("User Account", id));
    }

    @Override
    public UserAccountResponse create(UserAccountCreateRequest userAccountCreateRequest) {
        final UserAccount userAccount = Optional.ofNullable(userAccountMapper.map(userAccountCreateRequest))
                .orElseThrow(() -> new ConversionException(("User Account")));

        final UserAccount savedUserAccount = userAccountRepository.save(userAccount);

        return userAccountMapper.map(savedUserAccount);
    }

    @Override
    public UserAccountResponse update(Long userAccountId, UserAccountCreateRequest userAccountCreateRequest) {
        final UserAccount userAccount = Optional.ofNullable(userAccountMapper.map(userAccountCreateRequest))
                .orElseThrow(() -> new ConversionException("User Account"));
        userAccount.setId(userAccountId);

        final UserAccount savedUserAccount = userAccountRepository.save(userAccount);
        return userAccountMapper.map(savedUserAccount);
    }

    @Override
    public void delete(Long userAccountId) {
        try {
            userAccountRepository.deleteById(userAccountId);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("User Account", userAccountId);
        }
    }
}
