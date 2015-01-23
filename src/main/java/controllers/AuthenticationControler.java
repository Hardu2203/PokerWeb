package controllers;

import User.User;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import ninja.Context;
import ninja.jpa.UnitOfWork;
import ninja.session.Session;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import User.Game;

/**
 * Created by harduNel on 2015-01-16.
 */


public class AuthenticationControler {

    @Inject
    Provider<EntityManager> entityManagerProvider;

    List<String> someList = new ArrayList<String>();
    int tel = 0;
    @Inject CryptWithMD5 cr;
    public boolean LoginTheUser(String Username, String Password)
    {
        boolean test = false;
        someList.add("Hardu*Nel");
        if (Password != null) {
            Password = cr.cryptWithMD5(Password);
        }



        if ((Username!=null) && (Password!=null))
        {

            for (String item : someList) {
                if (item.toString().compareTo(Username + "*" + Password)==0) {
                    test = true;

                }

                List<User> us = new LinkedList<User>();
                us = getIndex();
                String UserNameS;
                String PasswordS;
                for (User u : us){
                     UserNameS = u.getUsername();
                    PasswordS = u.getPassword();
                    if (((Username.compareTo(UserNameS)==0)&&(Password.compareTo(PasswordS)==0))){
                        test = true;
                    }


                }

            }






        }
        return  test;


    }


    public boolean RegisterTheUser(String Username, String Password)
    {

       // Context Con;
       // Session Ses;
       // Ses = Con.getSession();


        if (Password != null) {
            Password = cr.cryptWithMD5(Password);
        }
        boolean test = true;
        someList.add("Hardu*Nel");


        if ((Username != null) && (Password != null))
        {



            for (String item : someList) {//TU
                if (item.toString().compareTo(Username + "*" + Password)==0) {
                    System.out.println("Bestaan Klaar");
                    test = false;

                }

            }


        }

        if (test == true)
        {
            User u = new User();
            u.setPassword(Password);
            u.setUsername(Username);
            postIndex(u);
            someList.add(Username +"*"+ Password);
            System.out.println("Added");
        }
        return  test;








    }



    @UnitOfWork
    public List<User> getIndex() {

        EntityManager entityManager = entityManagerProvider.get();

            Query q = entityManager.createQuery("SELECT x FROM User x");
            if(q!=null) {
            List<User> guestbookEntries = (List<User>) q.getResultList();
            return guestbookEntries;
        }
        return null;



    }


    @Transactional
      public void postIndex(User use) {

        //logger.info("In postRoute");

        EntityManager entityManager = entityManagerProvider.get();

        entityManager.persist(use);




        // return Results.redirect(router.getReverseRoute(ApplicationController.class, "getIndex"));

    }

    @Transactional
    public void postIndexGame(Game use)
    {

        //logger.info("In postRoute");

        EntityManager entityManager = entityManagerProvider.get();

        entityManager.persist(use);




        // return Results.redirect(router.getReverseRoute(ApplicationController.class, "getIndex"));

    }




}
