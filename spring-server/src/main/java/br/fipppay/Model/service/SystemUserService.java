package br.fipppay.Model.service;

import br.fipppay.Model.entities.SystemUser;
import br.fipppay.Model.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemUserService {

    @Autowired
    private SystemUserRepository repo;

    public SystemUser save (SystemUser user)
    {
        return repo.save(user);
    }

    public SystemUser getById(Long id)
    {
        SystemUser user = repo.findById(id).get();
        return user;
    }

    public List<SystemUser> getAll ()
    {
        return repo.findAll();
    }

    public boolean delete (Long id)
    {
        try
        {
            repo.deleteById(id);
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }

    public SystemUser getByEmail (SystemUser user)
    {
        return repo.getByEmail(user.getEmail());
    }

}
