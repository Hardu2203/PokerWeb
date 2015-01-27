/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;
import User.User;
import User.Game;
import User.UserGame;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import ninja.Result;
import ninja.Results;

import com.google.inject.Singleton;
import ninja.params.PathParam;
import ninja.session.Session;
import services.LoginService;
import services.PokerService;
import services.iPokerService;
import ninja.Context;

import javax.persistence.EntityManager;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Singleton
public class ApplicationController {


    @Inject
    private iPokerService pokerService;
    @Inject
    private PokerService pok;
    @Inject AuthenticationControler auth;
    @Inject DBcontroller db;
    @Inject GameController gc;

    public  Result Logout(Context Con)
    {
        Result result = Results.html();
        Session Ses;
        Ses = Con.getSession();
        Ses.clear();
        result.redirect("/");
        return  result;

    }

    public Result MultiPlayer(Context Con) {
        Result result = Results.html();
        pok.GenerateDeck();
        String Name = pokerService.getname();
        String Name2 = pokerService.getname();
        String Name3 = pokerService.getname();
        String Name4 = pokerService.getname();
        String Name5 = pokerService.getname();




        String[] parts = Name.split(",");
        String part1 = parts[1];
        String part2 = parts[2];
        String part3 = parts[3];
        String part4 = parts[4];
        String part5 = parts[5];
        String oef = Name.substring(0,Name.indexOf(","));




        result.render("card1", oef );
        result.render("card2", part1 );
        result.render("card3", part2 );
        result.render("card4", part3 );
        result.render("card5", part4 );

        //////// 1
        String[] parts1 = Name3.split(",");
        String part11 = parts1[1];
        String part12 = parts1[2];
        String part13 = parts1[3];
        String part14 = parts1[4];
        String part15 = parts1[5];
        String oef1 = Name3.substring(0,Name3.indexOf(","));




        result.render("card11", oef1 );
        result.render("card12", part11 );
        result.render("card13", part12 );
        result.render("card14", part13 );
        result.render("card15", part14 );


        //////// 2
        String[] parts2 = Name2.split(",");
        String part21 = parts2[1];
        String part22 = parts2[2];
        String part23 = parts2[3];
        String part24 = parts2[4];
        String part25 = parts2[5];
        String oef2 = Name2.substring(0,Name2.indexOf(","));




        result.render("card21", oef2 );
        result.render("card22", part21 );
        result.render("card23", part22 );
        result.render("card24", part23 );
        result.render("card25", part24 );
        //////// 3
        String[] parts3 = Name4.split(",");
        String part31 = parts3[1];
        String part32 = parts3[2];
        String part33 = parts3[3];
        String part34 = parts3[4];
        String part35 = parts3[5];
        String oef3 = Name4.substring(0,Name4.indexOf(","));




        result.render("card31", oef3 );
        result.render("card32", part31 );
        result.render("card33", part32 );
        result.render("card34", part33 );
        result.render("card35", part34 );

        ///////// 4

        String[] parts4 = Name5.split(",");
        String part41 = parts4[1];
        String part42 = parts4[2];
        String part43 = parts4[3];
        String part44 = parts4[4];
        String part45 = parts4[5];
        String oef4 = Name5.substring(0,Name5.indexOf(","));




        result.render("card41", oef4 );
        result.render("card42", part41 );
        result.render("card43", part42 );
        result.render("card44", part43 );
        result.render("card45", part44 );

        String[] handEval = {"High Card","One Pair","Two Pair","Three Of a Kind","Straight","Flush","Full House","Four of a Kind","Straight Flush"};
        String[] hands = {part5,part15,part25,part35,part45};
        int Index = 0;
        int winhandnr=0;

        for(int i =0;i<handEval.length;i++)
        {
            for(int h =0;h<hands.length;h++)
            {
                if(handEval[i].compareTo(hands[h])==0)
                {
                    if (i > Index)
                    {
                        Index = i;
                        winhandnr = h;
                    }
                }
            }
        }
        result.render("Strong", handEval[Index].toString());

        result.render("Name", part5);
        result.render("Name2", part15);
        result.render("Name3", part25);
        result.render("Name4", part35);
        result.render("Name5", part45);


        Game g = new Game();
        g.setgamename("Eerste Game");
        g.setTimestamp(new Date());


        User us = new User();
        us.setUsername("hard");
        us.setPassword("qq");
      //  auth.postIndex(us);

        UserGame UG = new UserGame();
        UG.setUsername("hard");
        UG.setWinner(true);
        UG.setHand(part1 +" " + part2 +" "+ part3+" "+ part4+" "+ part5);

        UG.setgameid(1);




      //  List<UserGame> Userlist = new ArrayList<UserGame>();
     //   Userlist.add(UG);
      //  g.setUsers(Userlist);



      //  postIndexGame(g);
      //  postIndexUserGame(UG);











        return result;









    }


    @Inject
    Provider<EntityManager> entityManagerProvider;
    @Transactional
    public void postIndexGame(Game use)
    {

        //logger.info("In postRoute");

        EntityManager entityManager = entityManagerProvider.get();

        entityManager.persist(use);




        // return Results.redirect(router.getReverseRoute(ApplicationController.class, "getIndex"));

    }
    @Transactional
    public void postIndexUserGame(UserGame use)
    {

        //logger.info("In postRoute");

        EntityManager entityManager = entityManagerProvider.get();

        entityManager.persist(use);




        // return Results.redirect(router.getReverseRoute(ApplicationController.class, "getIndex"));

    }




    public Result index(Context Con) {

       Result result = Results.html();
        Session Ses = Con.getSession();

        String Username = Con.getParameter("UserName");
        String Password = Con.getParameter("password");


        String SesUsername = "";
        SesUsername =  Ses.get("username");

      //  String Sespassword = Ses.get("password");


        if (SesUsername != null) {
      //      if ((Username.toString().compareTo(SesUsername.toString()) == 0)) {

                //Success??
            pok.GenerateDeck();
            String Name = pokerService.getname();
                result.render("Name", pokerService.getname());

            String[] parts = Name.split(",");
            String part1 = parts[1];
            String part2 = parts[2];
            String part3 = parts[3];
            String part4 = parts[4];
            String part5 = parts[5];
            String oef = Name.substring(0,Name.indexOf(","));




            result.render("card1", oef );
            result.render("card2", part1 );
            result.render("card3", part2 );
            result.render("card4", part3 );
            result.render("card5", part4 );

            result.render("Strong", part5);


                return result;
      //      }
        }






        if (auth.LoginTheUser(Username, Password)) {
//
            Ses.put("username", Username);
            pok.GenerateDeck();
            result.render("Name", pokerService.getname());
            String Name = pokerService.getname();
            String[] parts = Name.split(",");
            String part1 = parts[1];
            String part2 = parts[2];
            String part3 = parts[3];
            String part4 = parts[4];
            String part5 = parts[5];
            String oef = Name.substring(0,Name.indexOf(","));




            result.render("card1", oef );
            result.render("card2", part1 );
            result.render("card3", part2 );
            result.render("card4", part3 );
            result.render("card5", part4 );

            result.render("Strong", part5);


            return  result;
        }
        else {
            CheckLogin = false;
            System.out.println(CheckLogin.toString());
            result.redirect("/");


        }
        return result;
        //return Results.html();

    }
    //List<Session>?
    public Boolean CheckLogin = true;
    public Boolean CheckRegister = true;


    public Result indexRegister(Context Con) {

        ///////

        Result result = Results.html();
        String Username = Con.getParameter("UserName");
        String Password = Con.getParameter("Password");





        Session Ses;
        Ses = Con.getSession();

       // if ((Ses.get("username") == null) || (Ses.get("username") == "") ) {
         //   Ses.put("username", Username);
         //   Ses.put("password", Password);
        //    result.render("Name", pokerService.getname());

       // }
      //  else {result.render("Name", Username +" Is already registered on the system");}
        ///////



        //session.put("useename",Username)
        if (auth.RegisterTheUser(Username, Password)) {
            Ses.put("username", Username);
            pok.GenerateDeck();
            result.render("Name", pokerService.getname());


            String Name = pokerService.getname();
            String[] parts = Name.split(",");
            String part1 = parts[1];
            String part2 = parts[2];
            String part3 = parts[3];
            String part4 = parts[4];
            String part5 = parts[5];
            String oef = Name.substring(0,Name.indexOf(","));




            result.render("card1", oef );
            result.render("card2", part1 );
            result.render("card3", part2 );
            result.render("card4", part3 );
            result.render("card5", part4 );

        }
        else
        {
            CheckRegister = false;
            System.out.println(CheckRegister.toString());


                result.redirect("/Register");
            }





        return result;
        //return Results.html();

    }

//    public  Result Hand()
//    {
//        Result result = Results.html();
//        result.render("PokerHand",pokerService.deal)
//    }

    
    public Result helloWorldJson() {

        SimplePojo simplePojo = new SimplePojo();
        simplePojo.content = "Hello World! Hello Json!";

        return Results.json().render(simplePojo);

    }

    public Result History() {

        Result result = Results.html();


        List<UserGame> UserGameL = db.getHistory();

        if (UserGameL.size()>0) {
            result.render("GAMEID1", UserGameL.get(0).getgameid());
            result.render("UserName1", UserGameL.get(0).getUsername());
            result.render("Hand1", UserGameL.get(0).getHand());
        }
        else {result.render("GAMEID1", "");
            result.render("UserName1", "");
            result.render("Hand1", "");}

        if(UserGameL.size()>1) {
            result.render("GAMEID2", UserGameL.get(1).getgameid());
            result.render("UserName2", UserGameL.get(1).getUsername());
            result.render("Hand2", UserGameL.get(1).getHand());
        }
        else { result.render("GAMEID2", "");
            result.render("UserName2", "");
            result.render("Hand2", "");}

        if(UserGameL.size()>2) {
            result.render("GAMEID3", UserGameL.get(2).getgameid());
            result.render("UserName3", UserGameL.get(2).getUsername());
            result.render("Hand3", UserGameL.get(2).getHand());
        }
        else {
            result.render("GAMEID3", "");
            result.render("UserName3","");
            result.render("Hand3", "");
        }
        if(UserGameL.size()>3) {
            result.render("GAMEID4", UserGameL.get(4).getgameid());
            result.render("UserName4", UserGameL.get(4).getUsername());
            result.render("Hand4", UserGameL.get(4).getHand());
        }
        else {result.render("GAMEID4", "");
            result.render("UserName4", "");
            result.render("Hand4", "");}
        if(UserGameL.size()>4) {
            result.render("GAMEID5", UserGameL.get(5).getgameid());
            result.render("UserName5", UserGameL.get(5).getUsername());
            result.render("Hand5", UserGameL.get(5).getHand());

        }
        else {result.render("GAMEID5", "");
            result.render("UserName5","");
            result.render("Hand5", "");}




        return result;

    }

    public boolean testSession(Context Con)
    {
        boolean tested = false;
        Session Ses;
        Ses = Con.getSession();

        String Username = Ses.get("username");
        if (Username != null) {
             tested = true;
        }
        return tested;
    }
    public Result Login(Context Con)
    {
        Result result = Results.html();
       // String Username = Con.geameter("UserName");
        //String Password = Con.getParameter("Password


        //if (auth.LoginTheUser(Username, Password)) {
       // System.out.println(CheckLogin.toString());

       // if(testSession(Con) == true)
      //  {
       //     result.redirect("/Game");
       //     return result;

       // }
        Session Ses;
        Ses = Con.getSession();
        String Username = Ses.get("username");
        if (Username != null)
        {
            result.redirect("/Game");
                 return result;
        }
        if (CheckLogin == true) {
            result.render("Name", LoginService.ShowLogin1(""));
        }
        else if (CheckLogin == false)
        {
            result.render("Name",LoginService.ShowLogin1("Login Failed"));
        }

       // }
        return result;
        //return Results.html();
    }
    public Result Login2(Context Con)
    {
        Result result = Results.html();
        String Username = Con.getParameter("UserName");
        String Password = Con.getParameter("Password");



        if (auth.LoginTheUser(Username, Password)) {
            result.render("Name",pokerService.getname());

            String Name = pokerService.getname();
            String[] parts = Name.split(",");
            String part1 = parts[1];
            String part2 = parts[2];
            String part3 = parts[3];
            String part4 = parts[4];
            String part5 = parts[5];
            String oef = Name.substring(0,Name.indexOf(","));




            result.render("card1", oef );
            result.render("card2", part1 );
            result.render("card3", part2 );
            result.render("card4", part3 );
            result.render("card5", part4 );

        }
        return result;
        //return Results.html();
    }

    public Result Register()
    {
        Result result = Results.html();





            if (CheckRegister == true) {
                result.render("Name", LoginService.ShowLogin1(""));
            }
        else {
                if (CheckRegister == false)
                {
                    result.render("Name", LoginService.ShowLogin1("Registration Failed, the user already exists"));
                }
            }

        return result;
        //return Results.html();
    }
    public Result SelectPlayers(Context Con)
    {
        Session s = Con.getSession();

        String SesUsername = s.get("username");
        Result result = Results.html();

        String User1 = Con.getParameter("UserSelect");
        List<Game> GameList = db.getCreated();

        String html ="";
        html += "<form  method='post'>";
        html += "<h2> Available Games </h2> <table>";

        for(Game g : GameList)
        {
            if(g.getHostName().compareTo(SesUsername) != 0)
            {

                html += "<tr><td><a href='/GameLobby/";
                html += g.getGamename();


                html += "'>join game</a></td></td>";
                html += g.getGamename();
                html += "</td></td>";


            }
        }

        html += "</table>";
        html += "</form>";

        result.render("games",html);


        return  result;



    }
    Map<String, List <String>> m1 = new HashMap();
    String html;
    boolean changed = false;


    public Result JoinGameLobby(@PathParam("gamename") String GameName,Context Con)
    {
        boolean changed = true;
        Session s = Con.getSession();
        String SesUsername = s.get("username");

        List<String> Usernames = new ArrayList<>();
               // m1.get(GameName);



        if (m1.get(GameName) != null)
        {
            Usernames = m1.get(GameName);
        }




        Usernames.add(SesUsername);
        m1.put(GameName, Usernames);
        html ="";

        html ="<h1> Players </h1>";

        for(String Name : Usernames)
        {
            html += "<h2>" + Name +"</h2>";


        }

        return Results.redirect("/GameLobby");






    }



    public Result GameLobby(Context Con)
    {
        Session s = Con.getSession();

        String SesUsername = s.get("username");



        Result result = Results.html();
        String GameName = Con.getParameter("GameName");

        if (changed ==false) {
            Game g = new Game();
            g.setgamename(GameName);
            g.setTimestamp(new Date());
            g.SetHostName(SesUsername);
            g.SetStatus("Created");
            postIndexGame(g);


            List<String> Usernames = new ArrayList<>();

            Usernames.add(SesUsername);
            m1.put(GameName, Usernames);



            html ="";

            html ="<h1> Players </h1>";

            for(String Name : Usernames)
            {
                html += "<h2>" + Name +"</h2>";


            }



        }
        result.render("Members",html);



        return result;
    }
    
    public static class SimplePojo {

        public String content;
        
    }

    public void setPokerService(iPokerService pokerService)
    {
        this.pokerService = pokerService;
    }
}
