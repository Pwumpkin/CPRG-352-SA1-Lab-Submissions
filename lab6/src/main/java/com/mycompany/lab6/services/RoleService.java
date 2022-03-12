package com.mycompany.lab6.services;

import com.mycompany.lab6.dataaccess.RoleDB;
import com.mycompany.lab6.dataaccess.UserDB;
import com.mycompany.lab6.models.Role;
import com.mycompany.lab6.models.User;
import java.util.List;


public class RoleService {
    private RoleDB roleDB = new RoleDB();

    public List<Role> getAll(String email) throws Exception {

        List<Role> roles = this.roleDB.getAll();
        return roles;
    }
}
