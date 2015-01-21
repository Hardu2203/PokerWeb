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

import com.google.inject.Inject;
import ninja.Result;
import ninja.Results;

import com.google.inject.Singleton;
import ninja.session.Session;
import services.LoginService;
import services.PokerService;
import services.iPokerService;
import ninja.Context;

@Singleton
public class ApplicationController {


    @Inject
    private iPokerService pokerService;
    @Inject AuthenticationControler auth;

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


                return result;
      //      }
        }






        if (auth.LoginTheUser(Username,Password)) {
            result.render("Name", pokerService.getname());
            Ses.put("username", Username);

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
    
    public static class SimplePojo {

        public String content;
        
    }

    public void setPokerService(iPokerService pokerService)
    {
        this.pokerService = pokerService;
    }
}
