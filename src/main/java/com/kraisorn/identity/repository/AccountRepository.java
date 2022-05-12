package com.kraisorn.identity.repository;

import com.kraisorn.identity.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends CrudRepository<Account, Long>, PagingAndSortingRepository<Account, Long> {
}
