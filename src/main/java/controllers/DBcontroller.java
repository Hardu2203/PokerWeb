package controllers;

import User.User;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import User.UserGame;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import User.Game;
import ninja.jpa.UnitOfWork;

import java.util.List;

/**
 * Created by harduNel on 2015-01-22.
 */
public class DBcontroller<T>{




    @Inject
    private Provider<EntityManager> entityManagerProvider;
   // @Transactional
   // public void postIndex(T Entity) {


        //logger.info("In postRoute");

    //    getEntityManager().persist(Entity);
  //  }
  //  protected EntityManager getEntityManager(){return entityManagerProvider.get();}




        // return Results.redirect(router.getReverseRoute(ApplicationController.class, "getIndex"));
        @UnitOfWork
         public List<UserGame> getHistory() {

        EntityManager entityManager = entityManagerProvider.get();

        Query q = entityManager.createQuery("SELECT x FROM UserGame x WHERE WINNER = True");
        if(q!=null) {
            List<UserGame> guestbookEntries = (List<UserGame>) q.getResultList();
            return guestbookEntries;
        }
        return null;



    }
    @UnitOfWork
    public List<Game> getCreated() {

        EntityManager entityManager = entityManagerProvider.get();

        Query q = entityManager.createQuery("SELECT x FROM Game x WHERE x.Status = 'Created'");
        if(q!=null) {
            List<Game> guestbookEntries = (List<Game>) q.getResultList();
            return guestbookEntries;
        }
        return null;



    }

    @UnitOfWork
    public boolean IsHostOfGame(String GameName, String HostName) {

        EntityManager entityManager = entityManagerProvider.get();

        Query q = entityManager.createQuery("SELECT x FROM Game x WHERE x.Status = 'Created' AND x.gamename = :GameName");
        q.setParameter("GameName",GameName);
        if(q!=null) {
            List<Game> guestbookEntries = (List<Game>) q.getResultList();

            boolean HostOrNot = false;
            if(guestbookEntries.get(0).getHostName().compareTo(HostName) ==0)
            {
                HostOrNot = true;
            }
            return HostOrNot;
        }
        return false;



    }

    @UnitOfWork
    public Game GetGameForUpdate(String GameName) {

        EntityManager entityManager = entityManagerProvider.get();

        Query q = entityManager.createQuery("SELECT x FROM Game x WHERE x.Status = 'Created' AND x.gamename = :GameName");
        q.setParameter("GameName",GameName);
        if(q!=null) {
            List<Game> guestbookEntries = (List<Game>) q.getResultList();

            Game UpdateGame = guestbookEntries.get(0);

            return UpdateGame;
        }
        return null;



    }
    @Transactional
    public void UpdateGame(Game use)
    {

        //logger.info("In postRoute");

        EntityManager entityManager = entityManagerProvider.get();

        entityManager.merge(use);




        // return Results.redirect(router.getReverseRoute(ApplicationController.class, "getIndex"));

    }





}
