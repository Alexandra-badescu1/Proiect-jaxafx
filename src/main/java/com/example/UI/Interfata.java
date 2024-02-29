package com.example.UI;
import com.example.domain.dto.*;
import com.example.domain.Pacient;
import com.example.domain.Programare;
import com.example.service.ServicePacient;
import com.example.Exception.RepositoryException;
import com.example.service.ServiceProgramare;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Scanner;
import com.example.Validation.VarstaValidation;
import com.example.Validation.NameValidation;
import com.example.Validation.OraValidation;
public class Interfata {
    //interfata cu meniu
    private ServicePacient pacient;
    private ServiceProgramare programare;

    Scanner choises = new Scanner(System.in);

    public Interfata(ServicePacient pacient,ServiceProgramare programare){
        this.pacient = pacient;
        this.programare = programare;
    }
    public void Meniu() {
        int opt ;
        Scanner cons = new Scanner(System.in);

        System.out.println("Introduceti numere intregi. Stop pentru oprire.");
        Option();
        while (cons.hasNext()) {
            String token = cons.next();
            try{
                if ("stop".equals(token)) {
                    break;
                }
                opt = Integer.parseInt(token);
                if (opt == 1) {
                    Add();
                }
                if (opt == 2) {
                    GetAll();
                }
                if (opt == 3) {
                    Update();
                }
                if (opt == 4) {
                    Delete();
                }
                if(opt == 5){
                    Raport1();
                }
                if(opt == 6){

                }
                if(opt == 7){

                }
                Option();

            }catch (RepositoryException repositoryException) {
                System.out.println(repositoryException.getMessage());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void Pacients() throws RepositoryException, SQLException {
        pacient.Add(1,"cristi","radu",15);
        pacient.Add(2,"cristina","raducu",17);
        pacient.Add(3,"cosmin","gabi",21);
        pacient.Add(4,"Flaviu","Razan",40);
        pacient.Add(5,"Sara","Makenzy",32);
        ArrayList<Pacient> pacients = pacient.GetAll();
        for(int i = 0 ;i < 9 ; i++){
            String nume = pacients.get(i).GetNume();
            int n = pacients.size() + 1;
            for(int j = 0;j< 9; j++){
                if(pacients.get(j).GetNume() == nume)
                {  pacient.Add(n,nume,pacients.get(j).GetPrenume(),pacients.get(j).GetVarsta());
                n++;
                }
            }
        }
        for( int i = 0 ;i < 9 ; i++){
            String prenume = pacients.get(i).GetPrenume();
            int n = pacients.size() + 1;
            for(int j = 0;j<5;j++){
                if(pacients.get(j).GetPrenume() == prenume)
                { pacient.Add(n,pacients.get(j).GetNume(),prenume,pacients.get(j).GetVarsta());
                n++;
                }
            }
        }
        for( int i = 0 ;i < 9 ; i++){
            int varsta = pacients.get(i).GetVarsta();
            int n = pacients.size() + 1;
            for(int j =0;j<9;j++){
                if(pacients.get(j).GetVarsta() != varsta)
                {   pacient.Add(n,pacients.get(j).GetNume(),pacients.get(j).GetPrenume(),varsta);
                n++;
                }
            }
        }

    }
    public void Programarii() throws RepositoryException, SQLException {
        ArrayList<Pacient> pacientul = pacient.GetAll();
        programare.Add(1,pacientul.get(1),12, LocalDate.parse("2023-12-12"),"dureri dentare");
        programare.Add(2,pacientul.get(2),13, LocalDate.parse("2023-12-12"),"scoatem masea");
        programare.Add(3,pacientul.get(3),2, LocalDate.parse("2023-12-12"),"pasme dentare");
        programare.Add(4,pacientul.get(4),7, LocalDate.parse("2023-12-12"),"dinti sparti");
        programare.Add(5, pacientul.get(5),14, LocalDate.parse("2023-12-12"),"curatare");

        int n = programare.GetAll().size();
        ArrayList<Programare> programares = programare.GetAll();
        for( int i = 0 ;i < 9 ; i++){
         Pacient pacient1 = programares.get(i).GetPacient();
         int n1 = n+1;
            for(int j = 0;j<5;j++){
                if(programares.get(j).GetPacient() != pacient1)
                {
                    programare.Add(n,pacient1,programares.get(j).GetOra(),programares.get(j).GetData(),programares.get(j).GetScopulProgramari());
                    n++;
                }

            }
            n = programares.size();
        }
        for( int i = 0 ;i < 9 ; i++){
            int ora = programare.GetAll().get(i).GetOra();
            int n1 = n+1;
            for(int j = 0;j<5;j++){
                if(programares.get(j).GetOra() == ora)
                {  programare.Add(n1,programares.get(j).GetPacient(),ora,programares.get(j).GetData(),programares.get(j).GetScopulProgramari());
                n1++;
                }
            }
            n = programares.size();
        }
        for( int i = 0 ;i < 9 ; i++){
            LocalDate data = programare.GetAll().get(i).GetData();
            int n2 = n+1;
            for(int j = 0;j<5;j++){

                if(programares.get(j).GetData() == data)
                {
                    programare.Add(n2,programares.get(j).GetPacient(),programares.get(j).GetOra(),data,programares.get(j).GetScopulProgramari());
                    n2++;
                }
            }
            n = programares.size();
        }
        for( int i = 0 ;i < 9 ; i++){
            String scop = programare.GetAll().get(i).GetScopulProgramari();
            int n3 = n+1;
            for(int j = 0; j < 5 ; j++)  {

                if(programares.get(j).GetScopulProgramari() == scop)
                {
                    programare.Add(n3,programares.get(j).GetPacient(),programares.get(j).GetOra(),programares.get(j).GetData(),scop);
                    n3++;
                }
            }
            n = programares.size();
        }
    }
    public void Option(){
        System.out.println("Introduceti optiunea dorita:");
        System.out.println("1.Introduceti programari/pacienti.");
        System.out.println("2.Afisati programari/pacienti.");
        System.out.println("3.Modificati programari/Pacienti.");
        System.out.println("4.Stergeti programari/pacienti.");
        System.out.println("5.Raport 1");
        System.out.println("6.Raport 2");
        System.out.println("7.Raport 3");
        System.out.println("stop");
    }
   public void GetAll() throws SQLException, RepositoryException {
       System.out.println("Introduceti 1 pentru pacient si 2 pentru programare");
       Scanner choise = new Scanner(System.in);
       String token = choise.next();
       if(token.equals("1")) {
           for(Pacient pacient1: pacient.GetAll())
                System.out.println(pacient1);
       }
       else if(token.equals("2"))
       {    for(Programare programare1: programare.GetAll())
           System.out.println(programare1);
        }
    }
   public void Add() throws RepositoryException{
       System.out.println("Introduceti 1 pentru pacient si 2 pentru programare");
       Scanner choise = new Scanner(System.in);
       String token = choises.next();

               if(token.equals("1"))
               {
                   System.out.println("Ïntroduceti id");
                   int id = Integer.parseInt(choise.next());
                   System.out.println("introduceti nume");
                   String nume = choise.next();
                   new NameValidation(nume);
                   System.out.println("Introduceti prenume");
                   String prenume = choise.next();
                   new NameValidation(prenume);
                   System.out.println("Introduceti varsta");
                   int varsta = Integer.parseInt(choise.next());
                   new VarstaValidation(varsta);
                   pacient.Add(id,nume,prenume,varsta);

               }
               else if(token.equals("2"))
               {
                   System.out.println("Ïntroduceti id");
                   int id = Integer.parseInt(choise.next());
                   System.out.println("Introduceti ora");
                   int ora = Integer.parseInt(choise.next());
                   new OraValidation(ora);
                   System.out.println("Introduceti data");
                   String data = choise.next();
                   System.out.println("Introduceti scopul vizitei.");
                   String scop = choise.next();
                   new NameValidation(scop);
                   System.out.println("Introduceti Id Pacient");
                   int id_p = Integer.parseInt(choise.next());
                   int ok =0;
                   try{
                   for(Pacient pacient1: pacient.GetAll()) {
                       if (pacient1.getId() == id_p)
                       {  programare.Add(id, pacient1, ora, LocalDate.parse(data), scop);
                       ok = 1;
                   }
                   }  if(ok == 0)
                        throw new RuntimeException("invalid pacient ,go and add him first");
                      }catch (RuntimeException | SQLException e)
                   {
                       System.out.println(e.getMessage());
                   }
               }
                else System.out.println("You insert an invalid option");
   }
   public void Delete() throws RepositoryException, SQLException {
       System.out.println("Introduceti 1 pentru pacient si 2 pentru programare");
       Scanner choise = new Scanner(System.in);
       String token = choises.next();
       System.out.println(pacient.GetAll());
       if(token.equals("1"))
       {    for(Pacient pacient1: pacient.GetAll())
                System.out.println(pacient1);
           System.out.println("Introduceti id pacient");
           int id = Integer.parseInt(choise.next());
           pacient.Delete(id);
       }
       else if(token.equals("2"))
       {
           for(Programare programare1: programare.GetAll())
                System.out.println(programare1);
           System.out.println("Introduceti id programare");
           int id = Integer.parseInt(choise.next());
           programare.Delete(id);
       }
   }
   public void Update() throws RepositoryException, SQLException {
       System.out.println("Introduceti 1 pentru pacient si 2 pentru programare");
       Scanner choise = new Scanner(System.in);
       String token = choises.next();
       if(token.equals("1"))
       {
           for(Pacient pacient1: pacient.GetAll())
               System.out.println(pacient1);
           System.out.println("Ïntroduceti id pacient modificat");
           int id = Integer.parseInt(choise.next());
           System.out.println("introduceti nume");
           String nume = choise.next();
           System.out.println("Introduceti prenume");
           String prenume = choise.next();
           System.out.println("Introduceti varsta");
           int varsta = Integer.parseInt(choise.next());
           pacient.Update(id,nume,prenume,varsta);
       }
       else if(token.equals("2"))
       {   for(Programare programare1: programare.GetAll())
                System.out.println(programare1);
           System.out.println("Ïntroduceti id programare modificari");
           int id = Integer.parseInt(choise.next());
           System.out.println("Introduceti ora");
           int ora = Integer.parseInt(choise.next());
           System.out.println("Introduceti data");
           String data = choise.next();
           System.out.println("Introduceti scopul vizitei.");
           String scop = choise.next();
           System.out.println("Introduceti Id Pacient");
           int id_p = Integer.parseInt(choise.next());
           Pacient pacient1 = programare.GetAt(id_p);
           programare.Update(id,pacient1,ora,LocalDate.parse(data),scop);
       }
       else System.out.println("You insert an invalid option");
   }
   public void Raport1(){
       Dictionary<Integer,Pacient> listaDesc = programare.ListaDesc();
       for(int i=0;i<listaDesc.size();i++)
           System.out.println(listaDesc.keys() + " " + listaDesc.elements());
    }
    public void Raport2() throws SQLException, RepositoryException {
        ArrayList<Programariluna> lista = programare.countProgramariLuna();
        for(var p: lista){
            System.out.println(p);
        }
    }
    public void Raport3(){

        int lista = programare.NrProgrmari(3);
            System.out.println(lista);
    }
}
