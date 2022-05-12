package com.kraisorn.identity.controller;

import com.kraisorn.identity.domain.Account;
import com.kraisorn.identity.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/account")
public class AccountController {
    @Autowired
    private AccountRepository repository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewAccount (@RequestParam String name
            , @RequestParam String email) {
        Account n = new Account();
        n.setName(name);
        n.setEmail(email);
        repository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Account> getAllAccounts() {
        return repository.findAll();
    }
}
