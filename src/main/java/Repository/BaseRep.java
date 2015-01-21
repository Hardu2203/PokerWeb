package Repository;

import User.User;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import controllers.ApplicationController;
import ninja.Results;
import ninja.jpa.UnitOfWork;
import org.eclipse.jetty.server.Authentication;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.xml.transform.Result;

import java.util.List;


/**
 * Created by harduNel on 2015-01-20.
 */

public class BaseRep {
   /* @Inject
    Provider<EntityManager> entityManagerProvider;

    @UnitOfWork
    public void getIndex() {

        EntityManager entityManager = entityManagerProvider.get();

        Query q = entityManager.createQuery("SELECT x FROM GuestbookEntry x");
        List<User> guestbookEntries = (List<User>) q.getResultList();




    }


    @Transactional
    public void postIndex(User use) {

       // logger.info("In postRoute");

        EntityManager entityManager = entityManagerProvider.get();

        entityManager.persist(use);




       // return Results.redirect(router.getReverseRoute(ApplicationController.class, "getIndex"));

    }*/

}
